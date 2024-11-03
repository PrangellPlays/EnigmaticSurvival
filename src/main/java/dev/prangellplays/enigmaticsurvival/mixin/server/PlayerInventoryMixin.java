package dev.prangellplays.enigmaticsurvival.mixin.server;

import dev.prangellplays.enigmaticsurvival.components.BackSlotComponent;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({PlayerInventory.class})
public class PlayerInventoryMixin {
    @Shadow
    @Final
    public PlayerEntity player;

    public PlayerInventoryMixin() {
    }

    @Inject(
            method = {"getMainHandStack"},
            at = {@At("HEAD")},
            cancellable = true
    )
    private void enigmaticsurvival$mainHandSlot(CallbackInfoReturnable<ItemStack> cir) {
        if (BackSlotComponent.isHoldingBackWeapon(this.player)) {
            if (!BackSlotComponent.getBackWeapon(this.player).isEmpty()) {
                cir.setReturnValue(BackSlotComponent.getBackWeapon(this.player));
            } else {
                BackSlotComponent.setHoldingBackWeapon(this.player, false);
            }
        }

    }

    @Inject(
            method = {"updateItems"},
            at = {@At("TAIL")}
    )
    private void enigmaticsurvival$selectSlot(CallbackInfo ci) {
        if (!BackSlotComponent.getBackWeapon(this.player).isEmpty()) {
            BackSlotComponent.getBackWeapon(this.player).inventoryTick(this.player.getWorld(), this.player, 0, BackSlotComponent.isHoldingBackWeapon(this.player));
        }

    }

    @Inject(
            method = {"getBlockBreakingSpeed"},
            at = {@At("HEAD")},
            cancellable = true
    )
    private void enigmaticsurvival$slotBreaking(BlockState block, CallbackInfoReturnable<Float> cir) {
        if (BackSlotComponent.isHoldingBackWeapon(this.player)) {
            if (!BackSlotComponent.getBackWeapon(this.player).isEmpty()) {
                cir.setReturnValue(BackSlotComponent.getBackWeapon(this.player).getMiningSpeedMultiplier(block));
            } else {
                BackSlotComponent.setHoldingBackWeapon(this.player, false);
            }
        }

    }

    @Inject(
            method = {"addPickBlock"},
            at = {@At("HEAD")}
    )
    private void enigmaticsurvival$nonPick(CallbackInfo ci) {
        BackSlotComponent.setHoldingBackWeapon(this.player, false);
    }

    @Inject(
            method = {"swapSlotWithHotbar"},
            at = {@At("HEAD")}
    )
    private void enigmaticsurvival$nonSwap(int slot, CallbackInfo ci) {
        BackSlotComponent.setHoldingBackWeapon(this.player, false);
    }

    @Inject(
            method = {"scrollInHotbar"},
            at = {@At("HEAD")}
    )
    private void enigmaticsurvival$nonScroll(double scrollAmount, CallbackInfo ci) {
        BackSlotComponent.setHoldingBackWeapon(this.player, false);
    }
}