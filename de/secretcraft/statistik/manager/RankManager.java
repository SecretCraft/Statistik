package de.secretcraft.statistik.manager;

import java.util.ArrayList;
import java.util.Set;

import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

import de.secretcraft.statistik.Rank;
import de.secretcraft.statistik.Statistik;
import de.secretcraft.statistik.StatistikPlayer;

public class RankManager {

	private Statistik plugin;
	private StatistikManager statistikManager;
	private Configuration config;
	
	private ArrayList<Rank> ranks;
	
	public RankManager() {
		plugin = Statistik.getInstance();
		statistikManager = plugin.getStatistikManager();
		config = plugin.getConfig();		
		
		ranks = new ArrayList<Rank>();
		
		load();
	}
	
	public void load() {
		
		Set<String> rankList = null;
		
		rankList = config.getConfigurationSection("Ranks").getKeys(false);
		
		
		if ( rankList != null ) {
			
			for ( String id : rankList ) {
				
				Rank rank = new Rank(Integer.parseInt(id), config);
				ranks.add(rank);
				
			}
			
		}
		
	}
	
	public ArrayList<Rank> getRanks() {		
		return ranks;
	}
	
	public ArrayList<Rank> getRanks( Player player ) {		
		
		ArrayList<Rank> available = null;
		
		if ( ranks.size() > 0 ) {
			
			available = new ArrayList<Rank>();
			
			for ( Rank rank : ranks ) {
				
				if ( checkRank(player, rank) ) {
					available.add(rank);
				}
				
			}
			
		}	
		
		return available;
	}
	
	public Boolean checkRank( Player player, Rank rank ) {
		
		StatistikPlayer stats = statistikManager.getPlayer(player);
		
		if ( rank.getPlayTime() == 0 || rank.getPlayTime() <= stats.getPlayTime() ) {
			
			if ( rank.getBlockBreak() == 0 || rank.getBlockBreak() <= stats.getBlockBreak() ) {
				
				if ( rank.getBlockPlace() == 0 || rank.getBlockPlace() <= stats.getBlockPlace() ) {
					
					if ( rank.getPlayerKill() == 0 || rank.getPlayerKill() <= stats.getPlayerKill() ) {
						
						if ( rank.getPlayerDeath() == 0 || rank.getPlayerDeath() <= stats.getPlayerDeath() ) {
							
							if ( rank.getMonsterKill() == 0 || rank.getMonsterKill() <= stats.getMonsterKill() ) {
								
								if ( rank.getMonsterDeath() == 0 || rank.getMonsterDeath() <= stats.getMonsterDeath() ) {
									
									if ( rank.getOtherDeath() == 0 || rank.getOtherDeath() <= stats.getOtherDeath() ) {
										
										if ( rank.getFishing() == 0 || rank.getFishing() <= stats.getFishing() ) {
											
											if ( rank.getBlockMove() == 0 || rank.getBlockMove() <= stats.getBlockMove() ) {
												
												if ( rank.getBlockFall() == 0 || rank.getBlockFall() <= stats.getBlockFall() ) {
													
													if ( rank.getPoints() == 0 || rank.getPoints() <= stats.getPoints() ) {
														
														return true;
														
													}
													
												}
												
											}
											
										}
										
									}
									
								}
								
							}
							
						}
						
					}
					
				}
				
			}
			
		}
		
		return false;
	}
	
	public Rank getRankByTag( String tag ) {
		
		for ( Rank rank : ranks ) {
			
			if ( rank.getTag().equalsIgnoreCase(tag) ) {
				return rank;
			}
			
		}		
		
		return null;
	}
	
}
