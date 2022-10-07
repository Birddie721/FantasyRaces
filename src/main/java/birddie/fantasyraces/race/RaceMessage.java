package birddie.fantasyraces.race;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class RaceMessage implements IMessage{

	private IRace race;
	
	public RaceMessage() {
		this.race = null;
	}
	
	public RaceMessage(IRace race) {
		this.race = race;
	}
	
	
	@Override
	public void fromBytes(ByteBuf buf) {
		if(this.race != null) {
			this.race.setRace(buf.readInt());
		}else {
			this.race = new Race();
			this.race.setRace(buf.readInt());
		}	
	}

	@Override
	public void toBytes(ByteBuf buf) {
		if(this.race !=null) {
			buf.writeInt(this.race.getRace());
		}	
	}
	
	public void setRace(IRace race) {
		this.race = race;
	}
	
	public IRace getRace() {
		return this.race;
	}

}
