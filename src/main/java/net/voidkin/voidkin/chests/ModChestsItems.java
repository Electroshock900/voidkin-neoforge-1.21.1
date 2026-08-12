package net.voidkin.voidkin.chests;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.voidkin.voidkin.Voidkin;

import java.util.function.Function;
import java.util.function.Supplier;
public class ModChestsItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Voidkin.MODID);

    public static final DeferredItem<ChestUpgradeItem> IRON_TO_GOLD_CHEST_UPGRADE = ITEMS.register("iron_to_gold_chest_upgrade", () -> new ChestUpgradeItem(ModChestsUpgradeType.IRON_TO_GOLD, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<ChestUpgradeItem> GOLD_TO_DIAMOND_CHEST_UPGRADE = ITEMS.register("gold_to_diamond_chest_upgrade", () -> new ChestUpgradeItem(ModChestsUpgradeType.GOLD_TO_DIAMOND, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<ChestUpgradeItem> COPPER_TO_IRON_CHEST_UPGRADE = ITEMS.register("copper_to_iron_chest_upgrade", () -> new ChestUpgradeItem(ModChestsUpgradeType.COPPER_TO_IRON, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<ChestUpgradeItem> DIAMOND_TO_CRYSTAL_CHEST_UPGRADE = ITEMS.register("diamond_to_crystal_chest_upgrade", () -> new ChestUpgradeItem(ModChestsUpgradeType.DIAMOND_TO_CRYSTAL, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<ChestUpgradeItem> WOOD_TO_IRON_CHEST_UPGRADE = ITEMS.register("wood_to_iron_chest_upgrade", () -> new ChestUpgradeItem(ModChestsUpgradeType.WOOD_TO_IRON, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<ChestUpgradeItem> WOOD_TO_COPPER_CHEST_UPGRADE = ITEMS.register("wood_to_copper_chest_upgrade", () -> new ChestUpgradeItem(ModChestsUpgradeType.WOOD_TO_COPPER, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<ChestUpgradeItem> DIAMOND_TO_OBSIDIAN_CHEST_UPGRADE = ITEMS.register("diamond_to_obsidian_chest_upgrade", () -> new ChestUpgradeItem(ModChestsUpgradeType.DIAMOND_TO_OBSIDIAN, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<ChestUpgradeItem> IRON_TO_OBSIDIAN_CHEST_UPGRADE = ITEMS.register("iron_to_obsidian_chest_upgrade", () -> new ChestUpgradeItem(ModChestsUpgradeType.IRON_TO_OBSIDIAN, new Item.Properties().stacksTo(1)));


      public static <T extends Item> DeferredItem<T> register(String name, Function<Item.Properties, T> item, Supplier<Item.Properties> properties) {
        return ITEMS.register(name, () -> item.apply(properties.get()/*.setId(ResourceKey.create(Registries.ITEM, Voidkin.prefix(name)))*/));
    }

    public static <T extends Item> void register(String name, Function<Item.Properties, T> item, Supplier<Item.Properties> properties, ResourceKey<Block> blockResourceKey) {
        ITEMS.register(name, () -> item.apply(properties.get()));
                //.setId(ResourceKey.create(Registries.ITEM, blockResourceKey.location()))
                //.useBlockDescriptionPrefix()));
    }


    public static void register(IEventBus eventbus) {ITEMS.register(eventbus);}

}
