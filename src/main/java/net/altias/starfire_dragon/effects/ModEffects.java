package net.altias.starfire_dragon.effects;

import by.dragonsurvivalteam.dragonsurvival.DragonSurvival;
import by.dragonsurvivalteam.dragonsurvival.common.effects.ModifiableMobEffect;
import by.dragonsurvivalteam.dragonsurvival.registry.datagen.Translation;
import by.dragonsurvivalteam.dragonsurvival.registry.datagen.Translation.Translations;
import by.dragonsurvivalteam.dragonsurvival.registry.datagen.Translation.Type;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> REGISTRY;
    @Translations({@Translation(
            type = Type.EFFECT,
            comments = {"Starless Dark"}
    ), @Translation(
            type = Type.EFFECT_DESCRIPTION,
            comments = {"Applied to starfire dragons who are deep underground. Being far from the stars' light weakens their attacking and mining abilities."}
    )})
    public static Holder<MobEffect> STARLESS_DARK;

    @Translations({@Translation(
            type = Type.EFFECT,
            comments = {"Fire of Stars"}
    ), @Translation(
            type = Type.EFFECT_DESCRIPTION,
            comments = {"Applied to starfire dragons out under the stars at night. Exposure to the light of stars gives them speed and strength."}
    )})
    public static Holder<MobEffect> FIRE_OF_STARS;

    @Translations({@Translation(
            type = Type.EFFECT,
            comments = {"Soul Seeker"}
    ), @Translation(
            type = Type.EFFECT_DESCRIPTION,
            comments = {"Souls all burn with a hint of starfire. Starfire dragons can see them even when they aren't within their sight line."}
    )})
    public static Holder<MobEffect> SOUL_SEEKER;

    @Translations({@Translation(
            type = Type.EFFECT,
            comments = {"Starsight"}
    ), @Translation(
            type = Type.EFFECT_DESCRIPTION,
            comments = {"Starfire dragons are not hindered by darkness. As long as they're under the stars, anyway."}
    )})
    public static Holder<MobEffect> STARSIGHT;


    public ModEffects() {
    }

    static {
        REGISTRY = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, "starfire_dragon");
        STARLESS_DARK = REGISTRY.register("starless_dark", () -> new ModifiableMobEffect(MobEffectCategory.HARMFUL, 0, true)
                .addAttributeModifier(Attributes.ATTACK_DAMAGE,
                    DragonSurvival.res("depth_weakness"),
                    -0.25,
                    Operation.ADD_MULTIPLIED_TOTAL)
                .addAttributeModifier(
                    Attributes.BLOCK_BREAK_SPEED,
                    DragonSurvival.res("depth_fatigue"),
                    -0.3,
                    Operation.ADD_MULTIPLIED_TOTAL
        ));
        FIRE_OF_STARS = REGISTRY.register("fire_of_stars", () -> new ModifiableMobEffect(MobEffectCategory.BENEFICIAL, 0, false)
                .addAttributeModifier(Attributes.ATTACK_DAMAGE,
                        DragonSurvival.res("star_strength"),
                        0.25,
                        Operation.ADD_MULTIPLIED_TOTAL)
                .addAttributeModifier(Attributes.MOVEMENT_SPEED,
                        DragonSurvival.res("star_speed"),
                        0.25,
                        Operation.ADD_MULTIPLIED_TOTAL)
                );
        SOUL_SEEKER = REGISTRY.register("soul_seeker", () -> new ModifiableMobEffect(MobEffectCategory.BENEFICIAL, 0, false));
        STARSIGHT = REGISTRY.register("starsight", () -> new StarsightEffect());

    }
}
