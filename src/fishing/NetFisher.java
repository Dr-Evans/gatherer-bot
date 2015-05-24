package fishing;

import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.script.Script;

public abstract class NetFisher extends Fisher {
	NetFisher(Script script) {
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
		getScript().bank.depositAllExcept("Small fishing net");
	}
	
	@Override
	public String toString() {
		return "Net " + super.toString();
	}
}
