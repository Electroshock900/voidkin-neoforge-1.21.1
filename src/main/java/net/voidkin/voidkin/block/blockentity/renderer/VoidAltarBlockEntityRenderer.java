package net.voidkin.voidkin.block.blockentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.voidkin.voidkin.block.blockentity.VoidAltarBlockEntity;
import net.voidkin.voidkin.render.ModGhostRenderTypes;
import net.voidkin.voidkin.block.ModBlocks;
import org.joml.Vector2i;

import java.util.List;

public class VoidAltarBlockEntityRenderer implements BlockEntityRenderer<VoidAltarBlockEntity> {
    public VoidAltarBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }
    List<Vector2i> offsets = List.of(


            new Vector2i(0,3),
            new Vector2i(2,2),
            new Vector2i(3,0),
            new Vector2i(2,-2),

            new Vector2i(0,-3),
            new Vector2i(-2,-2),
            new Vector2i(-3,0),
            new Vector2i(-2,2));


    @Override
    public void render(VoidAltarBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack stack = pBlockEntity.itemHandler.getStackInSlot(0);

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
        offsets.forEach(offset ->{
            if (pBlockEntity.getLevel().getBlockState(pBlockEntity.getBlockPos().offset(offset.x, 0, offset.y)).isAir()){
                renderSidePedestal(pPoseStack,pBuffer,pPackedLight,pPackedOverlay, offset.x, offset.y);
            }
        });

//        pPoseStack.popPose();
    }
    private static void renderSidePedestal(PoseStack pPoseStack, MultiBufferSource pBuffer,int pPackedLight, int pPackedOverlay, float xOffset, float zOffset){
        BlockRenderDispatcher blockRenderer = Minecraft.getInstance().getBlockRenderer();
        BlockState state = ModBlocks.VOID_PEDESTAL.get().defaultBlockState();

        VertexConsumer consumer = pBuffer.getBuffer(ModGhostRenderTypes.GHOST);
        //RenderSystem.enableBlend();
        //RenderSystem.defaultBlendFunc();
        //RenderSystem.setShaderColor(1f, 1f, 1f, 0.5f);
//        RenderSystem.disableBlend();


        pPoseStack.pushPose();
        pPoseStack.translate(xOffset, 0f, zOffset);
        pPoseStack.scale(1f, 1f, 1f);



        BakedModel model = blockRenderer.getBlockModel(state);
        blockRenderer.getModelRenderer().renderModel(
                pPoseStack.last(),
                consumer,
                state,
                model,
                1f,1f,1f,
                pPackedLight,
                pPackedOverlay,
                ModelData.EMPTY,
                ModGhostRenderTypes.GHOST);

        pPoseStack.popPose();

    }

    @Override
    public boolean shouldRenderOffScreen(VoidAltarBlockEntity pBlockEntity) {
        return true;/*BlockEntityRenderer.super.shouldRenderOffScreen(pBlockEntity);*/
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}
