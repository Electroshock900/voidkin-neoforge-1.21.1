package net.voidkin.voidkin.chests;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.voidkin.voidkin.Voidkin;

public class ModChestsMenuTypes {

    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(Registries.MENU, Voidkin.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<ModChestMenu>> IRON_CHEST = CONTAINERS.register("iron_chest", () -> new MenuType<>(ModChestMenu::createIronContainer, FeatureFlags.REGISTRY.allFlags()));
    public static final DeferredHolder<MenuType<?>, MenuType<ModChestMenu>> GOLD_CHEST = CONTAINERS.register("gold_chest", () -> new MenuType<>(ModChestMenu::createGoldContainer, FeatureFlags.REGISTRY.allFlags()));
    public static final DeferredHolder<MenuType<?>, MenuType<ModChestMenu>> DIAMOND_CHEST = CONTAINERS.register("diamond_chest", () -> new MenuType<>(ModChestMenu::createDiamondContainer, FeatureFlags.REGISTRY.allFlags()));
    public static final DeferredHolder<MenuType<?>, MenuType<ModChestMenu>> CRYSTAL_CHEST = CONTAINERS.register("crystal_chest", () -> new MenuType<>(ModChestMenu::createCrystalContainer, FeatureFlags.REGISTRY.allFlags()));
    public static final DeferredHolder<MenuType<?>, MenuType<ModChestMenu>> COPPER_CHEST = CONTAINERS.register("copper_chest", () -> new MenuType<>(ModChestMenu::createCopperContainer, FeatureFlags.REGISTRY.allFlags()));
    public static final DeferredHolder<MenuType<?>, MenuType<ModChestMenu>> OBSIDIAN_CHEST = CONTAINERS.register("obsidian_chest", () -> new MenuType<>(ModChestMenu::createObsidianContainer, FeatureFlags.REGISTRY.allFlags()));
    public static final DeferredHolder<MenuType<?>, MenuType<ModChestMenu>> DIRT_CHEST = CONTAINERS.register("dirt_chest", () -> new MenuType<>(ModChestMenu::createDirtContainer, FeatureFlags.REGISTRY.allFlags()));
}
