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

}
