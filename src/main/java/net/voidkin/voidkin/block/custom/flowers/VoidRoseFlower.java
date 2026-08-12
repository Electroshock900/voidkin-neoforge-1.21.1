package net.voidkin.voidkin.block.custom.flowers;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidType;
import net.voidkin.voidkin.fluid.ModFluidTypes;
import net.voidkin.voidkin.particles.ModParticles;
import net.voidkin.voidkin.util.ModTags;
import org.jetbrains.annotations.Nullable;

public class VoidRoseFlower extends FlowerBlock implements SimpleWaterloggedBlock  {
    public VoidRoseFlower(Holder<MobEffect> effect, float seconds, Properties properties) {
        super(effect, seconds, properties);
    }

    @Override
    public boolean canPlaceLiquid(@Nullable Player player, BlockGetter level, BlockPos pos, BlockState state, Fluid fluid) {
        //if (fluid.getFluidType() == ModFluidTypes.SHIMMER_FLUID_TYPE){
        if (fluid.getFluidType() == Fluids.LAVA.getFluidType()){
            return true;
        }
        if (fluid.getFluidType() == ModFluidTypes.SHIMMER_FLUID_TYPE){
            return true;
        }
        return SimpleWaterloggedBlock.super.canPlaceLiquid(player, level, pos, state, fluid);
    }

    @Override
    public void animateTick(BlockState state, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        super.animateTick(state, pLevel, pPos, pRandom);
        boolean submerged = false;

        for(int i = 0; i < 7; ++i) {
            double d0 = (double)pPos.getX() + pRandom.nextDouble();
            double d1 = (double)pPos.getY() + pRandom.nextDouble();
            double d2 = (double)pPos.getZ() + pRandom.nextDouble();
            double d3 = ((double)pRandom.nextFloat() - 0.5D) * 0.5D;
            double d4 = ((double)pRandom.nextFloat() - 0.5D) * 0.5D;
            double d5 = ((double)pRandom.nextFloat() - 0.5D) * 0.5D;
            int j = pRandom.nextInt(2) * 2 - 1;
            if (!pLevel.getBlockState(pPos.west()).is(this) && !pLevel.getBlockState(pPos.east()).is(this)) {
                d0 = (double)pPos.getX() + 0.5D + 0.25D * (double)j;
                d3 = (double)(pRandom.nextFloat() * 2.0F * (float)j);
            } else {
                d2 = (double)pPos.getZ() + 0.5D + 0.25D * (double)j;
                d5 = (double)(pRandom.nextFloat() * 2.0F * (float)j);
            }

            pLevel.addParticle(ModParticles.VOID_FLAME.get(), d0, d1, d2, d3, d4, d5);

        }


    }

}
