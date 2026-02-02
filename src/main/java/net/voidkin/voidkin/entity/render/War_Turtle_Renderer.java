package net.voidkin.voidkin.entity.render;


import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.entity.custom.WarTurtle;
import net.voidkin.voidkin.entity.model.War_Turtle_Model;
import net.voidkin.voidkin.entity.render.layers.WarTurtleArmorLayer;
import net.voidkin.voidkin.entity.variants.WarTurtleVariant;

import java.util.Map;

public class War_Turtle_Renderer extends MobRenderer<WarTurtle, War_Turtle_Model<WarTurtle>> {
    public War_Turtle_Renderer(EntityRendererProvider.Context context) {
        super(context, new War_Turtle_Model<>(context.bakeLayer(War_Turtle_Model.LAYER_LOCATION)), 1.5F);
        this.addLayer(new WarTurtleArmorLayer(this, context.getModelSet()));
    }
        private static final Map<WarTurtleVariant, ResourceLocation> LOCATION_BY_VARIANT =
                Util.make(Maps.newEnumMap(WarTurtleVariant.class), map -> {
                    map.put(WarTurtleVariant.PLAINS,
                            ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "textures/entity/warturtle/warturtle.png"));
                    map.put(WarTurtleVariant.DESERT,
                            ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "textures/entity/warturtle/warturtle.png"));
                    map.put(WarTurtleVariant.VOID,
                            ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "textures/entity/warturtle/warturtle.png"));

                });

    @Override
    public ResourceLocation getTextureLocation(WarTurtle cb) {
        return ResourceLocation.fromNamespaceAndPath(Voidkin.MODID,"textures/entity/warturtle/warturtle.png");
                //LOCATION_BY_VARIANT.get(cb.getVariant());
        //ResourceLocation.fromNamespaceAndPath(Voidkin.MODID,"textures/entity/custom/cactus_buddy.png");
    }

    @Override
    public void render(WarTurtle cactusBuddy, float p_115456_, float p_115457_, PoseStack pStack, MultiBufferSource p_115459_, int p_115460_) {
        if(cactusBuddy.isBaby()) {
            pStack.scale(0.5f, 0.5f, 0.5f);
        }else{
            pStack.scale(1f, 1f, 1f);
            //pStack.scale(2f,2f,2f);

        }
        super.render(cactusBuddy, p_115456_, p_115457_, pStack, p_115459_, p_115460_);
    }
}
