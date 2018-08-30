package me.siasur.areacommunity.aogbot.event;

public class EventFactory {

	public static CommandEvent createCommandEvent(int invokerId, String cmd, String... arguments) {
		CommandEvent cmdEvent = new CommandEvent();
		
		cmdEvent.setInvokerId(invokerId);
		
		cmdEvent.setCommand(cmd);
		
		cmdEvent.setArguments(arguments);
		
		return cmdEvent;
	}
	
}
