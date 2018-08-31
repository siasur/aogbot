package me.siasur.areacommunity.aogbot.bridge;

public class BandwithUsage {

	private long _received;
	
	private long _sent;
	
	public BandwithUsage(long received, long sent) {
		_received = received;
		_sent = sent;
	}
	
	public long getReceived() {
		return _received;
	}
	
	public long getSent() {
		return _sent;
	}
}
