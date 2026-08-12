package net.voidkin.voidkin.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.voidkin.voidkin.block.blockentity.Pedestal2BlockEntity;
import net.voidkin.voidkin.block.blockentity.PedestalBlockEntity;
import org.jetbrains.annotations.Nullable;

public class Pedestal2Block extends BaseEntityBlock {
    public static final VoxelShape SHAPE = Block.box(2, 0, 2, 14, 13, 14);
    public static final MapCodec<Pedestal2Block> CODEC = simpleCodec(Pedestal2Block::new);

    private double angleOffset = 0;

    public Pedestal2Block(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new Pedestal2BlockEntity(blockPos, blockState);
    }

    @Override
    protected RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    protected void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if(pState.getBlock() != pNewState.getBlock()) {
            if(pLevel.getBlockEntity(pPos) instanceof Pedestal2BlockEntity pedestalBlockEntity) {
                pedestalBlockEntity.drops();//Containers.dropContents(pLevel, pPos, pedestalBlockEntity);
                pLevel.updateNeighbourForOutputSignal(pPos, this);
            }
            super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
        }
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos,
                                              Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult) {
        if(pLevel.getBlockEntity(pPos) instanceof Pedestal2BlockEntity pedestalBlockEntity) {
            if(pPlayer.isCrouching() && !pLevel.isClientSide()) {
                ((ServerPlayer) pPlayer).openMenu(new SimpleMenuProvider(pedestalBlockEntity, Component.literal("Pedestal")), pPos);
                return ItemInteractionResult.SUCCESS;
            }

            if(pedestalBlockEntity.inventory.getStackInSlot(0).isEmpty() && !pStack.isEmpty()) {
                pedestalBlockEntity.inventory.insertItem(0, pStack.copy(),false);
                spawnRotatingParticleRing(pLevel, pPos, ParticleTypes.REVERSE_PORTAL, 1D, 13);
                pStack.shrink(1);
                pLevel.playSound(pPlayer, pPos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 2f);
            } else if(pStack.isEmpty()) {
                ItemStack stackOnPedestal = pedestalBlockEntity.inventory.extractItem(0,1,false);
                pPlayer.getInventory().add(stackOnPedestal);
                spawnParticleRing(pLevel, pPos, ParticleTypes.SOUL, 1D, 13);
                //pLevel.addParticle(ParticleTypes.SMOKE, pPos.getX() + 0.5D,pPos.getY(),pPos.getZ() + 0.5D,0,0.2,0);
                //pPlayer.setItemInHand(InteractionHand.MAIN_HAND, stackOnPedestal);
                pedestalBlockEntity.clearContents();
                pLevel.playSound(pPlayer, pPos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 1f);
            }
        }

        return ItemInteractionResult.SUCCESS;
    }

    public static void spawnParticleRing(Level level, BlockPos pos, ParticleOptions particle,
                                         double radius, int count) {

        if (level.isClientSide) {
            double centerX = pos.getX() + 0.5;
            double centerY = pos.getY() + 1.0;   // slightly above block
            double centerZ = pos.getZ() + 0.5;

            for (int i = 0; i < count; i++) {
                double angle = (2 * Math.PI * i) / count;

                double x = centerX + radius * Math.cos(angle);
                double z = centerZ + radius * Math.sin(angle);

                level.addParticle(
                        particle,
                        x, centerY, z,
                        0, 0, 0
                );
            }
        }
    }
    public static void spawnRotatingParticleRing(Level level, BlockPos pos, ParticleOptions particle,
                                         double radius, int count) {

        if (level.isClientSide) {
            double cx = pos.getX() + 0.5;
            double cy = pos.getY() + 1.0;
            double cz = pos.getZ() + 0.5;

            for (int i = 0; i < count; i++) {
                double angle = (2 * Math.PI * i) / count + 3;

                double x = cx + radius * Math.cos(angle);
                double z = cz + radius * Math.sin(angle);

                level.addParticle(particle, x, cy, z, 0, 0, 0);
            }
        }
    }




}
