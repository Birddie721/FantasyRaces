package birddie.fantasyraces.race;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class AdvancementEffectMessage implements IMessage{

	private EntityPlayer player;
	
	public AdvancementEffectMessage() {
		this.player = null;
	}
	
	public AdvancementEffectMessage(EntityPlayer player) {
		this.player = player;
	}
	
	
	@Override
	public void fromBytes(ByteBuf buf) {
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		
	}

}
