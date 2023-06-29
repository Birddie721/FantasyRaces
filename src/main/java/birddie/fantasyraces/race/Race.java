package birddie.fantasyraces.race;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class Race {
	private int race = -1;
	
	public Race() {
		this(0);
	}
	
	public Race(int r) {
		race = r;
	}
	
	public int getRace() {
		return this.race;
	}

	public void setRace(int race) {
		this.race = race;
	}
	
	
	public static class RaceStorage implements Capability.IStorage<Race>{
		
		@Override
		public INBT writeNBT(Capability<Race> capability, Race instance, Direction side) {
			return IntNBT.valueOf(instance.getRace());
		}

		@Override
		public void readNBT(Capability<Race> capability, Race instance, Direction side, INBT nbt) {
			if (nbt instanceof IntNBT) {
				instance.setRace(((IntNBT) nbt).getAsInt());
			}
		}
		
	}
	
	
	
	public static Race createADefaultInstance() {
		return new Race();
	}
	
}
