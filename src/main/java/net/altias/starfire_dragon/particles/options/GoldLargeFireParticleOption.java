package net.altias.starfire_dragon.particles.options;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.altias.starfire_dragon.particles.ModParticles;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import org.jetbrains.annotations.NotNull;

public record GoldLargeFireParticleOption(float duration, boolean swirls) implements ParticleOptions {
    public static final MapCodec<GoldLargeFireParticleOption> CODEC = RecordCodecBuilder.mapCodec((codecBuilder) -> codecBuilder.group(Codec.FLOAT.fieldOf("duration").forGetter(net.altias.starfire_dragon.particles.options.GoldLargeFireParticleOption::duration), Codec.BOOL.fieldOf("swirls").forGetter(net.altias.starfire_dragon.particles.options.GoldLargeFireParticleOption::swirls)).apply(codecBuilder, net.altias.starfire_dragon.particles.options.GoldLargeFireParticleOption::new));
    public static final StreamCodec<ByteBuf, GoldLargeFireParticleOption> STREAM_CODEC;

    public @NotNull ParticleType<?> getType() {
        return (ParticleType) ModParticles.GOLD_LARGE_FIRE.value();
    }

    static {
        STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.FLOAT, net.altias.starfire_dragon.particles.options.GoldLargeFireParticleOption::duration, ByteBufCodecs.BOOL, net.altias.starfire_dragon.particles.options.GoldLargeFireParticleOption::swirls, net.altias.starfire_dragon.particles.options.GoldLargeFireParticleOption::new);
    }
}

