package dev.prangellplays.enigmaticsurvival.client.render.entity;

import com.mojang.authlib.GameProfile;
import dev.prangellplays.enigmaticsurvival.entity.PlayerHeadEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.AbstractSkullBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SkullBlock;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.SkullBlockEntityModel;
import net.minecraft.client.render.block.entity.SkullBlockEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

import java.util.Map;
import java.util.Random;

@Environment(EnvType.CLIENT)
public class PlayerHeadEntityRenderer extends EntityRenderer<PlayerHeadEntity> {
    private final ItemRenderer itemRenderer;
    private final Random random = new Random();
    private final Map<SkullBlock.SkullType, SkullBlockEntityModel> skullModels;
    private ItemEntity playerSkullEntity;

    public PlayerHeadEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.itemRenderer = ctx.getItemRenderer();
        this.shadowRadius = 0.15F;
        this.shadowOpacity = 0.75F;
        this.skullModels = SkullBlockEntityRenderer.getModels(ctx.getModelLoader());
    }

    public void render(PlayerHeadEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        ItemStack itemStack;
        if (this.playerSkullEntity == null) {
            itemStack = new ItemStack(Items.SKELETON_SKULL);
            this.playerSkullEntity = new ItemEntity(entity.getWorld(), entity.getX(), entity.getY(), entity.getZ(), itemStack);
        }

        matrices.push();
        itemStack = this.playerSkullEntity.getStack();
        int j = itemStack.isEmpty() ? 187 : Item.getRawId(itemStack.getItem()) + itemStack.getDamage();
        this.random.setSeed((long)j);
        BakedModel bakedModel = this.itemRenderer.getModel(itemStack, this.playerSkullEntity.getWorld(), (LivingEntity) null, this.playerSkullEntity.getId());
        boolean bl = bakedModel.hasDepth();
        int k = 1;
        float l = MathHelper.sin(((float)entity.age + tickDelta) / 10.0F + this.playerSkullEntity.uniqueOffset) * 0.1F + 0.1F;
        float m = bakedModel.getTransformation().getTransformation(ModelTransformationMode.GROUND).scale.y();
        matrices.translate(0.0, (double)(l + 0.25F * m), 0.0);
        float n = (float)entity.age + tickDelta;
        matrices.multiply(RotationAxis.POSITIVE_Y.rotation(n / 20.0F));
        float o = bakedModel.getTransformation().ground.scale.x();
        float p = bakedModel.getTransformation().ground.scale.y();
        float q = bakedModel.getTransformation().ground.scale.z();

        for(int u = 0; u < k; ++u) {
            matrices.push();
            matrices.scale(0.75F, 0.75F, 0.75F);
            matrices.translate(-0.5, 0.0, -0.5);
            ItemStack stack = this.playerSkullEntity.getStack();
            Block block = ((BlockItem)stack.getItem()).getBlock();
            SkullBlock.SkullType skullType = ((AbstractSkullBlock)block).getSkullType();
            SkullBlockEntityModel skullBlockEntityModel = (SkullBlockEntityModel)this.skullModels.get(skullType);
            RenderLayer renderLayer = SkullBlockEntityRenderer.getRenderLayer(skullType, (GameProfile)null);
            SkullBlockEntityRenderer.renderSkull((Direction)null, 180.0F, 0.0F, matrices, vertexConsumers, light, skullBlockEntityModel, renderLayer);
            matrices.pop();
            if (!bl) {
                matrices.translate((double)(0.0F * o), (double)(0.0F * p), (double)(0.09375F * q));
            }
        }

        matrices.pop();
        if (entity.hasCustomName()) {
            this.renderLabelIfPresent(entity, entity.getDisplayName(), matrices, vertexConsumers, light);
        }

    }

    public Identifier getTexture(PlayerHeadEntity entity) {
        return null;
    }
}