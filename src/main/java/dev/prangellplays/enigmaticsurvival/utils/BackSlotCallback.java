package dev.prangellplays.enigmaticsurvival.utils;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;

public interface BackSlotCallback {
    Event<BackSlotCallback> EVENT = EventFactory.createArrayBacked(BackSlotCallback.class, (listeners) -> {
        return (player, stack) -> {
            BackSlotCallback[] var3 = listeners;
            int var4 = listeners.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                BackSlotCallback listener = var3[var5];
                ActionResult result = listener.interact(player, stack);
                if (result != ActionResult.PASS) {
                    return result;
                }
            }

            return ActionResult.PASS;
        };
    });

    ActionResult interact(PlayerEntity var1, ItemStack var2);
}