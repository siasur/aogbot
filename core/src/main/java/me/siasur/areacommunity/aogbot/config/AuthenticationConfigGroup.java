package me.siasur.areacommunity.aogbot.config;

public class AuthenticationConfigGroup {

	private String _name;
	
	private String _password;
	
	protected AuthenticationConfigGroup() {
		
	}
	
	public String getName() {
		return _name;
	}
	
	protected void setName(String name) {
		_name = name;
	}
	
	public String getPassword() {
		return _password;
	}
	
	protected void setPassword(String password) {
		_password = password;
	}
	
}
