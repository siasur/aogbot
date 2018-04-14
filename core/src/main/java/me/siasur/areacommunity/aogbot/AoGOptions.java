package me.siasur.areacommunity.aogbot;

import joptsimple.OptionSet;
import me.siasur.areacommunity.aogbot.utility.StringUtils;

/**
 * Holds a collection of configuration options for the TS3Api
 * 
 */
public class AoGOptions {

	private AoGOptions() {
		_serverId = Integer.MIN_VALUE;
		_slowmode = false;
	}

	private String _host;

	private String _username;

	private String _password;

	private boolean _slowmode;

	private int _serverId;
	
	private String _displayName;
	
	public boolean isValid() {
		boolean returnValue = true;
		
		if (StringUtils.isNullOrEmpty(_host)) returnValue = false;
		if (StringUtils.isNullOrEmpty(_username)) returnValue = false;
		if (StringUtils.isNullOrEmpty(_password)) returnValue = false;
		if (_serverId == Integer.MIN_VALUE) returnValue = false;
		if (StringUtils.isNullOrEmpty(_displayName)) returnValue = false;
		
		return returnValue;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getHost() {
		return _host;
	}
	
	public String getUsername() {
		return _username;
	}
	
	public String getPassword() {
		return _password;
	}
	
	public String getDisplayName() {
		return _displayName;
	}
	
	public boolean getSlowmode() {
		return _slowmode;
	}
	
	public int getServerId() {
		return _serverId;
	}
	
	static AoGOptions fromOptionSet(OptionSet optionSet) {
		AoGOptions options = new AoGOptions();
		
		options._host = (String)optionSet.valueOf("host");
		options._username = (String)optionSet.valueOf("user");
		options._password = (String)optionSet.valueOf("password");
		options._displayName = (String)optionSet.valueOf("name");
		options._slowmode = optionSet.has("slowmode");
		options._serverId = (int)optionSet.valueOf("id");
		
		return options;
	}
	
}
