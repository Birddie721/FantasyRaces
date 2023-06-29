package birddie.fantasyraces.cmd.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;

import birddie.fantasyraces.race.CapabilityRace;
import birddie.fantasyraces.race.Race;

public class CommandRace implements Command<CommandSource>{
private static final CommandRace CMD = new CommandRace();
	
	private CommandRace() {}
	
	public static LiteralArgumentBuilder<CommandSource> register(CommandDispatcher<CommandSource> dispatcher) {
        return Commands.literal("race")
                .executes(CMD);
    }
	

	@Override
	public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
		PlayerEntity player = context.getSource().getPlayerOrException();
		Race race = player.getCapability(CapabilityRace.RACE).orElse(null);
		context.getSource().sendSuccess(new StringTextComponent("Race = " + returnRace(race.getRace())), false);
		return 0;
	}
	
	private String returnRace(int Race) {
		switch(Race) {
		case 1: return "Dwarf";
		case 2: return "Elf";
		case 3: return "Halfling";
		default: return "Human";
		}
	}
}
