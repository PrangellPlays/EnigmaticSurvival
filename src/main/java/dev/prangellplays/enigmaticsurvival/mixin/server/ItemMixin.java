package dev.prangellplays.enigmaticsurvival.mixin.server;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({Item.class})
public abstract class ItemMixin {
    @Shadow public abstract Item asItem();

    public ItemMixin() {
    }

    @Inject(
            method = {"use"},
            at = {@At("HEAD")},
            cancellable = true
    )
    private void enigmaticsurvival$throw(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        Item item = this.asItem();
        if (item == Items.FIRE_CHARGE) {
            ItemStack stack = user.getStackInHand(hand);
            if (!world.isClient()) {
                world.syncWorldEvent((PlayerEntity)null, 1018, user.getBlockPos(), 0);
                Vec3d vec3d = user.getRotationVec(1.0F).normalize().multiply(2.0);
                SmallFireballEntity smallFireballEntity = new SmallFireballEntity(world, user, vec3d.getX(), vec3d.getY(), vec3d.getZ());
                smallFireballEntity.setPosition(smallFireballEntity.getX(), user.getEyeY(), smallFireballEntity.getZ());
                world.spawnEntity(smallFireballEntity);
                stack.decrement(1);
                user.getItemCooldownManager().set(Items.FIRE_CHARGE, 6);
            }

            cir.setReturnValue(TypedActionResult.success(stack));
        }

    }
}