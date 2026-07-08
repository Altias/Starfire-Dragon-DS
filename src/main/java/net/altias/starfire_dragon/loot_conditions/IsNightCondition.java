package net.altias.starfire_dragon.loot_conditions;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public class IsNightCondition implements LootItemCondition {

    public static final IsNightCondition INSTANCE = new IsNightCondition();

    public static final MapCodec<IsNightCondition> CODEC =
            MapCodec.unit(INSTANCE);

    private IsNightCondition() {}

    @Override
    public boolean test(LootContext context) {
        if (!(context.getLevel() instanceof ServerLevel level))
            return false;

        return level.isNight();
    }

    public MapCodec<? extends LootItemCondition> codec() {
        return CODEC;
    }

    @Override
    public LootItemConditionType getType() {
        return ModLootConditions.IS_NIGHT.get();
    }
}
