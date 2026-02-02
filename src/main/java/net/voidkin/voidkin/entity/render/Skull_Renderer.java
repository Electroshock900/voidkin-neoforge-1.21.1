package net.voidkin.voidkin.entity.render;

import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.entity.ModModelLayers;
import net.voidkin.voidkin.entity.custom.Skull_Entity;
import net.voidkin.voidkin.entity.model.Skull_Entity_Model;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class Skull_Renderer extends MobRenderer<Skull_Entity, Skull_Entity_Model<Skull_Entity>> {
    public Skull_Renderer(EntityRendererProvider.Context context){
        super(context, new Skull_Entity_Model<>(context.bakeLayer(ModModelLayers.SKULL_LAYER)),0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(Skull_Entity cb) {
        return ResourceLocation.fromNamespaceAndPath(Voidkin.MODID,"textures/entity/custom/skull.png");
    }

    @Override
    public void render(Skull_Entity skull, float p_115456_, float p_115457_, PoseStack pStack, MultiBufferSource p_115459_, int p_115460_) {
        if(skull.isBaby()) {
            pStack.scale(2f, 2f, 2f);
        }else{pStack.scale(4f,4f,4f);

        }
        super.render(skull, p_115456_, p_115457_, pStack, p_115459_, p_115460_);
    }
}
