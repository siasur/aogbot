package me.siasur.areacommunity.aogbot.event;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import me.siasur.areacommunity.aogbot.ApplicationState;

public class AoGHandler implements Runnable {

	BlockingQueue<AoGBaseEvent> _queue;
	List<EventHandleRequest> _handlers;
	
	protected AoGHandler(BlockingQueue<AoGBaseEvent> queue, List<EventHandleRequest> handlers) {
		_queue = queue;
		_handlers = handlers;
	}
	
	@Override
	public void run() {
		AoGBaseEvent event = null;
		
		do {
			try {
				event = _queue.poll(10, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (event == null) {
				continue;
			}
			
			for (EventHandleRequest eventHandleRequest : _handlers) {
				Class<?> eventClass = event.getClass();
				
				if (eventHandleRequest.getEvent().equals(eventClass)
						&& eventHandleRequest.getOwner().isEnabled()) {
					
					if (eventHandleRequest.handle(event)) {
						break;
					}
				}
			}
			
		} while(ApplicationState.isRunning);
	}

}
