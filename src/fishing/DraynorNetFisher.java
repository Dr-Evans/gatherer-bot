package fishing;

import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.Area;

import utils.Constants;

public class DraynorNetFisher extends NetFisher {

	public DraynorNetFisher(Script script) {
		super(script);
	}

	@Override
	protected Area getResourceArea() {
		return Constants.DRAYNOR_FISHING_AREA;
	}

	@Override
	protected Area getBankArea() {
		return Constants.DRAYNOR_BANK;
	}

	@Override
	protected void walkToResource() throws InterruptedException {
		traversePath(Constants.PATH_FROM_DRAYNOR_BANK_TO_DRAYNOR_FISHING_AREA, false);
	}

	@Override
	protected void walkToBank() throws InterruptedException {
		traversePath(Constants.PATH_FROM_DRAYNOR_BANK_TO_DRAYNOR_FISHING_AREA, true);
	}

	@Override
	protected int[] getResourceIDs() {
		return Constants.DRAYNOR_FISHING_SPOT_IDS;
	}
	
	@Override
	public String toString() {
		return "Draynor " + super.toString();
	}

}
