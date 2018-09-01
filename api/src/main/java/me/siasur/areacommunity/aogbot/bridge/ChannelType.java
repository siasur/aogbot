package me.siasur.areacommunity.aogbot.bridge;

/**
 * Types of channels
 * 
 */
public enum ChannelType {

	/**
	 * A temporary channel.
	 * <p>
	 * It will be deleted when empty.
	 */
	TEMPORARY,
	
	/**
	 * A semi permanent channel.
	 * <p>
	 * It will be deleted on the next server restart.
	 */
	SEMI_PERMANENT,
	
	/**
	 * A permantent channel.
	 * <p>
	 * It will never be automatically deleted.
	 */
	PERMANENT
	
}
