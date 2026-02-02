package net.voidkin.voidkin.entity.render;


import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.entity.custom.WarTortoiseHybrid;
import net.voidkin.voidkin.entity.model.War_Tortoise_Hybrid_Model;
import net.voidkin.voidkin.entity.variants.WarTortoiseHybridVariant;

import java.util.Map;

public class War_Tortoise_Hybrid_Renderer extends MobRenderer<WarTortoiseHybrid, War_Tortoise_Hybrid_Model<WarTortoiseHybrid>> {
    public War_Tortoise_Hybrid_Renderer(EntityRendererProvider.Context context) {
        super(context, new War_Tortoise_Hybrid_Model<>(context.bakeLayer(War_Tortoise_Hybrid_Model.LAYER_LOCATION)), 1.5F);
    }
        private static final Map<WarTortoiseHybridVariant, ResourceLocation> LOCATION_BY_VARIANT =
                Util.make(Maps.newEnumMap(WarTortoiseHybridVariant.class), map -> {
                    map.put(WarTortoiseHybridVariant.SHADOW,
                            ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "textures/entity/wartortoisehybrid/wartortoisehybrid.png"));
                    map.put(WarTortoiseHybridVariant.BONE_VOID,
                            ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "textures/entity/wartortoisehybrid/wartortoisehybrid.png"));
                    map.put(WarTortoiseHybridVariant.VOID,
                            ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "textures/entity/wartortoisehybrid/wartortoisehybrid.png"));

                });

    @Override
    public ResourceLocation getTextureLocation(WarTortoiseHybrid cb) {
        return LOCATION_BY_VARIANT.get(cb.getVariant()); //ResourceLocation.fromNamespaceAndPath(Voidkin.MODID,"textures/entity/custom/cactus_buddy.png");
    }

    @Override
    public void render(WarTortoiseHybrid cactusBuddy, float p_115456_, float p_115457_, PoseStack pStack, MultiBufferSource p_115459_, int p_115460_) {
        if(cactusBuddy.isBaby()) {
            pStack.scale(1f, 1f, 1f);
        }else{pStack.scale(2f,2f,2f);
        }
        //if(cactusBuddy.has)
        super.render(cactusBuddy, p_115456_, p_115457_, pStack, p_115459_, p_115460_);
    }
}
