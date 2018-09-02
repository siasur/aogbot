package me.siasur.areacommunity.aogbot.event;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class EventManager implements IEventManager {
	
	Map<Class<? extends BaseEvent>, EventHandler<? extends BaseEvent>> _newHandlers;
	
	Map<Class<? extends BaseEvent>, Map<String, Function<? extends BaseEvent, Boolean>>> _handlers;
	
	public EventManager() {
		_handlers = new HashMap<Class<? extends BaseEvent>, Map<String,Function<? extends BaseEvent,Boolean>>>();
	}
	
	@Override
	public <T extends BaseEvent> void registerEventHandler(Class<T> event, String identifier, Function<T, Boolean> handler) {
		if (!_handlers.containsKey(event)) {
			_handlers.put(event, new HashMap<String,Function<? extends BaseEvent, Boolean>>());
		}
		
		Map<String, Function<? extends BaseEvent, Boolean>> handlers = _handlers.get(event);
		handlers.put(identifier, handler);
	}

	@Override
	public <T extends BaseEvent> boolean removeEventHandler(Class<T> event, String identifier) {
		_handlers.get(event).remove(identifier);
		return false;
	}

	public <T extends BaseEvent> void fireEvent(Class<T> event, T params) {
		_handlers.get(event);
	}
	
//	public <T extends BaseEvent>void fireEvent(Class<T> clazz, T event) {
//		if (!_handlers.containsKey(clazz)) {
//			return;
//		}
//		
//		Collection<Function<? extends BaseEvent, Boolean>> handlers = _handlers.get(clazz).values();
//		
//		for (Function<? extends BaseEvent, Boolean> handler : handlers) {
//			handler.apply(event);
//		}
//	}
}
