package de.secretcraft.statistik.command;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import de.secretcraft.statistik.Statistik;
import de.secretcraft.statistik.StatistikPlayer;
import de.secretcraft.statistik.manager.StatistikManager;

public class StatistikCommand {
	
	private Statistik plugin;
	private StatistikManager statistikManger;
	
	public StatistikCommand() {
		
		plugin = Statistik.getInstance();
		statistikManger = plugin.getStatistikManager();
		
	}
	
	public void execute( Player player, String[] args) {
		

		StatistikPlayer stats = null;
		
		if ( args.length > 0 ) {
			
			if ( plugin.getServer().getPlayer( args[0] ) != null ) {
				
				if ( statistikManger.getPlayer( plugin.getServer().getPlayer( args[0] ) ) == null ) {
					
					statistikManger.loadPlayer( plugin.getServer().getPlayer( args[0] ) );
					
				}
				
				statistikManger.savePlayer( plugin.getServer().getPlayer( args[0] ) );
				stats = statistikManger.getPlayer( plugin.getServer().getPlayer( args[0] ) );
				
			} else {
				player.sendMessage(ChatColor.RED + "Spiler nicht gefunden");
				return;
			}
			
		} else {
			statistikManger.savePlayer(player);
			stats = statistikManger.getPlayer(player);
		}
		
		player.sendMessage( ChatColor.BLUE + "-----------------------------------------------------");
		
		// Play Time
		player.sendMessage( ChatColor.WHITE + "Spielzeit : " + ChatColor.GOLD + secondsToString( stats.getPlayTime() ) );
		
		
		// Blšcke
		player.sendMessage( ChatColor.WHITE + "Blšcke Zerstšrt : " + ChatColor.GOLD + stats.getBlockBreak() );
		player.sendMessage( ChatColor.WHITE + "Blšcke Plaziert : " + ChatColor.GOLD + stats.getBlockPlace() );
		
		// Player
		player.sendMessage( ChatColor.WHITE + "Spieler getštet : " + ChatColor.GOLD + stats.getPlayerKill());
		player.sendMessage( ChatColor.WHITE + "von Spielern getštet : " + ChatColor.GOLD + stats.getPlayerDeath() );
		
		// Monster
		player.sendMessage( ChatColor.WHITE + "Monster getštet : " + ChatColor.GOLD + stats.getMonsterKill() );
		player.sendMessage( ChatColor.WHITE + "von Monstern getštet : " + ChatColor.GOLD + stats.getMonsterDeath() );
		
		player.sendMessage( ChatColor.WHITE + "andere Tode : " + ChatColor.GOLD + stats.getOtherDeath() );
		
		player.sendMessage( ChatColor.WHITE + "Fische gefanden : " + ChatColor.GOLD + stats.getFishing() );
		
		// Move and Fall
		player.sendMessage( ChatColor.WHITE + "Blšcke gelaufen : " + ChatColor.GOLD + stats.getBlockMove() );
		player.sendMessage( ChatColor.WHITE + "Blšcke gefallen : " + ChatColor.GOLD + stats.getBlockFall() );
		
		player.sendMessage( ChatColor.WHITE + "Punkte Gesamt : " + ChatColor.GOLD + stats.getPoints() );
		
		player.sendMessage( ChatColor.BLUE + "-----------------------------------------------------");
		
	}
	
	public String secondsToString( Integer time) {
        
		Integer seconds =  (time % 60);
		Integer minutes = ((time / 60) % 60);
		Integer hours = ((time / 3600) % 24);
        String secondsStr = (seconds < 10 ? "0" : "") + seconds;
        String minutesStr = (minutes < 10 ? "0" : "") + minutes;
        String hoursStr = (hours < 10 ? "0" : "") + hours;
        
        return new StringBuffer(hoursStr).append(":").append(minutesStr).append(":").append(secondsStr).toString();
    }
	
	
}
