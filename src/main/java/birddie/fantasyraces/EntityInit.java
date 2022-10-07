package birddie.fantasyraces;

import net.minecraftforge.fml.common.registry.EntityRegistry;
import birddie.fantasyraces.entity.EntityOrc;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class EntityInit {
	private final static int ORC_ID = 120;
	
	public static void registerEntities() {
		registerEntity("orc", EntityOrc.class, ORC_ID, 50, 27904, 9201730);
	}
	
	private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2) {
		EntityRegistry.registerModEntity(new ResourceLocation(fantasyraces.MODID + ":" + name), entity, name, id, fantasyraces.instance, range, 1, true, color1, color2);
	}
}
