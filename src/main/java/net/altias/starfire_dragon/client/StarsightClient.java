package net.altias.starfire_dragon.client;

import net.altias.starfire_dragon.mixin.OptionInstanceAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;

public class StarsightClient{

    private static Double oldGamma = null;

    public static void enable() {
        Minecraft mc = Minecraft.getInstance();

        OptionInstance<Double> gamma = mc.options.gamma();

        if (oldGamma == null) {
            oldGamma = gamma.get();
        }

        ((OptionInstanceAccessor<Double>) (Object) gamma)
                .starfire$setValue(14.0D);
    }

    public static void disable() {
        Minecraft mc = Minecraft.getInstance();

        if (oldGamma != null) {
            ((OptionInstanceAccessor<Double>) (Object) mc.options.gamma())
                    .starfire$setValue(oldGamma);

            oldGamma = null;
        }
    }
}
