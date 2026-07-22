package net.altias.starfire_dragon.datagen.penalties;

import by.dragonsurvivalteam.dragonsurvival.DragonSurvival;
import by.dragonsurvivalteam.dragonsurvival.common.codecs.*;
import by.dragonsurvivalteam.dragonsurvival.common.codecs.duration_instance.DurationInstanceBase;
import by.dragonsurvivalteam.dragonsurvival.common.conditions.EntityCondition;
import by.dragonsurvivalteam.dragonsurvival.common.conditions.ItemCondition;
import by.dragonsurvivalteam.dragonsurvival.registry.DSAttributes;
import by.dragonsurvivalteam.dragonsurvival.registry.DSDamageTypes;
import by.dragonsurvivalteam.dragonsurvival.registry.DSEffects;
import by.dragonsurvivalteam.dragonsurvival.registry.DSItems;
import by.dragonsurvivalteam.dragonsurvival.registry.datagen.Translation;
import by.dragonsurvivalteam.dragonsurvival.registry.datagen.tags.DSBlockTags;
import by.dragonsurvivalteam.dragonsurvival.registry.datagen.tags.DSEntityTypeTags;
import by.dragonsurvivalteam.dragonsurvival.registry.datagen.tags.DSItemTags;
import by.dragonsurvivalteam.dragonsurvival.registry.dragon.ability.entity_effects.ModifierEffect;
import by.dragonsurvivalteam.dragonsurvival.registry.dragon.penalty.*;
import by.dragonsurvivalteam.dragonsurvival.util.Functions;
import net.altias.starfire_dragon.effects.ModEffects;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.predicates.AllOfCondition;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.List;
import java.util.Optional;

import static by.dragonsurvivalteam.dragonsurvival.registry.dragon.penalty.DragonPenalties.key;

public class StarfireDragonPenalties {
    @Translation.Translations({@Translation(
            type = Translation.Type.PENALTY_DESCRIPTION,
            comments = {"■ Starfire dragons are severely weakened deep underground, where the stars cannot reach them.\n", "■ The skill §2«Inner Flame»§r §7can help you resist this.\n"}
    ), @Translation(
            type = Translation.Type.PENALTY,
            comments = {"Starless Dark"}
    )})
    public static final ResourceKey<DragonPenalty> STARLESS_DARK = key("starless_dark");

    public static void registerPenalties(BootstrapContext<DragonPenalty> context) {
        context.register(STARLESS_DARK, new DragonPenalty(Optional.of(DragonSurvival.res("penalties/starfire/starless_dark")), Optional.of(AllOfCondition.allOf(new LootItemCondition.Builder[]{Condition.thisEntity(EntityPredicate.Builder.entity().located(LocationPredicate.Builder.location().setY(MinMaxBounds.Doubles.atMost(-1))).build()), Condition.thisEntity(EntityCondition.hasEffect(new Holder[]{ModEffects.INNER_FLAME})).invert()}).build()), new ModifierPenalty(List.of(new ModifierWithDuration(DurationInstanceBase.create(DragonSurvival.res("starless_dark")).removeAutomatically().customIcon(DragonSurvival.res("textures/ability_effect/starless_dark.png")).build(), List.of(Modifier.per(Attributes.ATTACK_DAMAGE, -0.25F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), Modifier.per(Attributes.BLOCK_BREAK_SPEED, -0.3F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL))))), PenaltyTrigger.instant()));
    }
}
