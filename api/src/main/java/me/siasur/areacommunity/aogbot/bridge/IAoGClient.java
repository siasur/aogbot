package me.siasur.areacommunity.aogbot.bridge;

import java.util.Date;

public interface IAoGClient {

	public int getId();
	
	public String getNickname();
	
	public void sendMessage(String message);
	
	public void poke(String message);

	public IAoGChannel getChannel();
	
	public void kickFromChannel();
	
	public void kickFromServer(String message);
	
	public boolean canTalk();
	
	public String getAwayMessage();
	
	public Date getCreatedDate();
	
	public long getIdleTime();
	
	public String getIp();
	
	public Date getLastConnectedDate();
	
	public int getTalkPower();
	
	public String getUniqueId();
	
	public String getVersion();
	
	// public boolean hasOverwolf();
	
	public boolean isAway();
	
	public boolean isChannelCommander();
	
	// public boolean isInputHardware();
	
	public boolean isInputMuted();
	
	//public boolean isOutputHardware();
	
	public boolean isOutputMuted();
	
	public boolean isPrioritySpeaker();
	
	public boolean isRecording();
	
	public ClientType getClientType();
	
	public boolean isTalking();

}
