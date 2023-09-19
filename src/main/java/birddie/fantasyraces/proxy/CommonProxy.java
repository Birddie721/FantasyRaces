package birddie.fantasyraces.proxy;

import birddie.fantasyraces.HeightChanger;
import birddie.fantasyraces.PlayerHandler;
import birddie.fantasyraces.RaceChanger;
import birddie.fantasyraces.fantasyraces;
import birddie.fantasyraces.handlers.CapabilityHandler;
import birddie.fantasyraces.handlers.GuiHandler;
import birddie.fantasyraces.handlers.NewRaceSyncHandler;
import birddie.fantasyraces.handlers.RaceSyncHandler;
import birddie.fantasyraces.handlers.RegistryHandler;
import birddie.fantasyraces.race.AdvancementEffectMessage;
import birddie.fantasyraces.race.IRace;
import birddie.fantasyraces.race.NewRaceMessage;
import birddie.fantasyraces.race.Race;
import birddie.fantasyraces.race.RaceMessage;
import birddie.fantasyraces.race.RaceStorage;
import birddie.fantasyraces.handlers.AdvancementMessageHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy {
	public static SimpleNetworkWrapper NETWORK_TO_CLIENT;
	public static SimpleNetworkWrapper NETWORK_TO_SERVER;
	public static SimpleNetworkWrapper ADVANCEMENT_MESSAGE;
	@EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
		RegistryHandler.Common();
		event.getModMetadata().modId = fantasyraces.MODID;
    	event.getModMetadata().version = fantasyraces.VERSION;
    	NetworkRegistry.INSTANCE.registerGuiHandler(fantasyraces.instance, new GuiHandler());
    	NETWORK_TO_CLIENT = NetworkRegistry.INSTANCE.newSimpleChannel(fantasyraces.MODID + "CLIENT");
    	NETWORK_TO_CLIENT.registerMessage(RaceSyncHandler.class, RaceMessage.class, 1, Side.CLIENT);
    	NETWORK_TO_SERVER = NetworkRegistry.INSTANCE.newSimpleChannel(fantasyraces.MODID + "SERVER");
    	NETWORK_TO_SERVER.registerMessage(NewRaceSyncHandler.class, NewRaceMessage.class, 1, Side.SERVER);
    	ADVANCEMENT_MESSAGE = NetworkRegistry.INSTANCE.newSimpleChannel(fantasyraces.MODID + "SERVER ADVANCEMENT");
    	ADVANCEMENT_MESSAGE.registerMessage(AdvancementMessageHelper.class, AdvancementEffectMessage.class, 1, Side.SERVER);
    	CapabilityManager.INSTANCE.register(IRace.class, new RaceStorage(), Race::new);
    	RegistryHandler.preinitRegistries();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    	MinecraftForge.EVENT_BUS.register(new RaceSyncHandler());
    	MinecraftForge.EVENT_BUS.register(new HeightChanger());
        MinecraftForge.EVENT_BUS.register(new PlayerHandler());
        MinecraftForge.EVENT_BUS.register(new RaceChanger());
        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());

    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	
    }
    
    public IThreadListener getListener(MessageContext ctx) {
    	return (WorldServer) ctx.getServerHandler().player.world;
    }
    
    public EntityPlayer getPlayer(MessageContext ctx) {
    	return ctx.getServerHandler().player;
    }
    
}
