package me.siasur.areacommunity.aogbot.core;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.TS3Query.FloodRate;
import com.github.theholywaffle.teamspeak3.api.reconnect.ConnectionHandler;

import me.siasur.areacommunity.aogbot.AoGOptions;

/***
 * Core of the AoGBot
 * 
 */
public class AoGBot {
	
	private AoGOptions _options;
	private TS3Config _ts3Config;
	private TS3Query _ts3Query;
	private Thread _consoleThread;
	private TeamspeakListener _tsListener;
	private static AoGBot _instance;
	
	public AoGBot(AoGOptions options) {
		_instance = this;
		_options = options;
		
		prepareTS3Config();
	}
	
	public static AoGBot getInstance() {
		return _instance;
	}
	
	private void prepareTS3Config() {
		_ts3Config = new TS3Config();
		_ts3Config.setHost(_options.getHost());
		
		FloodRate targetFloodRate = _options.getSlowmode() ? FloodRate.DEFAULT : FloodRate.UNLIMITED;
		_ts3Config.setFloodRate(targetFloodRate);
		
		_ts3Config.setConnectionHandler(new ConnectionHandler() {
			
			@Override
			public void onConnect(TS3Query ts3Query) {
				login();
				
				ts3Query.getApi().registerAllEvents();
			}
			
			@Override
			public void onDisconnect(TS3Query ts3Query) {
				// nothing to do
			}
		});
	}
	
	protected void login() {
		TS3Api ts3Api = _ts3Query.getApi();
		
		ts3Api.login(_options.getUsername(), _options.getPassword());
		ts3Api.selectVirtualServerById(_options.getServerId());
		ts3Api.setNickname(_options.getDisplayName());
		System.out.println("logged in");
	}
	
	public void connect() {
		_ts3Query = new TS3Query(_ts3Config);
		_ts3Query.connect();
		System.out.println("connected");
	}
	
	public void run() {
		TS3Api ts3Api = _ts3Query.getApi();
		_tsListener = new TeamspeakListener(_ts3Query);
		
		ts3Api.addTS3Listeners(_tsListener);
		
		_consoleThread = new Thread(new BotConsole(_ts3Query));
		_consoleThread.start();
	}
}
