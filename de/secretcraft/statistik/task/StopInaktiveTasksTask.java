package de.secretcraft.statistik.task;

import de.secretcraft.statistik.Statistik;



public class StopInaktiveTasksTask extends Task {

	private Statistik plugin;

	public StopInaktiveTasksTask() {
		this.plugin = Statistik.getInstance();
	}

	@Override
	public void run() {

		for (Task task : plugin.getTaskManager().getInaktiveTasks()) {

			plugin.getServer().getScheduler().cancelTask(task.getPid());

		}

	}

}
