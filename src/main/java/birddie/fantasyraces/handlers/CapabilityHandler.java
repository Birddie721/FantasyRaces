package birddie.fantasyraces.handlers;

import birddie.fantasyraces.fantasyraces;
import birddie.fantasyraces.race.RaceProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
		if(!(event.getObject() instanceof EntityPlayer)) return;
		event.addCapability(RACE, new RaceProvider());
	}
}
