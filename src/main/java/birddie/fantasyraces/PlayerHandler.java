package birddie.fantasyraces;

import java.io.File;
import java.io.IOException;

import com.google.common.io.Files;

import birddie.fantasyraces.race.CapabilityRace;
import birddie.fantasyraces.race.Race;
import birddie.fantasyraces.race.RacePacket;
import birddie.fantasyraces.race.RacePacket.RaceSyncPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.PacketDistributor;

/*
 * Playable Fantasy Races
 * 
 * This class saves and loads the race data to keep player selected race persistent between logins
 * 
 */

public class PlayerHandler{
	
	@SubscribeEvent
	public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
		loadPlayerData((ServerPlayerEntity) event.getPlayer());
	}
	

	
	@SubscribeEvent
	public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
		savePlayerData(event.getPlayer());
	}
	
	

	public void savePlayerData(PlayerEntity player) {
		Race p = player.getCapability(CapabilityRace.RACE).orElse(null);
		World world = player.level;
		new File("FantasyRaces").mkdirs();
		File playerDataFile = new File("FantasyRaces/fantasyrace-" + String.valueOf(player.getUUID()) + String.valueOf(world.getServer().getWorldData().worldGenSettings().seed()));
		CompoundNBT tag = new CompoundNBT();
		tag.putInt("Race", p.getRace());
		try {
			Files.touch(playerDataFile);
			playerDataFile.createNewFile();
			CompressedStreamTools.write(tag, playerDataFile);
		}catch(IOException e) {
			System.out.println("Error writing player data." + e);
		}
		
	}
	
	public void loadPlayerData(ServerPlayerEntity player) {
		//serverside
		Race p = player.getCapability(CapabilityRace.RACE).orElse(null);
		World world = player.level;
		File playerDataFile = new File("FantasyRaces/fantasyrace-" + String.valueOf(player.getUUID()) + String.valueOf(world.getServer().getWorldData().worldGenSettings().seed()));
		if(player != null && playerDataFile.exists()) {
			try {
				CompoundNBT tag = CompressedStreamTools.read(playerDataFile);
				p.setRace(tag.getInt("Race"));
				RacePacket.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), new RaceSyncPacket(p.getRace(), player.getUUID()));
			}catch(IOException e){
				System.out.println("Error reading player data file. " + e);
				p.setRace(-1);
			}
		}else {
			p.setRace(-1);
			RacePacket.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), new RaceSyncPacket(p.getRace(), player.getUUID()));
		}
	}
	
}
