package birddie.fantasyraces.race;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityRace {
	@CapabilityInject(Race.class)
	public static final Capability<Race> RACE = null;
	
	public static void register() {
		CapabilityManager.INSTANCE.register(
			Race.class,
			new Race.RaceStorage(),
			Race::createADefaultInstance);
	}
}
