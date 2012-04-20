package de.secretcraft.statistik.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import de.secretcraft.statistik.Statistik;
import de.secretcraft.statistik.manager.StatistikManager;

public class BlockListener implements Listener {
	
	private Statistik plugin;
	private StatistikManager statistikManager;
	
	public BlockListener() {
		plugin = Statistik.getInstance();
		statistikManager = plugin.getStatistikManager();
		
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onBlockBrakeEvent( BlockBreakEvent event ) {
		
		Player player = event.getPlayer();
				
		if ( player == null ) {
			return;
		}
		
		statistikManager.getPlayer(player).addBlockBreak();	
		
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onBlockPlaceEvent( BlockPlaceEvent event ) {
		
		Player player = event.getPlayer();
				
		if ( player == null ) {
			return;
		}
		
		statistikManager.getPlayer(player).addBlockPlace();	
		
	}
	
}
