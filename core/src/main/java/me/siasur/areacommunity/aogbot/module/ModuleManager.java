package me.siasur.areacommunity.aogbot.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	
	public List<IModule> getAllEnabledModules() {
		List<IModule> modules = new ArrayList<IModule>(_modules.size());
		
		for (IModule module : _modules.values()) {
			if (module.isEnabled()) {
				modules.add(module);
			}
		}
		
		return modules;
	}

	@Override
	public IModule getModule(String name) {
		return _modules.get(name);
	}

	@Override
	public boolean isModuleEnabled(String name) {
		return _modules.get(name).isEnabled();
	}

	public List<IModule> getAllModules() {
		List<IModule> modules = new ArrayList<IModule>(_modules.size());
		
		for (IModule module : _modules.values()) {
			modules.add(module);
		}
		
		return modules;
	}
}
