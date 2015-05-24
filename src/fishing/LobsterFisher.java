package fishing;

import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.script.Script;

public abstract class LobsterFisher extends Fisher{

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
		getScript().bank.depositAllExcept("Lobster pot", "Coins");
	}
	
	@Override
	public String toString() {
		return "Lobster " + super.toString();
	}

}
