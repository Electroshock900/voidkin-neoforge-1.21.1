package net.voidkin.voidkin.chests;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.chests.regular.entity.*;
import net.voidkin.voidkin.chests.trapped.entity.*;

import java.util.function.Supplier;

public class ModChestsBlockEntityTypes {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Voidkin.MODID);
    
    //public static final Supplier<BlockEntityType<?>, BlockEntityType<IronChestBlockEntity>> IRON_CHEST2 = BLOCK_ENTITIES.register("iron_chest", () -> new BlockEntityType<>(IronChestBlockEntity::new, ModChestsBlocks.IRON_CHEST.get()));

    public static final Supplier<BlockEntityType<IronChestBlockEntity>> IRON_CHEST = BLOCK_ENTITIES.register("iron_chest", ()-> BlockEntityType.Builder.of(IronChestBlockEntity::new, ModChestsBlocks.IRON_CHEST.get()).build(null));
    public static final Supplier<BlockEntityType<ObsidianChestBlockEntity>> OBSIDIAN_CHEST = BLOCK_ENTITIES.register("obsidian_chest", ()-> BlockEntityType.Builder.of(ObsidianChestBlockEntity::new, ModChestsBlocks.OBSIDIAN_CHEST.get()).build(null));

    public static final Supplier<BlockEntityType<TrappedIronChestBlockEntity>> TRAPPED_IRON_CHEST = BLOCK_ENTITIES.register("trapped_iron_chest", ()-> BlockEntityType.Builder.of(TrappedIronChestBlockEntity::new, ModChestsBlocks.TRAPPED_IRON_CHEST.get()).build(null));
    public static final Supplier<BlockEntityType<TrappedObsidianChestBlockEntity>> TRAPPED_OBSIDIAN_CHEST = BLOCK_ENTITIES.register("trapped_obsidian_chest", ()-> BlockEntityType.Builder.of(TrappedObsidianChestBlockEntity::new, ModChestsBlocks.TRAPPED_OBSIDIAN_CHEST.get()).build(null));

    //public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TrappedIronChestBlockEntity>> TRAPPED_IRON_CHEST = BLOCK_ENTITIES.register("trapped_iron_chest", () -> new BlockEntityType<>(TrappedIronChestBlockEntity::new, ModChestsBlocks.TRAPPED_IRON_CHEST.get()));

    //public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TrappedCopperChestBlockEntity>> TRAPPED_COPPER_CHEST = BLOCK_ENTITIES.register("trapped_copper_chest", () -> new BlockEntityType<>(TrappedCopperChestBlockEntity::new, ModChestsBlocks.TRAPPED_COPPER_CHEST.get()));

}
