package net.voidkin.voidkin.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.voidkin.voidkin.block.blockentity.SBlockEntity;
import org.jetbrains.annotations.Nullable;

public class BlockS extends BaseEntityBlock{
    public static final MapCodec<BlockS> CODEC = simpleCodec(BlockS::new);
    public BlockS(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new SBlockEntity(pPos,pState);
    }
}
