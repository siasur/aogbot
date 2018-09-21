package me.siasur.areacommunity.aogbot.event;

public enum Priority {
	
	LOWEST(0),
	LOW(5),
	NORMAL(10),
	HIGH(15),
	HIGHEST(20);
	
	private final int _level;
	
	Priority(int level) {
		_level = level;
	}
	
	public int getLevel() {
		return _level;
	}
	
}
