package de.secretcraft.statistik;

import java.sql.SQLException;
import java.util.ArrayList;

import net.windwaker.sql.Table;

public class StatistikPlayer {
	
	private Table table;
	
	private String name;
	
	private Integer loginTime;
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
	
	private ArrayList<Rank> aviableRanks;
	
	public StatistikPlayer( Table table, String name ) {
		this.table = table;
		this.name = name;
		
		aviableRanks = new ArrayList<Rank>();
		
		load();
	}
	
	public void load() {
		
		try {
			
			if ( table.containsValue("name", name) ) {
				
				setPlayTime( table.getInteger("play_time", "name", name, 0) );
				
				// Block
				setBlockBreak( table.getInteger("block_break", "name", name, 0) );
				setBlockPlace( table.getInteger("block_place", "name", name, 0) );
				
				// Player
				setPlayerKill( table.getInteger("player_kill", "name", name, 0) );
				setPlayerDeath( table.getInteger("player_death", "name", name, 0) );
				
				// Monster
				setMonsterKill( table.getInteger("monster_kill", "name", name, 0) );
				setMonsterDeath( table.getInteger("monster_death", "name", name, 0) );
				
				setOtherDeath( table.getInteger("other_death", "name", name, 0) );
				setFishing( table.getInteger("fishing", "name", name, 0) );
				
				// Blocks Move and Fall
				setBlockMove( table.getInteger("block_move", "name", name, 0) );
				setBlockFall( table.getInteger("block_fall", "name", name, 0) );
				
				setPoints( table.getInteger("points", "name", name, 0) );
				
			} else {
				
				setPlayTime(0);
				
				setBlockBreak(0);
				setBlockPlace(0);
				
				setPlayerKill(0);
				setPlayerDeath(0);
				
				setMonsterKill(0);
				setMonsterDeath(0);
				
				setOtherDeath(0);
				setFishing(0);
				
				setBlockMove(0);
				setBlockFall(0);
				
			}
			
			setLoginTime( (int) System.currentTimeMillis() / 1000 );
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void save() {
		
		try {
			
			if ( !table.containsValue("name", name) ) {
				
				String[] fields = {"name"};
				String[] values = {name};
				
				table.add(fields, values);
			
			}
			
			// Time
			addPlayTime( ( (int)System.currentTimeMillis() / 1000 ) - loginTime  );
			setLoginTime( (int)System.currentTimeMillis() / 1000 );
			table.set("play_time", getPlayTime().toString(), "name", name);
			
			// Block
			table.set("block_break", getBlockBreak().toString(), "name", name);
			table.set("block_place", getBlockPlace().toString(), "name", name);
			
			// Player
			table.set("player_kill", getPlayerKill().toString(), "name", name);
			table.set("player_death", getPlayerDeath().toString(), "name", name);
			
			// Monster
			table.set("monster_kill", getMonsterKill().toString(), "name", name);
			table.set("monster_death", getMonsterDeath().toString(), "name", name);
			
			table.set("other_death", getOtherDeath().toString(), "name", name);
			
			// Block Move and Fall
			table.set("block_move", getBlockMove().toString(), "name", name);
			table.set("block_fall", getBlockFall().toString(), "name", name);
			
			
			// Geangelte Fische
			table.set("fishing", getFishing().toString(), "name", name);
			
			table.set("points", getPoints().toString(), "name", name);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getBlockBreak() {
		return blockBreak;
	}
	
	public void setBlockBreak(Integer blockBreak) {
		this.blockBreak = blockBreak;
	}
	
	public void addBlockBreak() {
		this.blockBreak++;
	}

	public Integer getBlockPlace() {
		return blockPlace;
	}

	public void setBlockPlace(Integer blockPlace) {
		this.blockPlace = blockPlace;
	}
	
	public void addBlockPlace() {
		this.blockPlace++;
	}

	public Integer getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Integer loginTime) {
		this.loginTime = loginTime;
	}
		
	public Integer getPlayTime() {
		return playTime;
	}

	public void setPlayTime(Integer playTime) {
		this.playTime = playTime;
	}
	
	public void addPlayTime(Integer playTime) {
		this.playTime = this.playTime + playTime;
	}

	public Integer getPlayerKill() {
		return playerKill;
	}

	public void setPlayerKill(Integer playerKill) {
		this.playerKill = playerKill;
	}
	
	public void addPlayerKill() {
		this.playerKill++;
	}

	public Integer getPlayerDeath() {
		return playerDeath;
	}

	public void setPlayerDeath(Integer playerDeath) {
		this.playerDeath = playerDeath;
	}
	
	public void addPlayerDeath() {
		this.playerDeath++;
	}

	public Integer getMonsterKill() {
		return monsterKill;
	}

	public void setMonsterKill(Integer monsterKill) {
		this.monsterKill = monsterKill;
	}
	
	public void addMonsterKill() {
		this.monsterKill++;
	}

	public Integer getMonsterDeath() {
		return monsterDeath;
	}

	public void setMonsterDeath(Integer monsterDeaht) {
		this.monsterDeath = monsterDeaht;
	}
	
	public void addMonsterDeath() {
		this.monsterDeath++;
	}

	public Integer getBlockMove() {
		return blockMove;
	}

	public void setBlockMove(Integer blockMove) {
		this.blockMove = blockMove;
	}
	
	public void addBlockMove() {
		this.blockMove++;
	}

	public Integer getBlockFall() {
		return blockFall;
	}

	public void setBlockFall(Integer blockFall) {
		this.blockFall = blockFall;
	}
	
	public void addBlockFall( Integer blocks ) {
		this.blockFall = this.blockFall + blocks;
	}

	public Integer getOtherDeath() {
		return otherDeath;
	}

	public void setOtherDeath(Integer otherDeath) {
		this.otherDeath = otherDeath;
	}
	
	public void addOtherDeath() {
		this.otherDeath++;
	}

	public Integer getFishing() {
		return fishing;
	}

	public void setFishing(Integer fisching) {
		this.fishing = fisching;
	}
	
	public void addFishing() {
		this.fishing++;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public ArrayList<Rank> getAviableRanks() {
		return aviableRanks;
	}

	public void setAviableRanks(ArrayList<Rank> aviableRanks) {
		this.aviableRanks = aviableRanks;
	}
}
