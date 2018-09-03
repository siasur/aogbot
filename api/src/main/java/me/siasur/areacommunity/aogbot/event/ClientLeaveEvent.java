package me.siasur.areacommunity.aogbot.event;

import me.siasur.areacommunity.aogbot.bridge.IAoGClient;

public class ClientLeaveEvent extends AoGEvent {

	IAoGClient _client;
	
	protected ClientLeaveEvent() {

	}

	public IAoGClient getClient() {
		return _client;
	}

	protected void setClient(IAoGClient client) {
		_client = client;
	}
}
