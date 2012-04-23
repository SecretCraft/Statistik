package de.secretcraft.statistik.manager;

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
	
	
	// For / Points
	private Integer playTimeFor;
	private Integer playTimePoints;
	
	private Integer blockBreakFor;
	private Integer blockBreakPoints;
	private Integer blockPlaceFor;
	private Integer blockPlacePoints;
	
	private Integer playerKillFor;
	private Integer playerKillPoints;
	private Integer playerDeathFor;
	private Integer playerDeathPoints;
	
	private Integer monsterKillFor;
	private Integer monsterKillPoints;
	private Integer monsterDeathFor;
	private Integer monsterDeathPoints;
	
	private Integer otherDeathFor;
	private Integer otherDeathPoints;
	private Integer fishingFor;
	private Integer fishingPoints;
	
	private Integer blockMoveFor;
	private Integer blockMovePoints;
	private Integer blockFallFor;
	private Integer blockFallPoints;
	
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
		
		config.addDefault("Settings.Points.PlayTime.For", 6000);
		config.addDefault("Settings.Points.PlayTime.Points", 5);
		
		config.addDefault("Settings.Points.BlockBreak.For", 200);
		config.addDefault("Settings.Points.BlockBreak.Points", 5);
		config.addDefault("Settings.Points.BlockPlace.For", 200);
		config.addDefault("Settings.Points.BlockPlace.Points", 5);
		
		config.addDefault("Settings.Points.PlayerKill.For", 200);
		config.addDefault("Settings.Points.PlayerKill.Points", 5);
		config.addDefault("Settings.Points.PlayerDeath.For", 200);
		config.addDefault("Settings.Points.PlayerDeath.Points", 5);
		
		config.addDefault("Settings.Points.MonsterKill.For", 200);
		config.addDefault("Settings.Points.MonsterKill.Points", 5);
		config.addDefault("Settings.Points.MonsterDeath.For", 200);
		config.addDefault("Settings.Points.MonsterDeath.Points", 5);
		
		config.addDefault("Settings.Points.OtherDeath.For", 200);
		config.addDefault("Settings.Points.OtherDeath.Points", 5);
		config.addDefault("Settings.Points.Fishing.For", 200);
		config.addDefault("Settings.Points.Fishing.Points", 5);
		
		config.addDefault("Settings.Points.BlockMove.For", 200);
		config.addDefault("Settings.Points.BlockMove.Points", 5);
		config.addDefault("Settings.Points.BlockFall.For", 200);
		config.addDefault("Settings.Points.BlockFall.Points", 5);
		
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
		config.addDefault("Ranks.1.Points", 200);
		
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
		config.addDefault("Ranks.2.Points", 200);
		
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
		config.addDefault("Ranks.3.Points", 200);
		
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
	
		setPlayTimeFor( config.getInt("Settings.Points.PlayTime.For") );
		setPlayTimePoints( config.getInt("Settings.Points.PlayTime.Points") );
		
		setBlockBreakFor( config.getInt("Settings.Points.BlockBreak.For") );
		setBlockBreakPoints( config.getInt("Settings.Points.BlockBreak.Points") );
		setBlockPlaceFor( config.getInt("Settings.Points.BlockPlace.For") );
		setBlockPlacePoints( config.getInt("Settings.Points.BlockPlace.Points") );
		
		setPlayerKillFor( config.getInt("Settings.Points.PlayerKill.For") );
		setPlayerKillPoints( config.getInt("Settings.Points.PlayerKill.Points") );
		setPlayerDeathFor( config.getInt("Settings.Points.PlayerDeath.For") );
		setPlayerDeathPoints( config.getInt("Settings.Points.PlayerDeath.Points") );
		
		setMonsterKillFor( config.getInt("Settings.Points.MonsterKill.For") );
		setMonsterKillPoints( config.getInt("Settings.Points.MonsterKill.Points") );
		setMonsterDeathFor( config.getInt("Settings.Points.MonsterDeath.For") );
		setMonsterDeathPoints( config.getInt("Settings.Points.MonsterDeath.Points") );
		
		setOtherDeathFor( config.getInt("Settings.Points.OtherDeath.For") );
		setOtherDeathPoints( config.getInt("Settings.Points.OtherDeath.Points") );
		
		setFishingFor( config.getInt("Settings.Points.Fishing.For") );
		setFishingPoints( config.getInt("Settings.Points.Fishing.Points") );
		
		setBlockMoveFor( config.getInt("Settings.Points.BlockMove.For") );
		setBlockMovePoints( config.getInt("Settings.Points.BlockMove.Points") );
		setBlockFallFor( config.getInt("Settings.Points.BlockFall.For") );
		setBlockFallPoints( config.getInt("Settings.Points.BlockFall.Points") );
		
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


	public Integer getBlockBreakFor() {
		return blockBreakFor;
	}


	public void setBlockBreakFor(Integer blockBreakFor) {
		this.blockBreakFor = blockBreakFor;
	}


	public Integer getBlockBreakPoints() {
		return blockBreakPoints;
	}


	public void setBlockBreakPoints(Integer blockBreakPoints) {
		this.blockBreakPoints = blockBreakPoints;
	}


	public Integer getBlockPlaceFor() {
		return blockPlaceFor;
	}


	public void setBlockPlaceFor(Integer blockPlaceFor) {
		this.blockPlaceFor = blockPlaceFor;
	}


	public Integer getBlockPlacePoints() {
		return blockPlacePoints;
	}


	public void setBlockPlacePoints(Integer blockPlacePoints) {
		this.blockPlacePoints = blockPlacePoints;
	}


	public Integer getPlayerKillFor() {
		return playerKillFor;
	}


	public void setPlayerKillFor(Integer playerKillFor) {
		this.playerKillFor = playerKillFor;
	}


	public Integer getPlayerKillPoints() {
		return playerKillPoints;
	}


	public void setPlayerKillPoints(Integer playerKillPoints) {
		this.playerKillPoints = playerKillPoints;
	}


	public Integer getPlayerDeathFor() {
		return playerDeathFor;
	}


	public void setPlayerDeathFor(Integer playerDeathFor) {
		this.playerDeathFor = playerDeathFor;
	}


	public Integer getPlayerDeathPoints() {
		return playerDeathPoints;
	}


	public void setPlayerDeathPoints(Integer playerDeathPoints) {
		this.playerDeathPoints = playerDeathPoints;
	}


	public Integer getMonsterKillFor() {
		return monsterKillFor;
	}


	public void setMonsterKillFor(Integer monsterKillFor) {
		this.monsterKillFor = monsterKillFor;
	}


	public Integer getMonsterKillPoints() {
		return monsterKillPoints;
	}


	public void setMonsterKillPoints(Integer monsterKillPoints) {
		this.monsterKillPoints = monsterKillPoints;
	}


	public Integer getMonsterDeathFor() {
		return monsterDeathFor;
	}


	public void setMonsterDeathFor(Integer monsterDeathFor) {
		this.monsterDeathFor = monsterDeathFor;
	}


	public Integer getMonsterDeathPoints() {
		return monsterDeathPoints;
	}


	public void setMonsterDeathPoints(Integer monsterDeathPoints) {
		this.monsterDeathPoints = monsterDeathPoints;
	}


	public Integer getOtherDeathFor() {
		return otherDeathFor;
	}


	public void setOtherDeathFor(Integer otherDeathFor) {
		this.otherDeathFor = otherDeathFor;
	}


	public Integer getOtherDeathPoints() {
		return otherDeathPoints;
	}


	public void setOtherDeathPoints(Integer otherDeahPoints) {
		this.otherDeathPoints = otherDeahPoints;
	}


	public Integer getFishingFor() {
		return fishingFor;
	}


	public void setFishingFor(Integer fishingFor) {
		this.fishingFor = fishingFor;
	}


	public Integer getFishingPoints() {
		return fishingPoints;
	}


	public void setFishingPoints(Integer fishingPoints) {
		this.fishingPoints = fishingPoints;
	}


	public Integer getBlockMoveFor() {
		return blockMoveFor;
	}


	public void setBlockMoveFor(Integer blockMoveFor) {
		this.blockMoveFor = blockMoveFor;
	}


	public Integer getBlockMovePoints() {
		return blockMovePoints;
	}


	public void setBlockMovePoints(Integer blockMovePoints) {
		this.blockMovePoints = blockMovePoints;
	}


	public Integer getBlockFallFor() {
		return blockFallFor;
	}


	public void setBlockFallFor(Integer blockFallFor) {
		this.blockFallFor = blockFallFor;
	}


	public Integer getBlockFallPoints() {
		return blockFallPoints;
	}


	public void setBlockFallPoints(Integer blockFallPoints) {
		this.blockFallPoints = blockFallPoints;
	}


	public Integer getPlayTimeFor() {
		return playTimeFor;
	}


	public void setPlayTimeFor(Integer playTimeFor) {
		this.playTimeFor = playTimeFor;
	}


	public Integer getPlayTimePoints() {
		return playTimePoints;
	}


	public void setPlayTimePoints(Integer playTimePoints) {
		this.playTimePoints = playTimePoints;
	}
	
	
	
}
