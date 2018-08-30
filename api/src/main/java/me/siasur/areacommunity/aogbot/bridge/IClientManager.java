package me.siasur.areacommunity.aogbot.bridge;

import java.util.List;

/**
 * Manages a list of all clients currently connected to the Teamspeak3 server.
 * 
 */
public interface IClientManager {

	/**
	 * Gets all clients currently connected to the server.
	 * @return A list with all connected clients.
	 */
	List<IAoGClient> getAllClients();
	
	/**
	 * Gets a client with the given {@code clientId}.
	 * @param clientId The client ID.
	 * @return The client with the given ID.
	 */
	IAoGClient getClientById(int clientId);
	
	/**
	 * Gets a client with the given {@code name}.
	 * @param name The client name.
	 * @return The client with the given name, or {@code null} if no matching user was found.
	 */
	IAoGClient getClientByName(String name);
}
