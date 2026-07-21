package net.altias.starfire_dragon.datagen.abilities;

import by.dragonsurvivalteam.dragonsurvival.DragonSurvival;
import by.dragonsurvivalteam.dragonsurvival.common.codecs.*;
import by.dragonsurvivalteam.dragonsurvival.common.codecs.ability.ActionContainer;
import by.dragonsurvivalteam.dragonsurvival.common.codecs.ability.ManaCost;
import by.dragonsurvivalteam.dragonsurvival.common.codecs.ability.animation.AnimationKey;
import by.dragonsurvivalteam.dragonsurvival.common.codecs.ability.animation.AnimationLayer;
import by.dragonsurvivalteam.dragonsurvival.common.codecs.ability.animation.SimpleAbilityAnimation;
import by.dragonsurvivalteam.dragonsurvival.common.codecs.duration_instance.DurationInstanceBase;
import by.dragonsurvivalteam.dragonsurvival.common.conditions.BlockCondition;
import by.dragonsurvivalteam.dragonsurvival.common.conditions.EntityCondition;
import by.dragonsurvivalteam.dragonsurvival.common.particles.LargeFireParticleOption;
import by.dragonsurvivalteam.dragonsurvival.common.particles.SmallFireParticleOption;
import by.dragonsurvivalteam.dragonsurvival.registry.DSAttributes;
import by.dragonsurvivalteam.dragonsurvival.registry.DSDamageTypes;
import by.dragonsurvivalteam.dragonsurvival.registry.DSEffects;
import by.dragonsurvivalteam.dragonsurvival.registry.DSSounds;
import by.dragonsurvivalteam.dragonsurvival.registry.datagen.Translation;
import by.dragonsurvivalteam.dragonsurvival.registry.dragon.ability.DragonAbilities;
import by.dragonsurvivalteam.dragonsurvival.registry.dragon.ability.DragonAbility;
import by.dragonsurvivalteam.dragonsurvival.registry.dragon.ability.activation.*;
import by.dragonsurvivalteam.dragonsurvival.registry.dragon.ability.block_effects.BlockBreakEffect;
import by.dragonsurvivalteam.dragonsurvival.registry.dragon.ability.block_effects.FireEffect;
import by.dragonsurvivalteam.dragonsurvival.registry.dragon.ability.entity_effects.*;
import by.dragonsurvivalteam.dragonsurvival.registry.dragon.ability.targeting.*;
import by.dragonsurvivalteam.dragonsurvival.registry.dragon.ability.upgrade.ConditionUpgrade;
import by.dragonsurvivalteam.dragonsurvival.registry.dragon.ability.upgrade.ExperienceLevelUpgrade;
import by.dragonsurvivalteam.dragonsurvival.registry.dragon.ability.upgrade.ExperiencePointsUpgrade;
import by.dragonsurvivalteam.dragonsurvival.util.Functions;
import net.altias.starfire_dragon.effects.ModEffects;
import net.altias.starfire_dragon.effects.ability.StarsightAbilityEffect;
import net.altias.starfire_dragon.loot_conditions.IsNightCondition;
import net.altias.starfire_dragon.particles.options.GoldFireParticleOption;
import net.altias.starfire_dragon.particles.options.GoldLargeFireParticleOption;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.AllOfCondition;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.InvertedLootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.NeoForgeMod;

import java.util.List;
import java.util.Optional;

public class StarfireDragonAbilities {
    @Translation.Translations({@Translation(
            type = Translation.Type.ABILITY_DESCRIPTION,
            comments = {"■ The stream of fire that §cignites§r§7 enemies, items and blocks. Is able to §cdestroy§r§7 some blocks.\n", "■ §fRange§r§7 depends on age of the dragon.\n", "■ §8Cannot be used under water, and during rain.§r"}
    ), @Translation(
            type = Translation.Type.ABILITY,
            comments = {"Golden Fire Breath"}
    )})
    public static final ResourceKey<DragonAbility> GOLDEN_FIRE_BREATH = DragonAbilities.key("golden_fire_breath");

