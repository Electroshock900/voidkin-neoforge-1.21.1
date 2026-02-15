package net.voidkin.voidkin.block.custom;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.voidkin.voidkin.block.blockentity.VoidAltarBlockEntity;
import net.voidkin.voidkin.block.ModBlockEntities;
import net.voidkin.voidkin.block.ModBlocks;
import net.voidkin.voidkin.menu.ModMenuTypes;
import net.voidkin.voidkin.particles.ModParticles;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector2i;

import java.util.List;

public class VoidAltarBlock extends BaseEntityBlock {
    public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 14, 16);


    public static final MapCodec<VoidAltarBlock> CODEC = simpleCodec(VoidAltarBlock::new);
    public VoidAltarBlock(Properties pProperties) {
        super(pProperties);

    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        VoidAltarBlockEntity altar = (VoidAltarBlockEntity) newBlockEntity(pPos,pState);

        if (altar.hasPedestals()) {
            pLevel.addParticle(ModParticles.ANTI_VOID.get(), pPos.getX() + 0.5f, pPos.getY() + 0.5f, pPos.getX() + 0.5f, 0, 0, 0);
        }
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }
    protected MapCodec<? extends BaseEntityBlock> codec(){return CODEC;};

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if(pState.getBlock() != pNewState.getBlock()){
            if(pLevel.getBlockEntity(pPos) instanceof VoidAltarBlockEntity altarPedestalBlockEntity) {
                //altarPedestalBlockEntity.inventory.getStackInSlot(0);
                BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
                if (blockEntity instanceof VoidAltarBlockEntity) {
                    ((VoidAltarBlockEntity) blockEntity).drops();
                    pLevel.updateNeighbourForOutputSignal(pPos, this);
                }
            }
            super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
                }



    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult) {
        //ItemStack pStack = pPlayer.getItemInHand(pHand);
        if (pLevel.getBlockEntity(pPos) instanceof VoidAltarBlockEntity altar) {
            if (pPlayer.isCrouching()){
                pLevel.playSound(pPlayer, pPos, SoundEvents.ENDERMAN_AMBIENT, SoundSource.BLOCKS, 1f, -3f);
                //pLevel.playSound(pPlayer, pPos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, -1f);
            }
            if (!pLevel.isClientSide() && pPlayer.isCrouching()){
                //NetworkHooks.openScreen(((ServerPlayer)pPlayer), altar, pPos);
                ((ServerPlayer) pPlayer).openMenu(new SimpleMenuProvider(altar, Component.literal("Void Altar")), pPos);
                return ItemInteractionResult.SUCCESS;
                //LogUtils.getLogger().debug("PINGING");
                //return ItemInteractionResult.SUCCESS;
            }
            if(pStack.is(ModBlocks.VOID_PEDESTAL.get().asItem())){
                for(Vector2i offset : VoidAltarBlockEntity.offsets) {
                    //altar.offsets.forEach(offset2 ->{
                    if (isPositionEmpty(pLevel, pPos.offset(offset.x, 0, offset.y))) {
                        placePedestal(pLevel, pPos.offset(offset.x, 0, offset.y));
                        pStack.shrink(1);
                        pLevel.playSound(pPlayer, pPos.offset(offset.x, 0, offset.y), SoundEvents.ENDER_CHEST_CLOSE, SoundSource.BLOCKS, 1f, -1f);
                    } else if (hasSidePedestals(pLevel, pPos.offset(offset.x, 0, offset.y))) {
                        continue;
                    } else {
                        //Any other block
                        continue;
                        //pPlayer.displayClientMessage(Component.literal("PEDESTAL ALREADY THERE!!!"), true);
                    }
                    pPlayer.displayClientMessage(Component.literal("Pedestals Placed"), true);
                    break;


                }
                return ItemInteractionResult.SUCCESS;

            }

            if (!pLevel.isClientSide()) {
                BlockEntity entity = pLevel.getBlockEntity(pPos);
                if(entity instanceof VoidAltarBlockEntity altarBlockEntity) {
                    ((ServerPlayer)pPlayer).openMenu(new SimpleMenuProvider(altarBlockEntity,Component.literal("Void Altar")));//).openScreen(((ServerPlayer)pPlayer), (AltarPedestalBlockEntity)entity, pPos);
                    pLevel.playSound(pPlayer, pPos, SoundEvents.ENDER_CHEST_OPEN, SoundSource.BLOCKS, 2f, -1f);
                } else {
                    throw new IllegalStateException("Our Container provider is missing!");
                }
            }
            int freeSlot = pPlayer.getInventory().getFreeSlot();

            int matchingSlot = pPlayer.getInventory().findSlotMatchingItem(pStack);

            if (altar.itemHandler.getStackInSlot(0).isEmpty() && !pStack.is(Items.AIR)){
                altar.itemHandler.insertItem(0, pStack.copy(), false);
                pLevel.sendBlockUpdated(altar.getBlockPos(),altar.getBlockState(),altar.getBlockState(),3);
                LogUtils.getLogger().debug("String");
                pStack.shrink(1);
                //pStack.shrink(pStack.getCount());

                pLevel.playSound(pPlayer, pPos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 3f);
            }else if(!altar.itemHandler.getStackInSlot(0).isEmpty()){
                int fff = altar.itemHandler.getStackInSlot(0).getCount();
                ItemStack stackOnPedestal = altar.itemHandler.getStackInSlot(0);
                        altar.itemHandler.extractItem(0, fff, false);
                pLevel.sendBlockUpdated(altar.getBlockPos(),altar.getBlockState(),altar.getBlockState(),3);
                //pPlayer.setItemInHand(InteractionHand.MAIN_HAND,stackOnPedestal);


                        pPlayer.getInventory().add(matchingSlot,stackOnPedestal);
                pLevel.playSound(pPlayer, pPos, SoundEvents.ENDERMAN_AMBIENT, SoundSource.BLOCKS, 1f, 1.2f);

                LogUtils.getLogger().debug("EMPTY STRING");
                //altar.itemHandler.extractItem(0,1,false);
                //pLevel.playSound(pPlayer, pPos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 2f);
            }

        }
        return ItemInteractionResult.SUCCESS;
        //super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }


    private boolean isPositionEmpty(Level level, BlockPos pos){
        return level.getBlockState(pos).isAir();
    }
    private boolean hasSidePedestals(Level level, BlockPos pos){
        return level.getBlockState(pos).is(ModBlocks.VOID_PEDESTAL.get());
    }
    private void placePedestal(Level level, BlockPos pPos){
        level.setBlockAndUpdate(pPos, ModBlocks.VOID_PEDESTAL.get().defaultBlockState());
    }

    @Override
    public List<ItemStack> getDrops(BlockState pState, LootParams.Builder pParams) {
        super.getDrops(pState, pParams);
        return List.of(new ItemStack(this.asItem()));
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new VoidAltarBlockEntity(pPos,pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        /*if(pLevel.isClientSide()) {
            return null;
        }*/

        return createTickerHelper(pBlockEntityType, ModBlockEntities.VOID_ALTAR.get(),
                (pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tick(pLevel1, pPos, pState1));
    }

}
