package net.voidkin.voidkin.entity.custom.cheesehorse;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.entity.ModModelLayers;
import net.voidkin.voidkin.entity.model.ChestedCheeseHorseModel;

public class HoagieMuleRenderer extends AbstractCheeseHorseRenderer<AbstractChestedCheeseHorse, ChestedCheeseHorseModel<AbstractChestedCheeseHorse>> {
    public HoagieMuleRenderer(EntityRendererProvider.Context context) {
        super(context, new ChestedCheeseHorseModel(context.bakeLayer(ModModelLayers.HOAGIE_MULE_LAYER)), 1);
    }


    public ResourceLocation getTextureLocation(AbstractChestedCheeseHorse entity) {
        return ResourceLocation.fromNamespaceAndPath(Voidkin.MODID,"textures/entity/horse/mule.png");
    }

}
