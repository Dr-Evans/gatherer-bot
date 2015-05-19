import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.input.mouse.MiniMapTileDestination;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import org.osbot.rs07.utility.Area;

import java.awt.*;
import java.util.ArrayList;

@ScriptManifest(author = "abotter", info = "Coal Miner", name = "AMiner", version = 0.1, logo = "")
public class Miner extends Script {
	private static final Area MINING_GUILD_VEIN_AREA = new Area(3026, 9732, 3054, 9756);
	private static final Area FALADOR_EAST_BANK_AREA = new Area(3010, 3355, 3016, 3358);
	private static final int[] COAL_ID = {13714, 13706};
	private static final int[] MITH_ID = {13718, 13719};
	private static final int[] BOTH_ID = {13714, 13706, 13718, 13719};
	
	private Position[] pathFromBankToLadders = {
			new Position(3013, 3354, 0), //In Falador East Bank
			new Position(3026, 3350, 0), //Stone path
			new Position(3021, 3338, 0)  //In front of ladders
	};
	
	private static final Position positionNextToLadders = new Position(3030, 9737, 0);
	
	private enum State {
		WALK_TO_MINE, WALK_TO_BANK, MINE, BANK, ANTIBAN
	};

	private State getState() {
		if (!inventory.isFull() && FALADOR_EAST_BANK_AREA.contains(myPlayer())){
			return State.WALK_TO_MINE;
		}
		else if (inventory.isFull() && MINING_GUILD_VEIN_AREA.contains(myPlayer())){
			return State.WALK_TO_BANK;
		}
		else if (inventory.isFull() && FALADOR_EAST_BANK_AREA.contains(myPlayer())){
			return State.BANK;
		}
		else if (!inventory.isFull() && MINING_GUILD_VEIN_AREA.contains(myPlayer())){
			return State.MINE;
		}
		else {
			return State.ANTIBAN;
		}
	}
	
	@Override
	public void onStart() {
		log("MINER STARTED");
	}
	
	@Override
	public int onLoop() throws InterruptedException {
		State state = getState();
		
		switch (state) {
			case ANTIBAN:
				//bot.closeSelf();
				break;
			case BANK:
				bank();
				break;
			case MINE:
				mine();
				break;
			case WALK_TO_BANK:
				walkToBank();
				break;
			case WALK_TO_MINE:
				walkToMine();
				break;
		}
		
		return random(200, 300);
	}
	
	@Override
	public void onExit() {
		log("MINER EXITED");
	}
	
	@Override
	public void onPaint(Graphics2D g){
		
	}
	
	private void walkToMine() throws InterruptedException {
		traversePath(pathFromBankToLadders, false);
		
		RS2Object ladder = objects.closest("Ladder");
		ladder.interact("Climb-down");
		
		traversePath(new Position[]{
				new Position(3026, 9732, 0),
				MINING_GUILD_VEIN_AREA.getRandomPosition(0) //TODO: Refine so we start mining away from other players
		}, false);
		
	}
	
	private void walkToBank() throws InterruptedException {
		walkTile(positionNextToLadders);
		
		RS2Object ladder = objects.closest("Ladder");
		ladder.interact("Climb-up");

		traversePath(pathFromBankToLadders, true);
	}
	
	private void bank() throws InterruptedException {
		RS2Object bankBooth = objects.closest("Bank booth");
		
	    if (bankBooth != null) {
	        if (bankBooth.interact("Bank")) {
	        	//Sleep until bank is open
	            while (!bank.isOpen())
	                sleep(250);
	            
	            bank.depositAll();
	        }
	    }
	}
	
	private void mine() {
		//TODO: Refine this if to stop mining if other miner is there or vein is already mined
		if (!myPlayer().isAnimating()) {
			RS2Object vein = selectVein();
             
            if (vein != null) {
                vein.interact("Mine");
            }
        }
	}
	
	//TODO: Refine vein selection
	private RS2Object selectVein() {
		return objects.closest(BOTH_ID);
	}
	
	private void traversePath(Position[] path, boolean reversed) throws InterruptedException {
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
	
	private boolean walkTile(Position p) throws InterruptedException {
		mouse.move(new MiniMapTileDestination(bot, p), false);
		sleep(random(150, 250));
		mouse.click(false);
		int failsafe = 0;
		while (failsafe < 10 && myPlayer().getPosition().distance(p) > 2) {
			sleep(200);
			failsafe++;
			if (myPlayer().isMoving())
				failsafe = 0;
		}
		if (failsafe == 10)
			return false;
		return true;
	}
}
