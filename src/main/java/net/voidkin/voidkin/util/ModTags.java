package net.voidkin.voidkin.util;

import net.minecraft.core.registries.Registries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.material.Fluid;
import net.voidkin.voidkin.Voidkin;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> VOIDKIN_BLOCKS = tag("voidkin_blocks");
        public static final TagKey<Block> ANTI_VOID_BLOCKS = tag("anti_void_blocks");
        public static final TagKey<Block> VOID_ELEVATIONS = tag("void_elevations");
        public static final TagKey<Block> PORTAL_POOL = tag("portal_pool");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, name));
        }
    }
    public static class Items {
        public static final TagKey<Item> CHAKRAM_ENCHANTABLE = tag("chakram_enchantables");
        public static final TagKey<Item> S = tag("s");
        public static final TagKey<Item> VOIDKIN_CHANGEABLE = tag("voidkin_changeable");
        public static final TagKey<Item> PORTAL_ACTIVATOR = tag("portal_activator");


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
    public static class Enchantments {
        public static final TagKey<Enchantment> VOID_ENCHANTS = tag("void_enchants");

        private static TagKey<Enchantment> tag(String name) {
            return TagKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, name));

        }
    }
    public static class Fluids {
        public static final TagKey<Fluid>BLOOD_FLUIDS = tag("blood_fluids");
        public static final TagKey<Fluid>VOID_FLUIDS = tag("void_fluids");
        public static final TagKey<Fluid>DEITY_FLUIDS = tag("deity_fluids");
        private static TagKey<Fluid> tag(String name) {
            return FluidTags.create(ResourceLocation.fromNamespaceAndPath(Voidkin.MODID,name));
        }
    }

    public static class Structures{
        public static final TagKey<Structure>VOID_STRUCTURES = tag("void_structures");
        private static TagKey<Structure> tag(String name) {
            return TagKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, name));

        }
    }




}
