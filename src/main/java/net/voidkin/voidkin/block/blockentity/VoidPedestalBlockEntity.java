package net.voidkin.voidkin.block.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.voidkin.voidkin.block.ModBlockEntities;
import net.voidkin.voidkin.menu.PedestalMenu;
import net.voidkin.voidkin.menu.VoidPedestalMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VoidPedestalBlockEntity extends BlockEntity implements Container, MenuProvider {
    public final ItemStackHandler inventory = new ItemStackHandler(1) {
        @Override
        protected int getStackLimit(int slot, @NotNull ItemStack stack) {
            return 1;
        }

        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(level !=null && !level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };
    private float rotation = 0;
    //private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public VoidPedestalBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.VOID_PEDESTAL.get(), pPos, pBlockState);
    }

    @Override
    public int getContainerSize() {
        return inventory.getSlots();
    }

    @Override
    public boolean isEmpty() {
        for(int i = 0; i < getContainerSize(); i++) {
            ItemStack stack = getItem(i);
            if(!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getItem(int i) {
        setChanged();
        return inventory.getStackInSlot(i);
    }

    @Override
    public ItemStack removeItem(int i, int amount) {
        setChanged();
        ItemStack stack = inventory.getStackInSlot(i);
        stack.shrink(amount);
        return inventory.insertItem(i, stack, false);
    }

    @Override
    public ItemStack removeItemNoUpdate(int i) {
        setChanged();
        return i >= 0 && i < getContainerSize() ? inventory.insertItem(i, ItemStack.EMPTY, false) : ItemStack.EMPTY;
    }

    @Override
    public void setItem(int i, ItemStack itemStack) {
        setChanged();
        inventory.extractItem(0,64,false);
        inventory.insertItem(i, itemStack.copyWithCount(1), false);
        level.sendBlockUpdated(getBlockPos(),getBlockState(),getBlockState(),3);
    }

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public void clearContent() {
        for(int i = 0; i < getContainerSize(); i++) {
            setChanged();
            inventory.extractItem(i, 64, false);
            level.sendBlockUpdated(getBlockPos(),getBlockState(),getBlockState(),3);

        }
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.saveAdditional(pTag, pRegistries);
        pTag.put("inventory", inventory.serializeNBT(pRegistries));
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        inventory.deserializeNBT(pRegistries, pTag.getCompound("inventory"));
    }

    public float getRenderingRotation() {
        rotation += 0.5f;
        if(rotation >= 360) {
            rotation = 0;
        }
        return rotation;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    @Override
    public void onDataPacket(Connection connection, ClientboundBlockEntityDataPacket pkt, HolderLookup.Provider lookup) {
        super.onDataPacket(connection, pkt, lookup);
        CompoundTag tag = pkt.getTag();
        if (tag != null) {
            loadAdditional(tag, lookup);
        }
    }


/*
    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            if(side == null) {
                return lazyItemHandler.cast();
            }
        }

        return super.getCapability(cap, side);
    }
*/
    @Override
    public Component getDisplayName() {
        return Component.translatable("block.voidkin.void_pedestal");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new VoidPedestalMenu(i, inventory, this);
    }
}