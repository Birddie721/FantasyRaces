package birddie.fantasyraces.race;

import java.util.UUID;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class RaceMessage implements IMessage{

	private IRace race;
	private EntityPlayer player;
	
	public RaceMessage() {
		this.race = null;
	}
	
	public RaceMessage(IRace race, EntityPlayer player) {
		this.race = race;
		this.player = player;
	}
	
	
	@Override
	public void fromBytes(ByteBuf buf) {
		try {
			if(this.race == null) {
				this.race = new Race();
			}
			this.race.setRace(buf.readInt());
			this.player = (Minecraft.getMinecraft().world.getPlayerEntityByUUID(new UUID(buf.readLong(), buf.readLong())));
		}catch(Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		if(this.race !=null) {
			buf.writeInt(this.race.getRace());
		}	
		if(this.player != null) {
			buf.writeLong(this.player.getPersistentID().getMostSignificantBits());
			buf.writeLong(this.player.getPersistentID().getLeastSignificantBits());
		}

	}
	
	public void setRace(IRace race) {
		this.race = race;
	}
	
	public IRace getRace() {
		return this.race;
	}
	
	public EntityPlayer getPlayer() {
		return this.player;
	}

}
