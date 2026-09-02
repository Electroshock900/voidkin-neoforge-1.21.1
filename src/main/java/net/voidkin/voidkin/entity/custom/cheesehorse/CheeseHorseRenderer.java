package net.voidkin.voidkin.entity.custom.cheesehorse;

import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.entity.ModModelLayers;
import net.voidkin.voidkin.entity.custom.CheeseHorse;
import net.voidkin.voidkin.entity.model.CheeseHorseModel;
import net.voidkin.voidkin.entity.variants.CheeseHorseVariant;

import java.util.Map;

public class CheeseHorseRenderer extends AbstractCheeseHorseRenderer<CheeseHorse, CheeseHorseModel<CheeseHorse>> {

    private static final Map<CheeseHorseVariant, ResourceLocation> LOCATION_BY_VARIANT = (Map) Util.make(Maps.newEnumMap(CheeseHorseVariant.class), (p_349902_) -> {
        p_349902_.put(CheeseHorseVariant.PROVOLONE, ResourceLocation.fromNamespaceAndPath(Voidkin.MODID,"textures/entity/horse/cheese_horse_provolone.png"));
        p_349902_.put(CheeseHorseVariant.LIMBURGER, ResourceLocation.fromNamespaceAndPath(Voidkin.MODID,"textures/entity/horse/cheese_horse_limburger.png"));
        p_349902_.put(CheeseHorseVariant.CHEDDAR, ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "textures/entity/horse/cheese_horse_cheddar.png"));

    });

    public CheeseHorseRenderer(EntityRendererProvider.Context context) {
        super(context, new CheeseHorseModel<>(context.bakeLayer(ModModelLayers.CHEESE_HORSE_LAYER)), 0.8F);
        this.addLayer(new CheeseHorseMarkingLayer(this));
        this.addLayer(new CheeseHorseArmorLayer(this, context.getModelSet()));
    }

    @Override
    public ResourceLocation getTextureLocation(CheeseHorse cheeseHorse) {
        return (ResourceLocation)LOCATION_BY_VARIANT.get(cheeseHorse.getVariant());
    }
}
