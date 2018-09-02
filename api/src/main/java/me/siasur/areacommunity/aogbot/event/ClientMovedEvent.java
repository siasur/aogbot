package me.siasur.areacommunity.aogbot.event;

import me.siasur.areacommunity.aogbot.bridge.IAoGChannel;

public class ClientMovedEvent extends BaseEvent {

	IAoGChannel _sourceChannel;
	IAoGChannel _targetChannel;
	
	protected ClientMovedEvent() {
		
	}
	
	protected void setSourceChannel(IAoGChannel channel) {
		_sourceChannel = channel;
	}
	
	protected void setTargetChannel(IAoGChannel channel) {
		_targetChannel = channel;
	}
	
	public IAoGChannel getSourceChannel() {
		return _sourceChannel;
	}
	
	public IAoGChannel getTargetChannel() {
		return _targetChannel;
	}
	
}
