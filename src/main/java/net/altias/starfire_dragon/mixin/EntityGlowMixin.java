package net.altias.starfire_dragon.mixin;

import net.altias.starfire_dragon.client.SoulSeekerClient;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.scores.Team;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityGlowMixin {

    @Inject(
            method = "isCurrentlyGlowing",
            at = @At("RETURN"),
            cancellable = true
    )
    private void soulSeekerGlow(CallbackInfoReturnable<Boolean> cir) {

        Entity entity = (Entity)(Object)this;

        if (SoulSeekerClient.shouldGlow(entity)) {
            cir.setReturnValue(true);
        }
    }

    @Inject(
            method = "getTeam",
            at = @At("HEAD"),
            cancellable = true
    )
    private void soulSeekerTeam(CallbackInfoReturnable<Team> cir) {
        Entity entity = (Entity)(Object)this;

        if (SoulSeekerClient.shouldGlow(entity)) {
            cir.setReturnValue(SoulSeekerClient.SOUL_TEAM);
        }
    }
}
