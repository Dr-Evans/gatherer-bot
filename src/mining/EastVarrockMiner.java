package mining;

import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.Area;

import utils.Constants;

public class EastVarrockMiner extends Miner {

	public EastVarrockMiner(Script script) {
		super(script);
	}

	@Override
	protected Area getMineArea() {
		return Constants.EAST_VARROCK_VEIN_AREA;
	}

	@Override
	protected Area getBankArea() {
		return Constants.EAST_VARROCK_BANK;
	}

	@Override
	protected void walkToMine() throws InterruptedException {
		traversePath(Constants.PATH_FROM_EAST_VARROCK_BANK_TO_BARBARIAN_VILLAGE_VEINS, false);
	}

	@Override
	protected void walkToBank() throws InterruptedException {
		traversePath(Constants.PATH_FROM_EAST_VARROCK_BANK_TO_BARBARIAN_VILLAGE_VEINS, true);
	}

	@Override
	protected int[] getVeinIDs() {
		return Constants.EAST_VARROCK_VEIN_IDS;
	}

}
