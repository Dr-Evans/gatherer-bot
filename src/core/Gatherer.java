package core;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.input.mouse.MiniMapTileDestination;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.Area;


public abstract class Gatherer {
	private Script script;
	private boolean isDropper;
	
	protected Gatherer(Script script) {
		this.script = script;
		this.isDropper = true;
	}
	
	public abstract Skill getSkill();
	protected abstract Area getResourceArea();
	protected abstract Area getBankArea();
	protected abstract void walkToResource() throws InterruptedException;
	protected abstract void walkToBank() throws InterruptedException;
	protected abstract void gather();
	protected abstract int[] getResourceIDs();
	
	public void execute() throws InterruptedException {
		switch (getState()){
			case WALK_TO_RESOURCE:
				walkToResource();
				break;
			case WALK_TO_BANK:
				walkToBank();
				break;
			case BANK:
				bank();
				break;
			case DEPOSIT:
				deposit();
				break;
			case GATHER:
				if (!getScript().myPlayer().isAnimating() && !getScript().myPlayer().isMoving()) {
					gather();
				}
				break;
			case DROP:
				drop();
				break;
			default:
				break;
		}
	}

	public State getState() {
		final Player player = getScript().myPlayer();
		final boolean isInvFull = getScript().inventory.isFull();
		final boolean isPlayerInBank = getBankArea().contains(player);
		final boolean isPlayerInResourceArea = getResourceArea().contains(player);
		
		if (!isInvFull) {
			if (isPlayerInResourceArea) {
				return State.GATHER;
			}
			else {
				return State.WALK_TO_RESOURCE;
			}
		}
		else {
			if (isDropper) {
				return State.DROP;
			}
			else {
				if (isPlayerInBank) {
					if (!getScript().bank.isOpen()) {
						return State.BANK;
					}
					else {
						return State.DEPOSIT;
					}
				}
				else {
					return State.WALK_TO_BANK;
				}
			}
		}
	};

	protected Script getScript() {
		return script;
	}
	
	protected void bank() throws InterruptedException{
		RS2Object bankBooth = getScript().objects.closest("Bank booth");
		
	    if (bankBooth != null) {
            bankBooth.interact("Bank");
	    }
	};
	
	protected void deposit() {
		getScript().bank.depositAll();
	}
	
	protected void drop() {
		getScript().inventory.dropAll();
	}
	
	protected void error() {
		//TODO: Log error
		getScript().stop();
	}

	protected enum State {
		WALK_TO_RESOURCE, WALK_TO_BANK, GATHER, BANK, DEPOSIT, DROP
	}
	
	//TODO: Make more robust
	protected Entity selectResource() {
		return getScript().objects.closest(getResourceIDs());
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
		Script.sleep(Script.random(150, 250));
		getScript().mouse.click(false);
		int failsafe = 0;
		while (failsafe < 10 && getScript().myPlayer().getPosition().distance(p) > 2) {
			Script.sleep(200);
			failsafe++;
			if (getScript().myPlayer().isMoving())
				failsafe = 0;
		}
		if (failsafe == 10)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Gatherer";
	}
}
