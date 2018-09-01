package me.siasur.areacommunity.aogbot.module;

public abstract class BaseModule implements IModule {

	private boolean _isEnabled;
	
	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}
	
	@Override
	public final boolean isEnabled() {
		return _isEnabled;
	}
	
	@Override
	public void enable() {
		_isEnabled = true;
		onEnable();
	}
	
	@Override
	public void disable() {
		_isEnabled = false;
		onDisable();
		
	}
	
	protected abstract void onEnable();
	
	protected abstract void onDisable();
	
}
