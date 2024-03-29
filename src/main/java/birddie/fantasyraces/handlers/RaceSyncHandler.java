package birddie.fantasyraces.handlers;

import birddie.fantasyraces.fantasyraces;
import birddie.fantasyraces.race.IRace;
import birddie.fantasyraces.race.RaceMessage;
import birddie.fantasyraces.race.RaceProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/*
 * Playable Fantasy Races
 * 
 * This class works to get the race to sync to the client.
 * NewRaceSyncHandler can only provide the race to the server.
 * 
 */

public class RaceSyncHandler implements IMessageHandler<RaceMessage, IMessage>{

	@Override
	public IMessage onMessage(RaceMessage message, MessageContext ctx) {
		IThreadListener thread = fantasyraces.proxy.getListener(ctx);
		final EntityPlayer player = fantasyraces.proxy.getPlayer(ctx);
		final EntityPlayer sender = message.getPlayer();
		final IRace race = message.getRace();
		thread.addScheduledTask(new Runnable() {

			@Override
			public void run() {
				if(player != null) {
					IRace p = sender.getCapability(RaceProvider.RACE, null);
					if(p!=null && race!=null) {
						p.setRace(race.getRace());
					}
				}
			}
			
		});
		return null;
	}

}
