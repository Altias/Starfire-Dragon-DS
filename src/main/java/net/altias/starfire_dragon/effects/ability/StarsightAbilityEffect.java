package net.altias.starfire_dragon.effects.ability;

import by.dragonsurvivalteam.dragonsurvival.DragonSurvival;
import by.dragonsurvivalteam.dragonsurvival.registry.datagen.Translation;
import by.dragonsurvivalteam.dragonsurvival.registry.dragon.ability.DragonAbilityInstance;
import by.dragonsurvivalteam.dragonsurvival.registry.dragon.ability.entity_effects.AbilityEntityEffect;
import com.mojang.serialization.MapCodec;
import net.altias.starfire_dragon.client.StarsightClient;
import net.altias.starfire_dragon.network.StarsightTogglePacket;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.List;

public record StarsightAbilityEffect() implements AbilityEntityEffect {

    public static final MapCodec<StarsightAbilityEffect> CODEC =
            MapCodec.unit(new StarsightAbilityEffect());

    @Override
    public void apply(ServerPlayer dragon, DragonAbilityInstance ability, Entity target) {
        if (target instanceof ServerPlayer player) {
            PacketDistributor.sendToPlayer(player, new StarsightTogglePacket(true));
        }
    }

    @Override
    public void remove(ServerPlayer dragon, DragonAbilityInstance ability, Entity target, boolean isAutoRemoval) {
        if (target instanceof ServerPlayer player) {
            PacketDistributor.sendToPlayer(player, new StarsightTogglePacket(false));
        }
    }

    @Override
    public List<ResourceLocation> getEffectIDs() {
        return List.of(DragonSurvival.res("starsight"));
    }

    @Override
    public List<MutableComponent> getDescription(Player dragon, DragonAbilityInstance ability) {
        return List.of(Component.translatable(Translation.Type.GUI.wrap("starsight_effect.starsight")));
    }

    @Override
    public MapCodec<? extends AbilityEntityEffect> entityCodec() {
        return CODEC;
    }
}
