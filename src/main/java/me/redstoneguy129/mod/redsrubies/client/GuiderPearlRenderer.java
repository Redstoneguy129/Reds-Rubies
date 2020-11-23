package me.redstoneguy129.mod.redsrubies.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import me.redstoneguy129.mod.redsrubies.common.objects.RRItems;
import me.redstoneguy129.mod.redsrubies.common.objects.entities.GuiderPearlEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiderPearlRenderer<T extends GuiderPearlEntity & IRendersAsItem> extends EntityRenderer<T> {

    private final ItemRenderer renderer;

    public GuiderPearlRenderer(EntityRendererManager rendererManager, ItemRenderer itemRenderer) {
        super(rendererManager);
        this.renderer = itemRenderer;
    }

    @Override
    public void render(T entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.push();
        matrixStackIn.scale(1F, 1F, 1F);
        matrixStackIn.rotate(this.renderManager.getCameraOrientation());
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F));
        this.renderer.renderItem(entityIn.getItem(), ItemCameraTransforms.TransformType.GROUND, packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
        matrixStackIn.pop();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getEntityTexture(T entity) {
        return PlayerContainer.LOCATION_BLOCKS_TEXTURE;
    }
}
