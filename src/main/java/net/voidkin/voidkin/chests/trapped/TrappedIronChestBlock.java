package net.voidkin.voidkin.chests.trapped;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.voidkin.voidkin.chests.AbstractTrappedModChestBlock;
import net.voidkin.voidkin.chests.ModChestsBlockEntityTypes;
import net.voidkin.voidkin.chests.ModChestsTypes;
import net.voidkin.voidkin.chests.trapped.entity.TrappedIronChestBlockEntity;

import javax.annotation.Nullable;

public class TrappedIronChestBlock extends AbstractTrappedModChestBlock {

    public static final MapCodec<TrappedIronChestBlock> CODEC = simpleCodec(TrappedIronChestBlock::new);

    public TrappedIronChestBlock(BlockBehaviour.Properties properties) {
        super(properties, ModChestsBlockEntityTypes.TRAPPED_IRON_CHEST::get, ModChestsTypes.IRON);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new TrappedIronChestBlockEntity(blockPos, blockState);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
