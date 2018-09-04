package me.siasur.areacommunity.aogbot.module;

import me.siasur.areacommunity.aogbot.bridge.IAoGClient;
import me.siasur.areacommunity.aogbot.event.ClientJoinEvent;
import me.siasur.areacommunity.aogbot.event.IEventManager;
import me.siasur.areacommunity.aogbot.event.MessageEvent;
import me.siasur.areacommunity.aogbot.utility.ServiceLocator;

public class GreeterModule extends BaseModule {

	IEventManager eventManager;
	
	public GreeterModule() {
		eventManager = ServiceLocator.getServiceLocator().getService(IEventManager.class);
	}
	
	@Override
	protected void onDisable() {
		eventManager.removeEventHandler(ClientJoinEvent.class, "greeter_join");
		eventManager.removeEventHandler(MessageEvent.class, "greeter_message");
	}

	@Override
	protected void onEnable() {
		eventManager.registerEventHandler(ClientJoinEvent.class, "greeter_join", this::greetNewPeople);
		eventManager.registerEventHandler(MessageEvent.class, "greeter_message", this::openChatOnBotCommand);
		
		System.out.println("running...");
	}
	
	public boolean greetNewPeople(ClientJoinEvent event) {
		IAoGClient client = event.getClient();
		
		if (client.getTotalConnections() == 1) {
			client.poke(String.format("Hallo %s, herzlich willkommen auf dem AoG Teamspeak", client.getNickname()));
			client.sendMessage("Es scheint als sie dies dein erster Besuch auf diesem Teamspeakserver."
					+ "Ich bin der AoG Bot und erweitere den Sever um einige nützliche Features.\n"
					+ "Wann immer du mich brauchst, schreibe einfach [color=#ff0000]!bot[/color] in der Eingangshalle."
					+ "Ich werde dich dann anschreiben, so dass du mir dann im privaten Chat weitere Commands geben kannst.");
		}
		
		return false;
	}
	
	public boolean openChatOnBotCommand(MessageEvent event) {
		IAoGClient client = event.getInvoker();
		String message = event.getMessage();
		
		if (message.startsWith("!bot")) {
			client.sendMessage("Was kann ich für dich tun? :3");
		}
		
		
		return false;
	}

}
