package birddie.fantasyraces.cmd;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;

import birddie.fantasyraces.fantasyraces;
import birddie.fantasyraces.cmd.commands.CommandChangeRace;
import birddie.fantasyraces.cmd.commands.CommandHelp;
import birddie.fantasyraces.cmd.commands.CommandRace;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class CommandHandler {


	public static void register(CommandDispatcher<CommandSource> dispatcher) {
   	 	LiteralCommandNode<CommandSource> cmdFantasyRaces =dispatcher.register(
               Commands.literal("fantasyraces")
                       .then(CommandHelp.register(dispatcher))
                       .then(CommandChangeRace.register(dispatcher))
                       .then(CommandRace.register(dispatcher))
   	 	);
       
       dispatcher.register(Commands.literal(fantasyraces.MODID)
       		.redirect(cmdFantasyRaces));
		
	}

}
