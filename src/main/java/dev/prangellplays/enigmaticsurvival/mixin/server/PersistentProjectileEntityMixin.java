package dev.prangellplays.enigmaticsurvival.mixin.server;

import dev.prangellplays.enigmaticsurvival.utils.BackSlotHolder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({PersistentProjectileEntity.class})
public abstract class PersistentProjectileEntityMixin extends ProjectileEntityMixin {
    @Shadow
    public PersistentProjectileEntity.PickupPermission pickupType;

    public PersistentProjectileEntityMixin() {
    }

    @Shadow
    protected abstract ItemStack asItemStack();

    @Inject(
            method = {"tryPickup"},
            at = {@At("HEAD")},
            cancellable = true
    )
    private void enigmaticsurvival$pickupSlot(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        if (this.enigmaticsurvival$getOwnedSlot() != -1) {
            if (this.pickupType == PersistentProjectileEntity.PickupPermission.ALLOWED) {
                PlayerInventory var4 = player.getInventory();
                if (var4 instanceof BackSlotHolder) {
                    BackSlotHolder holder = (BackSlotHolder)var4;
                    if (holder.enigmaticsurvival$tryInsertIntoSlot(this.enigmaticsurvival$getOwnedSlot(), this.asItemStack())) {
                        cir.setReturnValue(true);
                    }
                }
            }

        }
    }
}