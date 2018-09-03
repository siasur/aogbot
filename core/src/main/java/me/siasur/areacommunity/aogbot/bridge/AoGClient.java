package me.siasur.areacommunity.aogbot.bridge;

import java.util.Date;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.ClientProperty;
import com.github.theholywaffle.teamspeak3.api.TextMessageTargetMode;
import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;

/**
 * Represents a client that is currently connected to the server
 *
 */
public class AoGClient implements IAoGClient {

	private String _avatar;
	private String _awayMessage;
	private BandwithUsage _bandwidthUsageLastMinute;
	private BandwithUsage _bandwidthUsageLastSecond;
	private boolean _canTalk;
	private AoGChannel _channel;
	private int _channelId;
	private int _clientId;
	private ClientType _clientType;
	private Date _createdDate;
	private String _defaultToken;
	private String _description;
	private BandwithUsage _filetransferBandwidthUsage;
	private long _idleTime;
	private String _ip;
	private boolean _isAway;
	private boolean _isChannelCommander;
	private boolean _isInputMuted;
	private boolean _isOutputMuted;
	private boolean _isOutputOnlyMuted;
	private boolean _isPrioritySpeaker;
	private boolean _isRecording;
	private boolean _isRequestingToTalk;
	private Date _lastConnectedDate;
	private String _loginName;
	private String _metaData;
	private int _neededServerQueryViewPower;
	private String _nickname;
	private String _phoneticNickname;
	private int _talkPower;
	private String _talkRequestMessage;
	private long _timeConnected;
	private int _totalConnections;
	private TS3Api _ts3Api;
	private String _uniqueIdentifier;
	private int _unreadMessagesCount;
	private String _version;

	/**
	 * Initializes a new instance of the {@link AoGClient}
	 * 
	 * @param clientId
	 *            the clientId
	 * @param ts3Api
	 *            the {@link TS3Api}
	 */
	protected AoGClient(int clientId, TS3Api ts3Api) {
		_clientId = clientId;
		_ts3Api = ts3Api;
	}

	@Override
	public boolean canTalk() {
		return _canTalk;
	}

	@Override
	public String getAvatar() {
		return _avatar;
	}

	@Override
	public String getAwayMessage() {
		return _awayMessage;
	}

	@Override
	public BandwithUsage getBandwidthUsageLastMinute() {
		return _bandwidthUsageLastMinute;
	}

	@Override
	public BandwithUsage getBandwidthUsageLastSecond() {
		return _bandwidthUsageLastSecond;
	}

	@Override
	public IAoGChannel getChannel() {
		return _channel;
	}

	/**
	 * Gets the id of the channel the client is currently in
	 * 
	 * @return the {@code channelId}
	 */
	public int getChannelId() {
		return _channelId;
	}

	@Override
	public ClientType getClientType() {
		return _clientType;
	}

	@Override
	public Date getCreatedDate() {
		return _createdDate;
	}

	@Override
	public String getDefaultToken() {
		return _defaultToken;
	}

	@Override
	public String getDescription() {
		return _description;
	}

	@Override
	public BandwithUsage getFiletransferBandwidthUsage() {
		return _filetransferBandwidthUsage;
	}

	@Override
	public int getId() {
		return _clientId;
	}

	@Override
	public long getIdleTime() {
		return _idleTime;
	}

	@Override
	public String getIp() {
		return _ip;
	}

	@Override
	public Date getLastConnectedDate() {
		return _lastConnectedDate;
	}

	@Override
	public String getLoginName() {
		return _loginName;
	}

	@Override
	public String getMetaData() {
		return _metaData;
	}

	@Override
	public int getNeededServerQueryViewPower() {
		return _neededServerQueryViewPower;
	}

	@Override
	public String getNickname() {
		return _nickname;
	}

	@Override
	public String getPhoneticNickname() {
		return _phoneticNickname;
	}

	@Override
	public int getTalkPower() {
		return _talkPower;
	}

	@Override
	public String getTalkRequestMessage() {
		return _talkRequestMessage;
	}

	@Override
	public long getTimeConnected() {
		return _timeConnected;
	}

	@Override
	public int getTotalConnections() {
		return _totalConnections;
	}

	@Override
	public String getUniqueId() {
		return _uniqueIdentifier;
	}

	@Override
	public int getUnreadMessagesCount() {
		return _unreadMessagesCount;
	}

	@Override
	public String getVersion() {
		return _version;
	}

