package dev.prangellplays.enigmaticsurvival.block.originchoice.entity.renderer;

import dev.prangellplays.enigmaticsurvival.block.originchoice.entity.ChoiceLecternBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.LecternBlock;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.EnchantingTableBlockEntityRenderer;
import net.minecraft.client.render.entity.model.BookModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;

@Environment(EnvType.CLIENT)
public class ChoiceLecternBlockEntityRenderer implements BlockEntityRenderer<ChoiceLecternBlockEntity> {
    private final BookModel book;

    public ChoiceLecternBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        this.book = new BookModel(ctx.getLayerModelPart(EntityModelLayers.BOOK));
    }

    public void render(ChoiceLecternBlockEntity lecternBlockEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j) {
        BlockState blockState = lecternBlockEntity.getCachedState();
        matrixStack.push();
        matrixStack.translate(0.5F, 1.0625F, 0.5F);
        float g = ((Direction)blockState.get(LecternBlock.FACING)).rotateYClockwise().asRotation();
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-g));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(67.5F));
        matrixStack.translate(0.0F, -0.125F, 0.0F);
        this.book.setPageAngles(0.0F, 0.1F, 0.9F, 1.2F);
        VertexConsumer vertexConsumer = EnchantingTableBlockEntityRenderer.BOOK_TEXTURE.getVertexConsumer(vertexConsumerProvider, RenderLayer::getEntitySolid);
        this.book.renderBook(matrixStack, vertexConsumer, i, j, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.pop();
    }
}