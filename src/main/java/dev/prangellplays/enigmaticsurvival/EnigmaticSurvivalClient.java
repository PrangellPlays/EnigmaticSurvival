package dev.prangellplays.enigmaticsurvival;

import dev.prangellplays.enigmaticsurvival.block.originchoice.entity.renderer.ChoiceLecternBlockEntityRenderer;
import dev.prangellplays.enigmaticsurvival.client.EnigmaticSurvivalKeybinds;
import dev.prangellplays.enigmaticsurvival.client.render.entity.PlayerHeadEntityRenderer;
import dev.prangellplays.enigmaticsurvival.components.BackSlotComponent;
import dev.prangellplays.enigmaticsurvival.init.EnigmaticSurvivalBlockEntities;
import dev.prangellplays.enigmaticsurvival.init.EnigmaticSurvivalBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

import static dev.prangellplays.enigmaticsurvival.client.EnigmaticSurvivalKeybinds.swapKeybind;
import static dev.prangellplays.enigmaticsurvival.client.EnigmaticSurvivalKeybinds.weaponKeybind;

public class EnigmaticSurvivalClient implements ClientModInitializer {

    public EnigmaticSurvivalClient() {
    }

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(EnigmaticSurvival.PLAYER_HEAD, PlayerHeadEntityRenderer::new);
        EnigmaticSurvivalKeybinds.init();

        BlockRenderLayerMap.INSTANCE.putBlock(EnigmaticSurvivalBlocks.CLEAR_BARRIER, RenderLayer.getCutout());
        BlockEntityRendererFactories.register(EnigmaticSurvivalBlockEntities.CHOICE_LECTERN, ChoiceLecternBlockEntityRenderer::new);

        /*ClientTickEvents.END_WORLD_TICK.register((client) -> {
            ClientPlayerEntity player = MinecraftClient.getInstance().player;
            if (player != null && player.getWorld() != null && EnigmaticSurvivalKeybinds.swapWeaponAbility.wasPressed()) {
                PacketByteBuf packet = PacketByteBufs.create();
                //packet.writeBoolean(!((PlayerSpaceComponent) SpaceModComponents.SPACE.get(player)).isFlashlightOn());
                //ClientPlayNetworking.send(SpaceMod.SERVERBOUND_FLASHLIGHT_PACKET, packet);
            }
        });*/
        /*BackSlotCallback.EVENT.register((player, stack) -> {
            if (stack.getItem() == ModItems.ANCHORBLADE) {
                return ActionResult.FAIL;
            } else {
                return stack.getItem() == ModItems.CLOWN_SCYTHE ? ActionResult.FAIL : ActionResult.PASS;
            }
        });*/
        ClientTickEvents.END_CLIENT_TICK.register((client) -> {
            if (weaponKeybind.wasPressed() && client.player != null) {
                BackSlotComponent.setHoldingBackWeapon(client.player, !BackSlotComponent.isHoldingBackWeapon(client.player));
            }

            if (swapKeybind.wasPressed()) {
                ClientPlayNetworking.send(EnigmaticSurvival.swapWeaponPacketId, PacketByteBufs.empty());
            }

        });
    }
}