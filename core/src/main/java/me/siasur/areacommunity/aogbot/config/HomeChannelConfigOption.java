package me.siasur.areacommunity.aogbot.config;

public class HomeChannelConfigOption {

	private int _channelId;

	private boolean _force;

	protected HomeChannelConfigOption() {

	}

	public int getChannelId() {
		return _channelId;
	}

	public boolean isForced() {
		return _force;
	}

	protected void setChannelId(int channelId) {
		_channelId = channelId;
	}

	protected void setForced(boolean force) {
		_force = force;
	}
}
