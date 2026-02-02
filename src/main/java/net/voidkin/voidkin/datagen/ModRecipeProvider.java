package net.voidkin.voidkin.datagen;

import net.minecraft.core.HolderLookup;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.voidkin.voidkin.Voidkin;
//import net.voidkin.voidkin.recipe.providers.builder.AltarRecipeBuilder;
import net.voidkin.voidkin.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    private static final List<ItemLike> DARK_SHARD_SMELTABLES = List.of(ModBlocks.DARK_SHARD_ORE.get(),ModBlocks.DEEPSLATE_DARK_SHARD_ORE.get(),ModBlocks.NETHER_DARK_SHARD_ORE.get());
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(output, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {
/*
//FOOD BLOCK COOKING
        oreSmelting(consumer, List.of(ModBlocks.BBR.get()), RecipeCategory.FOOD, ModBlocks.BBC.get(), 0.7F, 200, "bbr_bbc");
        oreSmelting(consumer, List.of(ModBlocks.BCR.get()), RecipeCategory.FOOD, ModBlocks.BCC.get(), 0.7F, 200, "bcr_bcc");
        oreSmelting(consumer, List.of(ModBlocks.BMR.get()), RecipeCategory.FOOD, ModBlocks.BMC.get(), 0.7F, 200, "bmr_bmc");
        oreSmelting(consumer, List.of(ModBlocks.BPR.get()), RecipeCategory.FOOD, ModBlocks.BPC.get(), 0.7F, 200, "bpr_bpc");

        oreSmelting(consumer, List.of(ModItems.RAW_DARK_SHARD.get()), RecipeCategory.BUILDING_BLOCKS, ModItems.DARK_SHARD.get(), 0.7F, 50, "rds_ds");
        oreBlasting(consumer, List.of(ModItems.RAW_DARK_SHARD.get()), RecipeCategory.BUILDING_BLOCKS, ModItems.DARK_SHARD.get(), 1.4F, 25, "rds_ds_blasting");

        oreSmelting(consumer, DARK_SHARD_SMELTABLES,RecipeCategory.MISC,ModItems.DARK_SHARD.get(),0.25F,200,"darkshard");
        oreBlasting(consumer, DARK_SHARD_SMELTABLES,RecipeCategory.MISC,ModItems.DARK_SHARD.get(),0.50F,100,"darkshard");
//FOOD BLOCKS
        nineBlockStorageRecipes(consumer,RecipeCategory.FOOD, Items.BEEF,RecipeCategory.FOOD, ModBlocks.BBR.get());
        nineBlockStorageRecipes(consumer,RecipeCategory.FOOD, Items.COOKED_BEEF,RecipeCategory.FOOD, ModBlocks.BBC.get());
        nineBlockStorageRecipes(consumer,RecipeCategory.FOOD, Items.CHICKEN,RecipeCategory.FOOD, ModBlocks.BCR.get());
        nineBlockStorageRecipes(consumer,RecipeCategory.FOOD, Items.COOKED_CHICKEN,RecipeCategory.FOOD, ModBlocks.BCC.get());
        nineBlockStorageRecipes(consumer,RecipeCategory.FOOD, Items.MUTTON,RecipeCategory.FOOD, ModBlocks.BMR.get());
        nineBlockStorageRecipes(consumer,RecipeCategory.FOOD, Items.COOKED_MUTTON,RecipeCategory.FOOD, ModBlocks.BMC.get());
        nineBlockStorageRecipes(consumer,RecipeCategory.FOOD, Items.PORKCHOP,RecipeCategory.FOOD, ModBlocks.BPR.get());
        nineBlockStorageRecipes(consumer,RecipeCategory.FOOD, Items.COOKED_PORKCHOP,RecipeCategory.FOOD, ModBlocks.BPC.get());
//DARK SHARDS
        nineBlockStorageRecipes(consumer,RecipeCategory.BUILDING_BLOCKS, ModItems.RAW_DARK_SHARD.get(),RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_DARKNESS_BLOCK.get());
        nineBlockStorageRecipes(consumer,RecipeCategory.BUILDING_BLOCKS, ModItems.DARK_SHARD.get(),RecipeCategory.BUILDING_BLOCKS, ModBlocks.DARKNESS_BLOCK.get());
//CHAKRAM
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.CHAKRAM.get())
                .pattern("SSS")
                .pattern("SFS")
                .pattern("SSS")
                .define('S', ModItems.DARK_SHARD.get())
                .define('F', ModItems.FIRE_CHARM.get())
                .unlockedBy(getHasName(ModItems.CHAKRAM.get()),has(ModItems.CHAKRAM.get()))
                .unlockedBy(getHasName(ModItems.FIRE_CHARM.get()),has(ModItems.FIRE_CHARM.get()))
                .save(consumer,"chakram_recipe");

        nineBlockStorageRecipes(consumer,RecipeCategory.BUILDING_BLOCKS, ModItems.ANTI_SPINE.get(),RecipeCategory.BUILDING_BLOCKS, ModBlocks.ANTI_CACTUS.get());
        nineBlockStorageRecipes(consumer,RecipeCategory.BUILDING_BLOCKS, ModItems.BLOOD_SPINE.get(),RecipeCategory.BUILDING_BLOCKS, ModBlocks.DARK_CACTUS.get());
        nineBlockStorageRecipes(consumer,RecipeCategory.BUILDING_BLOCKS, ModItems.CACTUS_SPINE.get(),RecipeCategory.BUILDING_BLOCKS, Blocks.CACTUS );

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.ANTI_CACTUS_SWORD.get())
                        .pattern("  C")
                        .pattern(" C ")
                        .pattern("S  ")
                                .define('S',Items.STICK)
                                .define('C',ModBlocks.ANTI_CACTUS.get())
                .unlockedBy(getHasName(ModBlocks.ANTI_CACTUS.get()),has(ModBlocks.ANTI_CACTUS.get()))
                                        .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.END_CACTUS_SWORD.get())
                        .pattern("  C")
                        .pattern(" C ")
                        .pattern("S  ")
                                .define('S',Items.STICK)
                                .define('C',ModBlocks.END_CACTUS.get())
                .unlockedBy(getHasName(ModBlocks.END_CACTUS.get()),has(ModBlocks.END_CACTUS.get()))
                                        .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.BLOOD_CACTUS_SWORD.get())
                        .pattern("  C")
                        .pattern(" C ")
                        .pattern("S  ")
                                .define('S',Items.STICK)
                                .define('C',ModBlocks.DARK_CACTUS.get())
                .unlockedBy(getHasName(ModBlocks.DARK_CACTUS.get()),has(ModBlocks.DARK_CACTUS.get()))
                                        .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.CACTUS_SWORD.get())
                        .pattern("  C")
                        .pattern(" C ")
                        .pattern("S  ")
                                .define('S',Items.STICK)
                                .define('C',Blocks.CACTUS)
                .unlockedBy(getHasName(Blocks.CACTUS),has(Blocks.CACTUS))
                                        .save(consumer);

        nineBlockStorageRecipes(consumer,RecipeCategory.BUILDING_BLOCKS,ModItems.SOUL_MUD.get(),RecipeCategory.BUILDING_BLOCKS,Blocks.SOUL_SOIL);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, ModBlocks.DARK_BUTTON.get())
                .requires(ModBlocks.DARK_PLANKS.get())
                .unlockedBy(getHasName(ModBlocks.DARK_PLANKS.get()),has(ModBlocks.DARK_PLANKS.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, ModBlocks.BLOOD_BUTTON.get())
                .requires(ModBlocks.BLOOD_PLANKS.get())
                .unlockedBy(getHasName(ModBlocks.BLOOD_PLANKS.get()),has(ModBlocks.BLOOD_PLANKS.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, ModBlocks.VOID_BUTTON.get())
                .requires(ModBlocks.VOID_PLANKS.get())
                .unlockedBy(getHasName(ModBlocks.VOID_PLANKS.get()),has(ModBlocks.VOID_PLANKS.get()))
                .save(consumer);
//SIGNS
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS,ModItems.DARK_SIGN.get(),3)
                .pattern("DDD")
                .pattern("DDD")
                .pattern(" 3 ")
                .define('3', Items.STICK)
                .define('D', ModBlocks.DARK_PLANKS.get())
                .unlockedBy(getHasName(ModBlocks.DARK_PLANKS.get()),has(ModBlocks.DARK_PLANKS.get()))

                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS,ModItems.BLOOD_SIGN.get(),3)
                .pattern("DDD")
                .pattern("DDD")
                .pattern(" 3 ")
                .define('3', Items.STICK)
                .define('D', ModBlocks.BLOOD_PLANKS.get())
                .unlockedBy(getHasName(ModBlocks.BLOOD_PLANKS.get()),has(ModBlocks.BLOOD_PLANKS.get()))

                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS,ModItems.VOID_SIGN.get(),3)
                .pattern("DDD")
                .pattern("DDD")
                .pattern(" 3 ")
                .define('3', Items.STICK)
                .define('D', ModBlocks.VOID_PLANKS.get())
                .unlockedBy(getHasName(ModBlocks.VOID_PLANKS.get()),has(ModBlocks.VOID_PLANKS.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS,ModItems.BLOOD_HANGING_SIGN.get(),6)
                .pattern("C C")
                .pattern("DDD")
                .pattern("DDD")
                .define('C', Items.CHAIN)
                .define('D', ModBlocks.STRIPPED_BLOOD_LOG.get())
                .unlockedBy(getHasName(Blocks.CHAIN),has(Blocks.CHAIN))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS,ModItems.DARK_HANGING_SIGN.get(),6)
                .pattern("C C")
                .pattern("DDD")
                .pattern("DDD")
                .define('C', Items.CHAIN)
                .define('D', ModBlocks.STRIPPED_DARK_LOG.get())
                .unlockedBy(getHasName(Blocks.CHAIN),has(Blocks.CHAIN))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS,ModItems.VOID_HANGING_SIGN.get(),6)
                .pattern("C C")
                .pattern("DDD")
                .pattern("DDD")
                .define('C', Items.CHAIN)
                .define('D', ModBlocks.STRIPPED_VOID_LOG.get())
                .unlockedBy(getHasName(Blocks.CHAIN),has(Blocks.CHAIN))
                .save(consumer);
//WALLS
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocks.BLOOD_WALL.get())
                .pattern("DDD")
                .pattern("DDD")
                .define('D',ModBlocks.BLOOD_WOOD.get())
                .unlockedBy(getHasName(ModBlocks.BLOOD_WALL.get()),has(ModBlocks.BLOOD_WALL.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocks.DARK_WALL.get())
                .pattern("DDD")
                .pattern("DDD")
                .define('D',ModBlocks.DARK_WOOD.get())
                .unlockedBy(getHasName(ModBlocks.DARK_WALL.get()),has(ModBlocks.DARK_WALL.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocks.VOID_WALL.get())
                .pattern("DDD")
                .pattern("DDD")
                .define('D',ModBlocks.VOID_WOOD.get())
                .unlockedBy(getHasName(ModBlocks.VOID_WALL.get()),has(ModBlocks.VOID_WALL.get()))
                .save(consumer);
//CHEST BOATS
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.DARK_CHEST_BOAT.get())
                .requires(ModItems.DARK_BOAT.get())
                .requires(Items.CHEST)
                .unlockedBy(getHasName(ModItems.DARK_BOAT.get()),has(ModItems.DARK_BOAT.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.BLOOD_CHEST_BOAT.get())
                .requires(ModItems.BLOOD_BOAT.get())
                .requires(Items.CHEST)
                .unlockedBy(getHasName(ModItems.BLOOD_BOAT.get()),has(ModItems.BLOOD_BOAT.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.VOID_CHEST_BOAT.get())
                .requires(ModItems.VOID_BOAT.get())
                .requires(Items.CHEST)
                .unlockedBy(getHasName(ModItems.VOID_BOAT.get()),has(ModItems.VOID_BOAT.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItems.DARK_BOAT.get())
                .pattern("D D")
                .pattern("DDD")
                .define('D', ModBlocks.DARK_PLANKS.get())
                .unlockedBy(getHasName(ModBlocks.DARK_PLANKS.get()), insideOf(Blocks.WATER))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItems.BLOOD_BOAT.get())
                .pattern("D D")
                .pattern("DDD")
                .define('D', ModBlocks.BLOOD_PLANKS.get())
                .unlockedBy(getHasName(ModBlocks.BLOOD_PLANKS.get()), insideOf(Blocks.WATER))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItems.VOID_BOAT.get())
                .pattern("D D")
                .pattern("DDD")
                .define('D', ModBlocks.VOID_PLANKS.get())
                .unlockedBy(getHasName(ModBlocks.VOID_PLANKS.get()), insideOf(Blocks.WATER))
                .save(consumer);*/
        /*AltarRecipeBuilder.altaring(3, ModItems.SCYTHE.get())
                .pattern("%$%")
                .pattern("%$%")
                .pattern("%$%")
                .define('%',ModItems.DARK_SHARD.get())
                .define('$',Items.STICK)
                //.save(consumer,"scythe");
        ;*/


/**
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.DARK_SHARD.get(),9)
                .requires(ModBlocks.DARKNESS_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.DARKNESS_BLOCK.get()), has(ModBlocks.DARKNESS_BLOCK.get()))
                .save(consumer);
**/

    }
    protected static void nineBlockStorageRecipes(RecipeOutput p_249580_, RecipeCategory p_251203_, ItemLike p_251689_, RecipeCategory p_251376_, ItemLike p_248771_) {
        nineBlockStorageRecipes(p_249580_, p_251203_, p_251689_, p_251376_, p_248771_, getSimpleRecipeName(p_248771_), (String)null, getSimpleRecipeName(p_251689_), (String)null);
    }
    protected static void nineBlockStorageRecipes(RecipeOutput p_250423_, RecipeCategory p_250083_, ItemLike p_250042_, RecipeCategory p_248977_, ItemLike p_251911_, String p_250475_, @Nullable String p_248641_, String p_252237_, @Nullable String p_250414_) {
        ShapelessRecipeBuilder.shapeless(p_250083_, p_250042_, 9).requires(p_251911_).group(p_250414_).unlockedBy(getHasName(p_251911_), has(p_251911_)).save(p_250423_, ResourceLocation.fromNamespaceAndPath(Voidkin.MODID,p_252237_));
        ShapedRecipeBuilder.shaped(p_248977_, p_251911_).define('#', p_250042_).pattern("###").pattern("###").pattern("###").group(p_248641_).unlockedBy(getHasName(p_250042_), has(p_250042_)).save(p_250423_, ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, p_250475_));
    }

}
