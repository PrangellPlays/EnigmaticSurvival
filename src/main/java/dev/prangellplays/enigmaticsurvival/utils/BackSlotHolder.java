package dev.prangellplays.enigmaticsurvival.utils;

import net.minecraft.item.ItemStack;

public interface BackSlotHolder {
    int enigmaticsurvival$getSlotHolding(ItemStack var1);

    boolean enigmaticsurvival$tryInsertIntoSlot(int var1, ItemStack var2);
}