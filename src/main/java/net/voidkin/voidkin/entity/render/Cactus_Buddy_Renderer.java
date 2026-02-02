package net.voidkin.voidkin.entity.render;

import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.entity.ModModelLayers;
import net.voidkin.voidkin.entity.custom.Cactus_Buddy;
import net.voidkin.voidkin.entity.model.Cactus_Buddy_Model;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidkin.voidkin.entity.variants.CactusBuddyVariant;

import java.util.Map;

public class Cactus_Buddy_Renderer extends MobRenderer<Cactus_Buddy, Cactus_Buddy_Model<Cactus_Buddy>> {
    public Cactus_Buddy_Renderer(EntityRendererProvider.Context context){
        super(context, new Cactus_Buddy_Model<>(context.bakeLayer(Cactus_Buddy_Model.LAYER_LOCATION)),0.4F);
    }
    private static final Map<CactusBuddyVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(CactusBuddyVariant.class), map -> {
                map.put(CactusBuddyVariant.REGULAR,
                        ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "textures/entity/custom/cactus_buddy.png"));
                map.put(CactusBuddyVariant.BLOOD,
                        ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "textures/entity/custom/cactus_buddy_nether.png"));
                map.put(CactusBuddyVariant.DRY,
                        ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "textures/entity/custom/cactus_buddy_ender.png"));

            });

    @Override
    public ResourceLocation getTextureLocation(Cactus_Buddy cb) {
        return LOCATION_BY_VARIANT.get(cb.getVariant());
    }

    @Override
    public void render(Cactus_Buddy cactusBuddy, float p_115456_, float p_115457_, PoseStack pStack, MultiBufferSource p_115459_, int p_115460_) {
        if(cactusBuddy.isBaby()) {
            pStack.scale(2f, 2f, 2f);
        }else{pStack.scale(4f,4f,4f);

        }
        super.render(cactusBuddy, p_115456_, p_115457_, pStack, p_115459_, p_115460_);
    }
}
