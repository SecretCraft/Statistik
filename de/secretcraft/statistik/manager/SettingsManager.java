package de.secretcraft.statistik.manager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.Configuration;

import de.secretcraft.statistik.Statistik;

public class SettingsManager {

	private Statistik plugin;
	private Configuration config;
	
	private Boolean debug;
	
	private String host;
	private String database;
	private String user;
	private String password;
	
	private Integer saveTime;
	
	public SettingsManager() {
		plugin = Statistik.getInstance();
		config = plugin.getConfig();
		
		addDeafaults();
		load();
	}
	
	
	public void addDeafaults() {
		config.addDefault("Settings.Debug", false);
		
		config.addDefault("Settings.MySQL.Host", "localhost");
		config.addDefault("Settings.MySQL.Database", "stats");
		config.addDefault("Settings.MySQL.User", "stats");
		config.addDefault("Settings.MySQL.Password", "password");
		
		config.addDefault("Settings.SaveTime", "60");
		
		config.options().copyDefaults(true);
		plugin.saveConfig();
	}
	
	
	public void load() {
		plugin.reloadConfig();
		
		setDebug( config.getBoolean("Settings.Debug") );
		
		setHost( config.getString("Settings.MySQL.Host") );
		setDatabase( config.getString("Settings.MySQL.Database") );
		setUser( config.getString("Settings.MySQL.User") );
		setPassword( config.getString("Settings.MySQL.Password") );
		
		setSaveTime( config.getInt("Settings.SaveTime") );
	}	
	
	public void save() {
		plugin.reloadConfig();
		this.config = plugin.getConfig();
		
		// Wird zurzeit nicht gebraucht
		
		plugin.saveConfig();
	}

	public Boolean getDebug() {
		return debug;
	}

	public void setDebug(Boolean debug) {
		this.debug = debug;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public Integer getSaveTime() {
		return saveTime;
	}


	public void setSaveTime(Integer saveTime) {
		this.saveTime = saveTime;
	}
}
