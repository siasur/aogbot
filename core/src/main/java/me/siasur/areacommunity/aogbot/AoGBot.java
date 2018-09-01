package me.siasur.areacommunity.aogbot;

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

import me.siasur.areacommunity.aogbot.bridge.ChannelManager;
import me.siasur.areacommunity.aogbot.bridge.ClientManager;
import me.siasur.areacommunity.aogbot.bridge.IChannelManager;
import me.siasur.areacommunity.aogbot.bridge.IClientManager;
import me.siasur.areacommunity.aogbot.config.AoGBotConfig;
import me.siasur.areacommunity.aogbot.config.ServerIdentifierConfigOption;
import me.siasur.areacommunity.aogbot.module.ModuleManager;
import me.siasur.areacommunity.aogbot.module.TestModule;
import me.siasur.areacommunity.aogbot.utility.ServiceLocator;

/**
 * The Heart of this application
 *
 */
public class AoGBot {

	private volatile int clientId;
	ChannelManager _channelManager;
	ClientManager _clientManager;
	AoGBotConfig _config;

	ModuleManager _moduleManager;

	/**
	 * Initializes a new instance of the {@link AoGBot}.
	 * @param config
	 */
	AoGBot(AoGBotConfig config) {
		_config = config;
		_moduleManager = new ModuleManager();
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

		clientId = api.whoAmI().getId();
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
				if (clientMovedEvent.getClientId() == clientId) {
					if (_config.getSettings().getHomeChannel().isForced()
							&& _config.getSettings().getHomeChannel().getChannelId() > 0) {
						api.moveQuery(_config.getSettings().getHomeChannel().getChannelId());
					}

					return;
				}
			}

			@Override
			public void onTextMessage(TextMessageEvent textMessageEvent) {
				if (textMessageEvent.getInvokerId() == clientId) {
					return;
				}
			}
		});
	}

}
