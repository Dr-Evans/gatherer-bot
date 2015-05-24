package fishing;

import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;

import core.Gatherer;

public abstract class Fisher extends Gatherer {
	Fisher(Script script) {
		super(script);
	}
	
	public Skill getSkill() {
		return Skill.FISHING;
	};
	
	@Override
	protected Entity selectResource() {
		return getScript().npcs.closest(getResourceIDs());
	}

	@Override
	public String toString() {
		return "Fisher";
	}
}
