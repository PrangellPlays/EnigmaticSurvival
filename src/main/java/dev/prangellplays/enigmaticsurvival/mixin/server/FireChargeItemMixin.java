package dev.prangellplays.enigmaticsurvival.mixin.server;

import net.minecraft.item.FireChargeItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({FireChargeItem.class})
public class FireChargeItemMixin {
    public FireChargeItemMixin() {
    }

    @Inject(
            method = {"useOnBlock"},
            at = {@At("HEAD")},
            cancellable = true
    )
    private void enigmaticsurvival$throwInstead(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        cir.setReturnValue(ActionResult.PASS);
    }
}