package net.voidkin.voidkin.item;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ModItemProperties {
    public static void addCustomItemProperties() {
        /**ItemProperties.register(ModItems.CHISEL.get(), ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "used"),
                (stack, level, entity, seed) -> stack.get(ModDataComponents.COORDINATES) != null ? 1f : 0f);
**/
        makeCustomBow(ModItems.ARESBOW.get());
    }

    private static void makeCustomBow(Item item) {
        ItemProperties.register(item, ResourceLocation.withDefaultNamespace("pull"), (stack, level, entity, i) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return entity.getUseItem() != stack ? 0.0F : (float)(stack.getUseDuration(entity) - entity.getUseItemRemainingTicks()) / 20.0F;
            }
        });
        ItemProperties.register(
                item,
                ResourceLocation.withDefaultNamespace("pulling"),
                (stack, level, entity, i) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F
        );
    }
}
