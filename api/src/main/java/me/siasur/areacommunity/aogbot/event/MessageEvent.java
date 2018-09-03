package me.siasur.areacommunity.aogbot.event;

public class MessageEvent extends AoGEvent {
	
	private String _message;
	
	protected void setMessage(String message) {
		_message = message;
	}
	
	public String getMessage() {
		return _message;
	}
	
}
