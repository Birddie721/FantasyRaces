package birddie.fantasyraces.race;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import java.util.UUID;
import java.util.function.Supplier;

import birddie.fantasyraces.fantasyraces;

public class RacePacket {
    private static final String PROTOCOL_VERSION = "1.0";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
    		new ResourceLocation(fantasyraces.MODID, "fantasychannel"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void registerPackets() {
        int id = 0;
        CHANNEL.registerMessage(id++, RaceSyncPacket.class, RaceSyncPacket::encode, RaceSyncPacket::decode, RaceSyncPacket::handle);

    }

    public static class RaceSyncPacket {
        private int race;
        private UUID player;

        public RaceSyncPacket(int race, UUID player) {
            this.race = race;
            this.player = player;
        }
        

        public static void encode(RaceSyncPacket packet, PacketBuffer buffer) {
            buffer.writeInt(packet.race);
            buffer.writeUUID(packet.player);
        }

        public static RaceSyncPacket decode(PacketBuffer buffer) {
            int race = buffer.readInt();
            UUID player = buffer.readUUID();
            return new RaceSyncPacket(race, player);
        }

        public static void handle(RaceSyncPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
            NetworkEvent.Context context = contextSupplier.get();
            if (context.getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
                // Handle packet on the client side
                context.enqueueWork(() -> {
                	PlayerEntity sender = Minecraft.getInstance().level.getPlayerByUUID(packet.player);
            		if(sender != null) {
            			Race p = sender.getCapability(CapabilityRace.RACE).orElse(null);
            			if(p != null) {
            				p.setRace(packet.race);
            			}
            		}
                });
            } else if (context.getDirection() == NetworkDirection.PLAY_TO_SERVER) {
                // Handle packet on the server side
                context.enqueueWork(() -> {
                	PlayerEntity player = context.getSender();
            		if(player != null) {
            			Race p = player.getCapability(CapabilityRace.RACE).orElse(null);
            			if(p != null) {
            				p.setRace(packet.race);
            			}
            		}
                });
            }
            context.setPacketHandled(true);
        }
    }
}

