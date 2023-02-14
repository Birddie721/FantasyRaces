package birddie.fantasyraces;

import java.io.File;
import java.io.IOException;

import com.google.common.io.Files;

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

/*
 * Playable Fantasy Races
 * 
 * This class saves and loads the race data to keep player selected race persistent between logins
 * 
 */

public class PlayerHandler{
	
	@SubscribeEvent
	public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
		loadPlayerData(event.player);
	}
	

	
	@SubscribeEvent
	public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
		savePlayerData(event.player);
	}
	
	

	public void savePlayerData(EntityPlayer player) {
		IRace p = player.getCapability(RaceProvider.RACE, null);
		World world = player.world;
		new File("FantasyRaces").mkdirs();
		File playerDataFile = new File("FantasyRaces/fantasyrace-" + String.valueOf(player.getUniqueID()) + String.valueOf(world.getSeed()));
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("Race", p.getRace());
		try {
			Files.touch(playerDataFile);
			playerDataFile.createNewFile();
			CompressedStreamTools.write(tag, playerDataFile);
		}catch(IOException e) {
			System.out.println("Error writing player data." + e);
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
			}catch(IOException e){
				System.out.println("Error reading player data file. " + e);
				p.setRace(-1);
			}
		}else {
			p.setRace(-1);
		}
		CommonProxy.NETWORK_TO_CLIENT.sendTo(new RaceMessage(p, player), (EntityPlayerMP) player);
	}
	
}
