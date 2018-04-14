package me.siasur.areacommunity.aogbot.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.github.theholywaffle.teamspeak3.TS3Query;

public class BotConsole implements Runnable{

	private TS3Query _ts3Query;
	
	public BotConsole(TS3Query ts3Query) {
		_ts3Query = ts3Query;
	}
	
	@Override
	public void run() {
		BufferedReader bufferedReader;
		
		try {
		bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		String line;
		
			while ((line = bufferedReader.readLine()) != null) {
				if (line.equalsIgnoreCase("quit")) {
					_ts3Query.exit();
					break;
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
