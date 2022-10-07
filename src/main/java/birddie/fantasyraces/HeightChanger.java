package birddie.fantasyraces;

import net.minecraftforge.client.event.PlayerSPPushOutOfBlocksEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import birddie.fantasyraces.race.IRace;
import birddie.fantasyraces.race.RaceProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;

public class HeightChanger {
	public final float EYE_POS = 0.9f;
	
		
		public void changeHeight(EntityPlayer player) {
			IRace p = player.getCapability(RaceProvider.RACE, null);
			switch(p.getRace()) {
			case 0: player.width = 0.6f;
					player.height = 1.8f;
					break;
			case 1: player.width = 0.7f;
					player.height = 1.4f;
					break;
			case 2: player.width = 0.6f;
					player.height = 1.9f;
					break;
			case 3: player.width = 0.3f;
					player.height = 0.9f;
					break;
		}
			player.eyeHeight = player.height * EYE_POS;
			double d0 = (double)player.width/2.0;
			player.setEntityBoundingBox(new AxisAlignedBB(player.posX - d0, player.posY, player.posZ - d0, player.posX + d0, player.posY + player.height, player.posZ + d0));
			//AttributeModifier hehe = new AttributeModifier("MAX HEALTH", 14.0, 0);
			//player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).removeModifier(hehe);
			//player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(hehe);
		}
		
		
		@SubscribeEvent
		public void onPlayerTick(TickEvent.PlayerTickEvent event) {
			EntityPlayer player = event.player;
			if(player != null){			
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
