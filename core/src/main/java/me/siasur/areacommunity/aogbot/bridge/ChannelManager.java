package me.siasur.areacommunity.aogbot.bridge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.wrapper.Channel;

public class ChannelManager implements IChannelManager {

	TS3Api _ts3Api;
	HashMap<Integer, AoGChannel> _channels;
	
	public ChannelManager(TS3Api ts3Api) {
		_ts3Api = ts3Api;
		_channels = new HashMap<Integer, AoGChannel>();
	}

	public void refreshList() {
		_channels.clear();
		List<Channel> channels = _ts3Api.getChannels();
		channels.forEach(c -> {manageChannel(c, false);});
		buildHierarchy(channels);
	}
	
	public void manageChannel(Channel channel) {
		manageChannel(channel, true);
	}
	
	private void manageChannel(Channel channel, boolean withHierarchy) {
		AoGChannel aogChannel = new AoGChannel(channel, _ts3Api);
		_channels.put(channel.getId(), aogChannel);
		
		if (withHierarchy) {
			aogChannel.setParent(_channels.get(channel.getParentChannelId()));
		}
	}
	
	public void unmanageChannel(AoGChannel channel) {
		unmanageChannel(channel.getId());
	}
	
	public void unmanageChannel(int channelId) {
		_channels.remove(channelId);
	}
	
	private void buildHierarchy(List<Channel> channels) {
		channels.forEach(channel -> {
			int parentId = channel.getParentChannelId();
			if (parentId != 0) {
				AoGChannel parent = _channels.get(parentId);
				AoGChannel self = _channels.get(channel.getId());
				self.setParent(parent);
			}
		});
	}

	public void locateClients(List<IAoGClient> clients) {
		for (IAoGClient rawClient : clients) {
			AoGClient client = (AoGClient) rawClient;
			int channelId = client.getChannelId();
			AoGChannel channel = _channels.get(channelId);
			channel.clientJoin(client);
		}
	}

	public void refreshChannel(int channelId) {
		AoGChannel channel = _channels.get(channelId);
		channel.refresh();
	}
	
	@Override
	public IAoGChannel getChannel(int channelId) {
		return _channels.get(channelId);
	}

	@Override
	public List<IAoGChannel> getAllChannels() {
		List<IAoGChannel> channels = new ArrayList<IAoGChannel>(_channels.size());
		for (IAoGChannel channel : _channels.values()) {
			channels.add(channel);
		}
		
		return channels;
	}

	public void moveChannel(int channelId, int parentId) {
		AoGChannel channel = _channels.get(channelId);
		AoGChannel parent = _channels.get(parentId);
		
		channel.setParent(parent);
	}

}
