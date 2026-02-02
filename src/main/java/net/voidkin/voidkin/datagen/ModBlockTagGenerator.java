package net.voidkin.voidkin.datagen;

import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;

import net.voidkin.voidkin.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Voidkin.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(
                        ModBlocks.DARK_SHARD_ORE.get(),
                        ModBlocks.DEEPSLATE_DARK_SHARD_ORE.get(),
                        ModBlocks.NETHER_DARK_SHARD_ORE.get(),
                        ModBlocks.DARKNESS_BLOCK.get(),
                        ModBlocks.RAW_DARKNESS_BLOCK.get()
                        );
        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.DARK_SHARD_ORE.get())
                .add(ModBlocks.DEEPSLATE_DARK_SHARD_ORE.get());
        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.NETHER_DARK_SHARD_ORE.get());
        this.tag(ModTags.Blocks.VOIDKIN_BLOCKS)
                .add(ModBlocks.RAW_DARKNESS_BLOCK.get());
        this.tag(BlockTags.BUTTONS)
                .add(ModBlocks.BLOOD_BUTTON.get())
                .add(ModBlocks.DARK_BUTTON.get());
        this.tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.BLOOD_FENCE_GATE.get())
                .add(ModBlocks.DARK_FENCE_GATE.get());
        this.tag(BlockTags.FENCES)
                .add(ModBlocks.BLOOD_FENCE.get())
                .add(ModBlocks.DARK_FENCE.get());
        this.tag(BlockTags.WALLS)
                .add(ModBlocks.BLOOD_WALL.get())
                .add(ModBlocks.DARK_WALL.get());
        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.DARK_LOG.get())
                .add(ModBlocks.DARK_WOOD.get())
                .add(ModBlocks.STRIPPED_DARK_LOG.get())
                .add(ModBlocks.STRIPPED_DARK_WOOD.get())
                .add(ModBlocks.BLOOD_LOG.get())
                .add(ModBlocks.BLOOD_WOOD.get())
                .add(ModBlocks.STRIPPED_BLOOD_LOG.get())
                .add(ModBlocks.STRIPPED_BLOOD_WOOD.get())
                .add(ModBlocks.VOID_LOG.get())
                .add(ModBlocks.VOID_WOOD.get())
                .add(ModBlocks.STRIPPED_VOID_LOG.get())
                .add(ModBlocks.STRIPPED_VOID_WOOD.get())
                ;
        this.tag(BlockTags.LOGS)
                .add(ModBlocks.DARK_LOG.get())
                .add(ModBlocks.DARK_WOOD.get())
                .add(ModBlocks.STRIPPED_DARK_LOG.get())
                .add(ModBlocks.STRIPPED_DARK_WOOD.get())
                .add(ModBlocks.BLOOD_LOG.get())
                .add(ModBlocks.BLOOD_WOOD.get())
                .add(ModBlocks.STRIPPED_BLOOD_LOG.get())
                .add(ModBlocks.STRIPPED_BLOOD_WOOD.get())
                .add(ModBlocks.VOID_LOG.get())
                .add(ModBlocks.VOID_WOOD.get())
                .add(ModBlocks.STRIPPED_VOID_LOG.get())
                .add(ModBlocks.STRIPPED_VOID_WOOD.get())
        ;
        this.tag(BlockTags.PLANKS)
                .add(ModBlocks.BLOOD_PLANKS.get())
                .add(ModBlocks.DARK_PLANKS.get())
                .add(ModBlocks.VOID_PLANKS.get())
        ;
        this.tag(BlockTags.LEAVES)
                .add(ModBlocks.DARK_LEAVES.get(),ModBlocks.BLOOD_LEAVES.get(),ModBlocks.VOID_LEAVES.get());
        this.tag(BlockTags.FLOWERS)
                .add(ModBlocks.CATMINT.get(),ModBlocks.LOTUS.get());

    }
}
