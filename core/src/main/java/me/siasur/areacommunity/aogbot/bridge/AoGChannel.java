package me.siasur.areacommunity.aogbot.bridge;

import java.util.ArrayList;
import java.util.List;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.wrapper.ChannelInfo;

public class AoGChannel implements IAoGChannel {

	private int _channelId;
	private ChannelType _channelType;
	private final List<AoGChannel> _childs;
	private final List<AoGClient> _clients;
	private String _description;
	private boolean _hasPassword;
	private boolean _isDefault;
	private boolean _isEncrypted;
	private boolean _isForcedSilence;
	private int _maxClients;
	private int _maxFamilyClients;
	private String _name;
	private int _neededTalkPower;
	private AoGChannel _parent;
	private int _parentChannelId;
	private String _password;
	private String _phoneticName;
	private int _secondsEmpty;
	private String _topic;
	private final TS3Api _ts3Api;

	protected AoGChannel(final int channelId, final TS3Api ts3Api) {
		_ts3Api = ts3Api;
		_channelId = channelId;
		_clients = new ArrayList<AoGClient>();
		_childs = new ArrayList<AoGChannel>();
	}

	public void clientJoin(final AoGClient client) {
		client.setChannel(this);
		_clients.add(client);
	}

	public void clientLeave(final AoGClient client) {
		_clients.remove(client);
	}

	@Override
	public ChannelType GetChannelType() {
		return _channelType;
	}

	@Override
	public List<IAoGChannel> GetChilds() {
		final List<IAoGChannel> childs = new ArrayList<IAoGChannel>();

		for (final IAoGChannel channel : _childs) {
			childs.add(channel);
		}

		return childs;
	}

	@Override
	public List<IAoGClient> getClients() {
		final List<IAoGClient> clients = new ArrayList<IAoGClient>();

		for (final IAoGClient aogClient : _clients) {
			clients.add(aogClient);
		}

		return clients;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return _description;
	}

	@Override
	public int getId() {
		return _channelId;
	}

	@Override
	public int getMaxClients() {
		// TODO Auto-generated method stub
		return _maxClients;
	}

	@Override
	public int getMaxFamilyClients() {
		// TODO Auto-generated method stub
		return _maxFamilyClients;
	}

	@Override
	public String GetName() {
		return _name;
	}

	@Override
	public int getNeededTalkPower() {
		return _neededTalkPower;
	}

	@Override
	public IAoGChannel GetParent() {
		return _parent;
	}

	public int getParentChannelId() {
		return _parentChannelId;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return _password;
	}

	@Override
	public String getPhoneticName() {
		// TODO Auto-generated method stub
		return _phoneticName;
	}

	@Override
	public int getSecondsEmpty() {
		// TODO Auto-generated method stub
		return _secondsEmpty;
	}

	@Override
	public String getTopic() {
		return _topic;
	}

	@Override
	public boolean hasPassword() {
		return _hasPassword;
	}

	@Override
	public boolean isDefault() {
		// TODO Auto-generated method stub
		return _isDefault;
	}

	@Override
	public boolean isEmpty() {
		return _secondsEmpty > -1;
	}

	@Override
	public boolean isEncrypted() {
		// TODO Auto-generated method stub
		return _isEncrypted;
	}

	@Override
	public boolean isForcedSilence() {
		// TODO Auto-generated method stub
		return _isForcedSilence;
	}

	public void refresh() {
		final ChannelInfo channelInfo = _ts3Api.getChannelInfo(_channelId);

		_channelId = channelInfo.getId();
		_name = channelInfo.getName();
		_topic = channelInfo.getTopic();
		_description = channelInfo.getDescription();
		_password = channelInfo.getPassword();
		_maxClients = channelInfo.getMaxClients();
		_maxFamilyClients = channelInfo.getMaxFamilyClients();
		_isDefault = channelInfo.isDefault();
		_hasPassword = channelInfo.hasPassword();
		_isEncrypted = channelInfo.isEncrypted();
		_neededTalkPower = channelInfo.getNeededTalkPower();
		_isForcedSilence = channelInfo.isForcedSilence();
		_phoneticName = channelInfo.getPhoneticName();
		_secondsEmpty = channelInfo.getSecondsEmpty();
		_parentChannelId = channelInfo.getParentChannelId();

		if (channelInfo.isPermanent()) {
			_channelType = ChannelType.PERMANENT;
		} else if (channelInfo.isSemiPermanent()) {
			_channelType = ChannelType.SEMI_PERMANENT;
		} else {
			_channelType = ChannelType.TEMPORARY;
		}
	}

	public void setParent(final AoGChannel channel) {
		if (_parent != null) {
			_parent.removeChild(this);
		}

		_parent = channel;

		_parent.addChild(this);
	}

	private void addChild(final AoGChannel channel) {
		_childs.add(channel);
	}

	private void removeChild(final AoGChannel channel) {
		_childs.remove(channel);
	}
}
