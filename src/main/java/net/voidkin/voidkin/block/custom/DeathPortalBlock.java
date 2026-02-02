package net.voidkin.voidkin.block.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.voidkin.voidkin.particles.ModParticles;
import net.voidkin.voidkin.worldgen.dimension.ModDimensions;
//import net.voidkin.voidkin.worldgen.portal.ModTeleporter;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class DeathPortalBlock extends Block {
    public DeathPortalBlock(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        //if (pEntity.canChangeDimensions(pLevel, new Level)) {
            //handleHerbMayfairPortal(pEntity,pPos);
        //}
    }

    //public InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHit) {
        /*if (pPlayer.canChangeDimensions(pLevel, )) {
            handleHerbMayfairPortal(pPlayer, pPos);
            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.CONSUME;
        }*/
    //}

    private void handleHerbMayfairPortal(Entity player, BlockPos pPos) {
        if (player.level() instanceof ServerLevel serverlevel) {

            MinecraftServer minecraftserver = serverlevel.getServer();
            ResourceKey<Level> resourcekey = player.level().dimension() == ModDimensions.DEATH_DIM_LEVEL_KEY ?
                    Level.OVERWORLD : ModDimensions.DEATH_DIM_LEVEL_KEY;

            ServerLevel portalDimension = minecraftserver.getLevel(resourcekey);
            if (portalDimension != null && !player.isPassenger()) {

                if(resourcekey == ModDimensions.DEATH_DIM_LEVEL_KEY) {

                    //player.changeDimension(portalDimension, new ModTeleporter(pPos, true));
                } else {
                    //player.changeDimension(portalDimension, new ModTeleporter(pPos, false));
                }
            }
        }
    }
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pRandom.nextInt(100) == 0) {
            pLevel.playLocalSound((double)pPos.getX() + 0.5D, (double)pPos.getY() + 0.5D, (double)pPos.getZ() + 0.5D, SoundEvents.PORTAL_AMBIENT, SoundSource.BLOCKS, 0.5F, pRandom.nextFloat() * 0.4F + 0.8F, false);
        }

        for(int i = 0; i < 4; ++i) {
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
