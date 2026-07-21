package net.altias.starfire_dragon.mixin;

import net.minecraft.client.OptionInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.Consumer;

@Mixin(OptionInstance.class)
public interface OptionInstanceAccessor<T> {

    @Accessor("value")
    void starfire$setValue(T value);

}
