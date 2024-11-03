package dev.prangellplays.enigmaticsurvival.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.prangellplays.enigmaticsurvival.components.BackSlotComponent;
import dev.prangellplays.enigmaticsurvival.init.EnigmaticSurvivalComponents;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({InGameHud.class})
public abstract class InGameHudMixin {
    @Shadow private int scaledWidth;
    @Shadow private int scaledHeight;
    @Shadow protected abstract PlayerEntity getCameraPlayer();
    @Shadow protected abstract void renderHotbarItem(DrawContext context, int x, int y, float tickDelta, PlayerEntity player, ItemStack stack, int seed);

    @Inject(
            method = {"renderHotbar"},
            at = {@At("TAIL")}
    )
    private void enigmaticsurvival$renderBackSlot(float tickDelta, DrawContext context, CallbackInfo ci) {
        PlayerEntity player = this.getCameraPlayer();
        if (player != null) {
            ItemStack stack = BackSlotComponent.getBackWeapon(player);
            if (!stack.isEmpty()) {
                int i = this.scaledWidth / 2;
                int n;
                if (BackSlotComponent.isHoldingBackWeapon(player)) {
                    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                    RenderSystem.setShader(GameRenderer::getPositionTexProgram);
                    RenderSystem.setShaderTexture(0, ClickableWidget.WIDGETS_TEXTURE);
                    context.drawTexture(ClickableWidget.WIDGETS_TEXTURE, i - 12, this.scaledHeight - 23 - 70, 0, 22, 24, 24);
                    RenderSystem.enableBlend();
                    context.drawTexture(ClickableWidget.WIDGETS_TEXTURE, i - 12 + 4, this.scaledHeight - 23 - 70 + 4, 27, 26, 16, 16);
                    RenderSystem.defaultBlendFunc();
                    n = i - 90 + 80 + 2;
                    int p = this.scaledHeight - 19 - 70;
                    this.renderHotbarItem(context,  n, p, tickDelta, player, stack, 1);
                    RenderSystem.disableBlend();
                } else {
                    Arm arm = player.getMainArm().getOpposite();
                    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                    RenderSystem.setShader(GameRenderer::getPositionTexProgram);
                    RenderSystem.setShaderTexture(0, ClickableWidget.WIDGETS_TEXTURE);
                    RenderSystem.enableBlend();
                    RenderSystem.defaultBlendFunc();
                    if (arm == Arm.RIGHT) {
                        context.drawTexture(ClickableWidget.WIDGETS_TEXTURE, i - 91 - 29, this.scaledHeight - 23, 24, 22, 29, 24);
                    } else {
                        context.drawTexture(ClickableWidget.WIDGETS_TEXTURE, i + 91, this.scaledHeight - 23, 53, 22, 29, 24);
                    }

                    n = this.scaledHeight - 16 - 3;
                    if (arm == Arm.RIGHT) {
                        this.renderHotbarItem(context,  i - 91 - 26, n, tickDelta, player, stack, 0);
                    } else {
                        this.renderHotbarItem(context,  i + 91 + 10, n, tickDelta, player, stack, 0);
                    }

                    RenderSystem.disableBlend();
                }
            }

        }
    }
}