package dev.prangellplays.enigmaticsurvival.mixin.server;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.prangellplays.enigmaticsurvival.components.BackSlotComponent;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ServerPlayNetworkHandler.class})
public class ServerPlayNetworkHandlerMixin {
    @Shadow
    public ServerPlayerEntity player;

    public ServerPlayNetworkHandlerMixin() {
    }

    @WrapOperation(
            method = {"onPlayerMove"},
            at = {@At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/network/ServerPlayerEntity;isInTeleportationState()Z"
            )}
    )
    private boolean enigmaticsurvival$neverMovingWrong(ServerPlayerEntity instance, Operation<Boolean> original) {
        return true;
    }

    @Inject(
            method = {"onPlayerAction"},
            at = {@At("HEAD")}
    )
    private void enigmaticsurvival$swapHands(PlayerActionC2SPacket packet, CallbackInfo ci) {
        if (packet.getAction() == PlayerActionC2SPacket.Action.SWAP_ITEM_WITH_OFFHAND) {
            BackSlotComponent.setHoldingBackWeapon(this.player, false);
        }
    }
}