package net.voidkin.voidkin.entity.custom.cheesehorse;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.entity.ModEntities;
import net.voidkin.voidkin.entity.ModModelLayers;
import net.voidkin.voidkin.entity.custom.MeatDonkey;
import net.voidkin.voidkin.entity.model.ChestedCheeseHorseModel;

import java.util.Map;

public class MeatDonkeyRenderer extends AbstractCheeseHorseRenderer<MeatDonkey, ChestedCheeseHorseModel<MeatDonkey>> {
    public MeatDonkeyRenderer(EntityRendererProvider.Context context) {
        super(context, new ChestedCheeseHorseModel(context.bakeLayer(ModModelLayers.MEAT_DONKEY_LAYER)), 1);
    }


    public ResourceLocation getTextureLocation(MeatDonkey entity) {
        return ResourceLocation.fromNamespaceAndPath(Voidkin.MODID,"textures/entity/horse/meat_donkey.png");
    }

}
