package net.altias.starfire_dragon.datagen;

import by.dragonsurvivalteam.dragonsurvival.DragonSurvival;
import by.dragonsurvivalteam.dragonsurvival.registry.dragon.ability.DragonAbilities;
import by.dragonsurvivalteam.dragonsurvival.registry.dragon.ability.DragonAbility;
import net.altias.starfire_dragon.StarfireDragon;
import net.altias.starfire_dragon.datagen.abilities.StarfireDragonAbilities;
import net.altias.starfire_dragon.datagen.tags.StarfireAbilityTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = StarfireDragon.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModDataGen {

    @SubscribeEvent
    public static void generateData(GatherDataEvent event) {
        System.out.println("STARFIRE DATA GENERATING");

        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookup = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();

        RegistrySetBuilder builder = new RegistrySetBuilder();

        builder.add(
                DragonAbility.REGISTRY,
                context -> {
                    StarfireDragonAbilities.registerAbilities(context);
                }
        );

        DatapackBuiltinEntriesProvider datapackProvider =
                new DatapackBuiltinEntriesProvider(
                        output,
                        lookup,
                        builder,
                        Set.of("dragonsurvival", StarfireDragon.MODID)
                );


        generator.addProvider(event.includeServer(), datapackProvider);

        lookup = datapackProvider.getRegistryProvider();


        generator.addProvider(
                event.includeServer(),
                new StarfireAbilityTags(
                        output,
                        lookup,
                        event.getExistingFileHelper()
                )
        );


    }
}
