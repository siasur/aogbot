package me.siasur.areacommunity.aogbot.config;

public class AoGBotConfig {

	public static AoGBotConfig fromFile(String path) {
		// TODO Actually load the configuration from file...

		AoGBotConfig config = new AoGBotConfig();

		config.getConnection().setHost("127.0.0.1");
		config.getConnection().setPort(10011);

		config.getConnection().getIdentifier().setIdentifier(ServerIdentifierType.ID);
		config.getConnection().getIdentifier().setValue(1);

		config.getAuthentication().setName("serveradmin");
		config.getAuthentication().setPassword("ZO7zZDwy");

		config.getSettings().setDisplayName("Area of Gaming");
		config.getSettings().getHomeChannel().setChannelId(-1);
		config.getSettings().getHomeChannel().setForced(false);

		config.getSettings().getSlowmode().setEnabled(true);
		config.getSettings().getReconnect().setEnabled(true);

		// File configFile = new File(path);
		//
		// if (!configFile.exists()) {
		// throw new FileNotFoundException(path);
		// }
		//
		// if (!configFile.isFile()) {
		// throw new FileNotFoundException(path);
		// }
		//
		// if (!configFile.canRead()) {
		// throw new AccessException(path);
		// }
		//
		// DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		// DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		// Document doc = docBuilder.parse(configFile);

		return config;
	}

	private AuthenticationConfigGroup _authentication;

	private ConnectionConfigGroup _connection;

	private SettingsConfigGroup _settings;

	private AoGBotConfig() {
		_connection = new ConnectionConfigGroup();
		_authentication = new AuthenticationConfigGroup();
		_settings = new SettingsConfigGroup();
	}

	public AuthenticationConfigGroup getAuthentication() {
		return _authentication;
	}

	public ConnectionConfigGroup getConnection() {
		return _connection;
	}

	public SettingsConfigGroup getSettings() {
		return _settings;
	}

}
