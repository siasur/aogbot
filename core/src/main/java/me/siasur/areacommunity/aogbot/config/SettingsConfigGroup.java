package me.siasur.areacommunity.aogbot.config;

public class SettingsConfigGroup {
	
	private HomeChannelConfigOption _homeChannel;
	
	private GenericEnableableConfigOption _reconnect;
	
	private GenericEnableableConfigOption _slowmode;
	
	private String _displayName;

	protected SettingsConfigGroup() {
		_homeChannel = new HomeChannelConfigOption();
		_reconnect = new GenericEnableableConfigOption();
		_slowmode = new GenericEnableableConfigOption();
	}
	
	public HomeChannelConfigOption getHomeChannel() {
		return _homeChannel;
	}
	
	public GenericEnableableConfigOption getReconnect() {
		return _reconnect;
	}
	
	public GenericEnableableConfigOption getSlowmode() {
		return _slowmode;
	}
	
	public String getDisplayName() {
		return _displayName;
	}
	
	protected void setDisplayName(String displayName) {
		_displayName = displayName;
	}
	
}
