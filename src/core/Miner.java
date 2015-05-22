package core;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.input.mouse.MiniMapTileDestination;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.Area;


public abstract class Miner {
	private Script script;
	
	Miner(Script script) {
		this.script = script;
	}
	
	protected abstract Area getMineArea();
	protected abstract Area getBankArea();
	protected abstract void walkToMine() throws InterruptedException;
	protected abstract void walkToBank() throws InterruptedException;
	protected abstract int[] getVeinIDs();
	
	public void execute() throws InterruptedException {
		final State state = getState();
		
		getScript().log("Current state: " + state.name());
		
		switch (getState()){
			case WALK_TO_MINE:
				walkToMine();
				break;
			case WALK_TO_BANK:
				walkToBank();
				break;
			case BANK:
				bank();
				break;
			case MINE:
				if (!getScript().myPlayer().isAnimating() && !getScript().myPlayer().isMoving()) {
					mine();
				}
				break;
			case ERROR:
				error();
				break;
			default:
				break;
		}
	}
	
	public State getState() {
		final Player player = getScript().myPlayer();
		final boolean isInvFull = getScript().inventory.isFull();
		final boolean isPlayerInBank = getBankArea().contains(player);
		final boolean isPlayerInMine = getMineArea().contains(player);
		
		if (!isInvFull) {
			if (isPlayerInMine) {
				return State.MINE;
			}
			
			return State.WALK_TO_MINE;
		}
		else {
			if (isPlayerInBank) {
				return State.BANK;
			}
			
			return State.WALK_TO_BANK;
		}
	};

	protected Script getScript() {
		return script;
	}
	
	protected void bank() throws InterruptedException{
		RS2Object bankBooth = getScript().objects.closest("Bank booth");
		
	    if (bankBooth != null) {
	        if (bankBooth.interact("Bank")) {
	        	//Sleep until bank is open
	            while (!getScript().bank.isOpen()) getScript().sleep(250);
	            
	            getScript().bank.depositAll();
	        }
	    }
	};
	
	protected boolean mine() {
		//TODO: Refine this if to stop mining if other miner is there or vein is already mined
		RS2Object vein = selectVein();
        
		//Not sure if I like the short-circuit fanciness yet
		if (vein != null) { //&& !vein.interact("Mine")) {
			return vein.interact("Mine");
        }
		
		return false;
	};
	
	protected void error() {
		//TODO: Log error
		getScript().stop();
	}

	protected enum State {
		WALK_TO_MINE, WALK_TO_BANK, MINE, BANK, ERROR //LOOKING_FOR_VEIN, MINING, LOOKING_FOR_BANK, BANKING
	}
	
	protected RS2Object selectVein() {
		return getScript().objects.closest(getVeinIDs());
	};
	
	protected void traversePath(Position[] path, boolean reversed) throws InterruptedException {
		if (!reversed) {
			for (int i = 0; i < path.length; i++)
				if (!walkTile(path[i]))
					i--;
		} else {
			for (int i = path.length-1; i >= 0; i--)
				if (!walkTile(path[i]))
					i++;
		}
	} 
	
	protected boolean walkTile(Position p) throws InterruptedException {
		getScript().mouse.move(new MiniMapTileDestination(getScript().bot, p), false);
		getScript().sleep(getScript().random(150, 250));
		getScript().mouse.click(false);
		int failsafe = 0;
		while (failsafe < 10 && getScript().myPlayer().getPosition().distance(p) > 2) {
			getScript().sleep(200);
			failsafe++;
			if (getScript().myPlayer().isMoving())
				failsafe = 0;
		}
		if (failsafe == 10)
			return false;
		return true;
	}
}
