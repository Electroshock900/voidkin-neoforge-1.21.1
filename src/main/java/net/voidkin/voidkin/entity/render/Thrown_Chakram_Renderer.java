package net.voidkin.voidkin.entity.render;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.entity.model.Thrown_Chakram_Model;
import net.voidkin.voidkin.entity.projectiles.Thrown_Chakram;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class Thrown_Chakram_Renderer extends EntityRenderer<Thrown_Chakram> {//, Thrown_Chakram_Model<Thrown_Chakram>> {
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "textures/entity/chakram_flame.png");
    public Thrown_Chakram_Model model;

    public Thrown_Chakram_Renderer(EntityRendererProvider.Context manager) {
        super(manager);
        this.model = new Thrown_Chakram_Model(Thrown_Chakram_Model.createBodyLayer().bakeRoot());
    }

    @Override
    public void render(Thrown_Chakram pEntity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();

        if(!pEntity.isGrounded()) {
            poseStack.mulPose(Axis.XP.rotationDegrees(Mth.lerp(partialTick, pEntity.yRotO, pEntity.getYRot())));
            poseStack.mulPose(Axis.YP.rotationDegrees(pEntity.getRenderingRotation() * 5f));
            poseStack.translate(0, -1.0f, 0);
        } else {
            poseStack.mulPose(Axis.YP.rotationDegrees(pEntity.groundedOffset.y));
            poseStack.mulPose(Axis.XP.rotationDegrees(pEntity.groundedOffset.x));
            poseStack.translate(0, -1.0f, 0);
        }

        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(
                bufferSource, this.model.renderType(this.getTextureLocation(pEntity)),false, false);
        this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
        super.render(pEntity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    public ResourceLocation getTextureLocation(Thrown_Chakram arrow) {
        return TEXTURE;
    }
}
