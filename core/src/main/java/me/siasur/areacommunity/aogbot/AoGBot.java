package me.siasur.areacommunity.aogbot;

import java.util.Collection;
import java.util.function.Function;

import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.event.ChannelCreateEvent;
import com.github.theholywaffle.teamspeak3.api.event.ChannelDeletedEvent;
import com.github.theholywaffle.teamspeak3.api.event.ChannelDescriptionEditedEvent;
import com.github.theholywaffle.teamspeak3.api.event.ChannelEditedEvent;
import com.github.theholywaffle.teamspeak3.api.event.ChannelMovedEvent;
import com.github.theholywaffle.teamspeak3.api.event.ClientJoinEvent;
import com.github.theholywaffle.teamspeak3.api.event.ClientLeaveEvent;
import com.github.theholywaffle.teamspeak3.api.event.ClientMovedEvent;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.reconnect.ConnectionHandler;
import com.github.theholywaffle.teamspeak3.api.reconnect.ReconnectStrategy;

import me.siasur.areacommunity.aogbot.bridge.AoGChannel;
import me.siasur.areacommunity.aogbot.bridge.AoGClient;
import me.siasur.areacommunity.aogbot.bridge.ChannelManager;
import me.siasur.areacommunity.aogbot.bridge.ClientManager;
import me.siasur.areacommunity.aogbot.bridge.IChannelManager;
import me.siasur.areacommunity.aogbot.bridge.IClientManager;
import me.siasur.areacommunity.aogbot.config.AoGBotConfig;
import me.siasur.areacommunity.aogbot.config.ServerIdentifierConfigOption;
import me.siasur.areacommunity.aogbot.event.AoGEvent;
import me.siasur.areacommunity.aogbot.event.ClientMoveEvent;
import me.siasur.areacommunity.aogbot.event.EventFactory;
import me.siasur.areacommunity.aogbot.event.EventManager;
import me.siasur.areacommunity.aogbot.event.IEventManager;
import me.siasur.areacommunity.aogbot.module.IModuleManager;
import me.siasur.areacommunity.aogbot.module.ModuleManager;
import me.siasur.areacommunity.aogbot.module.TestModule;
import me.siasur.areacommunity.aogbot.utility.ServiceLocator;

/**
 * The Heart of this application
 *
 */
public class AoGBot {

	private volatile int _clientId;
	ChannelManager _channelManager;
	ClientManager _clientManager;
	AoGBotConfig _config;

	ModuleManager _moduleManager;
	EventManager _eventManager;

	/**
	 * Initializes a new instance of the {@link AoGBot}.
	 * @param config
	 */
	AoGBot(AoGBotConfig config) {
		_config = config;
		_moduleManager = new ModuleManager();
		ServiceLocator.getServiceLocator().addService(IModuleManager.class, _moduleManager);
		
		_eventManager = new EventManager();
		ServiceLocator.getServiceLocator().addService(IEventManager.class, _eventManager);
	}

	/**
	 * Starts the whole bot.
	 */
	public void run() {
		final TS3Config ts3Config = new TS3Config();
		ts3Config.setHost(_config.getConnection().getHost());
		ts3Config.setQueryPort(_config.getConnection().getPort());

		if (_config.getSettings().getReconnect().isEnabled()) {
			ts3Config.setReconnectStrategy(ReconnectStrategy.exponentialBackoff());
		} else {
			ts3Config.setReconnectStrategy(ReconnectStrategy.disconnect());
		}

		ts3Config.setConnectionHandler(new ConnectionHandler() {

			@Override
			public void onConnect(TS3Query query) {
				connect(query.getApi());
			}

			@Override
			public void onDisconnect(TS3Query query) {
				// Nothing
			}
		});

		//ts3Config.setEnableCommunicationsLogging(true);
		
		final TS3Query query = new TS3Query(ts3Config);

		query.connect();

		TS3Api api = query.getApi();

		_clientManager = new ClientManager(api);
		_channelManager = new ChannelManager(api);

		ServiceLocator.getServiceLocator().addService(IClientManager.class, _clientManager);
		ServiceLocator.getServiceLocator().addService(IChannelManager.class, _channelManager);

		registerListener(api);

		_clientManager.refreshList();
		_channelManager.refreshList();
		_channelManager.locateClients(_clientManager.getAllClients());

		loadModules();

		Thread consoleThread = new Thread(new BotConsole(query));
		consoleThread.start();
	}

