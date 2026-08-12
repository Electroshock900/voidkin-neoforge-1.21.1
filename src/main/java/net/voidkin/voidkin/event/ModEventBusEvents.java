package net.voidkin.voidkin.event;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.ArmorItem;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.entity.custom.*;
import net.voidkin.voidkin.entity.ModEntities;
import net.voidkin.voidkin.item.ModItems;
import net.voidkin.voidkin.item.armor.AbstractArmorItem;
import net.voidkin.voidkin.item.armor.client.ArmorClientExtension;
import net.voidkin.voidkin.item.armor.model.AbyssArmorModel;
import net.voidkin.voidkin.item.armor.model.provider.ArmorModelProvider;
import net.voidkin.voidkin.item.armor.model.provider.SimpleModelProvider;
import net.voidkin.voidkin.menu.ModMenuTypes;
import net.voidkin.voidkin.menu.screens.CrystallizerScreen;
import net.voidkin.voidkin.menu.screens.Pedestal2Screen;
import net.voidkin.voidkin.menu.screens.PedestalScreen;
import net.voidkin.voidkin.menu.screens.PolisherScreen;
import net.voidkin.voidkin.menu.screens.custom.WarTortoiseHybridScreen;
import net.voidkin.voidkin.menu.screens.custom.WarTortoiseScreen;
import net.voidkin.voidkin.menu.screens.custom.WarTurtleScreen;

import java.util.Map;

@EventBusSubscriber(modid= Voidkin.MODID,bus=EventBusSubscriber.Bus.MOD)
public class  ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntities.CACTUS_BUDDY.get(), Cactus_Buddy.setAttributes());
        event.put(ModEntities.CELESTIAL_TURTLE.get(), CelestialTurtle.setAttributes());
        event.put(ModEntities.EYEBALL_MONSTER.get(), Eyeball_Monster.setAttributes());
        event.put(ModEntities.LION_THING.get(), Lion_Thing.setAttributes());
        event.put(ModEntities.SKULL.get(), Skull_Entity.setAttributes());
        event.put(ModEntities.MINI_SKULL.get(), Mini_Skull_Entity.setAttributes());
        event.put(ModEntities.MANTA_RAY.get(), MantaRayEntity.setAttributes());
        event.put(ModEntities.HORNED_OWL.get(), HornedOwlEntity.setAttributes());
        event.put(ModEntities.OWL.get(), OwlEntity.setAttributes());
        event.put(ModEntities.PENGUIN.get(), PenguinEntity.setAttributes());
        event.put(ModEntities.WAR_TURTLE.get(), WarTurtle.createAttributes());
        event.put(ModEntities.WAR_TORTOISE.get(), WarTortoise.setAttributes());
        event.put(ModEntities.WAR_TORTOISE_HYBRID.get(), WarTortoiseHybrid.setAttributes());
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event){
        event.register(ModMenuTypes.PEDESTAL_MENU.get(), PedestalScreen::new);
        event.register(ModMenuTypes.PEDESTAL2_MENU.get(), Pedestal2Screen::new);
        event.register(ModMenuTypes.POLISHING_MENU.get(), PolisherScreen::new);
        event.register(ModMenuTypes.CRYSTALLIZER_MENU.get(), CrystallizerScreen::new);
        event.register(ModMenuTypes.WAR_TURTLE_MENU.get(), WarTurtleScreen::new);
        event.register(ModMenuTypes.WAR_TORTOISE_MENU.get(), WarTortoiseScreen::new);
        event.register(ModMenuTypes.WAR_TORTOISE_HYBRID_MENU.get(), WarTortoiseHybridScreen::new);
    }


    @SubscribeEvent
    public static void onRegisterClientExtensions(RegisterClientExtensionsEvent event) {


        registerArmorExtension(event, new SimpleModelProvider(AbyssArmorModel::createBodyLayer, AbyssArmorModel::new));
        //event.registerItem(new ArmorClientExtension(new SimpleModelProvider(WizardHatModel::createBodyLayer, WizardHatModel::new)), ModItems.WIZARD_HAT);


    }
    @SuppressWarnings("unchecked")
    private static <T extends AbstractArmorItem> void registerArmorExtension(RegisterClientExtensionsEvent event, ArmorModelProvider provider) {
        //event.registerItem(new ArmorClientExtension(provider), map.values().toArray(DeferredItem[]::new));
        event.registerItem(new ArmorClientExtension(provider), ModItems.ABYSS_HELMET);
        event.registerItem(new ArmorClientExtension(provider), ModItems.ABYSS_CHESTPLATE);
        event.registerItem(new ArmorClientExtension(provider), ModItems.ABYSS_LEGGINGS);
        event.registerItem(new ArmorClientExtension(provider), ModItems.ABYSS_BOOTS);
    }


}
