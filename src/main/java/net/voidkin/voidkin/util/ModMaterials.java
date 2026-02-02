package net.voidkin.voidkin.util;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

import net.neoforged.neoforge.common.SimpleTier;
import net.voidkin.voidkin.item.ModItems;

public class ModMaterials {
    public static final Tier DARKNESS = new SimpleTier(ModTags.Blocks.ANTI_VOID_BLOCKS,1300,13.0F,6.5F,13,()->Ingredient.of(ModItems.DARK_SHARD.get()));
    public static final Tier CACTUS = new SimpleTier(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 13,1300,3.0F,7, ()->Ingredient.of(Blocks.CACTUS));
}
