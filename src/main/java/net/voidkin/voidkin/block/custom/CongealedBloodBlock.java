package net.voidkin.voidkin.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.voidkin.voidkin.particles.ModParticles;


public class CongealedBloodBlock extends CoagulatedBloodBlock{
        //public static final MapCodec<CongealedBloodBlock> CODEC = simpleCodec(CongealedBloodBlock::new);
        public static final int MAX_AGE = 3;
        public static final IntegerProperty AGE;
        private static final int NEIGHBORS_TO_AGE = 4;
        private static final int NEIGHBORS_TO_MELT = 2;

        /*public MapCodec<CongealedBloodBlock> codec() {
            return CODEC;
        }*/

        public CongealedBloodBlock(Properties p_53564_) {
            super(p_53564_);
            this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(AGE, 0));
        }

        public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pMovedByPiston) {
            pLevel.scheduleTick(pPos, this, Mth.nextInt(pLevel.getRandom(), 60, 120));
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
                    level.addParticle(
                            ModParticles.DRIPPING_BLOOD.get(),
                            (double) pos.getX() + d0,
                            (double) pos.getY() + d1,
                            (double) pos.getZ() + d2,
                            0.0,
                            0.0,
                            0.0
                    );
                }
            }
        }
    }


        @Override
        public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
            if ((pRandom.nextInt(3) == 0 || this.fewerNeigboursThan(pLevel, pPos, 4)) && pLevel.getMaxLocalRawBrightness(pPos) > 11 - (Integer)pState.getValue(AGE) - pState.getLightBlock(pLevel, pPos) && this.slightlyMelt(pState, pLevel, pPos)) {
                BlockPos.MutableBlockPos $$4 = new BlockPos.MutableBlockPos();
                Direction[] var6 = Direction.values();
                int var7 = var6.length;

                for(int var8 = 0; var8 < var7; ++var8) {
                    Direction $$5 = var6[var8];
                    $$4.setWithOffset(pPos, $$5);
                    BlockState $$6 = pLevel.getBlockState($$4);
                    if ($$6.is(this) && !this.slightlyMelt($$6, pLevel, $$4)) {
                        pLevel.scheduleTick($$4, this, Mth.nextInt(pRandom, 20, 40));
                    }
                }

            } else {
                pLevel.scheduleTick(pPos, this, Mth.nextInt(pRandom, 20, 40));
            }
        }

        private boolean slightlyMelt(BlockState pState, Level pLevel, BlockPos pPos) {
            int $$3 = (Integer)pState.getValue(AGE);
            if ($$3 < 3) {
                pLevel.setBlock(pPos, (BlockState)pState.setValue(AGE, $$3 + 1), 2);
                return false;
            } else {
                this.melt(pState, pLevel, pPos);
                return true;
            }
        }
        @Override
        public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
            if (pBlock.defaultBlockState().is(this) && this.fewerNeigboursThan(pLevel, pPos, 2)) {
                this.melt(pState, pLevel, pPos);
            }

            super.neighborChanged(pState, pLevel, pPos, pBlock, pFromPos, pIsMoving);
        }

        private boolean fewerNeigboursThan(BlockGetter pLevel, BlockPos pPos, int pNeighborsRequired) {
            int $$3 = 0;
            BlockPos.MutableBlockPos $$4 = new BlockPos.MutableBlockPos();
            Direction[] var6 = Direction.values();
            int var7 = var6.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                Direction $$5 = var6[var8];
                $$4.setWithOffset(pPos, $$5);
                if (pLevel.getBlockState($$4).is(this)) {
                    ++$$3;
                    if ($$3 >= pNeighborsRequired) {
                        return false;
                    }
                }
            }

            return true;
        }

        protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
            pBuilder.add(new Property[]{AGE});
        }

        public ItemStack getCloneItemStack(LevelReader pLevel, BlockPos pPos, BlockState pState) {
            return ItemStack.EMPTY;
        }

        static {
            AGE = BlockStateProperties.AGE_3;
        }

}
