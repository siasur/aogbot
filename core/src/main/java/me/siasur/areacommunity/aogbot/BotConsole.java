package me.siasur.areacommunity.aogbot;

import java.util.List;
import java.util.Scanner;

import com.github.theholywaffle.teamspeak3.TS3ApiAsync;
import com.github.theholywaffle.teamspeak3.TS3Query;

import me.siasur.areacommunity.aogbot.bridge.IAoGClient;
import me.siasur.areacommunity.aogbot.bridge.IClientManager;
import me.siasur.areacommunity.aogbot.utility.ServiceLocator;

/**
 * Provides a way to interact with the bot from the command line.
 *
 */
public class BotConsole implements Runnable {

	private TS3ApiAsync _asyncApi;
	private TS3Query _query;
	private boolean _running;

	/**
	 * Initializes a new instance of the {@link BotConsole}.
	 * 
	 * @param query
	 */
	public BotConsole(TS3Query query) {
		_running = true;
		_query = query;
		_asyncApi = query.getAsyncApi();
	}

	/**
	 * Lets the magic happen...
	 */
	@Override
	public void run() {
		Scanner scanner = new Scanner(System.in);

		IClientManager clientManager = ServiceLocator.getServiceLocator().getService(IClientManager.class);

		do {
			String userInput = scanner.nextLine();

			if (userInput.startsWith("quit")) {
				_running = false;
				_asyncApi.logout().getUninterruptibly();
				_query.exit();
			}

			if (userInput.startsWith("pokeall ")) {
				clientManager.getAllVoiceClients().forEach(c -> {
					c.poke(userInput.substring(userInput.indexOf(" ") + 1));
				});
			}

			if (userInput.startsWith("poke ")) {
				String args = userInput.substring(5);
				String message = args.substring(args.indexOf(" ") + 1);
				int clientId = Integer.parseInt(args.substring(0, args.indexOf(" ")));

				clientManager.getClientById(clientId).poke(message);
			}

			if (userInput.startsWith("clientlist")) {
				List<IAoGClient> clients = clientManager.getAllClients();
				for (IAoGClient client : clients) {
					System.out.println(String.format("%s: %d | %s", client.getClientType() ,client.getId(), client.getNickname()));
				}
			}

			if (userInput.startsWith("whereis ")) {
				String args = userInput.substring(8);
				int clientId = Integer.parseInt(args);

				IAoGClient client = clientManager.getClientById(clientId);
				System.out.println(String.format("Der client \"%s\" (%d) befindet sich im Channel \"%s\".",
						client.getNickname(), clientId, client.getChannel().GetName()));
			}

		} while (_running);

		scanner.close();
	}

}
