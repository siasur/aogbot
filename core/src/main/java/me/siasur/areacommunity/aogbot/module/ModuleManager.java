package me.siasur.areacommunity.aogbot.module;

import java.util.ArrayList;
import java.util.List;

import me.siasur.areacommunity.aogbot.module.IModule;

public class ModuleManager {

	private List<IModule> _modules;
	
	public ModuleManager() {
		_modules = new ArrayList<IModule>();
	}
	
	public void addModule(IModule module) {
		_modules.add(module);
	}

	public void enableAllModules() {
		for (IModule module : _modules) {
			module.enable();
		}
	}
}
