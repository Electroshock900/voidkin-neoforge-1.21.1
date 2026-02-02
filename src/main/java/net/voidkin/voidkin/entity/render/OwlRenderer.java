package net.voidkin.voidkin.entity.render;

import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.entity.custom.OwlEntity;
import net.voidkin.voidkin.entity.model.Owl_Model;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class OwlRenderer extends MobRenderer<OwlEntity, Owl_Model<OwlEntity>> {
    public OwlRenderer(EntityRendererProvider.Context context) {
        super(context, new Owl_Model<>(context.bakeLayer(Owl_Model.LAYER_LOCATION)),0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(OwlEntity p_114482_) {
        return ResourceLocation.fromNamespaceAndPath(Voidkin.MODID,"textures/entity/custom/owl.png");
    }
    @Override
    public void render(OwlEntity owl, float p_115456_, float p_115457_, PoseStack pStack, MultiBufferSource p_115459_, int p_115460_) {
        if(owl.isBaby()) {
            pStack.scale(0.5f, 0.5f, 0.5f);
        }else{pStack.scale(1f,1f,1f);

        }
        super.render(owl, p_115456_, p_115457_, pStack, p_115459_, p_115460_);
    }
}
