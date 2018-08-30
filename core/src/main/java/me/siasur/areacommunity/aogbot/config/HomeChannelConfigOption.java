package me.siasur.areacommunity.aogbot.config;

public class HomeChannelConfigOption {
	
	private boolean _force;
	
	private int _channelId;
	
	protected HomeChannelConfigOption() {
		
	}
	
	public boolean isForced() {
		return _force;
	}
	
	protected void setForced(boolean force) {
		_force = force;
	}
	
	public int getChannelId() {
		return _channelId;
	}
	
	protected void setChannelId(int channelId) {
		_channelId = channelId;
	}
}
