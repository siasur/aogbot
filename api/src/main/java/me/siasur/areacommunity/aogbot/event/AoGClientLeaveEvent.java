package me.siasur.areacommunity.aogbot.event;

import me.siasur.areacommunity.aogbot.bridge.IAoGClient;

public class AoGClientLeaveEvent extends AoGBaseEvent {

	IAoGClient _client;
	
	protected AoGClientLeaveEvent() {

	}

	public IAoGClient getClient() {
		return _client;
	}

	protected void setClient(IAoGClient client) {
		_client = client;
	}
	
}
