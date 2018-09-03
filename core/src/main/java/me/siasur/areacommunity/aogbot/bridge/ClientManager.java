package me.siasur.areacommunity.aogbot.bridge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.github.theholywaffle.teamspeak3.TS3Api;

/**
 * Manages a list of all clients currently connected to the Teamspeak3 server.
 * 
 */
public class ClientManager implements IClientManager {

	HashMap<Integer, AoGClient> _clients;

	TS3Api _ts3Api;

	/**
	 * Initializes a new instance of the {@link ClientManager}
	 * 
	 * @param ts3Api
	 */
	public ClientManager(TS3Api ts3Api) {
		_clients = new HashMap<Integer, AoGClient>();
		_ts3Api = ts3Api;
	}

	/**
	 * Gets all clients currently connected to the server.
	 * 
	 * @return A list with all connected clients.
	 */
	@Override
	public List<IAoGClient> getAllClients() {
		Collection<AoGClient> base = _clients.values();
		ArrayList<IAoGClient> aogClients = new ArrayList<>(base.size());

		for (AoGClient aogClient : base) {
			if (aogClient.getClientType() == ClientType.VOICE) {
				aogClient.refresh();
				aogClients.add(aogClient);
			}
		}

		return aogClients;
	}

	/**
	 * Gets a client with the given {@code clientId}.
	 * 
	 * @param clientId
	 *            The client ID.
	 * @return The client with the given ID.
	 */
	@Override
	public IAoGClient getClientById(int clientId) {
		AoGClient client = _clients.get(clientId);

		if (client != null) {
			client.refresh();
		}

		return client;
	}

	/**
	 * Gets a client with the given {@code name}.
	 * 
	 * @param name
	 *            The client name.
	 * @return The client with the given name, or {@code null} if no matching user
	 *         was found.
	 */
	@Override
	public IAoGClient getClientByName(String name) {
		for (AoGClient client : _clients.values()) {
			if (client.getNickname().contains(name)) {
				client.refresh();

				return client;
			}
		}

		return null;
	}

	/**
	 * Adds a client to the manager.
	 * 
	 * @param clientId
	 *            The Client ID.
	 */
	public AoGClient manageClient(int clientId) {
		AoGClient aogClient = new AoGClient(clientId, _ts3Api);
		aogClient.refresh();
		_clients.put(clientId, aogClient);
		return aogClient;
	}

	/**
	 * Refreshes the complete list of clients
	 */
	public void refreshList() {
		_clients.clear();
		_ts3Api.getClients().forEach(c -> {
			manageClient(c.getId());
		});
	}

	/**
	 * Removes a client from the manager
	 * 
	 * @param clientId
	 *            The client ID.
	 */
	public AoGClient unmanageClient(int clientId) {
		return _clients.remove(clientId);
	}
}
