package me.siasur.areacommunity.aogbot.module;

import me.siasur.areacommunity.aogbot.bridge.IAoGClient;
import me.siasur.areacommunity.aogbot.bridge.IClientManager;
import me.siasur.areacommunity.aogbot.event.AoGClientJoinEvent;
import me.siasur.areacommunity.aogbot.event.AoGMessageEvent;
import me.siasur.areacommunity.aogbot.event.EventHandler;
import me.siasur.areacommunity.aogbot.utility.ServiceLocator;

public class GreeterModule extends BaseModule {
	
	@Override
	protected void onDisable() {
		System.out.println("Greeter disabled");
	}

	@Override
	protected void onEnable() {
		IClientManager clientManager = ServiceLocator.getServiceLocator().getService(IClientManager.class);
		for (IAoGClient client : clientManager.getAllClients()) {
			System.out.println(String.format("%d | %s", client.getId(), client.getNickname()));
		}
		
		System.out.println("Greeter enabled");
	}
	
	@EventHandler
	public void greetNewPeople(AoGClientJoinEvent event) {
		IAoGClient client = event.getClient();
		
		getLogger().info("Yup... " + event.getClient().getNickname() + " joined...");
		
		if (client.getTotalConnections() == 1) {
			client.poke(String.format("Hallo %s, herzlich willkommen auf dem AoG Teamspeak", client.getNickname()));
			client.sendMessage("Es scheint als sie dies dein erster Besuch auf diesem Teamspeakserver."
					+ "Ich bin der AoG Bot und erweitere den Sever um einige nützliche Features.\n"
					+ "Wann immer du mich brauchst, schreibe einfach [color=#ff0000]!bot[/color] in der Eingangshalle."
					+ "Ich werde dich dann anschreiben, so dass du mir dann im privaten Chat weitere Commands geben kannst.");
		}
	}
	
	@Override
	public String getName() {
		return "Greeter";
	}
	
	@EventHandler
	public void openChatOnBotCommand(AoGMessageEvent event) {
		IAoGClient client = event.getInvoker();
		String message = event.getMessage();
		
		if (message.startsWith("!bot")) {
			client.sendMessage("Was kann ich für dich tun? :3");
		}
	}

}
