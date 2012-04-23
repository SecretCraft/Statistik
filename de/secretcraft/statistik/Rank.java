package de.secretcraft.statistik;

import org.bukkit.configuration.Configuration;

public class Rank {
	
	private Integer id;
	
	private String name;
	private String tag;
	
	private Integer playTime;
	
	private Integer blockBreak;
	private Integer blockPlace;
	
	private Integer playerKill;
	private Integer playerDeath;
	
	private Integer monsterKill;
	private Integer monsterDeath;
	
	private Integer otherDeath;
	
	private Integer fishing;
	
	private Integer blockMove;
	private Integer blockFall;
	
	private Integer points;
	
	public Rank( Integer id, Configuration config ) {
		
		setId(id);
		
		load( config );	
	}
	
	public void load( Configuration config ) {
		
		
		setName( config.getString("Ranks." + id + ".Name") );
		setTag( config.getString("Ranks." + id + ".Tag") );
		
		setPlayTime( config.getInt("Ranks." + id + ".PlayTime") );
		
		setBlockBreak( config.getInt("Ranks." + id + ".BlockBreak") );
		setBlockPlace( config.getInt("Ranks." + id + ".BlockPlace") );
		
		setPlayerKill( config.getInt("Ranks." + id + ".PlayerKill") );
		setPlayerDeath( config.getInt("Ranks." + id + ".PlayerDeath") );
		
		setMonsterKill( config.getInt("Ranks." + id + ".MonsterKill") );
		setMonsterDeath( config.getInt("Ranks." + id + ".MonsterDeath") );
		
		setOtherDeath( config.getInt("Ranks." + id + ".OtherDeath") );
		setFishing( config.getInt("Ranks." + id + ".Fishing") );
		
		setBlockMove( config.getInt("Ranks." + id + ".BlockMove") );
		setBlockFall( config.getInt("Ranks." + id + ".BlockFall") );
		
		setPoints( config.getInt("Ranks." + id + ".Points") );
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer getPlayTime() {
		return playTime;
	}

	public void setPlayTime(Integer playTime) {
		this.playTime = playTime;
	}

	public Integer getBlockBreak() {
		return blockBreak;
	}

	public void setBlockBreak(Integer blockBreak) {
		this.blockBreak = blockBreak;
	}

	public Integer getBlockPlace() {
		return blockPlace;
	}

	public void setBlockPlace(Integer blockPlace) {
		this.blockPlace = blockPlace;
	}

	public Integer getPlayerKill() {
		return playerKill;
	}

	public void setPlayerKill(Integer playerKill) {
		this.playerKill = playerKill;
	}

	public Integer getPlayerDeath() {
		return playerDeath;
	}

	public void setPlayerDeath(Integer playerDeath) {
		this.playerDeath = playerDeath;
	}

	public Integer getMonsterKill() {
		return monsterKill;
	}

	public void setMonsterKill(Integer monsterKill) {
		this.monsterKill = monsterKill;
	}

	public Integer getMonsterDeath() {
		return monsterDeath;
	}

	public void setMonsterDeath(Integer monsterDeath) {
		this.monsterDeath = monsterDeath;
	}

	public Integer getOtherDeath() {
		return otherDeath;
	}

	public void setOtherDeath(Integer otherDeath) {
		this.otherDeath = otherDeath;
	}

	public Integer getFishing() {
		return fishing;
	}

	public void setFishing(Integer fishing) {
		this.fishing = fishing;
	}

	public Integer getBlockMove() {
		return blockMove;
	}

	public void setBlockMove(Integer blockMove) {
		this.blockMove = blockMove;
	}

	public Integer getBlockFall() {
		return blockFall;
	}

	public void setBlockFall(Integer blockFall) {
		this.blockFall = blockFall;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}	
	
}
