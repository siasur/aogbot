package me.siasur.areacommunity.aogbot.config;

public class SettingsConfigGroup {

	private String _displayName;

	private HomeChannelConfigOption _homeChannel;

	private GenericEnableableConfigOption _reconnect;

	private GenericEnableableConfigOption _slowmode;

	protected SettingsConfigGroup() {
		_homeChannel = new HomeChannelConfigOption();
		_reconnect = new GenericEnableableConfigOption();
		_slowmode = new GenericEnableableConfigOption();
	}

	public String getDisplayName() {
		return _displayName;
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

	protected void setDisplayName(String displayName) {
		_displayName = displayName;
	}

}
