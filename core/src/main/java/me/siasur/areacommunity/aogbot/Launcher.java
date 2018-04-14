package me.siasur.areacommunity.aogbot;

import java.io.IOException;
import java.util.Arrays;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import me.siasur.areacommunity.aogbot.core.AoGBot;

/***
 * Launcher for the AoGBot
 * 
 */
public class Launcher {

	public static void main(String[] args) throws IOException {
		OptionParser parser = getParser();
		OptionSet rawOptions = parser.parse(args);
		AoGOptions options = AoGOptions.fromOptionSet(rawOptions);
		
		if (!options.isValid()) {
			parser.printHelpOn(System.out);
			System.exit(1);
		}
		
		AoGBot aogBot = new AoGBot(options);
		
		aogBot.connect();
		aogBot.run();
	}
	
	/**
	 * Configures the parser for the arguments
	 * 
	 * @return The configured {@link OptionParser}
	 */
	private static OptionParser getParser() {
		OptionParser parser = new OptionParser();
		
		parser.acceptsAll(Arrays.asList("h", "host"), "address of the teamspeak server")
		.withRequiredArg()
		.ofType(String.class)
		.describedAs("ip-address")
		.isRequired();
		
		parser.acceptsAll(Arrays.asList("u", "user"), "Username for the query login")
		.withRequiredArg()
		.ofType(String.class)
		.describedAs("username")
		.isRequired();
		
		parser.acceptsAll(Arrays.asList("p", "password", "passwd"), "Password for the query login")
		.withRequiredArg()
		.ofType(String.class)
		.describedAs("password")
		.isRequired();
		
		parser.acceptsAll(Arrays.asList("i", "id"), "The id of the teamspeak instance")
		.withRequiredArg()
		.ofType(Integer.class)
		.describedAs("server-id")
		.isRequired();
		
		parser.acceptsAll(Arrays.asList("n", "name"), "The name that will be visible on the server")
		.withRequiredArg()
		.ofType(String.class)
		.describedAs("display-name")
		.isRequired();
		
		parser.accepts("slowmode", "Should the system run in slow mode");
		
		return parser;
	}
	
}
