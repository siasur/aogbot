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
import com.github.theholywaffle.teamspeak3.api.wrapper.Channel;
import com.github.theholywaffle.teamspeak3.api.wrapper.ChannelInfo;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import me.siasur.areacommunity.aogbot.bridge.ChannelManager;
import me.siasur.areacommunity.aogbot.bridge.ClientManager;
import me.siasur.areacommunity.aogbot.bridge.IChannelManager;
import me.siasur.areacommunity.aogbot.bridge.IClientManager;
import me.siasur.areacommunity.aogbot.bridge.ModuleManager;
import me.siasur.areacommunity.aogbot.config.AoGBotConfig;
import me.siasur.areacommunity.aogbot.config.ServerIdentifierConfigOption;
import me.siasur.areacommunity.aogbot.module.TestModule;
import me.siasur.areacommunity.aogbot.utility.ServiceLocator;

public class AoGBot {
	
	AoGBotConfig _config;
	ClientManager _clientManager;
	ChannelManager _channelManager;
	ModuleManager _moduleManager;
	
	private volatile int clientId;
	
	AoGBot(AoGBotConfig config) {
		_config = config;
		_moduleManager = new ModuleManager();
	}

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
	
	private void loadModules() {
		TestModule testModule = new TestModule();
		_moduleManager.addModule(testModule);
		
		_moduleManager.enableAllModules();
	}

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
	
	private void registerListener(TS3Api api) {
		api.addTS3Listeners(new TS3EventAdapter() {
			
			@Override
			public void onTextMessage(TextMessageEvent textMessageEvent) {
				if (textMessageEvent.getInvokerId() == clientId) {
					return;
				}
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
			public void onClientLeave(ClientLeaveEvent clientLeaveEvent) {
				int clientId = clientLeaveEvent.getClientId();
				
				_clientManager.unmanageClient(clientId);
			}
			
			@Override
			public void onClientJoin(ClientJoinEvent clientJoinEvent) {
				_clientManager.manageClient(clientJoinEvent.getClientId());
			}
			
			@Override
			public void onChannelMoved(ChannelMovedEvent channelMovedEvent) {
				int channelId = channelMovedEvent.getChannelId();
				int parentId = channelMovedEvent.getChannelParentId();
				
				_channelManager.moveChannel(channelId, parentId);
			}
			
			@Override
			public void onChannelEdit(ChannelEditedEvent channelEditedEvent) {
				int channelId = channelEditedEvent.getChannelId();
				
				_channelManager.refreshChannel(channelId);
			}
			
			@Override
			public void onChannelDescriptionChanged(ChannelDescriptionEditedEvent channelDescriptionEditedEvent) {
				int channelId = channelDescriptionEditedEvent.getChannelId();
				
				_channelManager.refreshChannel(channelId);
			}
			
			@Override
			public void onChannelDeleted(ChannelDeletedEvent channelDeletedEvent) {
				int channelId = channelDeletedEvent.getChannelId();
				
				_channelManager.unmanageChannel(channelId);
			}
			
			@Override
			public void onChannelCreate(ChannelCreateEvent channelCreateEvent) {
				int channelId = channelCreateEvent.getChannelId();
				ChannelInfo channelInfo = api.getChannelInfo(channelId);
				Channel channel = api.getChannelByNameExact(channelInfo.getName(), false);
				
				_channelManager.manageChannel(channel);				
			}
		});
	}

}
