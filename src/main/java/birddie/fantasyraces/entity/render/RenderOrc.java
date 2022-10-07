package birddie.fantasyraces.entity.render;

import birddie.fantasyraces.fantasyraces;
import birddie.fantasyraces.entity.EntityOrc;
import birddie.fantasyraces.entity.model.Halfling;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderOrc extends RenderLiving<EntityOrc>{

	public static final ResourceLocation TEXTURES = new ResourceLocation(fantasyraces.MODID + ":textures/entity/orc.png");
	
	public RenderOrc(RenderManager manager) {
		super(manager, new Halfling(), 0.75F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityOrc entity) {
		// TODO Auto-generated method stub
		return TEXTURES;
	}
	
	
	protected void applyRotations(EntityOrc entityLiving, float p_77043_2_, float rotationYaw, float partialTicks){
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}
}
