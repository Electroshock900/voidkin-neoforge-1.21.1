package net.voidkin.voidkin.chests.model;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.chests.ModChestsTypes;
import org.jetbrains.annotations.NotNull;

public class ModChestsModels {

    public static final Material IRON_CHEST_LOCATION = chestMaterial(false, "iron_chest");
    public static final Material GOLD_CHEST_LOCATION = chestMaterial(false, "block/chests/gold_chest");
    public static final Material DIAMOND_CHEST_LOCATION = chestMaterial(false, "block/chests/diamond_chest");
    public static final Material COPPER_CHEST_LOCATION = chestMaterial(false, "block/chests/copper_chest");
    public static final Material CRYSTAL_CHEST_LOCATION = chestMaterial(false, "block/chests/crystal_chest");
    public static final Material OBSIDIAN_CHEST_LOCATION = chestMaterial(false, "obsidian_chest");
    public static final Material DIRT_CHEST_LOCATION = chestMaterial(false, "block/chests/dirt_chest");
    public static final Material VANILLA_CHEST_LOCATION = chestMaterial(true, "normal");

    public static final Material TRAPPED_IRON_CHEST_LOCATION = chestMaterial(false, "trapped_iron_chest");
    public static final Material TRAPPED_GOLD_CHEST_LOCATION = chestMaterial(false, "block/chests/trapped_gold_chest");
    public static final Material TRAPPED_DIAMOND_CHEST_LOCATION = chestMaterial(false, "block/chests/trapped_diamond_chest");
    public static final Material TRAPPED_COPPER_CHEST_LOCATION = chestMaterial(false, "block/chests/trapped_copper_chest");
    public static final Material TRAPPED_CRYSTAL_CHEST_LOCATION = chestMaterial(false, "block/chests/trapped_crystal_chest");
    public static final Material TRAPPED_OBSIDIAN_CHEST_LOCATION = chestMaterial(false, "trapped_obsidian_chest");
    public static final Material TRAPPED_DIRT_CHEST_LOCATION = chestMaterial(false, "block/chests/trapped_dirt_chest");
    public static final Material TRAPPED_VANILLA_CHEST_LOCATION = chestMaterial(true, "trapped");

    public static Material chooseChestMaterial(ModChestsTypes type, boolean trapped) {
        if (trapped) {
            return getMaterial(type, TRAPPED_IRON_CHEST_LOCATION,
                    TRAPPED_GOLD_CHEST_LOCATION,
                    TRAPPED_DIAMOND_CHEST_LOCATION,
                    TRAPPED_COPPER_CHEST_LOCATION,
                    TRAPPED_CRYSTAL_CHEST_LOCATION,
                    TRAPPED_OBSIDIAN_CHEST_LOCATION,
                    TRAPPED_DIRT_CHEST_LOCATION,
                    TRAPPED_VANILLA_CHEST_LOCATION);
        } else {
            return getMaterial(type, IRON_CHEST_LOCATION,
                    GOLD_CHEST_LOCATION,
                    DIAMOND_CHEST_LOCATION,
                    COPPER_CHEST_LOCATION,
                    CRYSTAL_CHEST_LOCATION,
                    OBSIDIAN_CHEST_LOCATION,
                    DIRT_CHEST_LOCATION,
                    VANILLA_CHEST_LOCATION);
        }
    }

    @NotNull
    private static Material getMaterial(ModChestsTypes type, Material ironChestMaterial, Material goldChestMaterial, Material diamondChestMaterial, Material copperChestMaterial, Material crystalChestMaterial, Material obsidianChestMaterial, Material dirtChestMaterial, Material vanillaChestMaterial) {
        //Voidkin.LOGGER.warn(obsidianChestMaterial.toString());
        return switch (type) {
            case IRON -> ironChestMaterial;
            case GOLD -> goldChestMaterial;
            case DIAMOND -> diamondChestMaterial;
            case COPPER -> copperChestMaterial;
            case CRYSTAL -> crystalChestMaterial;
            case OBSIDIAN -> obsidianChestMaterial;
            case DIRT -> dirtChestMaterial;
            default -> vanillaChestMaterial;

        };
    }

    private static Material chestMaterial(boolean vanillaChest, String chestName) {
        if (vanillaChest) {
            return new Material(Sheets.CHEST_SHEET, ResourceLocation.withDefaultNamespace("entity/chest/" + chestName));
        } else {

            return new Material(Sheets.CHEST_SHEET, Voidkin.prefix("entity/chest/" + chestName));
        }

    }
}
