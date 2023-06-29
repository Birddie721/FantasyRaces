package birddie.fantasyraces.cmd.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;

public class CommandHelp implements Command<CommandSource>{
	
	private static final CommandHelp CMD = new CommandHelp();
	
	private CommandHelp() {}
	
	public static LiteralArgumentBuilder<CommandSource> register(CommandDispatcher<CommandSource> dispatcher) {
        return Commands.literal("help")
                .executes(CMD);
    }
	

	@Override
	public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
		context.getSource().sendSuccess(new StringTextComponent("§b/FantasyRaces help§7 - shows this page"), false);
		context.getSource().sendSuccess(new StringTextComponent("§b/FantasyRaces changerace§7 - allows the player to change their race"), false);
		context.getSource().sendSuccess(new StringTextComponent("§b/FantasyRaces race§7 - allows the player to see what race they are"), false);
		return 0;
	}

}
