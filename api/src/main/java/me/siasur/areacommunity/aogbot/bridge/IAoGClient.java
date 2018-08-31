package me.siasur.areacommunity.aogbot.bridge;

import java.util.Date;

/**
 * Represents a client that is currently connected to the teamspeak server
 */
public interface IAoGClient {

	/**
	 * Gets a value indicating whether the client can talk
	 * @return {@code true}, if the client can talk
	 * 
	 * @see #isRequestingToTalk()
	 */
	public boolean canTalk();

	/**
	 * Gets something about the avatar... Maybe the path... the ts3 api is bad documented...
	 * @return something about the avatar
	 */
	public String getAvatar();

	/**
	 * Gets the away message.
	 * @return the away message
	 * 
	 * @see #isAway()
	 */
	public String getAwayMessage();

	/**
	 * Gets the bandwidth usage of the last minute (in bytes?)
	 * @return the bandwidth usage
	 * 
	 * @see #getBandwidthUsageLastSecond()
	 * @see #getFiletransferBandwidthUsage()
	 */
	public BandwithUsage getBandwidthUsageLastMinute();

	/**
	 * Gets the bandwidth usage of the last second (in bytes?)
	 * @return the bandwidth usage
	 * 
	 * @see #getBandwidthUsageLastMinute()
	 * @see #getFiletransferBandwidthUsage()
	 */
	public BandwithUsage getBandwidthUsageLastSecond();

	/**
	 * Gets the channel in which the client is currently
	 * @return the {@link IAoGChannel}
	 */
	public IAoGChannel getChannel();

	/**
	 * Gets the {@link ClientType} of the client
	 * @return the {@link ClientType}
	 */
	public ClientType getClientType();

	/**
	 * Gets the date when the client joined the teamspeak server for the first time
	 * @return the {@link Date}
	 */
	public Date getCreatedDate();

	/**
	 * Gets the default token
	 * @return the token
	 */
	public String getDefaultToken();

	/**
	 * Gets the client description
	 * @return the description
	 * 
	 * @see #setDescription(String description)
	 */
	public String getDescription();
	
	/**
	 * Sets the client description
	 * @param description the new description
	 * 
	 * @see #getDescription()
	 */
	public void setDescription(String description);

	/**
	 * Gets the bandwidth usage for file transfer (in bytes?)
	 * @return the bandwidth usage
	 * 
	 * @see #getBandwidthUsageLastMinute()
	 * @see #getBandwidthUsageLastSecond()
	 */
	public BandwithUsage getFiletransferBandwidthUsage();

	/**
	 * Gets the currently assigned client id
	 * @return the client id
	 */
	public int getId();

	/**
	 * Gets the time since the client talked the last time
	 * @return the idle time
	 */
	public long getIdleTime();

	/**
	 * Gets the ip address
	 * @return the ip address
	 */
	public String getIp();

	/**
	 * Gets the date when the client joined the last time
	 * @return the {@link Date}
	 */
	public Date getLastConnectedDate();

	/**
	 * Gets the login name of the client (maybe only for queries?)
	 * @return the login name
	 */
	public String getLoginName();

	/**
	 * Gets the meta data (what?!)
	 * @return the meta data as {@link String}
	 */
	public String getMetaData();

	/**
	 * Gets the needed ServerQueryViewPower
	 * @return the needed ServerQueryViewPower
	 */
	public int getNeededServerQueryViewPower();

	/**
	 * Gets the nickname
	 * @return the nickname
	 * 
	 * @see #getPhoneticNickname()
	 */
	public String getNickname();

	/**
	 * Gets the phonetic nickname
	 * @return the phonetic nickname
	 * 
	 * @see #getNickname()
	 */
	public String getPhoneticNickname();

	/**
	 * Gets the current talk power
	 * @return the talk power
	 */
	public int getTalkPower();

	/**
	 * Gets the talk request message
	 * @return the talk request message
	 */
	public String getTalkRequestMessage();

	/**
	 * Gets the time since the client connected to the server (in seconds?)
	 * @return the time since the client connected
	 */
	public long getTimeConnected();

	/**
	 * Gets the amount of total connections
	 * @return the amount of total connections
	 */
	public int getTotalConnections();

	/**
	 * Gets the unique id
	 * @return the unique id
	 */
	public String getUniqueId();

	/**
	 * Gets the amount of unread messages
	 * @return the amount of unread messages
	 */
	public int getUnreadMessagesCount();

	/**
	 * Gets the used client version
	 * @return the version in {@link String} representation
	 */
	public String getVersion();

	/**
	 * Gets a value indicating whether the client is away
	 * @return {@code true}, if the client is away
	 * 
	 * @see #getAwayMessage()
	 */
	public boolean isAway();

	/**
	 * Gets a value indicating whether the client is channel commander
	 * @return {@code true}, if the client is channel commander
	 */
	public boolean isChannelCommander();

	/**
	 * Gets a value indicating whether the input is muted
	 * @return {@code true}, if the clients input is muted
	 * 
	 * @see #isOutputMuted()
	 */
	public boolean isInputMuted();

	/**
	 * Gets a value indicating whether the output is muted
	 * @return {@code true}, if the clients output is muted
	 * 
	 * @see #isInputMuted()
	 * @see #isOutputOnlyMuted()
	 */
	public boolean isOutputMuted();

	/**
	 * Gets a value indicating whether only the output is muted
	 * @return {@code true}, if only the clients output is muted
	 * 
	 * @see IAoGClient#isOutputMuted()
	 */
	public boolean isOutputOnlyMuted();

	/**
	 * Gets a value indicating whether the client is priority speaker
	 * @return {@code true}, if the client is priority speaker
	 */
	public boolean isPrioritySpeaker();

	/**
	 * Gets a value indicating whether the client is recording
	 * @return {@code true}, if the client is recording
	 */
	public boolean isRecording();

	/**
	 * Gets a value indicating whether the client is requesting to talk
	 * @return {@code true}, if the client is requesting to talk
	 * 
	 * @see #getTalkRequestMessage()
	 * @see #getTalkPower()
	 * @see #canTalk()
	 */
	public boolean isRequestingToTalk();

//	public boolean isTalking();

	/**
	 * Kicks the client from its current channel
	 * 
	 * @see #kickFromServer(String)
	 */
	public void kickFromChannel();

	/**
	 * Kicks the client from the server
	 * @param message The reason for the kick
	 * 
	 * @see #kickFromChannel()
	 */
	public void kickFromServer(String message);

	/**
	 * Pokes the client with the given message.
	 * <p>
	 * The message will be cut if it is too long.
	 * @param message The message that is shown to the client
	 */
	public void poke(String message);

	/**
	 * Sends a message to the client
	 * @param message The message that is sent to the client
	 */
	public void sendMessage(String message);
}
