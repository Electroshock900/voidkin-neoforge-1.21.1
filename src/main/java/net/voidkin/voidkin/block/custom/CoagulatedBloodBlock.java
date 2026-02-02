package net.voidkin.voidkin.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
//import net.minecraft.tags.EnchantmentTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.voidkin.voidkin.block.types.BloodDripBlock;
import net.voidkin.voidkin.block.ModBlocks;
import net.voidkin.voidkin.particles.ModParticles;

import javax.annotation.Nullable;

public class CoagulatedBloodBlock extends BloodDripBlock {
    //public static final MapCodec<CoagulatedBloodBlock> CODEC = simpleCodec(CoagulatedBloodBlock::new);

    /*public MapCodec<? extends CoagulatedBloodBlock> codec() {
        return CODEC;
    }*/

    public CoagulatedBloodBlock(Properties p_54155_) {
        super(p_54155_);
    }

    public static BlockState meltsInto() {
        return ModBlocks.BLOOD_BLOCK.get().defaultBlockState();
    }

    public void playerDestroy(Level pLevel, Player pPlayer, BlockPos pPos, BlockState pState, @Nullable BlockEntity pTe, ItemStack pStack) {
        super.playerDestroy(pLevel, pPlayer, pPos, pState, pTe, pStack);
         BlockState $$6 = pLevel.getBlockState(pPos.below());
            if ($$6.blocksMotion() || $$6.liquid()) {
                pLevel.setBlockAndUpdate(pPos, meltsInto());
            }

}
    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pLevel.rainLevel > 1F) {
            if (pLevel.getBrightness(LightLayer.BLOCK, pPos) > 11 - pState.getLightBlock(pLevel, pPos)) {
                this.melt(pState, pLevel, pPos);


            }
        }
        //pLevel.addParticle(ModParticles.DRIPPING_BLOOD.get(), pPos.getX() + 0.5f,pPos.getY() + 0.5f,pPos.getZ() + 0.5f,0, 0, 0);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource randomSource) {
        if (randomSource.nextInt(5) == 0) {
            Direction direction = Direction.getRandom(randomSource);
            if (direction != Direction.UP) {
                BlockPos blockpos = pos.relative(direction);
                BlockState blockstate = level.getBlockState(blockpos);
                if (!state.canOcclude() || !blockstate.isFaceSturdy(level, blockpos, direction.getOpposite())) {
                    double d0 = direction.getStepX() == 0 ? randomSource.nextDouble() : 0.5 + (double)direction.getStepX() * 0.6;
                    double d1 = direction.getStepY() == 0 ? randomSource.nextDouble() : 0.5 + (double)direction.getStepY() * 0.6;
                    double d2 = direction.getStepZ() == 0 ? randomSource.nextDouble() : 0.5 + (double)direction.getStepZ() * 0.6;
                    double d0_2 = direction.getStepX() == 0 ? randomSource.nextDouble() : 0.5 + (double)direction.getStepX() * 0.3;
                    double d1_2 = direction.getStepY() == 0 ? randomSource.nextDouble() : 0.5 + (double)direction.getStepY() * 0.7;
                    double d2_2 = direction.getStepZ() == 0 ? randomSource.nextDouble() : 0.5 + (double)direction.getStepZ() * 0.8;
                    level.addParticle(ModParticles.DRIPPING_BLOOD.get(), (double)pos.getX() + d0, (double)pos.getY() + d1, (double)pos.getZ() + d2, 0.0, 0.0, 0.0);
                    level.addParticle(ModParticles.FALLING_BLOOD.get(), (double)pos.getX() + d0, (double)pos.getY() + d1, (double)pos.getZ() + d2, 0.0, -0.3, 0.0);
                    level.addParticle(ModParticles.DRIPPING_BLOOD.get(), (double)pos.getX() + d0_2, (double)pos.getY() + d1_2, (double)pos.getZ() + d2_2, 0.0, 0.0, 0.0);
                }
            }
        }
    }

    protected void melt(BlockState pState, Level pLevel, BlockPos pPos) {
        if (pLevel.dimensionType().ultraWarm()) {
            pLevel.removeBlock(pPos, false);
        } else {
            pLevel.setBlockAndUpdate(pPos, meltsInto());
            pLevel.neighborChanged(pPos, meltsInto().getBlock(), pPos);
        }
    }
}
