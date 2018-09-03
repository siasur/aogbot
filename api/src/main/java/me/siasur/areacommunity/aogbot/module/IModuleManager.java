package me.siasur.areacommunity.aogbot.module;

/**
 * Defines functions for the interaction with the ModuleManager.
 *
 */
public interface IModuleManager {

	/**
	 * Gets the module with the given name
	 * 
	 * @param name
	 *            the name
	 * @return the {@link IModule} if it's know to the manager, null otherwise
	 */
	public IModule getModule(String name);

	/**
	 * Gets a value indicating whether the module is enabled.
	 * 
	 * @return {@code true}, if the module is enabled, {@code false} otherwise.
	 * 
	 * @see #setModuleState(boolean enabled)
	 */
	public boolean isModuleEnabled(String name);

}
