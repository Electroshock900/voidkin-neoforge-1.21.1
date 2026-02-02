package net.voidkin.voidkin.block.blockentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
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
import net.voidkin.voidkin.block.blockentity.PedestalBlockEntity;

public class PedestalBlockEntityRenderer implements BlockEntityRenderer<PedestalBlockEntity> {
    public PedestalBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(PedestalBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack itemStack = pBlockEntity.inventory.getStackInSlot(0);

        pPoseStack.pushPose();
        pPoseStack.translate(0.5f, 1.2f, 0.5f);
        pPoseStack.scale(0.5f, 0.5f, 0.5f);
        //itemStack.getEntityRepresentation().
        //pPoseStack.mulPose(Axis.YP.rotationDegrees(26f));
        pPoseStack.rotateAround(Axis.YP.rotationDegrees(13F),0f, 22f, 0f);
        itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, getLightLevel(pBlockEntity.getLevel(), pBlockEntity.getBlockPos()),
                OverlayTexture.NO_OVERLAY, pPoseStack, pBuffer, pBlockEntity.getLevel(), 1);

        ItemStack stack = pBlockEntity.inventory.getStackInSlot(0);

        if (!stack.isEmpty()) {
            double tick = pBlockEntity.getLevel().getGameTime() + pPartialTick;
            pPoseStack.pushPose();
            pPoseStack.translate(0.5f, 1.2f, 0.5f);
            pPoseStack.scale(
                    0.65f,0.65f,0.65f

            );
            //pPoseStack.scale(0.5f, 0.5f, 0.5f);
            pPoseStack.mulPose(Axis.YP.rotationDegrees((float)(tick * 2f) % 360));
            //pPoseStack.mulPose(Axis.YP.rotationDegrees((float)(tick * 10.0D) % 360));
            pPoseStack.translate(0.0D, Math.sin((tick/7) % (2 * Math.PI)) * 0.095D, 0.0D);
            //pPoseStack.rotateAround(Axis.YP.rotationDegrees(13F),0f, 22f, 0f);
            itemRenderer.renderStatic(stack, ItemDisplayContext.GROUND, getLightLevel(pBlockEntity.getLevel(), pBlockEntity.getBlockPos()),
                    OverlayTexture.NO_OVERLAY, pPoseStack, pBuffer, pBlockEntity.getLevel(), 1);
            pPoseStack.popPose();
        }


        pPoseStack.popPose();
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}
