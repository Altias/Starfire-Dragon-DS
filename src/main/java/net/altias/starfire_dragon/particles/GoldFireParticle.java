package net.altias.starfire_dragon.particles;

import by.dragonsurvivalteam.dragonsurvival.client.particles.dragon.DragonParticle;
import net.altias.starfire_dragon.particles.options.GoldFireParticleOption;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GoldFireParticle extends DragonParticle {
    protected GoldFireParticle(ClientLevel level, double x, double y, double z, double duration, boolean swirls, SpriteSet spriteSet,
                               double xSpeed, double ySpeed, double zSpeed) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed, duration, swirls,spriteSet);

        this.friction = 0.8f;

        this.lifetime = 80;
        this.setSpriteFromAge(spriteSet);

        this.rCol = 1f;
        this.gCol = 1f;
        this.bCol = 1f;
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public static class Factory implements ParticleProvider<GoldFireParticleOption> {
        private final SpriteSet spriteSet;

        public Factory(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Override
        public @Nullable Particle createParticle(GoldFireParticleOption type, @NotNull ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            GoldFireParticle particle = new GoldFireParticle(level, x, y, z, (double)type.duration(), type.swirls(), this.spriteSet, xSpeed, ySpeed, zSpeed);
            particle.setSpriteFromAge(this.spriteSet);
            return particle;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.age == 0) {
            System.out.println("Gold particle ticked");
        }
    }
}