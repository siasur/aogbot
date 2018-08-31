package me.siasur.areacommunity.aogbot.bridge;

import java.util.List;

public interface IAoGChannel {

	public int getId();
	
	public List<IAoGClient> getClients();
	
	public String GetName();
	
	public IAoGChannel GetParent();
	
	public List<IAoGChannel> GetChilds();
	
	public ChannelType GetChannelType();
	
	public boolean hasPassword();
	
	public String getTopic();
	
	public int getNeededTalkPower();
	
	public boolean isEmpty();
	
	public boolean isDefault();
	
	public int getMaxClients();
	
	public int getMaxFamilyClients();
	
	public int getSecondsEmpty();
	
	public String getDescription();

	public String getPassword();
	
	public boolean isEncrypted();
	
	public boolean isForcedSilence();
	
	public String getPhoneticName();
}
