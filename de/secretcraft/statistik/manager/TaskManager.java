package de.secretcraft.statistik.manager;

import java.util.ArrayList;

import de.secretcraft.statistik.Statistik;
import de.secretcraft.statistik.task.StopInaktiveTasksTask;
import de.secretcraft.statistik.task.Task;

public class TaskManager {

	private Statistik plugin;
	
	private ArrayList<Task> allTasks;
	private ArrayList<Task> inaktiveTasks;
	
	
	public TaskManager() {
		this.plugin = Statistik.getInstance();
		
		this.inaktiveTasks = new ArrayList<Task>();
		this.allTasks = new ArrayList<Task>();
		
		// TaskManagerTask für stoppen von Tasks alle 10min oder so.
		
		plugin.getServer().getScheduler().scheduleAsyncRepeatingTask(plugin, new StopInaktiveTasksTask(), 0, 108000L);
		
	}
	
	public Boolean createAsyncDelayedTask( Task task, long delay ) {
		
		Integer pid = null;
		
		pid = plugin.getServer().getScheduler().scheduleAsyncDelayedTask(plugin, task, delay);
		
		if ( pid != null ) {
			
			task.setPid(pid);
			this.allTasks.add(task);
			
			return true;
		}
		
		return false;
	}
	
	
	public Boolean createAsyncRepeatingTask( Task task, long delay, long time ) {
		
		Integer pid = null;
		
		pid = plugin.getServer().getScheduler().scheduleAsyncRepeatingTask(plugin, task, delay, time);
		
		if ( pid != null ) {
			
			task.setPid(pid);
			this.allTasks.add(task);
			
			return true;
		}
		
		return false;
	}
	
	
	public void setInaktiv( Task task ) {
		
		if ( task != null ) {
			this.inaktiveTasks.add(task);
		}
		
	}
	
	public ArrayList<Task> getInaktiveTasks() {
		return this.inaktiveTasks;
	}
	
	
	public void stopAllTask() {
		
		if ( allTasks.size() > 0 ) {
			
			for ( Task task : this.allTasks ) {
				plugin.getServer().getScheduler().cancelTask( task.getPid() );
			}
			
		}
		
	}
	
}
