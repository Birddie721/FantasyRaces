package birddie.fantasyraces.race;

import javax.annotation.Nonnull;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class RaceProvider implements ICapabilitySerializable<INBT>{

	
	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction facing) {
		if (CapabilityRace.RACE == capability) {
			return (LazyOptional<T>)LazyOptional.of(()-> race);
		}
		return LazyOptional.empty();
	}

	@Override
	public INBT serializeNBT() {
		return CapabilityRace.RACE.writeNBT(race, null);
	}

	@Override
	public void deserializeNBT(INBT nbt) {
		CapabilityRace.RACE.readNBT(race, null, nbt);
	}
	
	private Race race = new Race();

}
