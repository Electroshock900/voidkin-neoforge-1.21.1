package net.voidkin.voidkin.entity.custom.cheesehorse;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.entity.custom.CheeseHorse;
import net.voidkin.voidkin.entity.model.CheeseHorseModel;

import java.util.Map;

public class CheeseHorseMarkingLayer extends RenderLayer<CheeseHorse, CheeseHorseModel<CheeseHorse>> {
    private static final Map<CheeseMarkings, ResourceLocation> LOCATION_BY_MARKINGS = (Map) Util.make(Maps.newEnumMap(CheeseMarkings.class), (p_349908_) -> {
        p_349908_.put(CheeseMarkings.NONE, (Object)null);
        p_349908_.put(CheeseMarkings.SALAMI, ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "textures/entity/horse/cheese_horse_markings_salami.png"));
        p_349908_.put(CheeseMarkings.MARBLE, ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "textures/entity/horse/cheese_horse_markings_mold.png"));
        p_349908_.put(CheeseMarkings.PARMESAN, ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "textures/entity/horse/cheese_horse_markings_parmesan.png"));
        p_349908_.put(CheeseMarkings.BLUE_CHEESE, ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "textures/entity/horse/cheese_horse_markings_blue_cheese.png"));

    });

    public CheeseHorseMarkingLayer(RenderLayerParent<CheeseHorse, CheeseHorseModel<CheeseHorse>> renderer) {
        super(renderer);
    }

    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, CheeseHorse livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        ResourceLocation resourcelocation = (ResourceLocation)LOCATION_BY_MARKINGS.get(livingEntity.getMarkings());
        if (resourcelocation != null && !livingEntity.isInvisible()) {
            VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.entityTranslucent(resourcelocation));
            ((CheeseHorseModel)this.getParentModel()).renderToBuffer(poseStack, vertexconsumer, packedLight, LivingEntityRenderer.getOverlayCoords(livingEntity, 0.0F));
        }

    }
}