package me.siasur.areacommunity.aogbot.bridge;

import java.util.List;

public interface IChannelManager {

	IAoGChannel getChannel(int channelId);
	
	List<IAoGChannel> getAllChannels();

}
