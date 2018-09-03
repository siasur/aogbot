package me.siasur.areacommunity.aogbot.bridge;

import java.util.List;

/**
 * Defines functions for the interaction with the ChannelManager.
 *
 */
public interface IChannelManager {

	/**
	 * Gets all channels.
	 * 
	 * @return A list with all channels.
	 */
	List<IAoGChannel> getAllChannels();

	/**
	 * Gets a channel with the given {@code channelId}.
	 * 
	 * @param clientId
	 *            The channel ID.
	 * @return The channel with the given ID.
	 */
	IAoGChannel getChannel(int channelId);

}
