package net.voidkin.voidkin.chests.trapped.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.voidkin.voidkin.chests.*;

import java.util.function.Supplier;

public class TrappedIronChestBlockEntity extends AbstractTrappedModChestBlockEntity {

    public TrappedIronChestBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModChestsBlockEntityTypes.TRAPPED_IRON_CHEST.get(), blockPos, blockState, ModChestsTypes.IRON, ModChestsBlocks.TRAPPED_IRON_CHEST::get);
    }

    public TrappedIronChestBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState, ModChestsTypes chestTypeIn, Supplier<Block> blockToUseIn) {
        super(ModChestsBlockEntityTypes.TRAPPED_IRON_CHEST.get(), blockPos, blockState, ModChestsTypes.IRON, ModChestsBlocks.TRAPPED_IRON_CHEST::get);
    }


    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory playerInventory) {
        return ModChestMenu.createIronContainer(containerId, playerInventory, this);
    }
}