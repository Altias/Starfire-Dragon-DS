package net.altias.starfire_dragon.effects;

import by.dragonsurvivalteam.dragonsurvival.common.effects.ModifiableMobEffect;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class StarsightEffect extends ModifiableMobEffect {

    public StarsightEffect() {
        super(MobEffectCategory.BENEFICIAL, 0, false);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        entity.addEffect(new MobEffectInstance(
                MobEffects.NIGHT_VISION,
                220,
                0,
                false,
                false,
                false,
                null
        ));

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 100 == 0;
    }
}
