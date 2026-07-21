package net.altias.starfire_dragon.network;

import net.altias.starfire_dragon.client.StarsightClient;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record StarsightTogglePacket(boolean enabled) implements CustomPacketPayload {

    public static final Type<StarsightTogglePacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath("starfire_dragon", "starsight_toggle"));

    public static final StreamCodec<RegistryFriendlyByteBuf, StarsightTogglePacket> STREAM_CODEC =
            StreamCodec.of(
                    (buf, packet) -> buf.writeBoolean(packet.enabled),
                    buf -> new StarsightTogglePacket(buf.readBoolean())
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(StarsightTogglePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (packet.enabled()) {
                StarsightClient.enable();
            } else {
                StarsightClient.disable();
            }
        });
    }
}
