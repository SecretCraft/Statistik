package de.secretcraft.statistik.command;

import java.util.ArrayList;
import java.util.List;

import net.milkbowl.vault.chat.Chat;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;

import de.secretcraft.statistik.Helper;
import de.secretcraft.statistik.Rank;
import de.secretcraft.statistik.Statistik;
import de.secretcraft.statistik.StatistikPlayer;
import de.secretcraft.statistik.manager.RankManager;
import de.secretcraft.statistik.manager.StatistikManager;

public class RankCommand {
	
	private Statistik plugin;
	private StatistikManager statistikManger;
	private RankManager rankManager;
	private Chat chat;
	
	public RankCommand() {
		
		plugin = Statistik.getInstance();
		statistikManger = plugin.getStatistikManager();
		rankManager = plugin.getRankManager();
		chat = plugin.getChat();
		
	}
	
	public void execute( Player player, String[] args) {
		

		StatistikPlayer stats = null;
		
		if ( args.length > 0 ) {
						
			if ( args[0].equalsIgnoreCase("set") ) {
				
				if ( args.length > 1 ) {
					
					Rank rank = rankManager.getRankByTag( args[1] );				
					
					if ( rank == null ) {
						player.sendMessage(ChatColor.GOLD + "Diesen Rang gibt es nicht.");
						return;
					}
					
					if ( rankManager.checkRank(player, rank) ) {
						
						String[] groups = chat.getPlayerGroups(player);
						
						String prefix = rank.getName() + chat.getGroupPrefix(player.getWorld(), groups[0]);
						
						List<World> worlds = plugin.getServer().getWorlds();
						
						for ( World world : worlds ) {
							chat.setPlayerPrefix(world, player.getName(), prefix);						
						}
						
						player.sendMessage(ChatColor.GREEN + "Rank wurde gesetzt.");
					
					} else {
						
						player.sendMessage(ChatColor.GOLD + "Dieser Rang ist noch nicht verfügbar.");
						
					}	
										
				} else {
					player.sendMessage("/rank set RankTag");
				}
				
				
				
			} else if ( args[0].equalsIgnoreCase("all") ) {
				
				ArrayList<Rank> ranks = plugin.getRankManager().getRanks();
				
				player.sendMessage( ChatColor.BLUE + "-----------------------------------------------------");
				player.sendMessage( ChatColor.GREEN + "Freigeschaltete Ränge");
				
				listRanks(player, ranks);
			}
			
			
		} else {
			
			ArrayList<Rank> ranks = rankManager.getRanks(player);
			
			player.sendMessage( ChatColor.BLUE + "-----------------------------------------------------");
			player.sendMessage( ChatColor.GREEN + "Alle Ränge");
			
			listRanks(player, ranks);
			
		}
		
		
		
	}
	
	private void listRanks( Player player, ArrayList<Rank> ranks ) {
		
		if ( ranks.size() > 0 ) {
			
			for ( Rank rank : ranks ) {
				
				player.sendMessage( ChatColor.BLUE + "-----------------------------------------------------");
				
				// Name Tag
				player.sendMessage( ChatColor.WHITE + "Name : " + Helper.format(rank.getName()) );
				player.sendMessage( ChatColor.WHITE + "Tag : " + ChatColor.GOLD + rank.getTag() );
				
				// Play Time
				player.sendMessage( ChatColor.WHITE + "Spielzeit : " + ChatColor.GOLD + secondsToString( rank.getPlayTime() ) );
				
				// Blöcke
				player.sendMessage( ChatColor.WHITE + "Blöcke Zerstört : " + ChatColor.GOLD + rank.getBlockBreak() );
				player.sendMessage( ChatColor.WHITE + "Blöcke Plaziert : " + ChatColor.GOLD + rank.getBlockPlace() );
				
				// Player
				player.sendMessage( ChatColor.WHITE + "Spieler getötet : " + ChatColor.GOLD + rank.getPlayerKill());
				player.sendMessage( ChatColor.WHITE + "von Spielern getötet : " + ChatColor.GOLD + rank.getPlayerDeath() );
				
				// Monster
				player.sendMessage( ChatColor.WHITE + "Monster getötet : " + ChatColor.GOLD + rank.getMonsterKill() );
				player.sendMessage( ChatColor.WHITE + "von Monstern getötet : " + ChatColor.GOLD + rank.getMonsterDeath() );
				
				player.sendMessage( ChatColor.WHITE + "andere Tode : " + ChatColor.GOLD + rank.getOtherDeath() );
				
				player.sendMessage( ChatColor.WHITE + "Fische gefanden : " + ChatColor.GOLD + rank.getFishing() );
				
				// Move and Fall
				player.sendMessage( ChatColor.WHITE + "Blöcke gelaufen : " + ChatColor.GOLD + rank.getBlockMove() );
				player.sendMessage( ChatColor.WHITE + "Blöcke gefallen : " + ChatColor.GOLD + rank.getBlockFall() );
				
				player.sendMessage( ChatColor.WHITE + "Punkte gesamt : " + ChatColor.GOLD + rank.getPoints() );
			}
			
		} else {
			
			player.sendMessage( ChatColor.BLUE + "-----------------------------------------------------");
			
			player.sendMessage( ChatColor.RED + "Du hast noch keine Ränge freigeschaltet." );
			
		}
		
		player.sendMessage( ChatColor.BLUE + "-----------------------------------------------------");
		
	}
	
	private String secondsToString( Integer time) {
        
		Integer seconds =  (time % 60);
		Integer minutes = ((time / 60) % 60);
		Integer hours = ((time / 3600) % 24);
        String secondsStr = (seconds < 10 ? "0" : "") + seconds;
        String minutesStr = (minutes < 10 ? "0" : "") + minutes;
        String hoursStr = (hours < 10 ? "0" : "") + hours;
        
        return new StringBuffer(hoursStr).append(":").append(minutesStr).append(":").append(secondsStr).toString();
    }
	
	
}
