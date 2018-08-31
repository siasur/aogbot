package me.siasur.areacommunity.aogbot.bridge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.wrapper.Channel;

public class ChannelManager implements IChannelManager {

	HashMap<Integer, AoGChannel> _channels;
	TS3Api _ts3Api;

	public ChannelManager(final TS3Api ts3Api) {
		_ts3Api = ts3Api;
		_channels = new HashMap<Integer, AoGChannel>();
	}

	@Override
	public List<IAoGChannel> getAllChannels() {
		final List<IAoGChannel> channels = new ArrayList<IAoGChannel>(_channels.size());
		for (final AoGChannel channel : _channels.values()) {
			channel.refresh();
			channels.add(channel);
		}

		return channels;
	}

	@Override
	public IAoGChannel getChannel(final int channelId) {
		AoGChannel channel = _channels.get(channelId);
		channel.refresh();
		return channel;
	}

	public void locateClients(final List<IAoGClient> clients) {
		for (final IAoGClient rawClient : clients) {
			final AoGClient client = (AoGClient) rawClient;
			final int channelId = client.getChannelId();
			final AoGChannel channel = _channels.get(channelId);
			channel.clientJoin(client);
		}
	}

	public void manageChannel(final int channelId) {
		manageChannel(channelId, true);
	}

	public void moveChannel(final int channelId, final int parentId) {
		final AoGChannel channel = _channels.get(channelId);
		final AoGChannel parent = _channels.get(parentId);

		channel.setParent(parent);
	}

	public void refreshList() {
		_channels.clear();
		final List<Channel> channels = _ts3Api.getChannels();
		channels.forEach(c -> {
			manageChannel(c.getId(), false);
		});
		buildHierarchy(channels);
	}

	public void unmanageChannel(final AoGChannel channel) {
		unmanageChannel(channel.getId());
	}

	public void unmanageChannel(final int channelId) {
		_channels.remove(channelId);
	}

	private void buildHierarchy(final List<Channel> channels) {
		channels.forEach(channel -> {
			final int parentId = channel.getParentChannelId();
			if (parentId != 0) {
				final AoGChannel parent = _channels.get(parentId);
				final AoGChannel self = _channels.get(channel.getId());
				self.setParent(parent);
			}
		});
	}

	private void manageChannel(final int channelId, final boolean withHierarchy) {
		final AoGChannel channel = new AoGChannel(channelId, _ts3Api);
		channel.refresh();
		_channels.put(channelId, channel);

		if (withHierarchy) {
			channel.setParent(_channels.get(channel.getParentChannelId()));
		}
	}

}
