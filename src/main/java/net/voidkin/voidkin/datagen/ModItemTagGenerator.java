package net.voidkin.voidkin.datagen;

import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.block.ModBlocks;
import net.voidkin.voidkin.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.voidkin.voidkin.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, Voidkin.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ItemTags.ARROWS)
                .add(ModItems.ARESARROW.get());
        this.tag(ModTags.Items.CHAKRAM_ENCHANTABLE)
                .add(ModItems.CHAKRAM.get());
        this.tag(ItemTags.TRIDENT_ENCHANTABLE)
                .add(ModItems.CHAKRAM.get());

        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(
                        ModItems.AMETHYST_HELMET.get(),
                        ModItems.AMETHYST_CHESTPLATE.get(),
                        ModItems.AMETHYST_LEGGINGS.get(),
                        ModItems.AMETHYST_BOOTS.get()
                );
        this.tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.DARK_LOG.get().asItem())
                .add(ModBlocks.DARK_WOOD.get().asItem())
                .add(ModBlocks.BLOOD_LOG.get().asItem())
                .add(ModBlocks.BLOOD_WOOD.get().asItem())
                .add(ModBlocks.VOID_LOG.get().asItem())
                .add(ModBlocks.VOID_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_DARK_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_DARK_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_BLOOD_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_BLOOD_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_VOID_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_VOID_WOOD.get().asItem())
        ;
        this.tag(ItemTags.PLANKS)
                .add(ModBlocks.DARK_PLANKS.get().asItem())
                .add(ModBlocks.BLOOD_PLANKS.get().asItem())
                .add(ModBlocks.VOID_PLANKS.get().asItem())
        ;

    }

}
