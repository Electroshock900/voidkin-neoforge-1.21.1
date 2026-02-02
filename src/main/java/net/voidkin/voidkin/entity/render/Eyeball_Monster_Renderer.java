package net.voidkin.voidkin.entity.render;

import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.entity.ModModelLayers;
import net.voidkin.voidkin.entity.custom.Eyeball_Monster;
import net.voidkin.voidkin.entity.model.Eyeball_Monster_Model;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class Eyeball_Monster_Renderer extends MobRenderer<Eyeball_Monster, Eyeball_Monster_Model<Eyeball_Monster>> {
    public Eyeball_Monster_Renderer(EntityRendererProvider.Context context){
        super(context, new Eyeball_Monster_Model<>(context.bakeLayer(ModModelLayers.EYEBALL_LAYER)),2.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(Eyeball_Monster cb) {
        return ResourceLocation.fromNamespaceAndPath(Voidkin.MODID,"textures/entity/custom/eyeball_monster2.png");
    }

    @Override
    public void render(Eyeball_Monster pEntity, float p_115456_, float p_115457_, PoseStack pStack, MultiBufferSource p_115459_, int p_115460_) {
        pStack.scale(2f, 2f, 2f);
        /**if(pEntity.isBaby()) {
        }**/
        super.render(pEntity, p_115456_, p_115457_, pStack, p_115459_, p_115460_);
    }
}
