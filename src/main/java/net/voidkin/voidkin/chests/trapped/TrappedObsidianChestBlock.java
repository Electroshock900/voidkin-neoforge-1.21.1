package net.voidkin.voidkin.chests.trapped;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.voidkin.voidkin.chests.AbstractTrappedModChestBlock;
import net.voidkin.voidkin.chests.ModChestsBlockEntityTypes;
import net.voidkin.voidkin.chests.ModChestsTypes;
import net.voidkin.voidkin.chests.trapped.entity.TrappedObsidianChestBlockEntity;

import javax.annotation.Nullable;

public class TrappedObsidianChestBlock extends AbstractTrappedModChestBlock {

    public static final MapCodec<TrappedObsidianChestBlock> CODEC = simpleCodec(TrappedObsidianChestBlock::new);

    public TrappedObsidianChestBlock(Properties properties) {
        super(properties, ModChestsBlockEntityTypes.TRAPPED_OBSIDIAN_CHEST::get, ModChestsTypes.OBSIDIAN);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new TrappedObsidianChestBlockEntity(blockPos, blockState);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
