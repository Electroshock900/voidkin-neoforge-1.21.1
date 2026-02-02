package net.voidkin.voidkin.util;

import net.minecraft.core.registries.Registries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.voidkin.voidkin.Voidkin;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> VOIDKIN_BLOCKS = tag("voidkin_blocks");
        public static final TagKey<Block> ANTI_VOID_BLOCKS = tag("anti_void_blocks");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, name));
        }
    }
    public static class Items {
        public static final TagKey<Item> CHAKRAM_ENCHANTABLE = tag("chakram_enchantables");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, name));
        }
    }
    public static class Biomes {
        public static final TagKey<Biome> VOID_BIOMES = tag("void_biomes");
        public static final TagKey<Biome> GORE_OCEANS = tag("gore_oceans");
        private static TagKey<Biome> tag(String name) {
            return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, name));
        }
    }

}
