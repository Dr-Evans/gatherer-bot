package fishing;

import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.script.Script;

public abstract class LobsterFisher extends Fisher{
	private static final String LOBSTER_POT_ITEM_NAME = "Lobster pot";

	LobsterFisher(Script script) {
		super(script);
	}
	
	@Override
	protected void gather() {
		Entity fishingSpot = selectResource();
        
		if (fishingSpot != null) {
			fishingSpot.interact("Cage");
		}
	}  
	
	@Override
	protected void deposit() {
		getScript().bank.depositAllExcept(LOBSTER_POT_ITEM_NAME, "Coins");
	}
	
	@Override
	protected void drop() {
		getScript().inventory.dropAllExcept(LOBSTER_POT_ITEM_NAME, "Coins");
	}
	
	@Override
	public String toString() {
		return "Lobster " + super.toString();
	}

}