	/**
	 * Connects the client with the virtual server.
	 * @param api The {@link TS3Api} which is used for the connection
	 */
	private void connect(TS3Api api) {
		String username = _config.getAuthentication().getName();
		String password = _config.getAuthentication().getPassword();
		api.login(username, password);

		ServerIdentifierConfigOption identifier = _config.getConnection().getIdentifier();
		switch (identifier.getIdentifier()) {
		case ID:
			api.selectVirtualServerById(identifier.getValue());
			break;
		case PORT:
			api.selectVirtualServerByPort(identifier.getValue());
			break;
		default:
			System.err.println("Identifier problem...");
			System.exit(-1);
			break;
		}

		int homeChannelId = _config.getSettings().getHomeChannel().getChannelId();
		if (homeChannelId > 0) {
			api.moveQuery(homeChannelId);
		}

		api.setNickname(_config.getSettings().getDisplayName());

		api.registerAllEvents();

		_clientId = api.whoAmI().getId();
	}

	
	private void loadModules() {
		TestModule testModule = new TestModule();
		_moduleManager.addModule(testModule);

		_moduleManager.enableAllModules();
	}

	private void registerListener(TS3Api api) {
		api.addTS3Listeners(new TS3EventAdapter() {

			@Override
			public void onChannelCreate(ChannelCreateEvent channelCreateEvent) {
				int channelId = channelCreateEvent.getChannelId();

				_channelManager.manageChannel(channelId);
			}

			@Override
			public void onChannelDeleted(ChannelDeletedEvent channelDeletedEvent) {
				int channelId = channelDeletedEvent.getChannelId();

				_channelManager.unmanageChannel(channelId);
			}

			@Override
			public void onChannelDescriptionChanged(ChannelDescriptionEditedEvent channelDescriptionEditedEvent) {
				// TODO used when the event system exists
			}

			@Override
			public void onChannelEdit(ChannelEditedEvent channelEditedEvent) {
				// TODO used when the event system exists
			}

			@Override
			public void onChannelMoved(ChannelMovedEvent channelMovedEvent) {
				int channelId = channelMovedEvent.getChannelId();
				int parentId = channelMovedEvent.getChannelParentId();

				_channelManager.setParentChannel(channelId, parentId);
			}

			@Override
			public void onClientJoin(ClientJoinEvent clientJoinEvent) {
				_clientManager.manageClient(clientJoinEvent.getClientId());
			}

			@Override
			public void onClientLeave(ClientLeaveEvent clientLeaveEvent) {
				int clientId = clientLeaveEvent.getClientId();

				_clientManager.unmanageClient(clientId);
			}

			@Override
			public void onClientMoved(ClientMovedEvent clientMovedEvent) {
				int clientId = clientMovedEvent.getClientId();
				int channelId = clientMovedEvent.getTargetChannelId();
				
				if (clientMovedEvent.getClientId() == _clientId) {
					if (_config.getSettings().getHomeChannel().isForced()
							&& _config.getSettings().getHomeChannel().getChannelId() > 0) {
						api.moveQuery(_config.getSettings().getHomeChannel().getChannelId());
					}

					return;
				}
				
				AoGClient client = (AoGClient) _clientManager.getClientById(clientId);
				AoGChannel sourceChannel = (AoGChannel) client.getChannel();
				AoGChannel targetChannel = (AoGChannel) _channelManager.getChannel(channelId);
				
				ClientMoveEvent clientMoved = EventFactory.createClientMovedEvent(client, sourceChannel, targetChannel);
				_eventManager.fireEvent(ClientMoveEvent.class, clientMoved);
				
				// Send ClientMovedEvent to modules
				
				sourceChannel.clientLeave(client);
				targetChannel.clientJoin(client);
			}

			@Override
			public void onTextMessage(TextMessageEvent textMessageEvent) {
				if (textMessageEvent.getInvokerId() == _clientId) {
					return;
				}
			}
		});
	}

}
