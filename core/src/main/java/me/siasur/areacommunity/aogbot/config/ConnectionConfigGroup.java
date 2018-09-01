package me.siasur.areacommunity.aogbot.config;

public class ConnectionConfigGroup {

	private String _host;

	private ServerIdentifierConfigOption _identifier;

	private int _port;

	protected ConnectionConfigGroup() {
		_identifier = new ServerIdentifierConfigOption();
	}

	public String getHost() {
		return _host;
	}

	public ServerIdentifierConfigOption getIdentifier() {
		return _identifier;
	}

	public int getPort() {
		return _port;
	}

	protected void setHost(String host) {
		_host = host;
	}

	protected void setPort(int port) {
		_port = port;
	}

}
