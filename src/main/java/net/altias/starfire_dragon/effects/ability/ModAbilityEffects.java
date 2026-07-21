package net.altias.starfire_dragon.effects.ability;

import by.dragonsurvivalteam.dragonsurvival.registry.dragon.ability.entity_effects.AbilityEntityEffect;
import net.altias.starfire_dragon.StarfireDragon;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.RegisterEvent;

@EventBusSubscriber(modid = StarfireDragon.MODID)
public class ModAbilityEffects {

    @SubscribeEvent
    public static void register(RegisterEvent event) {
        if (event.getRegistry() == AbilityEntityEffect.REGISTRY) {
            event.register(
                    AbilityEntityEffect.REGISTRY_KEY,
                    ResourceLocation.fromNamespaceAndPath("starfire_dragon", "starsight"),
                    () -> StarsightAbilityEffect.CODEC
            );
        }
    }
}
