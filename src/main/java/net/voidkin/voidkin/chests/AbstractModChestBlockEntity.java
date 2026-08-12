package net.voidkin.voidkin.chests;


import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestLidController;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.voidkin.voidkin.Voidkin;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public abstract class AbstractModChestBlockEntity extends RandomizableContainerBlockEntity implements LidBlockEntity {

    private NonNullList<ItemStack> items;
    @Nullable
    public Component name;

    private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
        protected void onOpen(Level level, BlockPos pos, BlockState blockState) {
            AbstractModChestBlockEntity.playSound(level, pos, blockState, SoundEvents.CHEST_OPEN);
        }

        protected void onClose(Level level, BlockPos pos, BlockState blockState) {
            AbstractModChestBlockEntity.playSound(level, pos, blockState, SoundEvents.CHEST_CLOSE);
        }

        protected void openerCountChanged(Level level, BlockPos pos, BlockState blockState, int previousCount, int newCount) {
            AbstractModChestBlockEntity.this.signalOpenCount(level, pos, blockState, previousCount, newCount);
        }

        protected boolean isOwnContainer(Player player) {
            if (!(player.containerMenu instanceof ModChestMenu)) {
                return false;
            } else {
                Container container = ((ModChestMenu) player.containerMenu).getContainer();
                return container instanceof AbstractModChestBlockEntity || container instanceof CompoundContainer && ((CompoundContainer) container).contains(AbstractModChestBlockEntity.this);
            }
        }
    };

    private final ChestLidController chestLidController = new ChestLidController();

    private final ModChestsTypes chestType;
    private final Supplier<Block> blockToUse;

    protected AbstractModChestBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState, ModChestsTypes chestTypeIn, Supplier<Block> blockToUseIn) {
        super(blockEntityType, blockPos, blockState);

        this.items = NonNullList.withSize(chestTypeIn.size, ItemStack.EMPTY);
        this.chestType = chestTypeIn;
        this.blockToUse = blockToUseIn;
    }

    @Override
    public int getContainerSize() {
        return this.getItems().size();
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable(Voidkin.MODID + ".container." + this.chestType.getId() + "_chest");
    }

    @Override
    public void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);

        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);

        if (!this.tryLoadLootTable(pTag)) {
            ContainerHelper.loadAllItems(pTag, this.items, pRegistries);
        }
        if (pTag.contains("CustomName", 8)) {
            this.name = parseCustomNameSafe(pTag.getString("CustomName"), pRegistries);
        }
    }

    @Override
    public void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.saveAdditional(pTag, pRegistries);

        if (!this.trySaveLootTable(pTag)) {
            ContainerHelper.saveAllItems(pTag, this.items, pRegistries);
        }
        if (this.name != null) {
            pTag.putString("CustomName", Component.Serializer.toJson(this.name, pRegistries));
        }
    }

    public static void lidAnimateTick(Level level, BlockPos blockPos, BlockState blockState, AbstractModChestBlockEntity chestBlockEntity) {
        chestBlockEntity.chestLidController.tickLid();
    }

    static void playSound(Level level, BlockPos blockPos, BlockState blockState, SoundEvent soundEvent) {
        double d0 = (double) blockPos.getX() + 0.5;
        double d1 = (double) blockPos.getY() + 0.5;
        double d2 = (double) blockPos.getZ() + 0.5;

        level.playSound(null, d0, d1, d2, soundEvent, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
    }

    @Override
    public boolean triggerEvent(int id, int type) {
        if (id == 1) {
            this.chestLidController.shouldBeOpen(type > 0);
            return true;
        } else {
            return super.triggerEvent(id, type);
        }
    }

    @Override
    public void startOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.incrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    public void stopOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    public void setItems(NonNullList<ItemStack> itemsIn) {
        this.items = NonNullList.withSize(this.getChestType().size, ItemStack.EMPTY);

        for (int i = 0; i < itemsIn.size(); i++) {
            if (i < this.items.size()) {
                this.getItems().set(i, itemsIn.get(i));
            }
        }
    }

    @Override
    public float getOpenNess(float partialTicks) {
        return this.chestLidController.getOpenness(partialTicks);
    }

    public static int getOpenCount(BlockGetter blockGetter, BlockPos blockPos) {
        BlockState blockstate = blockGetter.getBlockState(blockPos);

        if (blockstate.hasBlockEntity()) {
            BlockEntity blockentity = blockGetter.getBlockEntity(blockPos);

            if (blockentity instanceof AbstractModChestBlockEntity) {
                return ((AbstractModChestBlockEntity) blockentity).openersCounter.getOpenerCount();
            }
        }

        return 0;
    }

    public void recheckOpen() {
        if (!this.remove) {
            this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    protected void signalOpenCount(Level level, BlockPos blockPos, BlockState blockState, int previousCount, int newCount) {
        Block block = blockState.getBlock();
        level.blockEvent(blockPos, block, 1, newCount);
    }

    public void removeAdornments() {
    }

    public ModChestsTypes getChestType() {
        ModChestsTypes type = ModChestsTypes.IRON;

        if (this.hasLevel()) {
            ModChestsTypes typeFromBlock = AbstractModChestBlock.getTypeFromBlock(this.getBlockState().getBlock());

            if (typeFromBlock != null) {
                type = typeFromBlock;
            }
        }

        return type;
    }

    public Block getBlockToUse() {
        return this.blockToUse.get();
    }
}