package core;

import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.Area;

import utils.Constants;

public class BarbarianVillageMiner extends Miner {

	//TODO: MAKE STATE TO GET OUT OF STRONGHOLD OF SECURITY!!!
	//TODO: MAKE STATE WHEN ACCIDENTLY ATTACKING BARBARIANS
	BarbarianVillageMiner(Script script) {
		super(script);
	}

	@Override
	protected Area getMineArea() {
		return Constants.BARBARIAN_VILLAGE_VEIN_AREA;
	}

	@Override
	protected Area getBankArea() {
		return Constants.WEST_VARROCK_BANK;
	}

	@Override
	protected void walkToMine() throws InterruptedException {
		traversePath(Constants.PATH_FROM_WEST_VARROCK_BANK_TO_BARBARIAN_VILLAGE_VEINS, false);
	}

	@Override
	protected void walkToBank() throws InterruptedException {
		traversePath(Constants.PATH_FROM_WEST_VARROCK_BANK_TO_BARBARIAN_VILLAGE_VEINS, true);
	}

	@Override
	protected int[] getVeinIDs() {
		return Constants.BARBARIAN_VILLAGE_VEIN_IDS;
	}

}