    @Translation.Translations({@Translation(
            type = Translation.Type.ABILITY_DESCRIPTION,
            comments = {"■ The power of stars servers as §2night vision§r for you."}
    ), @Translation(
            type = Translation.Type.ABILITY,
            comments = {"Starsight"}
    )})
    public static final ResourceKey<DragonAbility> STARSIGHT = DragonAbilities.key("starsight");

    @Translation.Translations({@Translation(
            type = Translation.Type.ABILITY_DESCRIPTION,
            comments = {"■ Upgrading this ability increases your §2maximum mana pool§r§7 and allows to restore mana by standing on §fhot blocks§r§7.\n", "■ §8The more levels you have, the more mana you get automatically."}
    ), @Translation(
            type = Translation.Type.ABILITY,
            comments = {"Starfire Magic"}
    )})
    public static final ResourceKey<DragonAbility> STARFIRE_MAGIC = DragonAbilities.key("starfire_magic");

    @Translation.Translations({@Translation(
            type = Translation.Type.ABILITY_DESCRIPTION,
            comments = {"■ You can somewhat maintain your §2magic§r§7 in §fthe deep undground§r§7 by upgrading this ability.\n"}
    ), @Translation(
            type = Translation.Type.ABILITY,
            comments = {"Inner Flame"}
    )})
    public static final ResourceKey<DragonAbility> INNER_FLAME= DragonAbilities.key("inner_flame");

    @Translation.Translations({@Translation(
            type = Translation.Type.ABILITY_DESCRIPTION,
            comments = {"■ Your target has a chance to receive the §c«Burned»§r effect from your attacks.\n", "■ The effect deals damage when the target §fmoves§r§7. The faster the movement, the §fmore damage§r§7 is done.\n", "■ §8Creatures with fire resistance are immune to this effect.§r"}
    ), @Translation(
            type = Translation.Type.ABILITY,
            comments = {"Burn"}
    )})
    public static final ResourceKey<DragonAbility> BURN = DragonAbilities.key("star_burn");

    @Translation.Translations({@Translation(
            type = Translation.Type.ABILITY_DESCRIPTION,
            comments = {"■ Dragons use §flevitation§r§7 to §2fly§r§7, but are rarely born with that ability."}
    ), @Translation(
            type = Translation.Type.ABILITY,
            comments = {"Starfire Wings"}
    )})
    public static final ResourceKey<DragonAbility> STARFIRE_WINGS = DragonAbilities.key("starfire_wings");

    @Translation.Translations({@Translation(
            type = Translation.Type.ABILITY_DESCRIPTION,
            comments = {"■ You can §2spin§r§7 through the §fair§r§7 and in §flava§r§7, boosting your speed. Head to §fthe End§r §7to learn this skill."}
    ), @Translation(
            type = Translation.Type.ABILITY,
            comments = {"Starfire Spin"}
    )})
    public static final ResourceKey<DragonAbility> STARFIRE_SPIN = DragonAbilities.key("starfire_spin");

    @Translation.Translations({@Translation(
            type = Translation.Type.ABILITY_DESCRIPTION,
            comments = {"■ Starfire dragons have innate §2immunity to fire§r."}
    ), @Translation(
            type = Translation.Type.ABILITY,
            comments = {"Fire Immunity"}
    )})
    public static final ResourceKey<DragonAbility> STARFIRE_IMMUNITY = DragonAbilities.key("starfire_immunity");

    @Translation.Translations({@Translation(
            type = Translation.Type.ABILITY_DESCRIPTION,
            comments = {"■ Starfire dragons are strongest at night, when starlight grants them §2strength and speed§r§7"}
    ), @Translation(
            type = Translation.Type.ABILITY,
            comments = {"Fire of Stars"}
    )})
    public static final ResourceKey<DragonAbility> FIRE_OF_STARS = DragonAbilities.key("fire_of_stars");

    @Translation.Translations({@Translation(
            type = Translation.Type.ABILITY_DESCRIPTION,
            comments = {"■ All living beings have a hint of starfire in their soul. This allows Starfire Dragons to §2sense living beings through walls§r§7"}
    ), @Translation(
            type = Translation.Type.ABILITY,
            comments = {"Soul Seeker"}
    )})
    public static final ResourceKey<DragonAbility> SOUL_SEEKER = DragonAbilities.key("soul_seeker");


