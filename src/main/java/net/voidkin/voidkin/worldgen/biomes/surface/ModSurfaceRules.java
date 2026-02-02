package net.voidkin.voidkin.worldgen.biomes.surface;

import net.voidkin.voidkin.block.ModBlocks;
import net.voidkin.voidkin.worldgen.biomes.ModBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class ModSurfaceRules {
    /**
     //candy cane
    private static final SurfaceRules.RuleSource CANDY_CANE_STONE = makeStateRule(SNSBlocks.CANDY_CANE_STONE.get());
    private static final SurfaceRules.RuleSource CANDY_CANE_GRASS_BLOCK = makeStateRule(SNSBlocks.CANDY_CANE_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource CANDY_CANE_DIRT = makeStateRule(SNSBlocks.CANDY_CANE_DIRT.get());
    //cinnamon
    private static final SurfaceRules.RuleSource CINNAMON_BLOCK = makeStateRule(SNSBlocks.CINNAMON_BLOCK.get());
    private static final SurfaceRules.RuleSource CINNAMON_GRASS_BLOCK = makeStateRule(SNSBlocks.CINNAMON_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource CINNAMON_DIRT = makeStateRule(SNSBlocks.CINNAMON_DIRT.get());
    **/
    //DARK
    private static final SurfaceRules.RuleSource DARK_STONE = makeStateRule(ModBlocks.DARK_STONE.get());
    private static final SurfaceRules.RuleSource DARK_GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource DARK_DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource DARK = makeStateRule(ModBlocks.DARKNESS_BLOCK.get());
    private static final SurfaceRules.RuleSource RAW_DARK = makeStateRule(ModBlocks.RAW_DARKNESS_BLOCK.get());
/**
    //mint
    private static final SurfaceRules.RuleSource MINT_DIRT = makeStateRule(SNSBlocks.MINT_DIRT.get());
    private static final SurfaceRules.RuleSource MINT_GRASS_BLOCK = makeStateRule(SNSBlocks.MINT_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource MINT_BLOCK = makeStateRule(SNSBlocks.MINT_BLOCK.get());
    //toothpaste
    private static final SurfaceRules.RuleSource TOOTHPASTE_BLOCK = makeStateRule(SNSBlocks.TOOTHPASTE_BLOCK.get());
    private static final SurfaceRules.RuleSource TOOTHPASTE_GRASS_BLOCK = makeStateRule(SNSBlocks.TOOTHPASTE_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource TOOTHPASTE_STONE = makeStateRule(SNSBlocks.TOOTHPASTE_STONE.get());
**/
    public static SurfaceRules.RuleSource makeRules() {

        return SurfaceRules.sequence(
//DARK BIOME
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.DARK_BIOME),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, DARK_GRASS_BLOCK)),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.DARK_BIOME),
                        SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DARK_DIRT)),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.DARK_BIOME),
                        SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, DARK_STONE)),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.DARK_BIOME),
                        SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, DARK_STONE)),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.DARK_BIOME),
                        SurfaceRules.ifTrue(SurfaceRules.VERY_DEEP_UNDER_FLOOR, DARK_STONE)),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.DARK_BIOME),
                        SurfaceRules.ifTrue(SurfaceRules.VERY_DEEP_UNDER_FLOOR, RAW_DARK)),

                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.DARK_BIOME),
                            SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, RAW_DARK)),
                        SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, RAW_DARK)


        );
/**
//cinnamon
                SurfaceRules.ifTrue(SurfaceRules.isBiome(SNSBiomes.CINNAMON_BIOME),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, CINNAMON_GRASS_BLOCK)),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(SNSBiomes.CINNAMON_BIOME),
                        SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, CINNAMON_DIRT)),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(SNSBiomes.CINNAMON_BIOME),
                        SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, CINNAMON_BLOCK)),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(SNSBiomes.CINNAMON_BIOME),
                        SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, CINNAMON_BLOCK)),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(SNSBiomes.CINNAMON_BIOME),
                        SurfaceRules.ifTrue(SurfaceRules.VERY_DEEP_UNDER_FLOOR, CINNAMON_BLOCK)),
//mint
                SurfaceRules.ifTrue(SurfaceRules.isBiome(SNSBiomes.MINT_BIOME),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, MINT_GRASS_BLOCK)),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(SNSBiomes.MINT_BIOME),
                        SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, MINT_DIRT)),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(SNSBiomes.MINT_BIOME),
                        SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, MINT_BLOCK)),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(SNSBiomes.MINT_BIOME),
                        SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, MINT_BLOCK)),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(SNSBiomes.MINT_BIOME),
                        SurfaceRules.ifTrue(SurfaceRules.VERY_DEEP_UNDER_FLOOR, MINT_BLOCK)),

//toothpaste
                SurfaceRules.ifTrue(SurfaceRules.isBiome(SNSBiomes.TOOTHPASTE_BIOME),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, TOOTHPASTE_GRASS_BLOCK)),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(SNSBiomes.TOOTHPASTE_BIOME),
                        SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, CANDY_CANE_DIRT)),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(SNSBiomes.TOOTHPASTE_BIOME),
                        SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, TOOTHPASTE_STONE)),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(SNSBiomes.TOOTHPASTE_BIOME),
                        SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, TOOTHPASTE_STONE)),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(SNSBiomes.TOOTHPASTE_BIOME),
                        SurfaceRules.ifTrue(SurfaceRules.VERY_DEEP_UNDER_FLOOR, TOOTHPASTE_BLOCK))
**/
        //);
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
