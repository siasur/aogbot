package me.siasur.areacommunity.aogbot.module;

public interface IModule {

	public void disable();

	public void enable();

	public String getName();

	public boolean isEnabled();
}
