package fishing;

import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.Area;

import utils.Constants;

public class KaramjaLobsterFisher extends LobsterFisher{

	public KaramjaLobsterFisher(Script script) {
		super(script);
	}
	
	@Override
	protected void bank() {
		RS2Object bankBooth = getScript().objects.closest("Bank deposit box");
		
	    if (bankBooth != null) {
            bankBooth.interact("Deposit");
	    }
	};
	
	@Override
	protected void deposit() {
		getScript().depositBox.depositAllExcept("Lobster pot", "Coins");
	}

	private enum WalkToResourceSubstate {
		WALK_TO_PORT_SARIM_SAILORS, TALK_TO_PORT_SARIM_SAILORS, CROSS_GANGPLANK, WALK_TO_KARAMJA_DOCK
	}
	
	private enum WalkToBankSubstate {
		WALK_TO_KARAMJA_SAILORS, TALK_TO_KARAMJA_SAILORS, CROSS_GANGPLANK, WALK_TO_DEPOSIT_BOX
	}
	
	protected WalkToResourceSubstate getWalkToResourceSubstate() {
		final Player player = getScript().myPlayer();
		final boolean isPlayerInPortSarim = Constants.PORT_SARIM_KARAMJA_SAILORS_AREA.contains(player);
		final boolean isPlayerInKaramjaBoat = Constants.KARAMJA_BOAT.contains(player);
		final boolean isPlayerInKaramjaPort = Constants.KARAMJA_PORT.contains(player);
		
		if (isPlayerInPortSarim) {
			return WalkToResourceSubstate.TALK_TO_PORT_SARIM_SAILORS;
		}
		else if (isPlayerInKaramjaBoat) {
			return WalkToResourceSubstate.CROSS_GANGPLANK;
		}
		else if (isPlayerInKaramjaPort) {
			return WalkToResourceSubstate.WALK_TO_KARAMJA_DOCK;
		}
		else {
			return WalkToResourceSubstate.WALK_TO_PORT_SARIM_SAILORS;
		}
		
	}
	
	protected WalkToBankSubstate getWalkToBankSubstate() {
		final Player player = getScript().myPlayer();
		final boolean isPlayerInKaramjaDock = Constants.KARAMJA_FISHING_AREA.contains(player);
		final boolean isPlayerInKaramjaPort = Constants.KARAMJA_PORT.contains(player);
		final boolean isPlayerInPortSarimBoat = Constants.PORT_SARIM_BOAT.contains(player);
		
		if (isPlayerInKaramjaDock) {
			return WalkToBankSubstate.WALK_TO_KARAMJA_SAILORS;
		}
		else if (isPlayerInKaramjaPort) {
			return WalkToBankSubstate.TALK_TO_KARAMJA_SAILORS;
		}
		else if (isPlayerInPortSarimBoat) {
			return WalkToBankSubstate.CROSS_GANGPLANK;
		}
		else {
			return WalkToBankSubstate.WALK_TO_DEPOSIT_BOX;
		}
	}
	
	@Override
	protected Area getResourceArea() {
		return Constants.KARAMJA_FISHING_AREA;
	}

	@Override
	protected Area getBankArea() {
		return Constants.PORT_SARIM_DEPOSIT_BOX;
	}

	@Override
	protected void walkToResource() {
		switch (getWalkToResourceSubstate()){
			case WALK_TO_PORT_SARIM_SAILORS:
				walkToPortSarimSailors();
				break;
			case TALK_TO_PORT_SARIM_SAILORS:
				talkToPortSarimSailors();
				break;
			case CROSS_GANGPLANK:
				crossGangplank();
				break;
			case WALK_TO_KARAMJA_DOCK:
				walkToKaramjaDock();
				break;
			default:
				break;
		}
		
	}

	private void walkToPortSarimSailors() {
		getScript().localWalker.walkPath(Constants.PATH_FROM_PORT_SARIM_DEPOSIT_BOX_TO_KARAMJA_SAILORS);
	}
	
	private void talkToPortSarimSailors() {
		NPC portSarimSailor = getScript().npcs.closest(Constants.PORT_SARIM_KARAMJA_SAILORS);
		
		if (portSarimSailor != null) {
			portSarimSailor.interact("Pay-Fare");
		}
	}
	
	private void crossGangplank() {
		RS2Object gankplank = getScript().objects.closest("Gangplank");
		
		if (gankplank != null) {
			gankplank.interact("Cross");
		}
	}
	
	private void walkToKaramjaDock() {
		getScript().localWalker.walkPath(Constants.PATH_FROM_KARAMJA_PORT_TO_KARAMJA_DOCK);
	}

	@Override
	protected void walkToBank() {
		switch (getWalkToBankSubstate()){
			case WALK_TO_KARAMJA_SAILORS:
				walkToKaramjaSailors();
				break;
			case TALK_TO_KARAMJA_SAILORS:
				talkToKaramjaSailors();
				break;
			case CROSS_GANGPLANK:
				crossGangplank();
				break;
			case WALK_TO_DEPOSIT_BOX:
				walkToDepositBox();
				break;
		}
	}
	
	private void walkToKaramjaSailors() {
		getScript().localWalker.walkPath(Constants.reversePath(Constants.PATH_FROM_KARAMJA_PORT_TO_KARAMJA_DOCK));
	}
	
	private void talkToKaramjaSailors() {
		NPC karamjaSailor = getScript().npcs.closest(Constants.KARAMJA_PORT_SAILORS);
		
		if (karamjaSailor != null) {
			karamjaSailor.interact("Pay-Fare");
		}
	}
	
	private void walkToDepositBox() {
		getScript().localWalker.walkPath(Constants.reversePath(Constants.PATH_FROM_PORT_SARIM_DEPOSIT_BOX_TO_KARAMJA_SAILORS));
	}

	@Override
	protected int[] getResourceIDs() {
		return Constants.KARAMJA_FISHING_SPOT_IDS;
	}
	
	@Override
	public String toString() {
		return "Karamja " + super.toString();
	}
}
