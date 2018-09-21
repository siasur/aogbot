package me.siasur.areacommunity.aogbot.module;

import java.util.logging.Logger;

public abstract class BaseModule implements IModule {

	private boolean _isEnabled;
	private IModuleBridge _bridge;
	Logger _logger;
	
	@Override
	public void disable() {
		_isEnabled = false;
		onDisable();

	}

	@Override
	public void enable() {
		_isEnabled = true;
		onEnable();
	}
	
	public final void setModuleBridge(IModuleBridge bridge) {
		_bridge = bridge;
	}
	
	public final IModuleBridge getModuleBridge() {
		return _bridge;
	}

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	@Override
	public final boolean isEnabled() {
		return _isEnabled;
	}
	
	public final Logger getLogger() {
		if (_logger == null) {
			_logger = Logger.getLogger(this.getName());
		}
		
		return _logger;
	}

	protected abstract void onDisable();

	protected abstract void onEnable();
	
//	protected final <T extends AoGEvent> void addEventHandler(Function<T, Boolean> callback) {
//		addEventHandler(callback, Priority.NORMAL);
//	}
//	
//	protected final <T extends AoGEvent> void addEventHandler(Function<T, Boolean> callback, Priority priority) {
//		_bridge.addEventHandler(this, priority, callback);
//	}

}
