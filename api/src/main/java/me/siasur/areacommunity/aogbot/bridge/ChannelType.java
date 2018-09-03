package me.siasur.areacommunity.aogbot.bridge;

/**
 * Types of channels
 * 
 */
public enum ChannelType {

	/**
	 * A permantent channel.
	 * <p>
	 * It will never be automatically deleted.
	 */
	PERMANENT,

	/**
	 * A semi permanent channel.
	 * <p>
	 * It will be deleted on the next server restart.
	 */
	SEMI_PERMANENT,

	/**
	 * A temporary channel.
	 * <p>
	 * It will be deleted when empty.
	 */
	TEMPORARY

}
