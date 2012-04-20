package de.secretcraft.statistik;

import java.sql.SQLException;

import net.windwaker.sql.Connection;
import net.windwaker.sql.Driver;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.secretcraft.statistik.command.StatistikCommand;
import de.secretcraft.statistik.listener.BlockListener;
import de.secretcraft.statistik.listener.EntityListener;
import de.secretcraft.statistik.listener.PlayerListener;
import de.secretcraft.statistik.manager.SettingsManager;
import de.secretcraft.statistik.manager.StatistikManager;
import de.secretcraft.statistik.manager.TaskManager;
import de.secretcraft.statistik.task.SaveStatsTask;
import de.secretcraft.statistik.task.Task;

public class Statistik extends JavaPlugin {

	
	private static Statistik plugin;
	
	// Manager
	private SettingsManager settingsManager;
	private StatistikManager statistikManager;
	private TaskManager taskManager;
	
	
	// Listener
	private PlayerListener playerListener;
	private BlockListener blockListener;
	private EntityListener entityListener;
	
	// Commands
	private StatistikCommand statistikCommand;
	
	// DB
	Connection connection;
	
	
	@Override
	public void onEnable() {
	
		plugin = this;
		
		// Manager
		settingsManager = new SettingsManager();
		connectToDB();
		
		statistikManager = new StatistikManager();
		taskManager = new TaskManager();
		
		// Listener
		playerListener = new PlayerListener();
		blockListener = new BlockListener();
		entityListener = new EntityListener();
		
		// Commands
		statistikCommand = new StatistikCommand();
		
		
		//long time = (int)settingsManager.getSaveTime() * 20L;
		
		Task task = new SaveStatsTask();		
		taskManager.createAsyncRepeatingTask(task, 1200L, 1200L);
		
	}
	
	@Override
	public void onDisable() {
		
		taskManager.stopAllTask();
		statistikManager.savePlayers();
		
	}
	
	private void connectToDB() {
		
		// DB Verbinden
		connection = new Connection( settingsManager.getHost() + "/" + settingsManager.getDatabase() , Driver.getByProtocol("mysql"));
		
		try {
			connection.connect( settingsManager.getUser(), settingsManager.getPassword() );
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
	
		Player player = (Player)sender;
		String commandName = command.getName().toLowerCase();
        
        if(commandName.equals("statistik") || commandName.equals("stats") ) {
        	statistikCommand.execute( player, args);
        }
        
        return false;
    }	
	
	public static Statistik getInstance() {
		return plugin;
	}
	
	public StatistikManager getStatistikManager() {
		return statistikManager;
	}
	
	public TaskManager getTaskManager() {
		return taskManager;
	}
	
	public SettingsManager getSettingsManager() {
		return settingsManager;
	}
	
	public Connection getConnection() {
		return connection;
	}
	
}
