package birddie.fantasyraces.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.world.World;

public class EntityOrc extends EntityIronGolem{

	public EntityOrc(World worldIn) {
		super(worldIn);
	}
	
	public EntityIronGolem createChild(EntityAgeable ageable) {
		return new EntityOrc(world);
	}
	
}
