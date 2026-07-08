package net.altias.starfire_dragon.client;

import net.altias.starfire_dragon.effects.ModEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Scoreboard;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class SoulSeekerClient {

    public static final Set<UUID> REVEALED = new HashSet<>();

    public static final PlayerTeam SOUL_TEAM = createSoulTeam();

    private static PlayerTeam createSoulTeam() {
        Scoreboard scoreboard = new Scoreboard();

        PlayerTeam team = scoreboard.addPlayerTeam("soul_seeker");
        team.setColor(ChatFormatting.GOLD);

        return team;
    }

    public static void tick(Minecraft minecraft) {
        REVEALED.clear();

        if (minecraft.player == null)
            return;

        if (!minecraft.player.hasEffect(ModEffects.SOUL_SEEKER))
            return;

        Level level = minecraft.level;

        //Filtering
        for (LivingEntity entity : level.getEntitiesOfClass(
                LivingEntity.class,
                minecraft.player.getBoundingBox().inflate(32),
                e -> e != minecraft.player
                        && e.getType().getCategory() != MobCategory.MONSTER
        )) {
            if (!canSeeEntity(minecraft.player, entity)) {
                REVEALED.add(entity.getUUID());
            }
        }

        }


    public static boolean shouldGlow(Entity entity) {
        return entity.isAlive() && REVEALED.contains(entity.getUUID());
    }

    private static boolean canSeeEntity(Player player, Entity target) {
        Vec3 start = player.getEyePosition();
        Vec3 end = target.getEyePosition();

        BlockHitResult result = player.level().clip(new ClipContext(
                start,
                end,
                ClipContext.Block.COLLIDER,
                ClipContext.Fluid.NONE,
                player
        ));

        return result.getType() == HitResult.Type.MISS;
    }
}