	@Override
	public boolean isAway() {
		return _isAway;
	}

	@Override
	public boolean isChannelCommander() {
		return _isChannelCommander;
	}

	@Override
	public boolean isInputMuted() {
		return _isInputMuted;
	}

	@Override
	public boolean isOutputMuted() {
		return _isOutputMuted;
	}

	@Override
	public boolean isOutputOnlyMuted() {
		return _isOutputOnlyMuted;
	}

	@Override
	public boolean isPrioritySpeaker() {
		return _isPrioritySpeaker;
	}

	@Override
	public boolean isRecording() {
		return _isRecording;
	}

	// @Override
	// public boolean isTalking() {
	// return _isTalking;
	// }

	@Override
	public boolean isRequestingToTalk() {
		return _isRequestingToTalk;
	}

	@Override
	public void kickFromChannel() {
		_ts3Api.kickClientFromChannel(_clientId);
	}

	@Override
	public void kickFromServer(String message) {
		_ts3Api.kickClientFromServer(message, _clientId);

	}

	@Override
	public void poke(String message) {
		_ts3Api.pokeClient(_clientId, message);
	}

	/**
	 * Reloads the client from the server
	 */
	public void refresh() {
		ClientInfo clientInfo = _ts3Api.getClientInfo(_clientId);

		_avatar = clientInfo.getAvatar();
		_awayMessage = clientInfo.getAwayMessage();
		_bandwidthUsageLastMinute = new BandwithUsage(clientInfo.getBandwidthReceivedLastMinute(),
				clientInfo.getBandwidthSentlastMinute());
		_bandwidthUsageLastSecond = new BandwithUsage(clientInfo.getBandwidthReceivedLastSecond(),
				clientInfo.getBandwidthSentLastSecond());
		_canTalk = clientInfo.canTalk();
		_createdDate = clientInfo.getCreatedDate();
		_defaultToken = clientInfo.getDefaultToken();
		_description = clientInfo.getDescription();
		_filetransferBandwidthUsage = new BandwithUsage(clientInfo.getFiletransferBandwidthReceived(),
				clientInfo.getFiletransferBandwidthSent());
		_idleTime = clientInfo.getIdleTime();
		_ip = clientInfo.getIp();
		_isAway = clientInfo.isAway();
		_isChannelCommander = clientInfo.isChannelCommander();
		_isInputMuted = clientInfo.isInputMuted();
		_isOutputMuted = clientInfo.isOutputMuted();
		_isOutputOnlyMuted = clientInfo.isOutputOnlyMuted();
		_isPrioritySpeaker = clientInfo.isPrioritySpeaker();
		_isRecording = clientInfo.isRecording();
		_isRequestingToTalk = clientInfo.isRequestingToTalk();
		// _isTalking = clientInfo.isTalking(); <- This is not supported
		_lastConnectedDate = clientInfo.getLastConnectedDate();
		_loginName = clientInfo.getLoginName();
		_metaData = clientInfo.getMetaData();
		_neededServerQueryViewPower = clientInfo.getNeededServerQueryViewPower();
		_nickname = clientInfo.getNickname();
		_phoneticNickname = clientInfo.getPhoneticNickname();
		_talkPower = clientInfo.getTalkPower();
		_talkRequestMessage = clientInfo.getTalkRequestMessage();
		_timeConnected = clientInfo.getTimeConnected();
		_totalConnections = clientInfo.getTotalConnections();
		_uniqueIdentifier = clientInfo.getUniqueIdentifier();
		_unreadMessagesCount = clientInfo.getUnreadMessages();
		_version = clientInfo.getVersion();
		_channelId = clientInfo.getChannelId();

		if (clientInfo.isServerQueryClient()) {
			_clientType = ClientType.SERVERQUERY;
		} else if (clientInfo.isRegularClient()) {
			_clientType = ClientType.VOICE;
		} else {
			_clientType = ClientType.UNKNOWN;
		}
	}

	@Override
	public void sendMessage(String message) {
		_ts3Api.sendTextMessage(TextMessageTargetMode.CLIENT, _clientId, message);
	}

	/**
	 * Sets the channel in which the client is currently
	 * 
	 * @param channel
	 *            the {@link AoGChannel}
	 */
	public void setChannel(AoGChannel channel) {
		_channel = channel;
	}

	@Override
	public void setDescription(String description) {
		_description = description;
		_ts3Api.editClient(_clientId, ClientProperty.CLIENT_DESCRIPTION, description);
	}
}
