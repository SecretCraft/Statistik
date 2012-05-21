package de.secretcraft.statistik.listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.secretcraft.statistik.Statistik;
import de.secretcraft.statistik.manager.StatistikManager;

public class PlayerListener implements Listener {
	
	private Statistik plugin;
	private StatistikManager statistikManager;
	
	public PlayerListener() {
		plugin = Statistik.getInstance();
		statistikManager = plugin.getStatistikManager();
		
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerJoinEvent( PlayerJoinEvent event ) {
		
		statistikManager.loadPlayer( event.getPlayer() );
		
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerQuitEvent( PlayerQuitEvent event ) {
		
		statistikManager.savePlayer( event.getPlayer() );
		statistikManager.removePlayer( event.getPlayer() );
		
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerMoveEvent( PlayerMoveEvent event ) {
		
		if ( event.getPlayer().getGameMode() == GameMode.SURVIVAL ) {
			
			if ( event.getPlayer().isInsideVehicle() == false ) {
				
				if ( event.getFrom().getBlock().getLocation().distance( event.getTo().getBlock().getLocation() ) > 0.8 && 
					event.getFrom().getBlock().getLocation().distance( event.getTo().getBlock().getLocation() ) < 1.2 ) {			
					statistikManager.getPlayer( event.getPlayer() ).addBlockMove();
				}	
				
			}					
		
		}			
		
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerFishEvent( PlayerFishEvent event ) {
		
		if ( event.getState().compareTo( State.CAUGHT_FISH ) == 0 ) {
			statistikManager.getPlayer(event.getPlayer()).addFishing();
		}		
		
	}
	
	
}
