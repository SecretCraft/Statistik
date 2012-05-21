package de.secretcraft.statistik.task;

import de.secretcraft.statistik.Statistik;

public class CheckNewRanksTask extends Task {

	private Statistik plugin;

	public CheckNewRanksTask() {
		this.plugin = Statistik.getInstance();
	}

	@Override
	public void run() {

		plugin.getStatistikManager().checkNewRanks();

	}

}
