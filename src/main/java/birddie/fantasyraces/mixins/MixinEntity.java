package birddie.fantasyraces.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import birddie.fantasyraces.race.IRace;
import birddie.fantasyraces.race.RaceProvider;
import net.minecraft.entity.player.EntityPlayer;

@Mixin(EntityPlayer.class)
public class MixinEntity {
	/**
	 * Playable Fantasy Races
	 * @author Birddie
	 * 
	 * Returns the Y offset of this entity.
	 * This method overrides the default Y offset for when a player mounts something, so smaller races won't look like they are sitting in the mount
	 *
	 * @return The Y offset of this entity.
	 * @reason When the player model is shrunk by becoming a smaller race, the Y offset needs to be changed so those races function with mounts correctly
	 */
	@Overwrite
	public double getYOffset()
	{
		System.out.println("MIXIN OUT");
		double offset = -0.35D;
		EntityPlayer player = (EntityPlayer)(Object)this;
		IRace p = player.getCapability(RaceProvider.RACE, null);
		switch(p.getRace()) {
		case 1: offset = -0.25D;
				break;
		case 2: offset = -0.40D;
				break;
		case 3: offset = -0.02D;
				break;
		default:
			offset = -0.35D;
		}
		return(offset);
	}
	
}
