package me.siasur.areacommunity.aogbot.module;

import me.siasur.areacommunity.aogbot.bridge.IAoGClient;
import me.siasur.areacommunity.aogbot.bridge.IClientManager;
import me.siasur.areacommunity.aogbot.utility.ServiceLocator;

public class TestModule extends BaseModule {

	@Override
	public void disable() {
		// TODO Auto-generated method stub

	}

	@Override
	public void enable() {
		IClientManager clientManager = ServiceLocator.getServiceLocator().getService(IClientManager.class);
		for (IAoGClient client : clientManager.getAllClients()) {
			client.poke(String.format("Du bist: \"%s\" und du befindest dich im Channel \"%s\".", client.getNickname(), client.getChannel().GetName()));
		}
	}
}
