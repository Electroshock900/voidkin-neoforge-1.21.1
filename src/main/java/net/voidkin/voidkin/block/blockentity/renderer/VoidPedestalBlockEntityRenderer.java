package net.voidkin.voidkin.block.blockentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.voidkin.voidkin.block.blockentity.VoidPedestalBlockEntity;

public class VoidPedestalBlockEntityRenderer implements BlockEntityRenderer<VoidPedestalBlockEntity> {
    public VoidPedestalBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(VoidPedestalBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack itemStack = pBlockEntity.inventory.getStackInSlot(0);

        pPoseStack.pushPose();
        pPoseStack.translate(0.5f, 1.2f, 0.5f);
        pPoseStack.scale(0.5f, 0.5f, 0.5f);
        //itemStack.getEntityRepresentation().
        //pPoseStack.mulPose(Axis.YP.rotationDegrees(26f));
        //pPoseStack.rotateAround(Axis.YP.rotationDegrees(13F),0f, 22f, 0f);
        itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, getLightLevel(pBlockEntity.getLevel(), pBlockEntity.getBlockPos()),
                OverlayTexture.NO_OVERLAY, pPoseStack, pBuffer, pBlockEntity.getLevel(), 1);



        pPoseStack.popPose();
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}
