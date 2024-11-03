package dev.prangellplays.enigmaticsurvival.client.render.feature;

import dev.prangellplays.enigmaticsurvival.components.BackSlotComponent;
import dev.prangellplays.enigmaticsurvival.utils.BackSlotCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;

public class BackSlotFeatureRenderer<T extends PlayerEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
    private final HeldItemRenderer heldItemRenderer;
    public BackSlotFeatureRenderer(FeatureRendererContext<T, M> context, HeldItemRenderer heldItemRenderer) {
        super(context);
        this.heldItemRenderer = heldItemRenderer;
    }

    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (!BackSlotComponent.isHoldingBackWeapon(entity)) {
            ItemStack stack = BackSlotComponent.getBackWeapon(entity);
            if (!stack.isEmpty()) {
                ActionResult result = ((BackSlotCallback)BackSlotCallback.EVENT.invoker()).interact(entity, stack);
                if (result != ActionResult.FAIL) {
                    matrices.push();
                    if (!entity.getEquippedStack(EquipmentSlot.CHEST).isEmpty()) {
                        matrices.translate(0.0, -0.5, -0.21);
                    } else {
                        matrices.translate(0.0, -0.5, -0.28);
                    }
                    heldItemRenderer.renderItem(entity, stack, ModelTransformationMode.HEAD, false, matrices, vertexConsumers, light);
                    matrices.pop();
                }
            }
        }
    }
}