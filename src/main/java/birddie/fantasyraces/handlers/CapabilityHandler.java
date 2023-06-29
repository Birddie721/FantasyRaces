package birddie.fantasyraces.handlers;

import birddie.fantasyraces.fantasyraces;
import birddie.fantasyraces.race.RaceProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/*
 * Playable Fantasy Races
 * 
 * This class implements the Race capability that is given to every player so that they may become a different race
 * 
 */

public class CapabilityHandler {
	public static final ResourceLocation RACE = new ResourceLocation(fantasyraces.MODID,"race");
	
	@SubscribeEvent
	public void attachCapability(AttachCapabilitiesEvent<Entity> event) {
		if(!(event.getObject() instanceof PlayerEntity)) return;
		event.addCapability(RACE, new RaceProvider());
	}
}
