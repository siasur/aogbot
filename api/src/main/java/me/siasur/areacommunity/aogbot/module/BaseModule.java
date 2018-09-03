package me.siasur.areacommunity.aogbot.module;

public abstract class BaseModule implements IModule {

	private boolean _isEnabled;

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

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	@Override
	public final boolean isEnabled() {
		return _isEnabled;
	}

	protected abstract void onDisable();

	protected abstract void onEnable();

}
