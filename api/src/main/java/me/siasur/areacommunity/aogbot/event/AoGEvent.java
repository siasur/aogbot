package me.siasur.areacommunity.aogbot.event;

import me.siasur.areacommunity.aogbot.bridge.IAoGClient;

public class AoGEvent implements IEvent {

	private IAoGClient _invoker;

	public IAoGClient getInvoker() {
		return _invoker;
	}

	protected void setInvoker(IAoGClient invoker) {
		_invoker = invoker;
	}

}
