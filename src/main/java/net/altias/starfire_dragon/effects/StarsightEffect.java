package net.altias.starfire_dragon.effects;

import by.dragonsurvivalteam.dragonsurvival.common.effects.ModifiableMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class StarsightEffect extends ModifiableMobEffect {

    public StarsightEffect() {
        super(MobEffectCategory.BENEFICIAL, 0, false);
    }

}
