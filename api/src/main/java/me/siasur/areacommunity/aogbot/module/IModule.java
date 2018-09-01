package me.siasur.areacommunity.aogbot.module;

public interface IModule {
	
	public void enable();
	
	public void disable();
	
	public String getName();
	
	public boolean isEnabled();
}
