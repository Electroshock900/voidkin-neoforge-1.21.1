package net.voidkin.voidkin.event;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.block.blockentity.renderer.PedestalBlockEntityRenderer;
import net.voidkin.voidkin.block.blockentity.renderer.PolisherBlockEntityRenderer;
import net.voidkin.voidkin.entity.ModModelLayers;
import net.voidkin.voidkin.entity.boats.ModBoatRenderer;
import net.voidkin.voidkin.entity.model.*;
import net.voidkin.voidkin.entity.render.*;
import net.voidkin.voidkin.item.ModItemProperties;
import net.voidkin.voidkin.item.ModItems;
import net.voidkin.voidkin.menu.ModMenuTypes;
import net.voidkin.voidkin.menu.screens.*;
import net.voidkin.voidkin.menu.screens.custom.WarTortoiseHybridScreen;
import net.voidkin.voidkin.menu.screens.custom.WarTortoiseScreen;
import net.voidkin.voidkin.menu.screens.custom.WarTurtleScreen;
import net.voidkin.voidkin.particles.*;
import net.voidkin.voidkin.block.ModBlockEntities;
import net.voidkin.voidkin.entity.ModEntities;
import net.voidkin.voidkin.particles.ModParticles;

@EventBusSubscriber(modid= Voidkin.MODID,bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(Cactus_Buddy_Model.LAYER_LOCATION, Cactus_Buddy_Model::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.SKULL_LAYER, Skull_Entity_Model::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.LION_THING_LAYER, Lion_Thing_Model::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.MINI_SKULL_LAYER, Mini_Skull_Entity_Model::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.EYEBALL_LAYER, Eyeball_Monster_Model::createBodyLayer);

        event.registerLayerDefinition(Thrown_Chakram_Model.LAYER_LOCATION, Thrown_Chakram_Model::createBodyLayer);

        event.registerLayerDefinition(Manta_Ray_Model.LAYER_LOCATION, Manta_Ray_Model::createBodyLayer);
        event.registerLayerDefinition(Owl_Model.LAYER_LOCATION, Owl_Model::createBodyLayer);
        event.registerLayerDefinition(HornedOwlModel.LAYER_LOCATION, HornedOwlModel::createBodyLayer);
        event.registerLayerDefinition(Penguin_Model.LAYER_LOCATION, Penguin_Model::createBodyLayer);

        event.registerLayerDefinition(War_Turtle_Model.LAYER_LOCATION, War_Turtle_Model::createBodyLayer);
        event.registerLayerDefinition(War_Turtle_Model.ARMOR_LAYER_LOCATION, War_Turtle_Model::createBodyLayer);
        event.registerLayerDefinition(War_Tortoise_Model.LAYER_LOCATION, War_Tortoise_Model::createBodyLayer);
        event.registerLayerDefinition(War_Tortoise_Hybrid_Model.LAYER_LOCATION, War_Tortoise_Hybrid_Model::createBodyLayer);

        event.registerLayerDefinition(ModModelLayers.DARK_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(ModModelLayers.DARK_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);
        event.registerLayerDefinition(ModModelLayers.BLOOD_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(ModModelLayers.BLOOD_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);
        event.registerLayerDefinition(ModModelLayers.VOID_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(ModModelLayers.VOID_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);
    }
    @SubscribeEvent
    public static void doSetup(FMLClientSetupEvent event) {


        EntityRenderers.register(ModEntities.CHAKRAM.get(), Thrown_Chakram_Renderer::new);
        EntityRenderers.register(ModEntities.SPINE.get(), Spine_Renderer::new);
        EntityRenderers.register(ModEntities.ANTI_SPINE.get(), Anti_Spine_Renderer::new);
        EntityRenderers.register(ModEntities.BLOODSPINE.get(), Blood_Spine_Renderer::new);
        EntityRenderers.register(ModEntities.ARESARROW.get(), Ares_Arrow_Renderer::new);

        EntityRenderers.register(ModEntities.CACTUS_BUDDY.get(), Cactus_Buddy_Renderer::new);
        EntityRenderers.register(ModEntities.EYEBALL_MONSTER.get(), Eyeball_Monster_Renderer::new);
        EntityRenderers.register(ModEntities.SKULL.get(), Skull_Renderer::new);
        EntityRenderers.register(ModEntities.MINI_SKULL.get(), Mini_Skull_Renderer::new);
        EntityRenderers.register(ModEntities.LION_THING.get(), Lion_Thing_Renderer::new);

        EntityRenderers.register(ModEntities.MANTA_RAY.get(), Manta_Ray_Renderer::new);
        EntityRenderers.register(ModEntities.OWL.get(), OwlRenderer::new);
        EntityRenderers.register(ModEntities.HORNED_OWL.get(), Horned_Owl_Renderer::new);
        EntityRenderers.register(ModEntities.PENGUIN.get(), PenguinRenderer::new);

        EntityRenderers.register(ModEntities.WAR_TURTLE.get(), War_Turtle_Renderer::new);
        EntityRenderers.register(ModEntities.WAR_TORTOISE.get(), War_Tortoise_Renderer::new);
        EntityRenderers.register(ModEntities.WAR_TORTOISE_HYBRID.get(), War_Tortoise_Hybrid_Renderer::new);

        EntityRenderers.register(ModEntities.MOD_BOAT.get(), pContext -> new ModBoatRenderer(pContext,false));
        EntityRenderers.register(ModEntities.MOD_CHEST_BOAT.get(), pContext -> new ModBoatRenderer(pContext,true));


        //MenuScreens.register(ModMenuTypes.CANDY_CANE_FURNACE_MENU.get(), CandyCaneFurnaceScreen::new);

        //MenuScreens.register(ModMenuTypes.POLISHING_MENU.get(), PolisherScreen::new);
        //MenuScreens.register(ModMenuTypes.CRYSTALLIZER_MENU.get(), CrystallizerScreen::new);

        /*MenuScreens.register(ModMenuTypes.DOM_MENU.get(), DomScreen::new);
        MenuScreens.register(ModMenuTypes.SUB_MENU.get(), SubScreen::new);*/
/**
        MenuScreens.register(ModMenuTypes.VOID_ALTAR_MENU.get(), VoidAltarScreen::new);
        MenuScreens.register(ModMenuTypes.VOID_PEDESTAL_MENU.get(), VoidPedestalScreen::new);
 **/
    /**
        MenuScreens.register(ModMenuTypes.PEDESTAL_MENU.get(), PedestalScreen::new);


        MenuScreens.register(ModMenuTypes.WAR_TURTLE_MENU.get(), WarTurtleScreen::new);
        MenuScreens.register(ModMenuTypes.WAR_TORTOISE_MENU.get(), WarTortoiseScreen::new);
        MenuScreens.register(ModMenuTypes.WAR_TORTOISE_HYBRID_MENU.get(), WarTortoiseHybridScreen::new);
**/

    }


    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.CANDY_CANE_FLAME_PARTICLES.get(), CandyCaneFlameParticles.Provider::new);
        event.registerSpriteSet(ModParticles.DEATH_SKULLS.get(), DeathSkullParticles.Provider::new);
        event.registerSpriteSet(ModParticles.VOID_FLAME.get(), VoidFlameParticles.Provider::new);
        event.registerSpriteSet(ModParticles.SMALL_VOID_FLAME.get(), VoidFlameParticles.SmallFlameProvider::new);
        event.registerSpriteSet(ModParticles.SOUL_FLAME.get(), SoulFlameParticles.Provider::new);
        event.registerSpriteSet(ModParticles.ANTI_VOID.get(), Anti_Void_Particles.Provider::new);
        event.registerSpriteSet(ModParticles.SMALL_ANTI_VOID.get(), Anti_Void_Particles.SmallFlameProvider::new);

        event.registerSpriteSet(ModParticles.DRIP.get(), ModDripParticle.Provider::new);
        event.registerSpriteSet(ModParticles.DRIP_FALL.get(), ModDripParticle.Provider::new);
        event.registerSpriteSet(ModParticles.DRIP_LAND.get(), ModDripParticle.Provider::new);

        event.registerSprite(ModParticles.DRIPPING_BLOOD.get(), ModDripParticle::createBloodDripHangParticle);
        event.registerSprite(ModParticles.FALLING_BLOOD.get(), ModDripParticle::createBloodDripFallParticle);
        event.registerSprite(ModParticles.LANDING_BLOOD.get(), ModDripParticle::createBloodDripLandParticle);

        event.registerSprite(ModParticles.DRIPPING_DEITY_BLOOD.get(), ModDripParticle::createDeityBloodDripHangParticle);
        event.registerSprite(ModParticles.FALLING_DEITY_BLOOD.get(), ModDripParticle::createDeityBloodDripFallParticle);
        event.registerSprite(ModParticles.LANDING_DEITY_BLOOD.get(), ModDripParticle::createDeityBloodDripLandParticle);

    }

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.POLISHER_BLOCK_ENTITY.get(), PolisherBlockEntityRenderer::new);
        //event.registerBlockEntityRenderer(ModBlockEntities.SPECIAL_FURNACE_BLOCK_ENTITY.get(), SpecialFurnaceBlockEntityRenderer::new);
        //event.registerBlockEntityRenderer(ModBlockEntities.DOM_BE.get(), DomBER::new);
        event.registerBlockEntityRenderer(ModBlockEntities.PEDESTAL.get(), PedestalBlockEntityRenderer::new);
        /**
        event.registerBlockEntityRenderer(ModBlockEntities.VOID_ALTAR.get(), VoidAltarBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.VOID_PEDESTAL.get(), VoidPedestalBlockEntityRenderer::new);
        **/
        //event.registerBlockEntityRenderer(ModBlockEntities.ABE.get(), AltarBER::new);

        event.registerBlockEntityRenderer(ModBlockEntities.MOD_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
    }


    @SubscribeEvent
    public static void onComputeFovModifierEvent(ComputeFovModifierEvent event) {
        if(event.getPlayer().isUsingItem() && event.getPlayer().getUseItem().getItem() == ModItems.ARESBOW.get()) {
            float fovModifier = 1f;
            int ticksUsingItem = event.getPlayer().getTicksUsingItem();
            float deltaTicks = (float)ticksUsingItem / 20f;
            if(deltaTicks > 1f) {
                deltaTicks = 1f;
            } else {
                deltaTicks *= deltaTicks;
            }
            fovModifier *= 1f - deltaTicks * 0.15f;
            event.setNewFovModifier(fovModifier);
        }
    }

}
