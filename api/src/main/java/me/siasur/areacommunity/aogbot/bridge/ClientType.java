package me.siasur.areacommunity.aogbot.bridge;

/**
 * Types of clients
 *
 */
public enum ClientType {

	/**
	 * A ServerQuery client.
	 */
	SERVERQUERY,

	/**
	 * An unknown client type.
	 * <p>
	 * This should never be applied and could be an indicator for a error...
	 */
	UNKNOWN,

	/**
	 * A voice client.
	 */
	VOICE

}
