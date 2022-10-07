package birddie.fantasyraces.race;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class RaceProvider implements ICapabilitySerializable<NBTBase>{
	@CapabilityInject(IRace.class)
	public static final Capability<IRace> RACE = null;
	
	private IRace instance = RACE.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == RACE;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		//if (RACE != null && capability == RACE)return (T)RACE.<T> cast(this.instance);
		//return null;
		return capability == RACE ? RACE.<T> cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return RACE.getStorage().writeNBT(RACE, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		RACE.getStorage().readNBT(RACE, this.instance, null, nbt);
	}

}
