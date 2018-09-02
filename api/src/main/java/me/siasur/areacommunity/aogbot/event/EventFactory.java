package me.siasur.areacommunity.aogbot.event;

import me.siasur.areacommunity.aogbot.bridge.IAoGChannel;
import me.siasur.areacommunity.aogbot.bridge.IAoGClient;

public class EventFactory {

	private EventFactory() {
		
	}
	
	public static ClientMoveEvent createClientMovedEvent(IAoGClient invoker, IAoGChannel source, IAoGChannel target) {
		ClientMoveEvent event = new ClientMoveEvent();
		
		event.setInvoker(invoker);
		event.setSourceChannel(source);
		event.setTargetChannel(target);
		
		return event;
	}
	
}
