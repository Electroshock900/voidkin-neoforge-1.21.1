package net.voidkin.voidkin.chests.regular;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.voidkin.voidkin.chests.AbstractModChestBlock;
import net.voidkin.voidkin.chests.ModChestsBlockEntityTypes;
import net.voidkin.voidkin.chests.ModChestsTypes;
import net.voidkin.voidkin.chests.regular.entity.IronChestBlockEntity;
import org.jetbrains.annotations.Nullable;

public class IronChestBlock extends AbstractModChestBlock {

    public static final MapCodec<IronChestBlock> CODEC = simpleCodec(IronChestBlock::new);

    public IronChestBlock(Properties properties) {
        super(properties, ModChestsBlockEntityTypes.IRON_CHEST::get, ModChestsTypes.IRON);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new IronChestBlockEntity(blockPos, blockState);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
