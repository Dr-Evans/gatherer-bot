package utils;

import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.utility.Area;

public class Constants {
	/* AREAS */
	/* BANK AREAS */
	public static final Area FALADOR_EAST_BANK_AREA = new Area(3009, 3355, 3018, 3358);
	public static final Area EAST_VARROCK_BANK = new Area(3250, 3419, 3257, 3423);
	public static final Area WEST_VARROCK_BANK = new Area(3178, 3431, 3185, 3446);
	public static final Area DRAYNOR_BANK = new Area(3082, 3240, 3097, 3246);
	
	/* MINING AREAS */
	public static final Area MINING_GUILD_VEIN_AREA = new Area(3025, 9732, 3055, 9749);
	public static final Area MINING_GUILD_UPPER_LADDERS = new Area(3014, 3336, 3024, 3342);
	public static final Area MINING_GUILD_LOWER_LADDERS = new Area(3016, 9736, 3024, 9743);
	public static final Area BARBARIAN_VILLAGE_VEIN_AREA = new Area(3077, 3416, 3086, 3425);
	public static final Area EAST_VARROCK_VEIN_AREA = new Area(3278, 3360, 3291, 3372);
	
	/* FISHING AREAS */
	public static final Area DRAYNOR_FISHING_AREA = new Area(3084, 3226, 3089, 3232);
	
	
	
	/* IDS */
	/* VEIN IDS */
	public static final int[] MINING_GUILD_VEIN_IDS = {
		13714, 13706, //COAL 
		13718, 13719  //MITH
	};
	
	public static final int[] BARBARIAN_VILLAGE_VEIN_IDS = {
		14860, 14861, 14862, //COAL
		14863, 14864 		 //TIN
	};
	
	public static final int[] EAST_VARROCK_VEIN_IDS = {
		13447, 13448, 13449, //TIN
		13450, 13451, 13452, //COPPER
		13444, 13445, 13456  //IRON
	};
	
	/* FISHING IDS */
	public static final int[] DRAYNOR_FISHING_SPOT_IDS = {
		1525
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
	
	public static final Position[] PATH_FROM_EAST_VARROCK_BANK_TO_EAST_VARROCK_VEINS = {
		new Position(3254, 3421, 0), //Inside bank
		new Position(3258, 3428, 0),
		new Position(3273, 3426, 0),
		new Position(3282, 3415, 0),
		new Position(3290, 3402, 0),
		new Position(3290, 3385, 0),
		new Position(3287, 3370, 0) //Inside veins
	};
	
	public static final Position[] PATH_FROM_DRAYNOR_BANK_TO_DRAYNOR_FISHING_AREA = {
		new Position(3092, 3245, 0),
		new Position(3087, 3239, 0),
		new Position(3086, 3231, 0)
		
	};
}
