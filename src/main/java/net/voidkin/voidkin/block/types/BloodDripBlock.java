package net.voidkin.voidkin.block.types;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.voidkin.voidkin.particles.ModParticles;

public class BloodDripBlock extends Block {


    public BloodDripBlock(Properties p_49795_) {
        super(p_49795_);
    }

    /*@Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        super.animateTick(pState, pLevel, pPos, pRandom);
        pLevel.addParticle(ModParticles.DRIPPING_BLOOD.get()
                ,pPos.getX(),pPos.getY()-1,pPos.getZ()
                , (float) Math.random(),-0.3F,((float) Math.random()));
    }*/
    @Override
    public void animateTick(BlockState p_221055_, Level p_221056_, BlockPos p_221057_, RandomSource p_221058_) {
        if (p_221058_.nextInt(5) == 0) {
            Direction direction = Direction.getRandom(p_221058_);
            if (direction != Direction.UP) {
                BlockPos blockpos = p_221057_.relative(direction);
                BlockState blockstate = p_221056_.getBlockState(blockpos);
                if (!p_221055_.canOcclude() || !blockstate.isFaceSturdy(p_221056_, blockpos, direction.getOpposite())) {
                    double d0 = direction.getStepX() == 0 ? p_221058_.nextDouble() : 0.5 + (double) direction.getStepX() * 0.6;
                    double d1 = direction.getStepY() == 0 ? p_221058_.nextDouble() : 0.5 + (double) direction.getStepY() * 0.6;
                    double d2 = direction.getStepZ() == 0 ? p_221058_.nextDouble() : 0.5 + (double) direction.getStepZ() * 0.6;
                    p_221056_.addParticle(
                            ModParticles.DRIPPING_BLOOD.get(),
                            (double) p_221057_.getX() + d0,
                            (double) p_221057_.getY() + d1,
                            (double) p_221057_.getZ() + d2,
                            0.0,
                            0.0,
                            0.0
                    );
                }
            }
        }
    }
}
