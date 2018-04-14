package me.siasur.areacommunity.aogbot.api.module;

import me.siasur.areacommunity.aogbot.api.CommandChannel;

public interface IModuleBridge {
	
	public interface CommandCallback {
		public void onCommand();
	}

	public boolean registerCommand(String command, CommandChannel channel, CommandCallback callback);
	
}
