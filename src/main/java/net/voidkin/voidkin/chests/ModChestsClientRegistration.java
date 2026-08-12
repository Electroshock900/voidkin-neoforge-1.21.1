package net.voidkin.voidkin.chests;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.chests.inventory.ModChestScreen;
import net.voidkin.voidkin.chests.model.ModChestModel;
import net.voidkin.voidkin.chests.model.ModChestRenderer;

@EventBusSubscriber(modid= Voidkin.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.GAME)
public class ModChestsClientRegistration {


    public static final ModelLayerLocation IRON_CHEST = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "iron_chest"), "main");

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(IRON_CHEST, ModChestModel::createLayerDefinition);
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModChestsMenuTypes.IRON_CHEST.get(), ModChestScreen::new);
        event.register(ModChestsMenuTypes.GOLD_CHEST.get(), ModChestScreen::new);
        event.register(ModChestsMenuTypes.DIAMOND_CHEST.get(), ModChestScreen::new);
        event.register(ModChestsMenuTypes.CRYSTAL_CHEST.get(), ModChestScreen::new);
        event.register(ModChestsMenuTypes.COPPER_CHEST.get(), ModChestScreen::new);
        event.register(ModChestsMenuTypes.OBSIDIAN_CHEST.get(), ModChestScreen::new);
        event.register(ModChestsMenuTypes.DIRT_CHEST.get(), ModChestScreen::new);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModChestsBlockEntityTypes.IRON_CHEST.get(), ModChestRenderer::new);
        /**
         event.registerBlockEntityRenderer(ModChestsBlockEntityTypes.GOLD_CHEST.get(), ModChestRenderer::new);
         event.registerBlockEntityRenderer(ModChestsBlockEntityTypes.DIAMOND_CHEST.get(), ModChestRenderer::new);
         event.registerBlockEntityRenderer(ModChestsBlockEntityTypes.COPPER_CHEST.get(), ModChestRenderer::new);
         event.registerBlockEntityRenderer(ModChestsBlockEntityTypes.CRYSTAL_CHEST.get(), ModChestRenderer::new);
         **/
        event.registerBlockEntityRenderer(ModChestsBlockEntityTypes.OBSIDIAN_CHEST.get(), ModChestRenderer::new);
        //event.registerBlockEntityRenderer(ModChestsBlockEntityTypes.DIRT_CHEST.get(), ModChestRenderer::new);

        event.registerBlockEntityRenderer(ModChestsBlockEntityTypes.TRAPPED_IRON_CHEST.get(), ModChestRenderer::new);
        /**
         event.registerBlockEntityRenderer(ModChestsBlockEntityTypes.TRAPPED_GOLD_CHEST.get(), ModChestRenderer::new);
         event.registerBlockEntityRenderer(ModChestsBlockEntityTypes.TRAPPED_DIAMOND_CHEST.get(), ModChestRenderer::new);
         event.registerBlockEntityRenderer(ModChestsBlockEntityTypes.TRAPPED_COPPER_CHEST.get(), ModChestRenderer::new);
         event.registerBlockEntityRenderer(ModChestsBlockEntityTypes.TRAPPED_CRYSTAL_CHEST.get(), ModChestRenderer::new);
         **/
        event.registerBlockEntityRenderer(ModChestsBlockEntityTypes.TRAPPED_OBSIDIAN_CHEST.get(), ModChestRenderer::new);
        //event.registerBlockEntityRenderer(ModChestsBlockEntityTypes.TRAPPED_DIRT_CHEST.get(), ModChestRenderer::new);
    }

    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(ModChestBlockItem.ModChestRender.INSTANCE,
                ModChestsBlocks.IRON_CHEST.asItem(), ModChestsBlocks.TRAPPED_IRON_CHEST.asItem(),
                //ModChestsBlocks.GOLD_CHEST.asItem(), ModChestsBlocks.TRAPPED_GOLD_CHEST.asItem(),
                //ModChestsBlocks.DIAMOND_CHEST.asItem(), ModChestsBlocks.TRAPPED_DIAMOND_CHEST.asItem(),
                //ModChestsBlocks.COPPER_CHEST.asItem(), ModChestsBlocks.TRAPPED_COPPER_CHEST.asItem(),
                //ModChestsBlocks.CRYSTAL_CHEST.asItem(), ModChestsBlocks.TRAPPED_CRYSTAL_CHEST.asItem(),
                ModChestsBlocks.OBSIDIAN_CHEST.asItem(), ModChestsBlocks.TRAPPED_OBSIDIAN_CHEST.asItem()
                //ModChestsBlocks.DIRT_CHEST.asItem(), ModChestsBlocks.TRAPPED_DIRT_CHEST.asItem()
        );
    }
}
