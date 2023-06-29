package birddie.fantasyraces;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import birddie.fantasyraces.race.CapabilityRace;
import birddie.fantasyraces.race.Race;
import java.util.UUID;

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
	
		
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void preRenderEvent(RenderPlayerEvent.Pre event) {
			float width;
			float height;
			width = getWidth(event.getPlayer());
			height = getHeight(event.getPlayer());
			event.getMatrixStack().pushPose();
			event.getMatrixStack().scale(width, height, width);
			if(event.getEntity().isCrouching() && height < 0.2F){
				event.getMatrixStack().translate(0, 1.0, 0);
			}
		}
		
		 @SubscribeEvent
		 @OnlyIn(Dist.CLIENT)
		 public void onRenderPlayerPost(RenderPlayerEvent.Post event){
			 event.getMatrixStack().popPose();
		 }
		
		 float getHeight(PlayerEntity player) {
			 Race p = player.getCapability(CapabilityRace.RACE).orElse(null);
				
				switch(p.getRace()) {
				case 1: return 0.77f;
				case 2: return 1.05f;
				case 3: return 0.5f;
				default: return 1.0f;
				}
		 }
		 
		 float getWidth(PlayerEntity player) {
			 Race p = player.getCapability(CapabilityRace.RACE).orElse(null);
				
				switch(p.getRace()) {
				case 3: return 0.5f;
				default: return 1.0f;
				}
		 }
		
}
