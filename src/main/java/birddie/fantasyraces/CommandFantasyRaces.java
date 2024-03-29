package birddie.fantasyraces;

import java.util.List;

import javax.annotation.Nullable;

import birddie.fantasyraces.proxy.CommonProxy;
import birddie.fantasyraces.race.IRace;
import birddie.fantasyraces.race.RaceMessage;
import birddie.fantasyraces.race.RaceProvider;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

/*
 * Playable Fantasy Races
 * 
 * This class contains all of the commands used in the mod.
 * 
 */

public class CommandFantasyRaces extends CommandBase{
	
	public static boolean showGUI = false;
	
	private static final String[] COMMANDS = new String[] {
			"help",
			"changerace"
	};
	
	@Override
	public String getName() {
		return "fantasyraces";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/fantasyraces <action>";
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos){
		if (args.length == 1) {
			return getListOfStringsMatchingLastWord(args, COMMANDS);
		}else {
			return null;
		}
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		IRace p = sender.getCommandSenderEntity().getCapability(RaceProvider.RACE, null);
		//serverside
		if (args.length == 0) {
			displayHelp(sender);
			return;
		}
		if(args.length > 0) {
			if ("help".equalsIgnoreCase(args[0])) {
				displayHelp(sender);
			} else if("changerace".equalsIgnoreCase(args[0])) {
				if(args.length == 1) {
					p.setRace(-1);
					CommonProxy.NETWORK_TO_CLIENT.sendTo(new RaceMessage(p, (EntityPlayer) sender.getCommandSenderEntity()), (EntityPlayerMP) sender.getCommandSenderEntity());
				}else if(args.length == 2) {
					try {
						if(parseInt(args[1]) == 0 || parseInt(args[1]) == 1|| parseInt(args[1]) == 2|| parseInt(args[1]) == 3) {
							p.setRace(parseInt(args[1]));
							CommonProxy.NETWORK_TO_CLIENT.sendTo(new RaceMessage(p, (EntityPlayer) sender.getCommandSenderEntity()), (EntityPlayerMP) sender.getCommandSenderEntity());
						}else {
							sender.sendMessage(new TextComponentString(I18n.format("fantasyraces.enterNumber")));
						}
					}catch(Exception e){
						sender.sendMessage(new TextComponentString(I18n.format("fantasyraces.enterNumber")));
					}
				}
			}else if("race".equalsIgnoreCase(args[0])){
				if(args.length == 1) {
					sender.sendMessage(new TextComponentString(I18n.format("fantasyraces.race") + " = " + sender.getCommandSenderEntity().getCapability(RaceProvider.RACE, null).getRace()));
				}
			}else {
				sender.sendMessage(new TextComponentString(I18n.format("fantasyraces.unknownCommand")));
			}
		}
	}
	
	public void displayHelp(ICommandSender sender) {
		sender.sendMessage(new TextComponentString(I18n.format("fantasyraces.help")));
		sender.sendMessage(new TextComponentString(I18n.format("fanatsyraces.changeRace")));
	}
}
