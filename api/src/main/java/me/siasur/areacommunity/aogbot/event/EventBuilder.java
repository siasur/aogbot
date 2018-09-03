package me.siasur.areacommunity.aogbot.event;

import me.siasur.areacommunity.aogbot.bridge.IAoGChannel;
import me.siasur.areacommunity.aogbot.bridge.IAoGClient;

public class EventBuilder {

	public static ClientMoveEvent createClientMovedEvent(IAoGClient invoker, IAoGClient client, IAoGChannel source,
			IAoGChannel target) {
		ClientMoveEvent event = new ClientMoveEvent();

		event.setInvoker(invoker);
		event.setClient(client);
		event.setSourceChannel(source);
		event.setTargetChannel(target);

		return event;
	}

	private EventBuilder() {

	}

	public static MessageEvent createMessageEvent(IAoGClient invoker, String message) {
		MessageEvent event = new MessageEvent();
		
		event.setInvoker(invoker);
		event.setMessage(message);
		
		return event;
	}

	public static ClientJoinEvent createClientJoinEvent(IAoGClient invoker, IAoGClient client) {
		ClientJoinEvent event = new ClientJoinEvent();
		
		event.setInvoker(invoker);
		event.setClient(client);
		
		return event;
	}

	public static ClientLeaveEvent createClientLeaveEvent(IAoGClient invoker, IAoGClient client) {
		ClientLeaveEvent event = new ClientLeaveEvent();
		
		event.setInvoker(invoker);
		event.setClient(client);
		
		return event;
	}

}
