package fishing;

import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.script.Script;

public abstract class SmallNetFisher extends Fisher {
	private static final String SMALL_NET_ITEM_NAME = "Small fishing net";
	SmallNetFisher(Script script) {
		super(script);
	}

	@Override
	protected void gather() {
		Entity fishingSpot = selectResource();
        
		if (fishingSpot != null) {
			fishingSpot.interact("Net");
		}
	}   
	
	@Override
	protected void deposit() {
		getScript().bank.depositAllExcept(SMALL_NET_ITEM_NAME);
	}
	
	@Override
	protected void drop() {
		getScript().inventory.dropAllExcept(SMALL_NET_ITEM_NAME);
	}
	
	@Override
	public String toString() {
		return "Net " + super.toString();
	}
}
