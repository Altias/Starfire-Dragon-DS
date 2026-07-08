package net.altias.starfire_dragon.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RenderLivingEvent;


@EventBusSubscriber(
        modid = "starfire_dragon",
        value = Dist.CLIENT
)
public class ClientEvents {

    @SubscribeEvent
    public static void clientTick(ClientTickEvent.Post event) {
        SoulSeekerClient.tick(Minecraft.getInstance());


    }

}
