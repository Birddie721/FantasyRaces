package birddie.fantasyraces.race;

public class Race implements IRace{
	private int race = -1;
	
	@Override
	public int getRace() {
		return this.race;
	}

	@Override
	public void setRace(int race) {
		this.race = race;
	}
	
}
