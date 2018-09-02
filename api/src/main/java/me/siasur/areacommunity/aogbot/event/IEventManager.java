package me.siasur.areacommunity.aogbot.event;

import java.util.function.Function;

public interface IEventManager {

	/**
	 * Adds a handler for a given event
	 * <p>
	 * Fails silently if there is already a handler with the same identifier for the given event
	 * @param event
	 * @param identifier
	 * @param handler
	 */
	<T extends BaseEvent> void registerEventHandler(Class<T> event, String identifier, Function<T, Boolean> handler);
	
	<T extends BaseEvent> boolean removeEventHandler(Class<T> event, String identifier);
	
}
