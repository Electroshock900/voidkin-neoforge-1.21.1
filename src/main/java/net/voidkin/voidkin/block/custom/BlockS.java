package net.voidkin.voidkin.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
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
    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        double centerX = pos.getX() + 0.5;
        double centerY = pos.getY() + 0.5;
        double centerZ = pos.getZ() + 0.5;

        // Random angle for swirl
        double angle = random.nextDouble() * 2 * Math.PI;

        // Radius of swirl around block
        double radius = 0.5;

        // Position around the block
        double x = centerX + Math.cos(angle) * radius;
        double y = centerY;
        double z = centerZ + Math.sin(angle) * radius;

        // Velocity: swirl + upward
        double vx = -Math.sin(angle) * 0.05; // tangent motion
        double vy = 0.05;                    // upward lift
        double vz = Math.cos(angle) * 0.05;  // tangent motion

        world.addParticle(ParticleTypes.HAPPY_VILLAGER, x, y, z, vx, vy, vz);
    }
}
