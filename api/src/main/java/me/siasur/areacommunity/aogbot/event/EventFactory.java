package me.siasur.areacommunity.aogbot.event;

import me.siasur.areacommunity.aogbot.bridge.IAoGChannel;
import me.siasur.areacommunity.aogbot.bridge.IAoGClient;

public class EventFactory {

	private EventFactory() {
		
	}
	
	public static ClientMovedEvent createClientMovedEvent(IAoGClient invoker, IAoGChannel source, IAoGChannel target) {
		ClientMovedEvent event = new ClientMovedEvent();
		
		event.setInvoker(invoker);
		event.setSourceChannel(source);
		event.setTargetChannel(target);
		
		return event;
	}
	
}
