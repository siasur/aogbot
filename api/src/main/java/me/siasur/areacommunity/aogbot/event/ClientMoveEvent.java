package me.siasur.areacommunity.aogbot.event;

import me.siasur.areacommunity.aogbot.bridge.IAoGChannel;

public class ClientMoveEvent extends AoGEvent {

	IAoGChannel _sourceChannel;
	IAoGChannel _targetChannel;
	
	protected ClientMoveEvent() {
		
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
