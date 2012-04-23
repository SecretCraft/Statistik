package de.secretcraft.statistik.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import de.secretcraft.statistik.Statistik;
import de.secretcraft.statistik.StatistikPlayer;
import net.windwaker.sql.Connection;
import net.windwaker.sql.DataType;
import net.windwaker.sql.Table;

public class StatistikManager {
	
	private Statistik plugin;
	private SettingsManager settingsManager;
	
	private Connection connection;
	private Table playerTable;
	
	
	private ArrayList<StatistikPlayer> players; 
	
	public StatistikManager() {
		plugin = Statistik.getInstance();
		settingsManager = plugin.getSettingsManager();
		connection = plugin.getConnection();
		
		
		playerTable = new Table(connection, "stats_player");
		
		try {
			
			if ( !playerTable.exists() ) {
				
				Map<String, DataType> columnDataTypeMap = new HashMap<String, DataType>();
				columnDataTypeMap.put("name", new DataType(DataType.TEXT));
				
				columnDataTypeMap.put("play_time", new DataType(DataType.INTEGER));
				
				columnDataTypeMap.put("block_break", new DataType(DataType.INTEGER));
				columnDataTypeMap.put("block_place", new DataType(DataType.INTEGER));
				
				columnDataTypeMap.put("player_kill", new DataType(DataType.INTEGER));
				columnDataTypeMap.put("player_death", new DataType(DataType.INTEGER));
				
				columnDataTypeMap.put("monster_kill", new DataType(DataType.INTEGER));
				columnDataTypeMap.put("monster_death", new DataType(DataType.INTEGER));
				
				columnDataTypeMap.put("other_death", new DataType(DataType.INTEGER));
				columnDataTypeMap.put("fishing", new DataType(DataType.INTEGER));
				
				columnDataTypeMap.put("block_move", new DataType(DataType.INTEGER));
				columnDataTypeMap.put("block_fall", new DataType(DataType.INTEGER));
				
				columnDataTypeMap.put("points", new DataType(DataType.INTEGER));
				
				playerTable.create(columnDataTypeMap);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		players = new ArrayList<StatistikPlayer>();
	}
	
	
	public void loadPlayer( Player player ) {
		
		StatistikPlayer statistikPlayer = new StatistikPlayer(playerTable, player.getName() );
		players.add(statistikPlayer);		
		
	}
	
	public void savePlayer( Player player ) {
		
		for ( StatistikPlayer player2 : players ) {
		
			if ( player2.getName().equalsIgnoreCase( player.getName() ) ) {
				
				// Spieler Stats speichern und entfernen
				calculatePoints(player2);
				player2.save();
				return;
			}
						
		}
		
	}
	
	public void removePlayer( Player player ) {
		
		for ( StatistikPlayer player2 : players ) {
		
			if ( player2.getName().equalsIgnoreCase( player.getName() ) ) {
				
				// Spieler Stats speichern und entfernen
				player2.save();
				players.remove(player2);
				return;
			}
						
		}
		
	}
	
	
	public void savePlayers() {
		
		if ( players.size() > 0 ) {
			
			for ( StatistikPlayer player : players ) {
				calculatePoints(player);
				player.save();
				
			}
			
		}
		
	}
	
	
	public StatistikPlayer getPlayer( Player player ) {
		
		for ( StatistikPlayer player2 : players ) {
			
			if ( player2.getName().equalsIgnoreCase( player.getName() ) ) {
				
				return player2;			
			}
			
		}
		
		return null;
		
	}
	
	public void calculatePoints( StatistikPlayer player ) {
		
		Integer points = 0;
		
		points = points + ( ( player.getPlayTime() / settingsManager.getPlayTimeFor() ) * settingsManager.getPlayTimePoints() );
		
		points = points + ( ( player.getBlockBreak() / settingsManager.getBlockBreakFor() ) * settingsManager.getBlockBreakPoints()  );
		points = points + ( ( player.getBlockPlace() / settingsManager.getBlockPlaceFor() ) * settingsManager.getBlockPlacePoints()  );
		
		points = points + ( ( player.getPlayerKill() / settingsManager.getPlayerKillFor() ) * settingsManager.getPlayerKillPoints() );
		points = points + ( ( player.getPlayerDeath() / settingsManager.getPlayerDeathFor() ) * settingsManager.getPlayerDeathPoints() );
		
		points = points + ( ( player.getMonsterKill() / settingsManager.getMonsterKillFor() ) * settingsManager.getMonsterKillPoints() );
		points = points + ( ( player.getMonsterDeath() / settingsManager.getMonsterDeathFor() ) * settingsManager.getMonsterDeathPoints() );
		
		points = points + ( ( player.getOtherDeath() / settingsManager.getOtherDeathFor() ) * settingsManager.getOtherDeathPoints() );
		points = points + ( ( player.getFishing() / settingsManager.getFishingFor() ) * settingsManager.getFishingPoints() );
		
		points = points + ( ( player.getBlockMove() / settingsManager.getBlockMoveFor() ) * settingsManager.getBlockMovePoints() );
		points = points + ( ( player.getBlockFall() / settingsManager.getBlockFallFor() ) * settingsManager.getBlockFallPoints() );
		
		player.setPoints(points);
		
	}
	
}
