package me.siasur.areacommunity.aogbot.module;

public abstract class BaseModule implements IModule {

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}
	
}
