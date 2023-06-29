package birddie.fantasyraces.cmd.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import birddie.fantasyraces.race.CapabilityRace;
import birddie.fantasyraces.race.Race;
import birddie.fantasyraces.race.RacePacket;
import birddie.fantasyraces.race.RacePacket.RaceSyncPacket;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.network.PacketDistributor;

public class CommandChangeRace implements Command<CommandSource>{
	
private static final CommandChangeRace CMD = new CommandChangeRace();
	
	private CommandChangeRace() {}
	
	public static LiteralArgumentBuilder<CommandSource> register(CommandDispatcher<CommandSource> dispatcher) {
        return Commands.literal("changerace")
                .requires(cs -> cs.hasPermission(2))
				.then(Commands.literal("0")
						.executes((context) -> {
							ServerPlayerEntity player = context.getSource().getPlayerOrException();
							Race race = player.getCapability(CapabilityRace.RACE).orElse(null);
							race.setRace(0);
							RacePacket.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), new RaceSyncPacket(race.getRace(), player.getUUID()));
							return 0;
						}))
				.then(Commands.argument("race", IntegerArgumentType.integer())
						.executes((context) -> {
							ServerPlayerEntity player = context.getSource().getPlayerOrException();
							Race race = player.getCapability(CapabilityRace.RACE).orElse(null);
							int raceID = IntegerArgumentType.getInteger(context,  "race");
							race.setRace(raceID);
							RacePacket.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), new RaceSyncPacket(race.getRace(), player.getUUID()));
							return 0;
						}))
				.executes(CMD);
    }
	

	@Override
	public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
		ServerPlayerEntity player = context.getSource().getPlayerOrException();
		Race race = player.getCapability(CapabilityRace.RACE).orElse(null);
		race.setRace(-1);
		RacePacket.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), new RaceSyncPacket(race.getRace(), player.getUUID()));
		return 0;
	}
}
