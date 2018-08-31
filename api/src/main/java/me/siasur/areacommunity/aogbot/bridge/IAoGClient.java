package me.siasur.areacommunity.aogbot.bridge;

import java.util.Date;

public interface IAoGClient {

	public boolean canTalk();

	public String getAvatar();

	public String getAwayMessage();

	public BandwithUsage getBandwidthUsageLastMinute();

	public BandwithUsage getBandwidthUsageLastSecond();

	public IAoGChannel getChannel();

	public ClientType getClientType();

	public Date getCreatedDate();

	public String getDefaultToken();

	public String getDescription();

	public BandwithUsage getFiletransferBandwidthUsage();

	public int getId();

	public long getIdleTime();

	public String getIp();

	public Date getLastConnectedDate();

	public String getLoginName();

	public String getMetaData();

	public int getNeededServerQueryViewPower();

	public String getNickname();

	public String getPhoneticNickname();

	public int getTalkPower();

	public String getTalkRequestMessage();

	public long getTimeConnected();

	public int getTotalConnections();

	public String getUniqueId();

	public int getUnreadMessagesCount();

	public String getVersion();

	public boolean isAway();

	public boolean isChannelCommander();

	public boolean isInputMuted();

	public boolean isOutputMuted();

	public boolean isOutputOnlyMuted();

	public boolean isPrioritySpeaker();

	public boolean isRecording();

	public boolean isRequestingToTalk();

//	public boolean isTalking();

	public void kickFromChannel();

	public void kickFromServer(String message);

	public void poke(String message);

	public void sendMessage(String message);
}
