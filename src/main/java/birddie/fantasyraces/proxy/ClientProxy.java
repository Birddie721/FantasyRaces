package birddie.fantasyraces.proxy;

import birddie.fantasyraces.handlers.RegistryHandler;
import birddie.fantasyraces.handlers.RenderHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class ClientProxy extends CommonProxy{
	@EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
		super.preInit(event);
		RegistryHandler.Client();
		RenderHandler.registerEntityRenders();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        super.init(event);
        
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	super.postInit(event);
    }
    
    public IThreadListener getListener(MessageContext ctx) {
    	return ctx.side == Side.CLIENT ? Minecraft.getMinecraft() : super.getListener(ctx);
    }
    
    public EntityPlayer getPlayer(MessageContext ctx) {
    	return ctx.side == Side.CLIENT ? Minecraft.getMinecraft().player : super.getPlayer(ctx);
    }
}
