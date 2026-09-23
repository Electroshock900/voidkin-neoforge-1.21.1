package net.voidkin.voidkin.world;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.*;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.block.ModBlocks;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> DARK_PLACED_KEY = registerKey("DARK_placed");

    public static final ResourceKey<PlacedFeature> DARK_SHARD_ORE_PLACED_KEY = registerKey("dark_shard_ore_placed");
    public static final ResourceKey<PlacedFeature> NETHER_DARK_SHARD_ORE_PLACED_KEY = registerKey("nether_dark_shard_ore_placed");
    public static final ResourceKey<PlacedFeature> END_DARK_SHARD_ORE_PLACED_KEY = registerKey("end_dark_shard_ore_placed");

    public static final ResourceKey<PlacedFeature> DARK_TREE_PLACED_KEY = registerKey("dark_tree_placed");
    public static final ResourceKey<PlacedFeature> BLOOD_TREE_PLACED_KEY = registerKey("blood_tree_placed");
    public static final ResourceKey<PlacedFeature> VOID_TREE_PLACED_KEY = registerKey("void_tree_placed");
    public static final ResourceKey<PlacedFeature> VERAWOOD_TREE_PLACED_KEY = registerKey("verawood_tree_placed");
    public static final ResourceKey<PlacedFeature> TRUE_VOID_TREE_PLACED_KEY = registerKey("true_void_tree_placed");

    public static final ResourceKey<PlacedFeature> DARK_STONE_PILLARS = registerKey("dark_stone_pillars_places");

    public static final ResourceKey<PlacedFeature> PETUNIA_PLACED_KEY = registerKey("petunia_placed");
    public static final ResourceKey<PlacedFeature> DARK_SHARD_GEODE_PLACED_KEY = registerKey("dark_shard_geode_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);


        register(context, DARK_SHARD_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.DARK_ORE_KEY),
                ModOrePlacement.commonOrePlacement(12,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));
        register(context, NETHER_DARK_SHARD_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_DARK_ORE_KEY),
                ModOrePlacement.commonOrePlacement(9,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));
        /*register(context, END_DARK_SHARD_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.END_DARK_ORE_KEY),
                ModOrePlacement.commonOrePlacement(9,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));*/

        register(context, DARK_TREE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.DARK_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2), ModBlocks.DARK_SAPLING.get()));
        register(context, BLOOD_TREE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLOOD_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 4), ModBlocks.BLOOD_SAPLING.get()));
        register(context, VOID_TREE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.VOID_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2), ModBlocks.VOID_SAPLING.get()));
        register(context, VERAWOOD_TREE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.VERAWOOD_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2), ModBlocks.VERAWOOD_SAPLING.get()));

        register(context, DARK_STONE_PILLARS, configuredFeatures.getOrThrow(ModConfiguredFeatures.DARK_STONE_PILLAR),
                countPlacement(4, PlacementUtils.FULL_RANGE));
                

        /*register(context, PETUNIA_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PETUNIA_KEY),
                List.of(RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));*/

        /*register(context, DARK_SHARD_GEODE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.DARK_SHARD_GEODE_KEY),
                List.of(RarityFilter.onAverageOnceEvery(50), InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(50)),
                        BiomeFilter.biome()));*/
    }


    private static List<PlacementModifier> countPlacement(int attempts, PlacementModifier heightRange) {
        return modifiedPlacement(CountPlacement.of(attempts), heightRange);
    }
    private static List<PlacementModifier> modifiedPlacement(PlacementModifier count, PlacementModifier heightRange) {
        return List.of(count, InSquarePlacement.spread(), heightRange, BiomeFilter.biome());
    }


    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Voidkin.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
