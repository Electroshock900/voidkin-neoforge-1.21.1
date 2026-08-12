package net.voidkin.voidkin.chests;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public abstract class AbstractTrappedModChestBlockEntity extends AbstractModChestBlockEntity {

    protected AbstractTrappedModChestBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState, ModChestsTypes chestTypeIn, Supplier<Block> blockToUseIn) {
        super(blockEntityType, blockPos, blockState, chestTypeIn, blockToUseIn);
    }

    @Override
    protected void signalOpenCount(Level level, BlockPos blockPos, BlockState blockState, int previousCount, int newCount) {
        super.signalOpenCount(level, blockPos, blockState, previousCount, newCount);

        if (previousCount != newCount) {
            Block block = blockState.getBlock();

            level.updateNeighborsAt(blockPos, block);
            level.updateNeighborsAt(blockPos.below(), block);
        }
    }
}
