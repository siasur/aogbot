package me.siasur.areacommunity.aogbot.module;

import me.siasur.areacommunity.aogbot.bridge.IAoGChannel;
import me.siasur.areacommunity.aogbot.bridge.IAoGClient;
import me.siasur.areacommunity.aogbot.bridge.IClientManager;
import me.siasur.areacommunity.aogbot.event.ClientMoveEvent;
import me.siasur.areacommunity.aogbot.event.IEventManager;
import me.siasur.areacommunity.aogbot.event.MessageEvent;
import me.siasur.areacommunity.aogbot.utility.ServiceLocator;

public class TestModule extends BaseModule {

	public boolean testing(ClientMoveEvent ev) {
		String message;

		IAoGClient invoker = ev.getInvoker();
		IAoGClient client = ev.getClient();
		IAoGChannel source = ev.getSourceChannel();
		IAoGChannel target = ev.getTargetChannel();

		if (invoker != null) {
			// client was moved by someone
			message = String.format("'%s' (%d) moved the client '%s' (%d) from '%s' to '%s'.", invoker.getNickname(),
					invoker.getId(), client.getNickname(), client.getId(), source.GetName(), target.GetName());
		} else {
			message = String.format("The client '%s' (%d) moved from '%s' to '%s'.", client.getNickname(),
					client.getId(), source.GetName(), target.GetName());
		}

		System.out.println(message);
		return false;
	}
	
	public boolean pokeback(MessageEvent ev) {
		IAoGClient invoker = ev.getInvoker();
		
		invoker.poke(ev.getMessage());
		
		return false;
	}

	@Override
	protected void onDisable() {
		// TODO Auto-generated method stub

		IEventManager eventManager = ServiceLocator.getServiceLocator().getService(IEventManager.class);

		eventManager.removeEventHandler(ClientMoveEvent.class, "testing");
	}

	@Override
	protected void onEnable() {
		IClientManager clientManager = ServiceLocator.getServiceLocator().getService(IClientManager.class);
		for (IAoGClient client : clientManager.getAllClients()) {
			System.out.println(String.format("%d | %s", client.getId(), client.getNickname()));
		}

		IEventManager eventManager = ServiceLocator.getServiceLocator().getService(IEventManager.class);

		eventManager.registerEventHandler(ClientMoveEvent.class, "testing", this::testing);
		eventManager.registerEventHandler(MessageEvent.class, "pokeback", this::pokeback);
	}
}
