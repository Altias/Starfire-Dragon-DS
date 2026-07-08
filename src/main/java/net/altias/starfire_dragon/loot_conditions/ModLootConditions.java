package net.altias.starfire_dragon.loot_conditions;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModLootConditions {

    public static final DeferredRegister<LootItemConditionType> REGISTRY =
            DeferredRegister.create(
                    BuiltInRegistries.LOOT_CONDITION_TYPE,
                    "starfire_dragon"
            );

    public static final DeferredHolder<LootItemConditionType, LootItemConditionType> IS_NIGHT =
            REGISTRY.register(
                    "is_night",
                    () -> new LootItemConditionType(IsNightCondition.CODEC)
            );
}
