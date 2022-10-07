package birddie.fantasyraces.handlers;

import birddie.fantasyraces.entity.EntityOrc;
import birddie.fantasyraces.entity.render.RenderOrc;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {
	public static void registerEntityRenders() {
		
		RenderingRegistry.registerEntityRenderingHandler(EntityOrc.class, new IRenderFactory<EntityOrc>()
				{
					@Override
					public Render<? super EntityOrc> createRenderFor(RenderManager manager){
						return new RenderOrc(manager);
					}
				});
	}
}
