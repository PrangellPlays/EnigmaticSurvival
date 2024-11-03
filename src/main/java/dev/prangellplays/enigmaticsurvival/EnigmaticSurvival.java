package dev.prangellplays.enigmaticsurvival;

import dev.prangellplays.enigmaticsurvival.components.BackSlotComponent;
import dev.prangellplays.enigmaticsurvival.entity.PlayerHeadEntity;
import dev.prangellplays.enigmaticsurvival.init.EnigmaticSurvivalBlockEntities;
import dev.prangellplays.enigmaticsurvival.init.EnigmaticSurvivalBlocks;
import dev.prangellplays.enigmaticsurvival.init.EnigmaticSurvivalItemGroups;
import dev.prangellplays.enigmaticsurvival.init.EnigmaticSurvivalItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnigmaticSurvival implements ModInitializer {
	public static final String MOD_ID = "enigmaticsurvival";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static EntityType<PlayerHeadEntity> PLAYER_HEAD;
	public static final Identifier holdWeaponPacketId = id("hold_packet");
	public static final Identifier swapWeaponPacketId = id("swap_packet");
	public static final Identifier swapInventoryPacketId = id("swap_inventory_packet");

	@Override
	public void onInitialize() {
		EnigmaticSurvivalBlocks.init();
		EnigmaticSurvivalItems.init();
		EnigmaticSurvivalBlockEntities.init();
		EnigmaticSurvivalItemGroups.init();

		ServerPlayNetworking.registerGlobalReceiver(holdWeaponPacketId, (server, player, handler, buf, responseSender) -> {
			boolean hold = buf.readBoolean();
			BackSlotComponent.setHoldingBackWeapon(player, hold);
		});
		ServerPlayNetworking.registerGlobalReceiver(swapWeaponPacketId, (server, player, handler, buf, responseSender) -> {
			if (!player.isSpectator()) {
				boolean toggled = BackSlotComponent.isHoldingBackWeapon(player);
				BackSlotComponent.setHoldingBackWeapon(player, false);
				ItemStack itemStack = BackSlotComponent.getBackWeapon(player);
				boolean success = BackSlotComponent.setBackWeapon(player, player.getStackInHand(Hand.MAIN_HAND));
				if (success) {
					player.setStackInHand(Hand.MAIN_HAND, itemStack);
				}

				player.clearActiveItem();
				BackSlotComponent.setHoldingBackWeapon(player, toggled);
			}

		});
		ServerPlayNetworking.registerGlobalReceiver(swapInventoryPacketId, (server, player, handler, buf, responseSender) -> {
			int slotId = buf.readInt();
			if (!player.isSpectator()) {
				if (!player.currentScreenHandler.isValid(slotId)) {
					return;
				}

				Slot slot = player.currentScreenHandler.getSlot(slotId);
				ItemStack itemStack = BackSlotComponent.getBackWeapon(player);
				boolean success = BackSlotComponent.setBackWeapon(player, slot.getStack());
				if (success) {
					slot.setStackNoCallbacks(itemStack);
				}
			}

		});
	}

	private static <T extends Entity> EntityType<T> registerEntity(String name, EntityType<T> entityType) {
		return (EntityType) Registry.register(Registries.ENTITY_TYPE, id(name), entityType);
	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}

	static {
		PLAYER_HEAD = registerEntity("player_head", FabricEntityTypeBuilder.<PlayerHeadEntity>create(SpawnGroup.MISC, PlayerHeadEntity::new).dimensions(EntityDimensions.changing(0.5F, 0.5F)).build());
	}
}