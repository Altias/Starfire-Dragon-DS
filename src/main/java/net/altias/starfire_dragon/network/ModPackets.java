package net.altias.starfire_dragon.network;

import net.altias.starfire_dragon.StarfireDragon;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

@EventBusSubscriber(modid = StarfireDragon.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModPackets {

    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {
        var registrar = event.registrar("1");

        registrar.playToClient(
                StarsightTogglePacket.TYPE,
                StarsightTogglePacket.STREAM_CODEC,
                StarsightTogglePacket::handle
        );
    }
}
