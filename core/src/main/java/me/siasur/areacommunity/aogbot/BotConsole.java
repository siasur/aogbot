package me.siasur.areacommunity.aogbot;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import com.github.theholywaffle.teamspeak3.TS3ApiAsync;
import com.github.theholywaffle.teamspeak3.TS3Query;

import me.siasur.areacommunity.aogbot.bridge.IAoGClient;
import me.siasur.areacommunity.aogbot.bridge.IClientManager;
import me.siasur.areacommunity.aogbot.module.IModule;
import me.siasur.areacommunity.aogbot.module.IModuleManager;
import me.siasur.areacommunity.aogbot.utility.ServiceLocator;

/**
 * Provides a way to interact with the bot from the command line.
 *
 */
public class BotConsole implements Runnable {

	private TS3ApiAsync _asyncApi;
	private TS3Query _query;

	/**
	 * Initializes a new instance of the {@link BotConsole}.
	 * 
	 * @param query
	 */
	public BotConsole(TS3Query query) {
		ApplicationState.isRunning = true;
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
		IModuleManager moduleManager = ServiceLocator.getServiceLocator().getService(IModuleManager.class);

		do {
			String userInput = scanner.nextLine();

			if (userInput.startsWith("quit")) {
				ApplicationState.isRunning = false;
				_asyncApi.logout().getUninterruptibly();
				_query.exit();
			}

			if (userInput.startsWith("pokeall ")) {
				clientManager.getAllClients().forEach(c -> {
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
					System.out.println(String.format("%d | %s", client.getId(), client.getNickname()));
				}
			}

			if (userInput.startsWith("whereis ")) {
				String args = userInput.substring(8);
				int clientId = Integer.parseInt(args);

				IAoGClient client = clientManager.getClientById(clientId);
				System.out.println(String.format("Der client \"%s\" (%d) befindet sich im Channel \"%s\".",
						client.getNickname(), clientId, client.getChannel().GetName()));
			}
			
			if (userInput.startsWith("toggle ")) {
				String moduleName = userInput.substring(7);
				
				IModule module = moduleManager.getModule(moduleName);
				
				if (module == null) {
					Logger.getLogger("BotConsole").info("Module " + moduleName + " not found!");
				}
				
				if (module.isEnabled()) {
					module.disable();
					Logger.getLogger("BotConsole").info("Module " + moduleName + " disabled!");
				} else {
					module.enable();
					Logger.getLogger("BotConsole").info("Module " + moduleName + " enabled!");
				}
			}

		} while (ApplicationState.isRunning);

		scanner.close();
	}

}
