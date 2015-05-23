package mining;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.Area;

import utils.Constants;

public class MiningGuildMiner extends Miner{
	public MiningGuildMiner(Script script) {
		super(script);
	}
	
	private enum WalkToMineSubstate {
		WALK_TO_UPPER_LADDERS, INTERACT_WITH_UPPER_LADDERS, WALK_TO_RANDOM_POSITION_IN_MINE
	}
	
	private enum WalkToBankSubstate {
		WALK_TO_LOWER_LADDERS, INTERACT_WITH_LOWER_LADDERS, WALK_TO_RANDOM_POSITION_IN_BANK
	}
	
	@Override
	protected Area getResourceArea() {
		return Constants.MINING_GUILD_VEIN_AREA;
	}

	@Override
	protected Area getBankArea() {
		return Constants.FALADOR_EAST_BANK_AREA;
	}
	
	@Override
	protected void walkToResource() throws InterruptedException {
		final WalkToMineSubstate substate = getWalkToMineSubstate();
		
		getScript().log("Current substate: " + substate.name());
		
		switch (substate) {
			case WALK_TO_UPPER_LADDERS:
				walkToUpperLadders();
				break;
			case INTERACT_WITH_UPPER_LADDERS:
				interactWithUpperLadders();
				break;
			case WALK_TO_RANDOM_POSITION_IN_MINE:
				walkToRandomPositionInMine();
				break;
			default:
				break;
			
		}
	}
	
	@Override
	protected void walkToBank() throws InterruptedException {
		final WalkToBankSubstate substate = getWalkToBankSubstate();
		
		getScript().log("Current substate: " + substate.name());
		
		switch (substate) {
			case WALK_TO_LOWER_LADDERS:
				walkToLowerLadders();
				break;
			case INTERACT_WITH_LOWER_LADDERS:
				interactWithLowerLadders();
				break;
			case WALK_TO_RANDOM_POSITION_IN_BANK:
				walkToRandomPositionInBank();
				break;
			default:
				break;
			
		}
	}

	@Override
	protected int[] getResourceIDs() {
		return Constants.MINING_GUILD_VEIN_IDS;
	}
	
	private void walkToUpperLadders() throws InterruptedException {
		traversePath(Constants.PATH_FROM_FALADOR_BANK_TO_MINING_GUILD_LADDERS, false);
	}
	
	private void interactWithUpperLadders() throws InterruptedException {
		RS2Object ladder = getScript().objects.closest("Ladder");
		
		if (ladder != null) {
			ladder.interact("Climb-down");
		}
	}
	
	private void walkToRandomPositionInMine() throws InterruptedException {
		getScript().localWalker.walk(Constants.MINING_GUILD_VEIN_AREA.getRandomPosition(0));
	}
	
	private void walkToLowerLadders() throws InterruptedException {
		getScript().localWalker.walk(Constants.MINING_GUILD_LOWER_LADDERS.getRandomPosition(0));
	}
	
	private void interactWithLowerLadders() throws InterruptedException {
		RS2Object ladder = getScript().objects.closest("Ladder");
		
		if (ladder != null) {
			ladder.interact("Climb-up");
		}
	}
	
	private void walkToRandomPositionInBank() throws InterruptedException {
		getScript().localWalker.walk(Constants.FALADOR_EAST_BANK_AREA.getRandomPosition(0));
	}
	
	private WalkToMineSubstate getWalkToMineSubstate() {
		final Player player = getScript().myPlayer();
		final boolean isPlayerNearUpperLadders = Constants.MINING_GUILD_UPPER_LADDERS.contains(player);
		final boolean isPlayerNearLowerLadders = Constants.MINING_GUILD_LOWER_LADDERS.contains(player);
		
		if (isPlayerNearLowerLadders) {
			return WalkToMineSubstate.WALK_TO_RANDOM_POSITION_IN_MINE;
		}
		else if (isPlayerNearUpperLadders) {
			return WalkToMineSubstate.INTERACT_WITH_UPPER_LADDERS;
		}
		else {
			return WalkToMineSubstate.WALK_TO_UPPER_LADDERS;
		}
	}
	
	private WalkToBankSubstate getWalkToBankSubstate() {
		final Player player = getScript().myPlayer();
		final boolean isPlayerNearLowerLadders = Constants.MINING_GUILD_LOWER_LADDERS.contains(player);
		final boolean isPlayerInMine = getResourceArea().contains(player);
		
		if (isPlayerInMine) {
			return WalkToBankSubstate.WALK_TO_LOWER_LADDERS;
		}
		else if (isPlayerNearLowerLadders) {
			return WalkToBankSubstate.INTERACT_WITH_LOWER_LADDERS;
		}
		else {
			return WalkToBankSubstate.WALK_TO_RANDOM_POSITION_IN_BANK;
		}
	}
	
	@Override
	public String toString() {
		return "Mining Guild Miner";
	}
}
