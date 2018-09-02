package me.siasur.areacommunity.aogbot.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class EventManager implements IEventManager {
	
	Map<Class<? extends AoGEvent>, Map<String, Function<? extends AoGEvent, Boolean>>> _handlers;
	
	public EventManager() {
		_handlers = new HashMap<Class<? extends AoGEvent>, Map<String,Function<? extends AoGEvent, Boolean>>>();
	}
	
	@Override
	public <T extends AoGEvent> void registerEventHandler(Class<T> event, String identifier, Function<T, Boolean> handler) {
		if (!_handlers.containsKey(event)) {
			_handlers.put(event, new HashMap<String,Function<? extends AoGEvent, Boolean>>());
		}
		
		Map<String, Function<? extends AoGEvent, Boolean>> handlers = _handlers.get(event);
		handlers.put(identifier, handler);
	}

	@Override
	public <T extends AoGEvent> boolean removeEventHandler(Class<T> event, String identifier) {
		_handlers.get(event).remove(identifier);
		return false;
	}

	public <T extends AoGEvent> void fireEvent(Class<T> event, T params) {
		boolean handled = false;
		for (Function<? extends AoGEvent, Boolean> handler : _handlers.get(event).values()) {			
			try {
				// Hate to have to do it this way...
				// But java says <? extends BaseEvent> is not compatible with <T extends BaseEvent>
				Method theMethod = handler.getClass().getMethods()[0];
				theMethod.setAccessible(true);
				handled = (boolean) theMethod.invoke(handler, params);				
			} catch (SecurityException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (handled) {
				break;
			}
		} ;
	}
}
