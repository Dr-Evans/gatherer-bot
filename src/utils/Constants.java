package utils;

import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.utility.Area;

public class Constants {
	/* AREAS */
	public static final Area MINING_GUILD_VEIN_AREA = new Area(3025, 9732, 3055, 9749);
	public static final Area FALADOR_EAST_BANK_AREA = new Area(3009, 3355, 3018, 3358);
	public static final Area MINING_GUILD_UPPER_LADDERS = new Area(3014, 3336, 3024, 3342);
	public static final Area MINING_GUILD_LOWER_LADDERS = new Area(3016, 9736, 3024, 9743);
	
	/* VEIN IDS */
	public static final int[] MINING_GUILD_VEIN_IDS = {
		13714, 13706, //COAL 
		13718, 13719  //MITH
	};
	
	/* PATHS */
	public static final Position[] PATH_FROM_FALADOR_BANK_TO_MINING_GUILD_LADDERS = {
		new Position(3012, 3356, 0), //In Falador East Bank
		new Position(3026, 3350, 0), //Stone path
		new Position(3021, 3338, 0)  //In front of ladders
	};
}
