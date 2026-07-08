package net.altias.starfire_dragon.datagen.tags;

import by.dragonsurvivalteam.dragonsurvival.DragonSurvival;
import by.dragonsurvivalteam.dragonsurvival.registry.datagen.abilities.CaveDragonAbilities;
import net.altias.starfire_dragon.datagen.abilities.StarfireDragonAbilities;
import by.dragonsurvivalteam.dragonsurvival.registry.dragon.ability.DragonAbility;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.TagKey;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.minecraft.data.tags.TagsProvider;

import java.util.concurrent.CompletableFuture;

public class StarfireAbilityTags extends TagsProvider<DragonAbility> {

    public static final TagKey<DragonAbility> STARFIRE =
            key("starfire_dragon");

    public static final TagKey<DragonAbility> ORDER =
            key("order");


    public StarfireAbilityTags(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> provider,
            ExistingFileHelper helper
    ) {
        super(
                output,
                DragonAbility.REGISTRY,
                provider,
                "dragonsurvival",
                helper
        );
    }


    @Override
    protected void addTags(HolderLookup.Provider provider) {

        this.tag(STARFIRE)
                .add(
                        StarfireDragonAbilities.GOLDEN_FIRE_BREATH,
                        StarfireDragonAbilities.STARSIGHT,
                        StarfireDragonAbilities.STARFIRE_MAGIC,
                        StarfireDragonAbilities.INNER_FLAME,
                        StarfireDragonAbilities.BURN,
                        StarfireDragonAbilities.STARFIRE_WINGS,
                        StarfireDragonAbilities.STARFIRE_SPIN,
                        StarfireDragonAbilities.STARFIRE_IMMUNITY,
                        StarfireDragonAbilities.FIRE_OF_STARS,
                        StarfireDragonAbilities.SOUL_SEEKER
                );


        this.tag(ORDER)
                .add(
                        StarfireDragonAbilities.GOLDEN_FIRE_BREATH,
                        StarfireDragonAbilities.STARSIGHT,
                        StarfireDragonAbilities.STARFIRE_MAGIC,
                        StarfireDragonAbilities.INNER_FLAME,
                        StarfireDragonAbilities.BURN,
                        StarfireDragonAbilities.STARFIRE_WINGS,
                        StarfireDragonAbilities.STARFIRE_SPIN,
                        StarfireDragonAbilities.STARFIRE_IMMUNITY,
                        StarfireDragonAbilities.FIRE_OF_STARS,
                        StarfireDragonAbilities.SOUL_SEEKER
                );
    }


    public static TagKey<DragonAbility> key(String path) {
        return TagKey.create(
                DragonAbility.REGISTRY,
                DragonSurvival.res(path)
        );
    }
}
