package de.secretcraft.statistik.task;

import de.secretcraft.statistik.Statistik;



public class SaveStatsTask extends Task {

	private Statistik plugin;

	public SaveStatsTask() {
		plugin = Statistik.getInstance();
	}

	@Override
	public void run() {

		plugin.getStatistikManager().savePlayers();

	}

}
