package dev.prangellplays.enigmaticsurvival.mixin.server;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import dev.prangellplays.enigmaticsurvival.entity.PlayerHeadEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Pair;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;

@Mixin({PlayerEntity.class})
public abstract class PlayerEntityDeathMixin extends LivingEntity {
    @Shadow
    @Final
    private PlayerInventory inventory;

    @Shadow
    protected abstract void vanishCursedItems();

    protected PlayerEntityDeathMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(
            method = {"dropInventory"},
            at = {@At("HEAD")},
            cancellable = true
    )
    protected void enigmaticsurvival$dropInventory(CallbackInfo ci) {
        if (!this.getWorld().getGameRules().getBoolean(GameRules.KEEP_INVENTORY)) {
            this.vanishCursedItems();
            boolean hasItems = false;

            for(int i = 0; i < this.inventory.size() && !hasItems; ++i) {
                if (!this.inventory.getStack(i).isEmpty()) {
                    hasItems = true;
                }
            }

            if (!hasItems && TrinketsApi.getTrinketComponent(this).isPresent()) {
                Iterator var4 = ((TrinketComponent)TrinketsApi.getTrinketComponent(this).get()).getAllEquipped().iterator();

                while(var4.hasNext()) {
                    Pair<SlotReference, ItemStack> slotReferenceItemStackPair = (Pair)var4.next();
                    if (!((ItemStack)slotReferenceItemStackPair.getRight()).isEmpty()) {
                        hasItems = true;
                        break;
                    }
                }
            }

            if (hasItems) {
                PlayerHeadEntity playerHeadEntity = new PlayerHeadEntity(this.getWorld(), (PlayerEntity) PlayerEntity.class.cast(this));
                this.getWorld().spawnEntity(playerHeadEntity);
                TrinketsApi.getTrinketComponent(this).ifPresent((trinkets) -> {
                    trinkets.forEach((ref, stack) -> {
                        ref.inventory().setStack(ref.index(), ItemStack.EMPTY);
                    });
                });
                ci.cancel();
            }
        }

    }
}