package birddie.fantasyraces.handlers;

import birddie.fantasyraces.fantasyraces;
import birddie.fantasyraces.race.AdvancementEffectMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class AdvancementMessageHelper implements IMessageHandler<AdvancementEffectMessage, IMessage>{

	@Override
	public IMessage onMessage(AdvancementEffectMessage message, MessageContext ctx) {
		IThreadListener thread = fantasyraces.proxy.getListener(ctx);
		final EntityPlayer player = fantasyraces.proxy.getPlayer(ctx);
		thread.addScheduledTask(new Runnable() {

			@Override
			public void run() {
				if(player != null) {
					player.addPotionEffect(new PotionEffect(Potion.getPotionById(1), 10, 0, false, false));
				}
			}
			
		});
		return null;
	}

}