    public StarfireDragonAbilities() {
    }

    public static void registerAbilities(BootstrapContext<DragonAbility> context) {
        registerActiveAbilities(context);
        registerPassiveAbilities(context);
    }

    private static void registerActiveAbilities(BootstrapContext<DragonAbility> context) {
        context.register(GOLDEN_FIRE_BREATH, new DragonAbility(new ChanneledActivation(Optional.empty(), Optional.of(ManaCost.ticking(LevelBasedValue.constant(0.025F))), Optional.of(LevelBasedValue.constant((float) Functions.secondsToTicks((double)1.0F))), Optional.of(LevelBasedValue.constant((float)Functions.secondsToTicks((double)2.0F))), Optional.empty(), Notification.DEFAULT, true, Sound.create().start((SoundEvent) DSSounds.FIRE_BREATH_START.get()).looping((SoundEvent)DSSounds.FIRE_BREATH_LOOP.get()).end((SoundEvent)DSSounds.FIRE_BREATH_END.get()).optional(), Animations.create().startAndCharging(SimpleAbilityAnimation.create(AnimationKey.SPELL_CHARGE, AnimationLayer.BREATH).transitionLength(5).build()).looping(SimpleAbilityAnimation.create(AnimationKey.BREATH, AnimationLayer.BREATH).transitionLength(5).build()).optional()), Optional.of(new ExperienceLevelUpgrade(4, LevelBasedValue.lookup(List.of(0.0F, 10.0F, 30.0F, 50.0F), LevelBasedValue.perLevel(15.0F)))), Optional.of(Condition.thisEntity(EntityCondition.isEyeInFluid(NeoForgeMod.WATER_TYPE)).or(Condition.thisEntity(EntityCondition.isInRainOrSnow())).build()), List.of(new ActionContainer(new DragonBreathTarget(AbilityTargeting.entity(Condition.thisEntity(EntityCondition.isLiving()).build(), List.of(new DamageEffect(context.lookup(Registries.DAMAGE_TYPE).getOrThrow(DSDamageTypes.FIRE_BREATH), LevelBasedValue.perLevel(3.0F), DSAttributes.DRAGON_ABILITY_DAMAGE, DamageEffect.DEFAULT_EXPRESSION, false), new IgniteEffect(LevelBasedValue.perLevel((float)Functions.secondsToTicks((double)5.0F))), new PotionEffect(PotionData.create(new Holder[]{DSEffects.BURN}).duration(10).probability(0.3F).build())), TargetingMode.NON_ALLIES), LevelBasedValue.constant(1.0F)), ActionContainer.TriggerPoint.DEFAULT, LevelBasedValue.constant(10.0F)), new ActionContainer(new DragonBreathTarget(AbilityTargeting.block(List.of(new FireEffect(LevelBasedValue.constant(0.05F)), new BlockBreakEffect(BlockCondition.blocks(new Block[]{Blocks.SNOW, Blocks.SHORT_GRASS}), LevelBasedValue.constant(1.0F), false), new BlockBreakEffect(BlockCondition.blocks(new Block[]{Blocks.SNOW_BLOCK, Blocks.POWDER_SNOW}), LevelBasedValue.perLevel(0.05F), false))), LevelBasedValue.constant(1.0F)), ActionContainer.TriggerPoint.DEFAULT, LevelBasedValue.constant(1.0F)), new ActionContainer(new SelfTarget(AbilityTargeting.entity(List.of(new BreathParticlesEffect(0.04F, 0.02F, new GoldFireParticleOption(37.0F, true), new GoldLargeFireParticleOption(37.0F, false))), TargetingMode.ALL)), ActionContainer.TriggerPoint.DEFAULT, LevelBasedValue.constant(1.0F))), true, new LevelBasedResource(List.of(new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/nether_breath_0"), 0), new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/nether_breath_1"), 1), new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/nether_breath_2"), 2), new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/nether_breath_3"), 3), new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/nether_breath_4"), 4)))));
        context.register(SOUL_SEEKER, new DragonAbility(new SimpleActivation(Optional.of(LevelBasedValue.constant(1.0F)), Optional.of(LevelBasedValue.constant((float)Functions.secondsToTicks((double)2.0F))), Optional.of(LevelBasedValue.constant((float)Functions.secondsToTicks((double)30.0F))), Notification.DEFAULT, false, Sound.create().end(SoundEvents.UI_TOAST_IN).optional(), Animations.create().startAndCharging(SimpleAbilityAnimation.create(AnimationKey.CAST_MAGIC_ALT, AnimationLayer.BASE).transitionLength(5).build()).end(SimpleAbilityAnimation.create(AnimationKey.MAGIC_ALT, AnimationLayer.BASE).transitionLength(4).build()).optional()), Optional.of(new ExperiencePointsUpgrade(4, LevelBasedValue.lookup(List.of(0.0F, 25.0F, 45.0F, 60.0F), LevelBasedValue.perLevel(15.0F)))), Optional.empty(), List.of(new ActionContainer(new SelfTarget(AbilityTargeting.entity(PotionEffect.only(PotionData.create(new Holder[]{ModEffects.SOUL_SEEKER}).amplifierPer(0.0F).durationPer(30).build()), TargetingMode.ALLIES_AND_SELF)), ActionContainer.TriggerPoint.DEFAULT, LevelBasedValue.constant(1.0F))), true, new LevelBasedResource(List.of(new LevelBasedResource.Entry(DragonSurvival.res("abilities/sea/sea_eyes_0"), 0), new LevelBasedResource.Entry(DragonSurvival.res("abilities/sea/sea_eyes_1"), 1), new LevelBasedResource.Entry(DragonSurvival.res("abilities/sea/sea_eyes_2"), 2), new LevelBasedResource.Entry(DragonSurvival.res("abilities/sea/sea_eyes_3"), 3), new LevelBasedResource.Entry(DragonSurvival.res("abilities/sea/sea_eyes_4"), 4)))));
        context.register(INNER_FLAME, new DragonAbility(new SimpleActivation(Optional.of(LevelBasedValue.constant(1.0F)), Optional.of(LevelBasedValue.constant((float)Functions.secondsToTicks((double)1.0F))), Optional.of(LevelBasedValue.constant((float)Functions.secondsToTicks((double)30.0F))), Notification.DEFAULT, false, Sound.create().optional(), Animations.create().startAndCharging(SimpleAbilityAnimation.create(AnimationKey.CAST_MAGIC_ALT, AnimationLayer.BASE).transitionLength(5).build()).optional()), Optional.of(new ExperiencePointsUpgrade(4, LevelBasedValue.lookup(List.of(0.0F, 25.0F, 45.0F, 60.0F), LevelBasedValue.perLevel(15.0F)))), Optional.empty(), List.of(new ActionContainer(new SelfTarget(AbilityTargeting.entity(PotionEffect.only(PotionData.create(new Holder[]{ModEffects.INNER_FLAME}).amplifierPer(0.0F).durationPer(30).build()), TargetingMode.ALLIES_AND_SELF)), ActionContainer.TriggerPoint.DEFAULT, LevelBasedValue.constant(1.0F))), true, new LevelBasedResource(List.of(new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/contrast_shower_0"), 0), new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/contrast_shower_1"), 1), new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/contrast_shower_2"), 2)))));

    }
    private static void registerPassiveAbilities(BootstrapContext<DragonAbility> context) {
        context.register(STARFIRE_MAGIC, new DragonAbility(PassiveActivation.DEFAULT, Optional.of(new ExperiencePointsUpgrade(10, LevelBasedValue.perLevel(36.0F))), Optional.empty(), List.of(new ActionContainer(new SelfTarget(AbilityTargeting.entity(ModifierEffect.only(new ModifierWithDuration(DurationInstanceBase.create(DragonSurvival.res("starfire_magic")).removeAutomatically().hidden().build(), List.of(Modifier.per(DSAttributes.MANA, 1.0F, AttributeModifier.Operation.ADD_VALUE)))), TargetingMode.ALLIES_AND_SELF)), ActionContainer.TriggerPoint.DEFAULT, LevelBasedValue.constant(1.0F)), new ActionContainer(new SelfTarget(AbilityTargeting.entity(AllOfCondition.allOf(new LootItemCondition.Builder[]{Condition.thisEntity(EntityPredicate.Builder.entity().located(LocationPredicate.Builder.location().setCanSeeSky(true)).build()), Condition.thisEntity(EntityCondition.isInSunlight(4))}).build(), ModifierEffect.only(new ModifierWithDuration(DurationInstanceBase.create(DragonSurvival.res("good_mana_condition")).removeAutomatically().hidden().build(), List.of(Modifier.per(DSAttributes.MANA_REGENERATION, 0.03F, AttributeModifier.Operation.ADD_VALUE)))), TargetingMode.ALLIES_AND_SELF)), ActionContainer.TriggerPoint.DEFAULT, LevelBasedValue.constant(1.0F))), true, new LevelBasedResource(List.of(new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/cave_magic_0"), 0), new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/cave_magic_1"), 1), new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/cave_magic_2"), 2), new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/cave_magic_3"), 3), new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/cave_magic_4"), 4), new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/cave_magic_5"), 5), new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/cave_magic_6"), 6), new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/cave_magic_7"), 7), new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/cave_magic_8"), 8), new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/cave_magic_9"), 9), new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/cave_magic_10"), 10)))));
        context.register(FIRE_OF_STARS, new DragonAbility(PassiveActivation.DEFAULT, Optional.of(new ExperiencePointsUpgrade(4, LevelBasedValue.lookup(List.of(0.0F, 25.0F, 45.0F, 60.0F), LevelBasedValue.perLevel(15.0F)))), Optional.empty(), List.of(new ActionContainer(new SelfTarget(AbilityTargeting.entity(AllOfCondition.allOf(new LootItemCondition.Builder[]{Condition.thisEntity(EntityPredicate.Builder.entity().located(LocationPredicate.Builder.location().setY(MinMaxBounds.Doubles.atLeast(0))).build()), Condition.thisEntity(EntityPredicate.Builder.entity().located(LocationPredicate.Builder.location().setCanSeeSky(true)).build()),() -> IsNightCondition.INSTANCE}).build(), ModifierEffect.only(new ModifierWithDuration(DurationInstanceBase.create(DragonSurvival.res("fire_of_stars")).removeAutomatically().build(), List.of(Modifier.per(Attributes.ATTACK_DAMAGE, 0.25F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),Modifier.per(DSAttributes.FLIGHT_SPEED, 0.25F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), Modifier.per(Attributes.MOVEMENT_SPEED, 0.25F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)))), TargetingMode.ALLIES_AND_SELF)), ActionContainer.TriggerPoint.DEFAULT, LevelBasedValue.constant(1.0F))), true, new LevelBasedResource(List.of(new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/cave_eyes_0"), 0), new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/cave_eyes_1"), 1), new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/cave_eyes_2"), 2), new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/cave_eyes_3"), 3), new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/cave_eyes_4"), 4)))));
        context.register(BURN, new DragonAbility(PassiveActivation.DEFAULT, Optional.of(new ExperiencePointsUpgrade(4, LevelBasedValue.perLevel(15.0F))), Optional.empty(), List.of(new ActionContainer(new SelfTarget(AbilityTargeting.entity(List.of(new OnAttackEffect(PotionData.create(new Holder[]{DSEffects.BURN}).durationPer(5).probabilityPer(0.15F).build())), TargetingMode.ALL)), ActionContainer.TriggerPoint.DEFAULT, LevelBasedValue.constant(1.0F))), true, new LevelBasedResource(List.of(new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/burn_0"), 0), new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/burn_1"), 1), new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/burn_2"), 2), new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/burn_3"), 3), new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/burn_4"), 4)))));
        registerWings(context);
        context.register(STARFIRE_SPIN, new DragonAbility(PassiveActivation.DEFAULT, Optional.of(new ConditionUpgrade(List.of(Condition.thisEntity(EntityCondition.spinWasGranted(true)).build()), false)), Optional.of(Condition.thisEntity(EntityCondition.isMarked(true)).build()), List.of(new ActionContainer(new SelfTarget(AbilityTargeting.entity(List.of(new SpinEffect(1, Optional.of(HolderSet.direct(new Holder[]{NeoForgeMod.EMPTY_TYPE})))), TargetingMode.ALLIES_AND_SELF)), ActionContainer.TriggerPoint.DEFAULT, LevelBasedValue.constant(1.0F))), true, new LevelBasedResource(List.of(new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/cave_spin_0"), 0), new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/cave_spin_1"), 1)))));
        context.register(STARFIRE_IMMUNITY, new DragonAbility(PassiveActivation.DEFAULT, Optional.empty(), Optional.empty(), List.of(new ActionContainer(new SelfTarget(AbilityTargeting.entity(DamageModificationEffect.only(new DamageModification(DurationInstanceBase.create(DragonSurvival.res("starfire_immunity")).removeAutomatically().customIcon(DragonSurvival.res("textures/ability_effect/fire_immunity.png")).build(), context.lookup(Registries.DAMAGE_TYPE).getOrThrow(DamageTypeTags.IS_FIRE), LevelBasedValue.constant(0.0F))), TargetingMode.ALLIES_AND_SELF)), ActionContainer.TriggerPoint.DEFAULT, LevelBasedValue.constant(1.0F))), true, new LevelBasedResource(List.of(new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/cave_dragon_0"), 0), new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/cave_dragon_1"), 1)))));
        context.register(STARSIGHT, new DragonAbility(PassiveActivation.DEFAULT, Optional.of(new ExperiencePointsUpgrade(4, LevelBasedValue.lookup(List.of(0.0F, 25.0F, 45.0F, 60.0F), LevelBasedValue.perLevel(15.0F)))), Optional.of(AnyOfCondition.anyOf(new LootItemCondition.Builder[]{Condition.thisEntity(EntityPredicate.Builder.entity().located(LocationPredicate.Builder.location().setY(MinMaxBounds.Doubles.atMost(-1))).build()), InvertedLootItemCondition.invert(() -> IsNightCondition.INSTANCE), Condition.thisEntity(EntityPredicate.Builder.entity().located(LocationPredicate.Builder.location().setCanSeeSky(false)).build())}).build()), List.of(new ActionContainer(new SelfTarget(AbilityTargeting.entity(List.of(new StarsightAbilityEffect()), TargetingMode.ALLIES_AND_SELF)), ActionContainer.TriggerPoint.DEFAULT, LevelBasedValue.constant(1.0F))), true, new LevelBasedResource(List.of(new LevelBasedResource.Entry(DragonSurvival.res("abilities/sea/sea_eyes_0"), 0), new LevelBasedResource.Entry(DragonSurvival.res("abilities/sea/sea_eyes_1"), 1), new LevelBasedResource.Entry(DragonSurvival.res("abilities/sea/sea_eyes_2"), 2), new LevelBasedResource.Entry(DragonSurvival.res("abilities/sea/sea_eyes_3"), 3), new LevelBasedResource.Entry(DragonSurvival.res("abilities/sea/sea_eyes_4"), 4)))));
    }

    public static void registerWings(BootstrapContext<DragonAbility> context) {
        context.register(STARFIRE_WINGS, new DragonAbility(PassiveActivation.DEFAULT, Optional.of(new ConditionUpgrade(List.of(Condition.thisEntity(EntityCondition.flightWasGranted(true)).build()), false)), Optional.of(Condition.thisEntity(EntityCondition.isMarked(true)).build()), List.of(new ActionContainer(new SelfTarget(AbilityTargeting.entity(List.of(new FlightEffect(1, DragonSurvival.res("textures/ability_effect/cave_dragon_wings.png"))), TargetingMode.ALLIES_AND_SELF)), ActionContainer.TriggerPoint.DEFAULT, LevelBasedValue.constant(1.0F))), true, new LevelBasedResource(List.of(new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/cave_wings_0"), 0), new LevelBasedResource.Entry(DragonSurvival.res("abilities/cave/cave_wings_1"), 1)))));
    }
}
