package birddie.fantasyraces.race;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class RaceStorage implements IStorage<IRace>{
	
	@Override
	public NBTBase writeNBT(Capability<IRace> capability, IRace instance, EnumFacing side) {
		return new NBTTagInt(instance.getRace());
	}

	@Override
	public void readNBT(Capability<IRace> capability, IRace instance, EnumFacing side, NBTBase nbt) {
		instance.setRace(((NBTPrimitive) nbt).getInt());
	}
	
}
