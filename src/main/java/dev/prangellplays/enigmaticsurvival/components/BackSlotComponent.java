package dev.prangellplays.enigmaticsurvival.components;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.prangellplays.enigmaticsurvival.EnigmaticSurvival;
import dev.prangellplays.enigmaticsurvival.init.EnigmaticSurvivalComponents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import org.jetbrains.annotations.NotNull;

public class BackSlotComponent implements AutoSyncedComponent {
    private final PlayerEntity player;
    private final SimpleInventory backWeapon = new SimpleInventory(1);
    private boolean holdingBackWeapon = false;

    public BackSlotComponent(PlayerEntity player) {
        this.player = player;
    }

    public void readFromNbt(@NotNull NbtCompound tag) {
        this.backWeapon.setStack(0, ItemStack.fromNbt(tag.getCompound("backWeapon")));
        this.holdingBackWeapon = tag.getBoolean("holdingBackWeapon");
    }

    public void writeToNbt(@NotNull NbtCompound tag) {
        tag.put("backWeapon", this.backWeapon.getStack(0).writeNbt(new NbtCompound()));
        tag.putBoolean("holdingBackWeapon", this.holdingBackWeapon);
    }

    public ItemStack getBackWeapon() {
        return this.backWeapon.getStack(0);
    }

    public static ItemStack getBackWeapon(PlayerEntity player) {
        return ((BackSlotComponent) EnigmaticSurvivalComponents.BACK_WEAPON_COMPONENT.get(player)).getBackWeapon();
    }

    public boolean setBackWeapon(ItemStack backWeapon) {
        this.backWeapon.setStack(0, backWeapon);
        EnigmaticSurvivalComponents.BACK_WEAPON_COMPONENT.sync(this.player);
        return true;
    }

    public static boolean setBackWeapon(PlayerEntity player, ItemStack backWeapon) {
        return ((BackSlotComponent)EnigmaticSurvivalComponents.BACK_WEAPON_COMPONENT.get(player)).setBackWeapon(backWeapon);
    }

    public SimpleInventory getBackWeaponInventory() {
        return this.backWeapon;
    }

    public static SimpleInventory getBackWeaponInventory(PlayerEntity player) {
        return ((BackSlotComponent)EnigmaticSurvivalComponents.BACK_WEAPON_COMPONENT.get(player)).getBackWeaponInventory();
    }

    public boolean isHoldingBackWeapon() {
        return this.holdingBackWeapon;
    }

    public static boolean isHoldingBackWeapon(PlayerEntity player) {
        return ((BackSlotComponent)EnigmaticSurvivalComponents.BACK_WEAPON_COMPONENT.get(player)).isHoldingBackWeapon();
    }

    public void setHoldingBackWeapon(boolean holdingBackWeapon) {
        this.holdingBackWeapon = holdingBackWeapon;
        EnigmaticSurvivalComponents.BACK_WEAPON_COMPONENT.sync(this.player);
    }

    public static void setHoldingBackWeapon(PlayerEntity player, boolean holdingBackWeapon) {
        if (player.getWorld().isClient()) {
            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeBoolean(holdingBackWeapon);
            ClientPlayNetworking.send(EnigmaticSurvival.holdWeaponPacketId, buf);
        } else {
            ((BackSlotComponent)EnigmaticSurvivalComponents.BACK_WEAPON_COMPONENT.get(player)).setHoldingBackWeapon(holdingBackWeapon);
        }
    }
}