package me.siasur.areacommunity.aogbot.module;

import java.util.HashMap;
import java.util.Map;

public class ModuleManager implements IModuleManager {

	private Map<String, IModule> _modules;

	public ModuleManager() {
		_modules = new HashMap<String, IModule>();
	}

	public void addModule(IModule module) {
		_modules.put(module.getName(), module);
	}

	public void enableAllModules() {
		for (IModule module : _modules.values()) {
			module.enable();
		}
	}

	@Override
	public IModule getModule(String name) {
		return _modules.get(name);
	}

	@Override
	public boolean isModuleEnabled(String name) {
		return _modules.get(name).isEnabled();
	}
}
