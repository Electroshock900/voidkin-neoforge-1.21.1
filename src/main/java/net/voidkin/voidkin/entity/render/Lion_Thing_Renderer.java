package net.voidkin.voidkin.entity.render;

import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.entity.ModModelLayers;
import net.voidkin.voidkin.entity.custom.Lion_Thing;
import net.voidkin.voidkin.entity.model.Lion_Thing_Model;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class Lion_Thing_Renderer extends MobRenderer<Lion_Thing, Lion_Thing_Model<Lion_Thing>> {
    public Lion_Thing_Renderer(EntityRendererProvider.Context context){
        super(context, new Lion_Thing_Model<>(context.bakeLayer(ModModelLayers.LION_THING_LAYER)),2.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(Lion_Thing cb) {
        return ResourceLocation.fromNamespaceAndPath(Voidkin.MODID,"textures/entity/custom/lion_thing.png");
    }

    @Override
    public void render(Lion_Thing pEntity, float p_115456_, float p_115457_, PoseStack pStack, MultiBufferSource p_115459_, int p_115460_) {
        pStack.scale(2f, 2f, 2f);
        /**if(pEntity.isBaby()) {
        }**/
        super.render(pEntity, p_115456_, p_115457_, pStack, p_115459_, p_115460_);
    }
}
