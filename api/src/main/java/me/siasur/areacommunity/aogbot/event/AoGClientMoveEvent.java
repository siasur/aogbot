package me.siasur.areacommunity.aogbot.event;

import me.siasur.areacommunity.aogbot.bridge.IAoGChannel;
import me.siasur.areacommunity.aogbot.bridge.IAoGClient;

public class AoGClientMoveEvent extends AoGBaseEvent {

	IAoGClient _client;
	IAoGChannel _sourceChannel;
	IAoGChannel _targetChannel;

	protected AoGClientMoveEvent() {

	}

	public IAoGClient getClient() {
		return _client;
	}

	public IAoGChannel getSourceChannel() {
		return _sourceChannel;
	}

	public IAoGChannel getTargetChannel() {
		return _targetChannel;
	}

	protected void setClient(IAoGClient client) {
		_client = client;
	}

	protected void setSourceChannel(IAoGChannel channel) {
		_sourceChannel = channel;
	}

	protected void setTargetChannel(IAoGChannel channel) {
		_targetChannel = channel;
	}
	
}
