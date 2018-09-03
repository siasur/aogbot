package me.siasur.areacommunity.aogbot.bridge;

/**
 * Represents used bandwidth.
 * 
 * @author Mischa
 *
 */
public class BandwithUsage {

	private long _received;

	private long _sent;

	/**
	 * Initializes a new {@link BandwithUsage} instance.
	 * 
	 * @param received
	 *            the amount of received data
	 * @param sent
	 *            the amount of sent data
	 */
	public BandwithUsage(long received, long sent) {
		_received = received;
		_sent = sent;
	}

	/**
	 * The amount of received data.
	 * 
	 * @return
	 */
	public long getReceived() {
		return _received;
	}

	/**
	 * The amount of sent data.
	 * 
	 * @return
	 */
	public long getSent() {
		return _sent;
	}
}
