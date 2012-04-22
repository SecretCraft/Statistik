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
		
		config.addDefault("Settings.SaveTime", "1200");
		
		// Beispiel Ranks 1
		config.addDefault("Ranks.1.Name", "Ranks1");
		config.addDefault("Ranks.1.Tag", "RA1");
		config.addDefault("Ranks.1.PlayTime", 100);
		config.addDefault("Ranks.1.BlockBreak", 5);
		config.addDefault("Ranks.1.BlockPlace", 5);
		config.addDefault("Ranks.1.PlayerKill", 5);
		config.addDefault("Ranks.1.PlayerDeath", 5);
		config.addDefault("Ranks.1.MonsterKill", 5);
		config.addDefault("Ranks.1.MonsterDeath", 5);
		config.addDefault("Ranks.1.OtherDeath", 5);
		config.addDefault("Ranks.1.Fishing", 5);
		config.addDefault("Ranks.1.BlockMove",5);
		config.addDefault("Ranks.1.BlockFall", 5);
		
		// Beispiel Ranks 2
		config.addDefault("Ranks.2.Name", "Ranks2");
		config.addDefault("Ranks.2.Tag", "RA2");
		config.addDefault("Ranks.2.PlayTime", 200);
		config.addDefault("Ranks.2.BlockBreak", 10);
		config.addDefault("Ranks.2.BlockPlace", 10);
		config.addDefault("Ranks.2.PlayerKill", 10);
		config.addDefault("Ranks.2.PlayerDeath", 10);
		config.addDefault("Ranks.2.MonsterKill", 10);
		config.addDefault("Ranks.2.MonsterDeath", 10);
		config.addDefault("Ranks.2.OtherDeath", 10);
		config.addDefault("Ranks.2.Fishing", 10);
		config.addDefault("Ranks.2.BlockMove", 10);
		config.addDefault("Ranks.2.BlockFall", 10);
		
		// Beispiel Ranks 3
		config.addDefault("Ranks.3.Name", "Ranks3");
		config.addDefault("Ranks.3.Tag", "RA3");
		config.addDefault("Ranks.3.PlayTime", 20000);
		config.addDefault("Ranks.3.BlockBreak", 800);
		config.addDefault("Ranks.3.BlockPlace", 800);
		config.addDefault("Ranks.3.PlayerKill", 800);
		config.addDefault("Ranks.3.PlayerDeath", 800);
		config.addDefault("Ranks.3.MonsterKill", 800);
		config.addDefault("Ranks.3.MonsterDeath", 800);
		config.addDefault("Ranks.3.OtherDeath", 800);
		config.addDefault("Ranks.3.Fishing", 800);
		config.addDefault("Ranks.3.BlockMove", 800);
		config.addDefault("Ranks.3.BlockFall", 800);
		
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
