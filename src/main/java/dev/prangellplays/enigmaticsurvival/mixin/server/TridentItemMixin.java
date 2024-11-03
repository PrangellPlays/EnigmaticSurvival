package dev.prangellplays.enigmaticsurvival.mixin.server;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import dev.prangellplays.enigmaticsurvival.utils.BackSlotHolder;
import dev.prangellplays.enigmaticsurvival.utils.ProjectileSlotHolder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({TridentItem.class})
public class TridentItemMixin {
    public TridentItemMixin() {
    }

    @WrapOperation(
            method = {"onStoppedUsing"},
            at = {@At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"
            )}
    )
    private boolean enigmaticsurvival$spawnEntity(World world, Entity entity, Operation<Boolean> operation, @Local(ordinal = 0) ItemStack stack, @Local(ordinal = 0) LivingEntity user) {
        if (user instanceof PlayerEntity player) {
            PlayerInventory var9 = player.getInventory();
            if (var9 instanceof BackSlotHolder holder) {
                if (entity instanceof ProjectileSlotHolder slotHolder) {
                    int index = holder.enigmaticsurvival$getSlotHolding(stack);
                    if (index != -1) {
                        slotHolder.enigmaticsurvival$setOwnedSlot(index);
                    }
                }
            }
        }

        return (Boolean)operation.call(world, entity);
    }
}