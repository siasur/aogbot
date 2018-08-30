package me.siasur.areacommunity.aogbot.bridge;

import java.util.Date;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.TextMessageTargetMode;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

public class AoGClient implements IAoGClient {

	private TS3Api _ts3Api;
	private Client _client;
	private AoGChannel _channel;
	
	protected AoGClient(Client client, TS3Api ts3Api) {
		_client = client;
		_ts3Api = ts3Api;
	}
	
	@Override
	public int getId() {
		return _client.getId();
	}

	@Override
	public String getNickname() {
		return _client.getNickname();
	}

	@Override
	public void sendMessage(String message) {
		_ts3Api.sendTextMessage(TextMessageTargetMode.CLIENT, _client.getId(), message);
	}

	@Override
	public void poke(String message) {
		_ts3Api.pokeClient(_client.getId(), message);
	}
	
	@Override
	public IAoGChannel getChannel() {
		return (IAoGChannel) _channel;
	}

	@Override
	public void kickFromChannel() {
		_ts3Api.kickClientFromChannel(_client.getId());
	}

	@Override
	public void kickFromServer(String message) {
		_ts3Api.kickClientFromServer(message, _client.getId());
		
	}

	@Override
	public boolean canTalk() {
		return _client.canTalk();
	}

	@Override
	public String getAwayMessage() {
		return _client.getAwayMessage();
	}

	@Override
	public Date getCreatedDate() {
		return _client.getCreatedDate();
	}

	@Override
	public long getIdleTime() {
		return _client.getIdleTime();
	}

	@Override
	public String getIp() {
		return _client.getIp();
	}

	@Override
	public Date getLastConnectedDate() {
		return _client.getLastConnectedDate();
	}

	@Override
	public int getTalkPower() {
		return _client.getTalkPower();
	}

	@Override
	public String getUniqueId() {
		return _client.getUniqueIdentifier();
	}

	@Override
	public String getVersion() {
		return _client.getVersion();
	}

	@Override
	public boolean isAway() {
		return _client.isAway();
	}

	@Override
	public boolean isChannelCommander() {
		return _client.isChannelCommander();
	}

	@Override
	public boolean isInputMuted() {
		return _client.isInputMuted();
	}

	@Override
	public boolean isOutputMuted() {
		return _client.isOutputMuted();
	}

	@Override
	public boolean isPrioritySpeaker() {
		return _client.isPrioritySpeaker();
	}

	@Override
	public boolean isRecording() {
		return _client.isRecording();
	}

	@Override
	public ClientType getClientType() {
		
		if (_client.isServerQueryClient()) {
			return ClientType.SERVERQUERY;
		}
		
		if (_client.isRegularClient()) {
			return ClientType.VOICE;
		}
		
		return ClientType.UNKNOWN;
	}

	@Override
	public boolean isTalking() {
		return _client.isTalking();
	}
	
	public void setChannel(AoGChannel channel) {
		_channel = channel;
	}
	
	public Client getInternalClient() {
		return _client;
	}
}
