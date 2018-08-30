package me.siasur.areacommunity.aogbot.config;

public class ConnectionConfigGroup {

	private String _host;
	
	private int _port;
	
	private ServerIdentifierConfigOption _identifier;
	
	protected ConnectionConfigGroup() {
		_identifier = new ServerIdentifierConfigOption();
	}
	
	public String getHost() {
		return _host;
	}
	
	protected void setHost(String host) {
		_host = host;
	}
	
	public int getPort() {
		return _port;
	}
	
	protected void setPort(int port) {
		_port = port;
	}
	
	public ServerIdentifierConfigOption getIdentifier() {
		return _identifier;
	}
	
}
