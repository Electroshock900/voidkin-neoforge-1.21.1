package net.voidkin.voidkin;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.items.wrapper.InvWrapper;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.voidkin.voidkin.block.ModBlockEntities;
import net.voidkin.voidkin.block.ModBlocks;
import net.voidkin.voidkin.chests.*;
import net.voidkin.voidkin.chests.network.TopStacksSyncPacket;
import net.voidkin.voidkin.datagen.ModDataComponents;
import net.voidkin.voidkin.effect.ModEffects;
import net.voidkin.voidkin.enchantments.ModEnchantmentEffects;
import net.voidkin.voidkin.enchantments.ModEnchantments;
import net.voidkin.voidkin.entity.ModEntities;
import net.voidkin.voidkin.fluid.ModFluidTypes;
import net.voidkin.voidkin.fluid.ModFluids;
import net.voidkin.voidkin.item.ModItemProperties;
import net.voidkin.voidkin.item.ModItems;
import net.voidkin.voidkin.menu.ModMenuTypes;
import net.voidkin.voidkin.particles.ModParticles;
import net.voidkin.voidkin.recipe.ModRecipes;
import net.voidkin.voidkin.sounds.ModSounds;
import net.voidkin.voidkin.util.*;
import net.voidkin.voidkin.worldgen.portal.ModDataAttachments;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Voidkin.MODID)
public class Voidkin {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "voidkin";
    public static final String MOD_ID = MODID;
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "voidkin" namespace
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "voidkin" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "voidkin" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // Creates a new Block with the id "voidkin:example_block", combining the namespace and path
    public static final DeferredBlock<Block> EXAMPLE_BLOCK = BLOCKS.registerSimpleBlock("example_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    // Creates a new BlockItem with the id "voidkin:example_block", combining the namespace and path
    public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("example_block", EXAMPLE_BLOCK);

    // Creates a new food item with the id "voidkin:example_id", nutrition 1 and saturation 2
    public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.registerSimpleItem("example_item", new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEdible().nutrition(1).saturationModifier(2f).build()));

    // Creates a creative tab with the id "voidkin:example_tab" for the example item, that is placed after the combat tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.voidkin")) //The language key for the title of your CreativeModeTab
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> EXAMPLE_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(EXAMPLE_ITEM.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
            }).build());

    public static String toEnglishName(String internalName) {
        return Arrays.stream(internalName.toLowerCase(Locale.ROOT).split("_"))
                .map(StringUtils::capitalize)
                .collect(Collectors.joining(" "));
    }
    public static ResourceLocation prefix(String name) {
        return ResourceLocation.fromNamespaceAndPath(MODID, name.toLowerCase(Locale.ROOT));
    }

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Voidkin(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::setupPackets);
        modEventBus.addListener(this::registerCapabilities);


        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModEntities.register(modEventBus);

        ModTabs.register(modEventBus);
        ModParticles.register(modEventBus);

        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);

        ModFluids.register(modEventBus);
        ModFluidTypes.register(modEventBus);

        ModPotions.register(modEventBus);
        ModSounds.register(modEventBus);

        ModEffects.register(modEventBus);
        ModEnchantments.register(modEventBus);
        ModEnchantmentEffects.register(modEventBus);

        ModChestsBlocks.CHEST_BLOCKS.register(modEventBus);
        ModChestsBlockEntityTypes.BLOCK_ENTITIES.register(modEventBus);
        ModChestsItems.ITEMS.register(modEventBus);
        ModChestsMenuTypes.CONTAINERS.register(modEventBus);

        ModDataComponents.REGISTRY.register(modEventBus);
        ModDataAttachments.ATTACHMENT_TYPES.register(modEventBus);
        modEventBus.addListener(ModGameRules::register);


        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (Voidkin) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.LOG_DIRT_BLOCK.getAsBoolean()) {
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
        }

        LOGGER.info("{}{}", Config.MAGIC_NUMBER_INTRODUCTION.get(), Config.MAGIC_NUMBER.getAsInt());

        Config.ITEM_STRINGS.get().forEach((item) -> LOGGER.info("ITEM >> {}", item));
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(EXAMPLE_BLOCK_ITEM);
        }
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS){
            event.accept(ModItems.CACTUS_EGG.get());
            event.accept(ModItems.CHEESE_HORSE_EGG.get());
            event.accept(ModItems.MEAT_DONKEY_EGG.get());
            event.accept(ModItems.HOAGIE_MULE_EGG.get());
            event.accept(ModItems.CELESTIAL_TURTLE_EGG.get());
            event.accept(ModItems.EYEBALL_MONSTER_EGG.get());
            event.accept(ModItems.SKULL_EGG.get());
            event.accept(ModItems.MINI_SKULL_EGG.get());
            event.accept(ModItems.LION_THING_EGG.get());
            event.accept(ModItems.MANTA_RAY_EGG.get());
            event.accept(ModItems.HORNED_OWL_EGG.get());
            event.accept(ModItems.OWL_EGG.get());
            event.accept(ModItems.PENGUIN_EGG.get());
            event.accept(ModItems.WAR_TURTLE_EGG);
            event.accept(ModItems.WAR_TORTOISE_EGG);
            event.accept(ModItems.WAR_TORTOISE_HYBRID_EGG);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value=Dist.CLIENT)
        public static class ClientModEvents{
            @SubscribeEvent
            public static void onClientSetup(FMLClientSetupEvent event) {
                ModItemProperties.addCustomItemProperties();
                Sheets.addWoodType(ModWoodTypes.DARK);
                Sheets.addWoodType(ModWoodTypes.BLOOD);
                Sheets.addWoodType(ModWoodTypes.VOID);
                Sheets.addWoodType(ModWoodTypes.VERAWOOD);
            }
        }


        public void setupPackets(RegisterPayloadHandlersEvent event) {
            PayloadRegistrar registrar = event.registrar(MODID).versioned("1.0.0").optional();

            registrar.playBidirectional(TopStacksSyncPacket.TYPE, TopStacksSyncPacket.STREAM_CODEC, TopStacksSyncPacket::handle);
        }

    public void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlock(Capabilities.ItemHandler.BLOCK, (level, pos, state, blockEntity, side) -> level.getBlockEntity(pos) instanceof AbstractModChestBlockEntity ironChestBlockEntity ? new InvWrapper(ironChestBlockEntity) : null,
                ModChestsBlocks.IRON_CHEST.get(), ModChestsBlocks.TRAPPED_IRON_CHEST.get(),
                //ModChestsBlocks.GOLD_CHEST.get(), ModChestsBlocks.TRAPPED_GOLD_CHEST.get(),
                //ModChestsBlocks.DIAMOND_CHEST.get(), ModChestsBlocks.TRAPPED_DIAMOND_CHEST.get(),
                //ModChestsBlocks.COPPER_CHEST.get(), ModChestsBlocks.TRAPPED_COPPER_CHEST.get(),
                //ModChestsBlocks.CRYSTAL_CHEST.get(), ModChestsBlocks.TRAPPED_CRYSTAL_CHEST.get(),
                ModChestsBlocks.OBSIDIAN_CHEST.get(), ModChestsBlocks.TRAPPED_OBSIDIAN_CHEST.get()
                //ModChestsBlocks.DIRT_CHEST.get()
        );
    }

}
