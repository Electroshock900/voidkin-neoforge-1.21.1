package net.voidkin.voidkin.entity.render;


import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.entity.custom.WarTortoise;
import net.voidkin.voidkin.entity.model.War_Tortoise_Model;
import net.voidkin.voidkin.entity.variants.CheeseHorseVariant;
import net.voidkin.voidkin.entity.variants.WarTortoiseVariant;

import java.util.Map;

public class War_Tortoise_Renderer extends MobRenderer<WarTortoise, War_Tortoise_Model<WarTortoise>> {
    public War_Tortoise_Renderer(EntityRendererProvider.Context context) {
        super(context, new War_Tortoise_Model<>(context.bakeLayer(War_Tortoise_Model.LAYER_LOCATION)), 1.5F);
    }
        private static final Map<WarTortoiseVariant, ResourceLocation> LOCATION_BY_VARIANT =
                Util.make(Maps.newEnumMap(WarTortoiseVariant.class), map -> {
                    map.put(WarTortoiseVariant.SHADOW,
                            ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "textures/entity/wartortoise/wartortoise.png"));
                    map.put(WarTortoiseVariant.BONE_VOID,
                            ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "textures/entity/wartortoise/wartortoise.png"));
                    map.put(WarTortoiseVariant.VOID,
                            ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "textures/entity/wartortoise/wartortoise.png"));

                });

    @Override
    public ResourceLocation getTextureLocation(WarTortoise cb) {
        return LOCATION_BY_VARIANT.get(cb.getVariant()); //ResourceLocation.fromNamespaceAndPath(Voidkin.MODID,"textures/entity/custom/cactus_buddy.png");
    }

    @Override
    public void render(WarTortoise cactusBuddy, float p_115456_, float p_115457_, PoseStack pStack, MultiBufferSource p_115459_, int p_115460_) {
        if(cactusBuddy.isBaby()) {
            pStack.scale(1f, 1f, 1f);
        }else{pStack.scale(2f,2f,2f);

        }
        super.render(cactusBuddy, p_115456_, p_115457_, pStack, p_115459_, p_115460_);
    }
}
