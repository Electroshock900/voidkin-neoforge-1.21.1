package net.voidkin.voidkin.chests.regular;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.voidkin.voidkin.chests.AbstractModChestBlock;
import net.voidkin.voidkin.chests.ModChestsBlockEntityTypes;
import net.voidkin.voidkin.chests.ModChestsTypes;
import net.voidkin.voidkin.chests.regular.entity.ObsidianChestBlockEntity;
import org.jetbrains.annotations.Nullable;

public class ObsidianChestBlock extends AbstractModChestBlock {

    public static final MapCodec<ObsidianChestBlock> CODEC = simpleCodec(ObsidianChestBlock::new);

    public ObsidianChestBlock(Properties properties) {
        super(properties, ModChestsBlockEntityTypes.OBSIDIAN_CHEST::get, ModChestsTypes.OBSIDIAN);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ObsidianChestBlockEntity(blockPos, blockState);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
