package me.siasur.areacommunity.aogbot;

import java.util.Arrays;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import me.siasur.areacommunity.aogbot.config.AoGBotConfig;

/***
 * Launcher for the AoGBot
 * 
 */
public class Launcher {

	/**
	 * Entry point
	 * 
	 * @param args The command line arguments
	 */
	public static void main(String[] args) {
		OptionParser optParser = getParser();
		OptionSet options = optParser.parse(args);
		
		String configPath = (String)options.valueOf("config");
		AoGBotConfig config = AoGBotConfig.fromFile(configPath);
		
		AoGBot aogBot = new AoGBot(config);
		aogBot.run();
	}
	
	/**
	 * Configures the parser for the arguments
	 * 
	 * @return The configured {@link OptionParser}
	 */
	private static OptionParser getParser() {
		OptionParser parser = new OptionParser();
		
		parser.acceptsAll(Arrays.asList("c", "config", "cfg", "cofiguration"), "Path to the configuration file.")
		.withRequiredArg()
		.ofType(String.class)
		.describedAs("path")
		.isRequired();
		
//		parser.acceptsAll(Arrays.asList("h", "host"), "address of the teamspeak server")
//		.withRequiredArg()
//		.ofType(String.class)
//		.describedAs("ip-address")
//		.isRequired();
//		
//		parser.acceptsAll(Arrays.asList("u", "user"), "Username for the query login")
//		.withRequiredArg()
//		.ofType(String.class)
//		.describedAs("username")
//		.isRequired();
//		
//		parser.acceptsAll(Arrays.asList("p", "password", "passwd"), "Password for the query login")
//		.withRequiredArg()
//		.ofType(String.class)
//		.describedAs("password")
//		.isRequired();
//		
//		parser.acceptsAll(Arrays.asList("i", "id"), "The id of the teamspeak instance")
//		.withRequiredArg()
//		.ofType(Integer.class)
//		.describedAs("server-id")
//		.isRequired();
//		
//		parser.acceptsAll(Arrays.asList("n", "name"), "The name that will be visible on the server")
//		.withRequiredArg()
//		.ofType(String.class)
//		.describedAs("display-name")
//		.isRequired();
//		
//		parser.accepts("slowmode", "Should the system run in slow mode");
		
		return parser;
	}
	
}
