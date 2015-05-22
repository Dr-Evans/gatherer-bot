package utils;

import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.utility.Area;

public class Constants {
	/* AREAS */
	public static final Area MINING_GUILD_VEIN_AREA = new Area(3025, 9732, 3055, 9749);
	public static final Area FALADOR_EAST_BANK_AREA = new Area(3009, 3355, 3018, 3358);
	public static final Area MINING_GUILD_UPPER_LADDERS = new Area(3014, 3336, 3024, 3342);
	public static final Area MINING_GUILD_LOWER_LADDERS = new Area(3016, 9736, 3024, 9743);
	
	public static final Area BARBARIAN_VILLAGE_VEIN_AREA = new Area(3077, 3416, 3086, 3425);
	public static final Area WEST_VARROCK_BANK = new Area(3178, 3431, 3185, 3446);
	
	/* VEIN IDS */
	public static final int[] MINING_GUILD_VEIN_IDS = {
		13714, 13706, //COAL 
		13718, 13719  //MITH
	};
	
	public static final int[] BARBARIAN_VILLAGE_VEIN_IDS = {
		14860, 14861, 14862, //COAL
		14863, 14864 		 //TIN
	};
	
	/* PATHS */
	public static final Position[] PATH_FROM_FALADOR_BANK_TO_MINING_GUILD_LADDERS = {
		new Position(3012, 3356, 0), //In Falador East Bank
		new Position(3026, 3350, 0), //Stone path
		new Position(3021, 3338, 0)  //In front of ladders
	};
	
	public static final Position[] PATH_FROM_WEST_VARROCK_BANK_TO_BARBARIAN_VILLAGE_VEINS = {
		new Position(3183, 3435, 0), //Inside west barrock bank
		new Position(3170, 3428, 0), //Just west of west varrock bank
		new Position(3157, 3418, 0), //South of Romeo and Juliet
		new Position(3141, 3416, 0),
		new Position(3127, 3414, 0),
		new Position(3113, 3420, 0), //Path before bridge
		new Position(3098, 3420, 0), //Bridge
		new Position(3084, 3420, 0)  //Barbarian Village
	};
}
