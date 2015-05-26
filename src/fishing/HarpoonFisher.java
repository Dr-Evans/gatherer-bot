package fishing;

import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.script.Script;

public abstract class HarpoonFisher extends Fisher {
	private static final String HARPOON_ITEM_NAME = "Harpoon";
	
	HarpoonFisher(Script script) {
		super(script);
	}
	
	@Override
	protected void gather() {
		Entity fishingSpot = selectResource();
        
		if (fishingSpot != null) {
			fishingSpot.interact("Harpoon");
		}
	}   
	
	@Override
	protected void deposit() {
		getScript().bank.depositAllExcept(HARPOON_ITEM_NAME);
	}
	
	@Override
	protected void drop() {
		getScript().inventory.dropAllExcept(HARPOON_ITEM_NAME);
	}
	
	@Override
	public String toString() {
		return "Harpoon " + super.toString();
	}
	
}
