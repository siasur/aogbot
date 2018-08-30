package me.siasur.areacommunity.aogbot.event;

public abstract class BaseEvent {

	private int _invokerId;
	
	public int getInvokerId() {
		return _invokerId;
	}
	
	protected void setInvokerId(int invokerId) {
		_invokerId = invokerId;
	}
}
