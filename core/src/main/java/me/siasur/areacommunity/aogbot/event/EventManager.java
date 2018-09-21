package me.siasur.areacommunity.aogbot.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import me.siasur.areacommunity.aogbot.module.IModule;
import me.siasur.areacommunity.aogbot.module.ModuleManager;

public class EventManager {

	private ModuleManager _moduleManager;
	private Thread _handlerThread;
	
	BlockingQueue<AoGBaseEvent> _eventQueue = new ArrayBlockingQueue<>(255, true);
	
	public EventManager(ModuleManager moduleManager) {
		_moduleManager = moduleManager;
	}
	
	private List<EventHandleRequest> getHandlerRequests() {
		List<EventHandleRequest> requests = new ArrayList<>();
		
		List<IModule> modules = _moduleManager.getAllModules();
		
		for (IModule module : modules) {
			Class<?> moduleClass = module.getClass();
			for (Method method : moduleClass.getDeclaredMethods()) {
				if (!method.isAnnotationPresent(EventHandler.class)) {
					// Method is not an event handler
					continue;
				}
				
				EventHandler handlerAnnotation = method.getAnnotation(EventHandler.class);
				Priority priority = handlerAnnotation.priority();
				
				EventHandleRequest request = new EventHandleRequest(module, priority, method);
				
				requests.add(request);
			}
		}
		
		Collections.sort(requests, new Comparator<EventHandleRequest>() {

			@Override
			public int compare(EventHandleRequest o1, EventHandleRequest o2) {
				int level1 = o1.getPriority().getLevel();
				int level2 = o2.getPriority().getLevel();
				
				if (level1 < level2) {
					return 1;
				}
				if (level1 > level2) {
					return -1;
				}
				
				return 0;
			}
			
		});
		
		return requests;
	}
	
	public boolean queueEvent(AoGBaseEvent event) {
		return _eventQueue.offer(event);
	}
	
	public void beginHandle() {
		if (_handlerThread == null) {
			_handlerThread = new Thread(new AoGHandler(_eventQueue, getHandlerRequests()));
			_handlerThread.start();
		}
	}
}
