package mining;

import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;

import core.Gatherer;

public abstract class Miner extends Gatherer {

	Miner(Script script) {
		super(script);
	}
	
	public Skill getSkill() {
		return Skill.MINING;
	}
	
	protected void gather() {
		Entity vein = selectResource();
        
		if (vein != null) { 
			vein.interact("Mine");
        }
	};

	@Override
	public String toString() {
		return "Miner";
	}
}
