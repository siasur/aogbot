package me.siasur.areacommunity.aogbot.bridge;

import java.util.List;

/**
 * Defines functions for the interaction with channels
 * 
 */
public interface IAoGChannel {

	/**
	 * Gets the channel id
	 * @return
	 */
	public int getId();
	
	/**
	 * Gets a list of all {@link IAoGClient}s that are currently in this channel
	 */
	public List<IAoGClient> getClients();
	
	/**
	 * Gets the name
	 */
	public String GetName();
	
	/**
	 * Gets the parent channel
	 */	
	public IAoGChannel GetParent();
	
	/**
	 * Gets all child channels
	 */	
	public List<IAoGChannel> GetChilds();
	
	/**
	 * Gets the {@link ChannelType}
	 */	
	public ChannelType GetChannelType();
	
	/**
	 * Gets a value indicating whether the channel has a password
	 * 
	 * @return {@code true}, if the channel has a password, {@code false} otherwise
	 */	
	public boolean hasPassword();
	
	/**
	 * Gets the topic
	 */	
	public String getTopic();
	
	/**
	 * Gets the needed TalkPower
	 */	
	public int getNeededTalkPower();
	
	/**
	 * Gets a value indicating whether the channel is empty
	 * 
	 * @return {@code true}, if the channel is empty, {@code false} otherwise
	 */	
	public boolean isEmpty();
	
	/**
	 * Gets a value indicating whether the channel is the default channel
	 * 
	 * @return {@code true}, if the channel is the default channel, {@code false} otherwise
	 */	
	public boolean isDefault();
	
	/**
	 * Gets the maximal amount of clients
	 */	
	public int getMaxClients();
	
	/**
	 * Gets the maximal amount of clients in the family
	 */	
	public int getMaxFamilyClients();
	
	/**
	 * Gets the time the channel is empty (in seconds)
	 */	
	public int getSecondsEmpty();
	
	/**
	 * Gets the description
	 */	
	public String getDescription();

	/**
	 * Gets the password
	 */	
	public String getPassword();
	
	/**
	 * Gets a value indicating whether the channels voice data is encrypted
	 * 
	 * @return {@code true}, if the voice data is encrypted, {@code false} otherwise
	 */	
	public boolean isEncrypted();
	
	/**
	 * Gets a value indicating whether the channel forces silence
	 * 
	 * @return {@code true}, if the channel forces silence, {@code false} otherwise
	 */	
	public boolean isForcedSilence();
	
	/**
	 * Gets the phonetic name
	 */	
	public String getPhoneticName();
}