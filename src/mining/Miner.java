package mining;

import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.Script;

import core.Gatherer;

public abstract class Miner extends Gatherer {

	Miner(Script script) {
		super(script);
	}
	
	protected void gather() {
		//TODO: Refine this if to stop mining if other miner is there or vein is already mined
		RS2Object vein = selectResource();
        
		if (vein != null) { 
			vein.interact("Mine");
        }
	};

	@Override
	public String toString() {
		return "Miner";
	}
}
