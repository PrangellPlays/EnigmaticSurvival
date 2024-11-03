package dev.prangellplays.enigmaticsurvival.mixin.server;

import dev.prangellplays.enigmaticsurvival.components.RecoveryPosComponent;
import dev.prangellplays.enigmaticsurvival.init.EnigmaticSurvivalComponents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin({PlayerEntity.class})
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(
            method = {"getLastDeathPos"},
            at = {@At("HEAD")},
            cancellable = true
    )
    private void enigmaticsurvival$newDeathPos(CallbackInfoReturnable<Optional<GlobalPos>> cir) {
        RecoveryPosComponent pos = (RecoveryPosComponent) EnigmaticSurvivalComponents.RECOVERY_POS_COMPONENT.get(this);
        pos.getDeathPos().ifPresent((deathPos) -> {
            cir.setReturnValue(Optional.of(GlobalPos.create(this.getWorld().getRegistryKey(), deathPos)));
        });
    }
}
