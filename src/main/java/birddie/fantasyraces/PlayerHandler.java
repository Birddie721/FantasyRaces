package birddie.fantasyraces;

import java.io.File;
import java.io.IOException;

import birddie.fantasyraces.proxy.CommonProxy;
import birddie.fantasyraces.race.IRace;
import birddie.fantasyraces.race.RaceMessage;
import birddie.fantasyraces.race.RaceProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class PlayerHandler{
	
	@SubscribeEvent
	public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
		//loadPlayerData(event.player);
		IRace p = event.player.getCapability(RaceProvider.RACE, null);
		p.setRace(-1);
	}
	
	@SubscribeEvent
	public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
		//savePlayerData(event.player);
	}
	
	

	public void savePlayerData(EntityPlayer player) {
		IRace p = player.getCapability(RaceProvider.RACE, null);
		World world = player.world;
		File playerDataFile = new File("FantasyRaces/fantasyrace-" + String.valueOf(player.getUniqueID()) + String.valueOf(world.getSeed()));
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("Race", p.getRace());
		try {
			CompressedStreamTools.write(tag, playerDataFile);
		}catch(IOException e) {
			System.out.println("Error writing player data.");
		}
		
	}
	
	public void loadPlayerData(EntityPlayer player) {
		//serverside
		IRace p = player.getCapability(RaceProvider.RACE, null);
		World world = player.world;
		File playerDataFile = new File("FantasyRaces/fantasyrace-" + String.valueOf(player.getUniqueID()) + String.valueOf(world.getSeed()));
		if(player != null && playerDataFile.exists()) {
			try {
				NBTTagCompound tag = CompressedStreamTools.read(playerDataFile);
				p.setRace(tag.getInteger("Race"));
				CommonProxy.NETWORK_TO_CLIENT.sendTo(new RaceMessage(p), (EntityPlayerMP) player);
			}catch(IOException e){
				System.out.println("Error reading player data file.");
			}
		}else {
			RaceChanger.nonRaced(player);
		}
	}
	
}
