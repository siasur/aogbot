package me.siasur.areacommunity.aogbot.bridge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

/**
 * Manages a list of all clients currently connected to the Teamspeak3 server.
 * 
 */
public class ClientManager implements IClientManager {

	TS3Api _ts3Api;
	
	HashMap<Integer, AoGClient> _clients;
	
	/**
	 * Creates a new instance of the {@link ClientManager}
	 * @param ts3Api
	 */
	public ClientManager(TS3Api ts3Api) {
		_clients = new HashMap<Integer, AoGClient>();
		_ts3Api = ts3Api;
	}
	
	/**
	 * Refreshes the complete list of clients
	 */
	public void refreshAll() {
		_clients.clear();
		_ts3Api.getClients().forEach(c -> {manageClient(c);});
	}
	
	/**
	 * Adds a client to the manager
	 * @param client The to be added {@link Client}
	 */
	public void manageClient(Client client) {
		AoGClient aogClient = new AoGClient(client, _ts3Api);
		_clients.put(client.getId(), aogClient);
	}
	
	/**
	 * Removes a client from the manager
	 * @param clientId The client ID.
	 */
	public void unmanageClient(int clientId) {
		_clients.remove(clientId);
	}

	/**
	 * Gets all clients currently connected to the server.
	 * @return A list with all connected clients.
	 */
	@Override
	public List<IAoGClient> getAllClients() {
		Collection<AoGClient> base = _clients.values();
		ArrayList<IAoGClient> aogClients = new ArrayList<>(base.size());
		
		for (AoGClient aogClient : base) {
			if (aogClient.getClientType() == ClientType.VOICE) {
				aogClients.add((IAoGClient) aogClient);
			}
		}
		
		return aogClients;
	}

	/**
	 * Gets a client with the given {@code clientId}.
	 * @param clientId The client ID.
	 * @return The client with the given ID.
	 */
	@Override
	public IAoGClient getClientById(int clientId) {
		return (IAoGClient) _clients.get(clientId);
	}

	/**
	 * Gets a client with the given {@code name}.
	 * @param name The client name.
	 * @return The client with the given name, or {@code null} if no matching user was found.
	 */
	@Override
	public IAoGClient getClientByName(String name) {
		for (AoGClient client : _clients.values()) {
			if (client.getNickname().contains(name)) {
				return (IAoGClient) client;
			}
		}
		
		return null;
	}
}
