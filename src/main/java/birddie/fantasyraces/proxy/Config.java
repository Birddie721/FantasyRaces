package birddie.fantasyraces.proxy;

import net.minecraftforge.common.config.Configuration;

public class Config{

	
	public static boolean isDwarfEnabled = true;
	public static float dwarfExtraMiningSpeed = 1.7f;
	public static float dwarfExtraAxeDamage = 1.25f;
	public static double dwarfExtraHearts = 2.0;
	public static boolean dwarfNightVision = true;
	public static boolean dwarfPoisonImmunity = true;
	public static float dwarfHungerDamage = 2.0f;
	public static float dwarfDrownDamage = 2.0f;
	public static boolean dwarfBonemeal = false;
	
	public static boolean isElfEnabled = true;
	public static float elfExtraBowDamage = 1.25f;
	public static double elfMovementSpeed = 1.2;
	public static boolean elfNightVision = true;
	public static float elfMiningSpeed = 0.8f;
	public static float elfFallDamage = 1.5f;
	
	public static boolean isHalflingEnabled = true;
	public static double halflingLuck = 5.0;
	public static double halflingDodgeChance = 0.4;
	public static boolean halflingWitherImmunity = true;
	public static float halflingDamageMultiplier = 1.5f;
	
	private Config() {}
	
	public static void syncConfig(Configuration Config) {
		try{
			Config.load();
			
			isDwarfEnabled = Config.getBoolean("1: isDwarfEnabled", "dwarf", true, "Are dwarves enabled?");
			dwarfExtraMiningSpeed = Config.getFloat("2: dwarfExtraMiningSpeed", "dwarf", 1.7f, 0.0f, 10.0f, "Modifier dwarves get to breaking blocks speed when under y=60.");
			dwarfExtraAxeDamage = Config.getFloat("3: dwarfExtraAxeDamage", "dwarf", 1.25f, 0.5f, 10.0f, "Multiplier on damage dwarves deal with axes");
			dwarfExtraHearts = (double) Config.getFloat("4: dwarfExtraHearts", "dwarf", 2.0f, 0.0f, 20.0f, "Amount of extra max health dwarves get in half hearts.");
			dwarfNightVision = Config.getBoolean("5: dwarfNightVision", "dwarf", true, "Do dwarves get nightvision under y=60?");
			dwarfPoisonImmunity = Config.getBoolean("6: dwarfPoisonImmunity", "dwarf", true, "Are dwarves immune to poison damage?");
			dwarfHungerDamage = Config.getFloat("7: dwarfHungerDamage", "dwarf", 2.0f, 0.5f, 10.0f, "Multiplier on damage dwarves take from starving.");
			dwarfDrownDamage = Config.getFloat("8: dwarfDrownDamage", "dwarf", 2.0f, 0.5f, 10.0f, "Multiplier on damage dwarves take from drowning.");
			dwarfBonemeal = Config.getBoolean("9: dwarfBonemeal", "dwarf", false, "Can dwarves use bonemeal?");
			
			isElfEnabled = Config.getBoolean("1: isElfEnabled", "elf", true, "Are elves enabled?");
			elfExtraBowDamage = Config.getFloat("2: elfExtraBowDamage", "elf", 1.25f, 0.5f, 10.0f, "Multiplier on damage elves deal with bows.");
			elfMovementSpeed = (double) Config.getFloat("3: elfMovementSpeed", "elf", 1.2f, 0.5f, 10.0f, "Multiplier on how fast elves move.");
			elfNightVision = Config.getBoolean("4: elfNightVision", "elf", true, "Do elves get nightvision above y=60?");
			elfMiningSpeed = Config.getFloat("5: elfMiningSpeed", "elf", 0.8f, 0.1f, 10.0f, "Multiplier elves get to breaking blocks speed when under y=60.");
			elfFallDamage = Config.getFloat("6: elfFallDamage", "elf", 1.5f, 0.0f, 10.0f, "Multiplier elves take on fall damage.");
			
			isHalflingEnabled = Config.getBoolean("1: isHalflingEnabled", "halfling", true, "Are halflings enabled?");
			halflingLuck = (double) Config.getFloat("2: halflingLuck", "halfling", 5.0f, 0.0f, 100.0f, "Amount of extra luck halflings get.");
			halflingDodgeChance = (double) Config.getFloat("3: halflingDodgeChance", "halfling", 0.4f, 0.0f, 1.0f, "The chance from 0 to 1 that a dwarf will dodge damage.");
			halflingWitherImmunity = Config.getBoolean("4: halflingWitherImmunity", "halfling", true, "Are halflings immune to wither damage?");
			halflingDamageMultiplier = Config.getFloat("5: halflingDamageMultiplier", "halfling", 1.5f, 0.0f, 10.0f, "Multiplier on all damage halflings take that isn't dodged.");
		}catch(Exception e) {
			
		}finally {
			if(Config.hasChanged()) Config.save();
		}
	}
	
}
