package me.siasur.areacommunity.aogbot.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import me.siasur.areacommunity.aogbot.module.IModule;

public class EventHandleRequest {

	private IModule _owner;
	
	private Priority _priority;
	
	private Class<?> _event;
	
	private Method _handler;
	
	public boolean handle(Object args) {
		boolean returnValue = false;
		
		try {
			_handler.setAccessible(true);
			Object handled = _handler.invoke(_owner, args);
			
			if (handled != null && handled.getClass() == Boolean.class) {
				returnValue = (boolean) handled;
			}
		} catch (IllegalAccessException e) {
			Logger.getLogger("EventSystem").log(Level.SEVERE, "Could not get access to the EventListener", e);
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			Logger.getLogger("EventSystem").log(Level.FINE, "Event got routed to wrong handler", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnValue;
	}
	
	public IModule getOwner() {
		return _owner;
	}
	
	public Class<?> getEvent() {
		return _event;
	}
	
	public Priority getPriority() {
		return _priority;
	}
	
	public EventHandleRequest(IModule owner, Priority priortiy, Method handler) {
		_owner = owner;
		_priority = priortiy;
		_handler = handler;
		
		_event = handler.getParameterTypes()[0];
	}
}
