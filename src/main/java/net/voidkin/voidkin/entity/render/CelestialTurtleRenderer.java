package net.voidkin.voidkin.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.entity.custom.CelestialTurtle;
import net.voidkin.voidkin.entity.model.CelestialTurtleModel;
import net.voidkin.voidkin.entity.model.Owl_Model;

public class CelestialTurtleRenderer extends MobRenderer<CelestialTurtle, CelestialTurtleModel> {
    public CelestialTurtleRenderer(EntityRendererProvider.Context context) {
        super(context, new CelestialTurtleModel(context.bakeLayer(CelestialTurtleModel.LAYER_LOCATION)),0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(CelestialTurtle p_114482_) {
        return ResourceLocation.fromNamespaceAndPath(Voidkin.MODID,"textures/entity/custom/celestial_turtle.png");
    }
    @Override
    public void render(CelestialTurtle owl, float p_115456_, float p_115457_, PoseStack pStack, MultiBufferSource p_115459_, int p_115460_) {
        if(owl.isBaby()) {
            pStack.scale(0.5f, 0.5f, 0.5f);
        }else{pStack.scale(1f,1f,1f);

        }
        super.render(owl, p_115456_, p_115457_, pStack, p_115459_, p_115460_);
    }
}
