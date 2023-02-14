package birddie.fantasyraces;

import net.minecraftforge.client.event.PlayerSPPushOutOfBlocksEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;

import birddie.fantasyraces.race.IRace;
import birddie.fantasyraces.race.RaceProvider;

import java.util.UUID;

import com.artemis.artemislib.util.attributes.ArtemisLibAttributes;

/*
 * Playable Fantasy Races
 * 
 * This class changes the height of the player utilizing the Artemis Library
 * 
 */

public class HeightChanger {
	public final float EYE_POS = 0.9f;
	public static UUID heightUUID = UUID.fromString("38805ce4-4fef-11ed-bdc3-0242ac120002");
	public static UUID widthUUID = UUID.fromString("6bb14bbe-4fef-11ed-bdc3-0242ac120002");
	
	
		
		public void changeHeight(EntityPlayer player) {
			float height = 0.0f;
			float width = 0.0f;
			IRace p = player.getCapability(RaceProvider.RACE, null);
			
			switch(p.getRace()) {
			case 0: height = 0.0f;
					width = 0.0f;
					break;
			case 1: height = -0.2f;
					width = 0.0f;
					break;
			case 2: height = 0.1f;
					width = 0.0f;
					break;
			case 3: height = -0.5f;
					width = -0.5f;
					break;
			}
			AttributeModifier heightMod = new AttributeModifier(heightUUID, "HEIGHT", height, 0);
			AttributeModifier widthMod = new AttributeModifier(widthUUID, "HEIGHT", width, 0);
			player.getEntityAttribute(ArtemisLibAttributes.ENTITY_HEIGHT).removeModifier(heightUUID);
			player.getEntityAttribute(ArtemisLibAttributes.ENTITY_WIDTH).removeModifier(widthUUID);
			player.getEntityAttribute(ArtemisLibAttributes.ENTITY_HEIGHT).applyModifier(heightMod);
			player.getEntityAttribute(ArtemisLibAttributes.ENTITY_WIDTH).applyModifier(widthMod);

		}
		
		
		@SubscribeEvent
		public void onPlayerTick(TickEvent.PlayerTickEvent event) {
			if(event.player != null){			
				if(event.player.getEntityWorld().playerEntities.contains(event.player)) {
					changeHeight(event.player);
				}
			}	
		}
		
		
		
		@SubscribeEvent
	    public void onPushPlayerSPOutOfBlock(PlayerSPPushOutOfBlocksEvent event) {
			event.setCanceled(true);
			changeHeight(event.getEntityPlayer());
			AxisAlignedBB playerBB = event.getEntityPlayer().getEntityBoundingBox();
			event.setEntityBoundingBox(playerBB);
			
		}
		
}
