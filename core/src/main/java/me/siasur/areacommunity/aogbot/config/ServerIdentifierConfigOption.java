package me.siasur.areacommunity.aogbot.config;

public class ServerIdentifierConfigOption {
	
	private ServerIdentifierType _identifier;
	
	private int _value;	
	
	protected ServerIdentifierConfigOption() {
		
	}
	
	public int getValue() {
		return _value;
	}
	
	protected void setValue(int value) {
		_value = value;
	}
	
	public ServerIdentifierType getIdentifier() {
		return _identifier;
	}
	
	protected void setIdentifier(ServerIdentifierType identifier) {
		_identifier = identifier;
	}
	
}
