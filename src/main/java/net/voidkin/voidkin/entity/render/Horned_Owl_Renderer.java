package net.voidkin.voidkin.entity.render;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.entity.custom.HornedOwlEntity;
import net.voidkin.voidkin.entity.custom.HornedOwlEntity;
import net.voidkin.voidkin.entity.model.HornedOwlModel;
import net.voidkin.voidkin.entity.variants.OwlVariant;

import java.util.Map;

public class Horned_Owl_Renderer extends MobRenderer<HornedOwlEntity, HornedOwlModel<HornedOwlEntity>> {
    public Horned_Owl_Renderer(EntityRendererProvider.Context context){
        super(context, new HornedOwlModel<>(context.bakeLayer(HornedOwlModel.LAYER_LOCATION)),0.4F);
    }
    private static final Map<OwlVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(OwlVariant.class), map -> {
                map.put(OwlVariant.MUD,
                        ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "textures/entity/owl/owl_reg.png"));
                map.put(OwlVariant.MUD_HORNED,
                        ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "textures/entity/owl/owl_reg_horned.png"));
                map.put(OwlVariant.BONE,
                        ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "textures/entity/owl/owl_snow.png"));
                map.put(OwlVariant.BONE_HORNED,
                        ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "textures/entity/owl/owl_snow_horned.png"));

            });

    @Override
    public ResourceLocation getTextureLocation(HornedOwlEntity cb) {
        return LOCATION_BY_VARIANT.get(cb.getVariant());
    }

    @Override
    public void render(HornedOwlEntity peary, float p_115456_, float p_115457_, PoseStack pStack, MultiBufferSource p_115459_, int p_115460_) {
        if(peary.isBaby()) {
            pStack.scale(0.5f, 0.5f, 0.5f);
        }else{pStack.scale(1f,1f,1f);

        }
        super.render(peary, p_115456_, p_115457_, pStack, p_115459_, p_115460_);
    }
}
