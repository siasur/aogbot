package me.siasur.areacommunity.aogbot.event;

import me.siasur.areacommunity.aogbot.bridge.IAoGChannel;
import me.siasur.areacommunity.aogbot.bridge.IAoGClient;

public class EventBuilder {

	public static AoGClientMoveEvent createClientMovedEvent(IAoGClient invoker, IAoGClient client, IAoGChannel source,
			IAoGChannel target) {
		AoGClientMoveEvent event = new AoGClientMoveEvent();

		event.setInvoker(invoker);
		event.setClient(client);
		event.setSourceChannel(source);
		event.setTargetChannel(target);

		return event;
	}

	private EventBuilder() {

	}

	public static AoGMessageEvent createMessageEvent(IAoGClient invoker, String message) {
		AoGMessageEvent event = new AoGMessageEvent();
		
		event.setInvoker(invoker);
		event.setMessage(message);
		
		return event;
	}

	public static AoGClientJoinEvent createClientJoinEvent(IAoGClient invoker, IAoGClient client) {
		AoGClientJoinEvent event = new AoGClientJoinEvent();
		
		event.setInvoker(invoker);
		event.setClient(client);
		
		return event;
	}

	public static AoGClientLeaveEvent createClientLeaveEvent(IAoGClient invoker, IAoGClient client) {
		AoGClientLeaveEvent event = new AoGClientLeaveEvent();
		
		event.setInvoker(invoker);
		event.setClient(client);
		
		return event;
	}

}