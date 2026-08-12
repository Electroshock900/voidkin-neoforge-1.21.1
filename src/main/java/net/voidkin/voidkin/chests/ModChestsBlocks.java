package net.voidkin.voidkin.chests;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.chests.regular.*;
import net.voidkin.voidkin.chests.trapped.*;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModChestsBlocks {
    public static final DeferredRegister.Blocks CHEST_BLOCKS = DeferredRegister.createBlocks(Voidkin.MODID);
    static final BlockBehaviour.Properties properties = BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(3.0F).sound(SoundType.METAL);


    //public static final DeferredBlock<ChestBlock> DARK_CHEST = CHEST_BLOCKS.register("dark_chest", new ChestBlock(BlockBehaviour.Properties.of().ignitedByLava()));
    public static final DeferredBlock<IronChestBlock> IRON_CHEST = registerWithItem("iron_chest", IronChestBlock::new, () -> properties, ModChestsTypes.IRON, false);
    public static final DeferredBlock<ObsidianChestBlock> OBSIDIAN_CHEST = registerWithItem("obsidian_chest", ObsidianChestBlock::new, () -> properties, ModChestsTypes.OBSIDIAN, false);

    public static final DeferredBlock<TrappedIronChestBlock> TRAPPED_IRON_CHEST = registerWithItem("trapped_iron_chest", TrappedIronChestBlock::new, () -> properties, ModChestsTypes.IRON, true);
    public static final DeferredBlock<TrappedObsidianChestBlock> TRAPPED_OBSIDIAN_CHEST = registerWithItem("trapped_obsidian_chest", TrappedObsidianChestBlock::new, () -> properties, ModChestsTypes.OBSIDIAN, true);


    public static <T extends Block> DeferredBlock<T> registerWithItem(String name, Function<BlockBehaviour.Properties, T> block, Supplier<BlockBehaviour.Properties> properties, ModChestsTypes chestType, Boolean trapped) {
        ResourceKey<Block> blockResourceKey = ResourceKey.create(Registries.BLOCK, Voidkin.prefix(name));
        DeferredBlock<T> ret = CHEST_BLOCKS.register(name, () -> block.apply(properties.get()));//.setId(blockResourceKey)));
        //ModChestsItems.ITEMS.register(name,()-> new ModChestBlockItem(ret.get(), new Item.Properties(), chestType, trapped));

        ModChestsItems.register(name, itemProps -> new ModChestBlockItem(ret.get(), itemProps, chestType, trapped), Item.Properties::new, blockResourceKey);
        return ret;
    }
}
