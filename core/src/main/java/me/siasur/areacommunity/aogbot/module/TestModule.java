package me.siasur.areacommunity.aogbot.module;

import me.siasur.areacommunity.aogbot.bridge.IAoGClient;
import me.siasur.areacommunity.aogbot.bridge.IClientManager;
import me.siasur.areacommunity.aogbot.utility.ServiceLocator;

public class TestModule extends BaseModule {

	@Override
	protected void onDisable() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onEnable() {
		IClientManager clientManager = ServiceLocator.getServiceLocator().getService(IClientManager.class);
		for (IAoGClient client : clientManager.getAllClients()) {
			System.out.println(String.format("%d | %s", client.getId(), client.getNickname()));
			//client.poke(String.format("Du bist: \"%s\" und du befindest dich im Channel \"%s\".", client.getNickname(), client.getChannel().GetName()));
		}
	}
}
