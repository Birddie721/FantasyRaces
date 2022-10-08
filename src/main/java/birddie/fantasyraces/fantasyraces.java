package birddie.fantasyraces;

import birddie.fantasyraces.proxy.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;


@Mod(modid = fantasyraces.MODID, name = fantasyraces.NAME, version = fantasyraces.VERSION, acceptedMinecraftVersions = "[1.12.2]")

public class fantasyraces
{
    public static final String MODID = "fantasyraces";
    public static final String NAME = "Birddie's Fantasy Races";
    public static final String VERSION = "beta-0.2";
    
    public static final String CLIENTPROXY = "birddie.fantasyraces.proxy.ClientProxy";
    public static final String COMMONPROXY = "birddie.fantasyraces.proxy.CommonProxy";
    
    @Mod.Instance(MODID)
    public static fantasyraces instance;

    @SidedProxy(clientSide = CLIENTPROXY, serverSide = COMMONPROXY)
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	event.getModMetadata().version = VERSION;
    	proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    	proxy.init(event);
    }
    
    @EventHandler
    public void onServerStart(FMLServerStartingEvent event) {
    	event.registerServerCommand(new CommandFantasyRaces());
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	proxy.postInit(event);
    }
}
