package me.siasur.areacommunity.aogbot.bridge;

import java.util.ArrayList;
import java.util.List;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.wrapper.Channel;
import com.github.theholywaffle.teamspeak3.api.wrapper.ChannelInfo;


public class AoGChannel implements IAoGChannel {

	private TS3Api _ts3Api;
	private Channel _channel;
	private List<AoGClient> _clients;
	private AoGChannel _parent;
	private List<AoGChannel> _childs;
	
	protected AoGChannel(Channel channel, TS3Api ts3Api) {
		_ts3Api = ts3Api;
		_channel = channel;
		_clients = new ArrayList<AoGClient>();
		_childs = new ArrayList<AoGChannel>();
	}

	@Override
	public int getId() {
		return _channel.getId();
	}

	@Override
	public List<IAoGClient> getClients() {
		List<IAoGClient> clients = new ArrayList<IAoGClient>();
		
		for (IAoGClient aogClient : _clients) {
				clients.add(aogClient);
		}
		
		return clients;		
	}

	@Override
	public String GetName() {
		return _channel.getName();
	}

	@Override
	public IAoGChannel GetParent() {
		return (IAoGChannel) _parent;
	}

	@Override
	public ChannelType GetChannelType() {
		
		if (_channel.isPermanent()) {
			return ChannelType.PERMANENT;
		}
		
		if (_channel.isSemiPermanent()) {
			return ChannelType.SEMI_PERMANENT;
		}
		
		return ChannelType.TEMPORARY;
	}

	@Override
	public boolean hasPassword() {
		return _channel.hasPassword();
	}

	@Override
	public String getTopic() {
		return _channel.getTopic();
	}

	@Override
	public int getNeededTalkPower() {
		return _channel.getNeededTalkPower();
	}

	@Override
	public boolean isEmpty() {
		return _channel.isEmpty();
	}

	@Override
	public int getNeededSubscribePower() {
		return _channel.getNeededSubscribePower();
	}
	
	@Override
	public List<IAoGChannel> GetChilds() {
		List<IAoGChannel> childs = new ArrayList<IAoGChannel>();
		
		for (IAoGChannel channel : _childs) {
			childs.add(channel);
		}
		
		return childs;
	}
	
	public void clientJoin(AoGClient client) {
		client.setChannel(this);
		_clients.add(client);
	}
	
	public void clientLeave(AoGClient client) {
		_clients.remove(client);
	}
	
	public void setParent(AoGChannel channel) {
		if (_parent != null) {
			_parent.removeChild(this);
		}
		
		_parent = channel;
		
		_parent.addChild(this);
	}
	
	private void addChild(AoGChannel channel) {
		_childs.add(channel);
	}
	
	private void removeChild(AoGChannel channel) {
		_childs.remove(channel);
	}

	public void refresh() {
		ChannelInfo channelInfo = _ts3Api.getChannelInfo(getId());
		Channel channel = _ts3Api.getChannelByNameExact(channelInfo.getName(), false);
		_channel = channel;
	}
}
