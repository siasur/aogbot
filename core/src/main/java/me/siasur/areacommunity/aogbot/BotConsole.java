package me.siasur.areacommunity.aogbot;

import java.util.Scanner;

import com.github.theholywaffle.teamspeak3.TS3ApiAsync;
import com.github.theholywaffle.teamspeak3.TS3Query;

import me.siasur.areacommunity.aogbot.bridge.IClientManager;
import me.siasur.areacommunity.aogbot.utility.ServiceLocator;

public class BotConsole implements Runnable {

	private boolean _running;
	private TS3Query _query;
	private TS3ApiAsync _asyncApi;
	
	public BotConsole(TS3Query query) {
		_running = true;
		_query = query;
		_asyncApi = query.getAsyncApi();
	}
	
	@Override
	public void run() {
		Scanner scanner = new Scanner(System.in);
		
		do {
			String userInput = scanner.nextLine();
			
			if (userInput.startsWith("quit")) {
				_running = false;
				_asyncApi.logout().getUninterruptibly();
				_query.exit();
			}
			
			if (userInput.startsWith("pokeall")) {
				ServiceLocator.getServiceLocator().getService(IClientManager.class).getAllClients().forEach(c -> {c.poke(userInput.substring(userInput.indexOf(" ") + 1));});
			}
			
		} while(_running);
	}

}
