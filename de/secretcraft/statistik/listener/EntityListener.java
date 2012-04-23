package de.secretcraft.statistik.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import de.secretcraft.statistik.Statistik;
import de.secretcraft.statistik.manager.StatistikManager;

public class EntityListener implements Listener {
	
	private Statistik plugin;
	private StatistikManager statistikManager;
	
	public EntityListener() {
		plugin = Statistik.getInstance();
		statistikManager = plugin.getStatistikManager();
		
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onEntityDeathEvent( EntityDeathEvent event ) {
		
		Entity entity = event.getEntity();
		
		if ( entity instanceof Player ) {
			
			Player player = (Player)entity;
			
			if ( player.getKiller() instanceof Player ) {
				
				statistikManager.getPlayer( player ).addPlayerDeath();
				statistikManager.getPlayer( player.getKiller() ).addPlayerKill();
				
			} else if ( player.getLastDamageCause() instanceof EntityDamageByEntityEvent ) {
				
				statistikManager.getPlayer(player).addMonsterDeath();
				
			} else {
				
				statistikManager.getPlayer( player ).addOtherDeath();
								
			}
			
									
		} else if ( entity instanceof Monster ) {
			
			Monster monster = (Monster)entity;
			
			if ( monster.getKiller() instanceof Player ) {
								
				statistikManager.getPlayer( monster.getKiller() ).addMonsterKill();
				
			}
		}		
		
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onEntityDamageEvent( EntityDamageEvent event ) {
		
		Entity entity = event.getEntity();
		
		if ( entity instanceof Player ) {
			
			Player player = (Player)entity;
			
			if ( event.getCause().compareTo( DamageCause.FALL ) == 0 ) {
				
				statistikManager.getPlayer(player).addBlockFall( (int)player.getFallDistance() );				
				
			}			
			
		}
		
	}
	
}
