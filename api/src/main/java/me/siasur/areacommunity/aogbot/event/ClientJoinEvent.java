package me.siasur.areacommunity.aogbot.event;

import me.siasur.areacommunity.aogbot.bridge.IAoGClient;

public class ClientJoinEvent extends AoGEvent {

	IAoGClient _client;
	
	protected ClientJoinEvent() {

	}

	public IAoGClient getClient() {
		return _client;
	}

	protected void setClient(IAoGClient client) {
		_client = client;
	}
}
