package dev.prangellplays.enigmaticsurvival.mixin.client;

import dev.prangellplays.enigmaticsurvival.components.BackSlotComponent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({MinecraftClient.class})
public class MinecraftClientMixin {
    @Shadow
    public @Nullable ClientPlayerEntity player;

    public MinecraftClientMixin() {
    }

    @Inject(
            method = {"handleInputEvents"},
            at = {@At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/network/ClientPlayerEntity;getInventory()Lnet/minecraft/entity/player/PlayerInventory;"
            )}
    )
    private void enigmaticsurvival$inputSlot(CallbackInfo ci) {
        if (this.player != null) {
            BackSlotComponent.setHoldingBackWeapon(this.player, false);
        }

    }

    @Inject(
            method = {"handleInputEvents"},
            at = {@At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/network/ClientPlayerEntity;isSpectator()Z",
                    ordinal = 1
            )}
    )
    private void enigmaticsurvival$swapStop(CallbackInfo ci) {
        if (this.player != null) {
            BackSlotComponent.setHoldingBackWeapon(this.player, false);
        }

    }

    @Inject(
            method = {"doItemPick"},
            at = {@At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/PlayerInventory;isValidHotbarIndex(I)Z"
            )}
    )
    private void enigmaticsurvival$pickSlot(CallbackInfo ci) {
        if (this.player != null) {
            BackSlotComponent.setHoldingBackWeapon(this.player, false);
        }

    }
}