package me.siasur.areacommunity.aogbot.config;

public class ServerIdentifierConfigOption {

	private ServerIdentifierType _identifier;

	private int _value;

	protected ServerIdentifierConfigOption() {

	}

	public ServerIdentifierType getIdentifier() {
		return _identifier;
	}

	public int getValue() {
		return _value;
	}

	protected void setIdentifier(ServerIdentifierType identifier) {
		_identifier = identifier;
	}

	protected void setValue(int value) {
		_value = value;
	}

}
