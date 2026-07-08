package net.altias.starfire_dragon.particles;

import com.mojang.serialization.MapCodec;
import net.altias.starfire_dragon.StarfireDragon;
import net.altias.starfire_dragon.particles.options.GoldFireParticleOption;
import net.altias.starfire_dragon.particles.options.GoldLargeFireParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

@EventBusSubscriber(
        modid = StarfireDragon.MODID,
        value = Dist.CLIENT
)
public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> REGISTRY;
    public static final DeferredHolder<ParticleType<?>, ParticleType<GoldFireParticleOption>> GOLD_FIRE;
    public static final DeferredHolder<ParticleType<?>, ParticleType<GoldLargeFireParticleOption>> GOLD_LARGE_FIRE;

    public ModParticles() {
    }

    private static <T extends ParticleOptions> DeferredHolder<ParticleType<?>, ParticleType<T>> register(String name, Supplier<MapCodec<T>> codecSupplier, Supplier<StreamCodec<? super RegistryFriendlyByteBuf, T>> streamCodecSupplier) {
        return REGISTRY.register(name, () -> new ParticleType<T>(false) {
            public @NotNull MapCodec<T> codec() {
                return (MapCodec)codecSupplier.get();
            }

            public @NotNull StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec() {
                return (StreamCodec)streamCodecSupplier.get();
            }
        });
    }

    @SubscribeEvent(
            priority = EventPriority.LOWEST
    )
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        System.out.println("REGISTERING PARTICLES");
        event.registerSpriteSet((ParticleType)GOLD_FIRE.get(), GoldFireParticle.Factory::new);
        event.registerSpriteSet((ParticleType)GOLD_LARGE_FIRE.get(), GoldLargeFireParticle.Factory::new);
    }

    static {
        REGISTRY = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, "starfire_dragon");
        GOLD_FIRE = register("gold_fire", () -> GoldFireParticleOption.CODEC, () -> GoldFireParticleOption.STREAM_CODEC);
        GOLD_LARGE_FIRE = register("gold_large_fire", () -> GoldLargeFireParticleOption.CODEC, () -> GoldLargeFireParticleOption.STREAM_CODEC);
    }
}

