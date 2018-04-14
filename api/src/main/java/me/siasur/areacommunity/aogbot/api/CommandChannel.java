package me.siasur.areacommunity.aogbot.api;

import java.util.EnumSet;

public enum CommandChannel {
	DIRECT_MESSAGE,
	CHANNEL_MESSAGE,
	SERVER_MESSAGE;
	
	public static final EnumSet<CommandChannel> ALL_CHANNELS = EnumSet.allOf(CommandChannel.class);
}
