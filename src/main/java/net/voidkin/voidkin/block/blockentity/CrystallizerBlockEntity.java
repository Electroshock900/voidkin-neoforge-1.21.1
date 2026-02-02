package net.voidkin.voidkin.block.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.voidkin.voidkin.block.ModBlockEntities;
import net.voidkin.voidkin.block.custom.CrystallizerBlock;
import net.voidkin.voidkin.item.ModItems;
import net.voidkin.voidkin.menu.CrystallizerMenu;
import net.voidkin.voidkin.recipe.CrystallizerRecipe;
import net.voidkin.voidkin.recipe.ModRecipes;
import net.voidkin.voidkin.recipe.recipeinput.CrystallizerRecipeInput;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CrystallizerBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler inv = new ItemStackHandler(4){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()){
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    private static int FLUID_ITEM_SLOT = 0;
    private static int INPUT_SLOT = 1;
    private static int OUTPUT_SLOT = 2;
    private static int ENERGY_ITEM_SLOT = 3;

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 72;
    private final int DEFAULT_MAX_PROGRESS = 72;

    //private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public CrystallizerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.CRYSTALLIZER.get(), pPos, pBlockState);
        data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i) {
                    case 0 -> CrystallizerBlockEntity.this.progress;
                    case 1 -> CrystallizerBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> CrystallizerBlockEntity.this.progress = pValue;
                    case 1 -> CrystallizerBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("name.voidkin.crystallizer");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new CrystallizerMenu(pContainerId, pPlayerInventory,this, this.data);
    }
/**
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER){
            if(side == null){
                return lazyItemHandler.cast();
            }
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(()->inv);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }**/

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        pTag.put("crystallizer_inventory", inv.serializeNBT(pRegistries));
        pTag.putInt("crystallizer.progress", progress);
        pTag.putInt("crystallizer.max_progress", maxProgress);

        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);

        inv.deserializeNBT(pRegistries, pTag.getCompound("crystallizer_inventory"));
        progress = pTag.getInt("crystallizer.progress");
        maxProgress = pTag.getInt("crystallizer.max_progress");
    }

    public void drops(){
        SimpleContainer inventory = new SimpleContainer(inv.getSlots());
        for(int i=0; i< inv.getSlots(); i++){
            inventory.setItem(i, inv.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }
    public void tick(Level level, BlockPos pos, BlockState pState){
        if(hasRecipe() && isOutputSlotEmptyOrReceivable()){
            increaseCraftingProgress();
            level.setBlockAndUpdate(pos,pState.setValue(CrystallizerBlock.LIT, true));
            setChanged(level, pos, pState);

            if(hasCraftingFinished()){
                spawnParticleRing(ParticleTypes.FLASH);

                craftItem();
                resetProgress();
            }
        }else{
            level.setBlockAndUpdate(pos,pState.setValue(CrystallizerBlock.LIT, false));
            resetProgress();
        }
    }

    private void resetProgress() {
        progress = 0;
        maxProgress = DEFAULT_MAX_PROGRESS;
    }

    private void spawnParticleRing(ParticleOptions particle) {
        if (level == null) return;

        double centerX = this.getBlockPos().getX() + 0.5;
        double centerY = this.getBlockPos().getY() + 1.1;
        double centerZ = this.getBlockPos().getZ() + 0.5;

        // Progress-based animation
        float progressRatio = (float) progress / maxProgress;
        float radius = 1.5f + Mth.sin(progressRatio * Mth.PI) * 0.4f;

        // Rotation over time
        float time = (level.getGameTime() + level.getRandom().nextFloat()) * 0.1f;

        int particleCount = 12;

        for (int i = 0; i < particleCount; i++) {
            double angle = time + (Math.PI * 2 * i / particleCount);

            double x = centerX + Math.cos(angle) * radius;
            double z = centerZ + Math.sin(angle) * radius;

            level.addParticle(particle, // or ParticleTypes.SOUL_FIRE_FLAME
                    x,
                    centerY,
                    z,
                    0,
                    0.01,
                    0
            );
        }
    }
    private void craftItem() {
        Optional<RecipeHolder<CrystallizerRecipe>> recipe = getCurrentRecipe();
        ItemStack result = recipe.get().value().output();

        //spawnParticleRing(ParticleTypes.SONIC_BOOM);
        inv.extractItem(INPUT_SLOT, 1, false);
        inv.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.inv.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
    }

    private boolean hasCraftingFinished() {
    return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
        /*spawnParticleRing(ParticleTypes.LAVA);
        level.addParticle(ParticleTypes.SOUL,
                this.getBlockPos().getX(),
                this.getBlockPos().getY() + 1.1,
                this.getBlockPos().getZ(),
                0,
                0.2,
                0
        );*/
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return this.inv.getStackInSlot(OUTPUT_SLOT).isEmpty()||
                this.inv.getStackInSlot(OUTPUT_SLOT).getCount() < this.inv.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }


    private boolean hasRecipe() {
        Optional<RecipeHolder<CrystallizerRecipe>> recipe = getCurrentRecipe();

        if(recipe.isEmpty()){
            return false;
        }
        ItemStack output = recipe.get().value().output();

        return canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);
    }

    private Optional<RecipeHolder<CrystallizerRecipe>> getCurrentRecipe() {
        return this.level.getRecipeManager()
                .getRecipeFor(ModRecipes.CRYSTALLIZER_TYPE.get(), new CrystallizerRecipeInput(inv.getStackInSlot(INPUT_SLOT)), level);
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return inv.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.inv.getStackInSlot(OUTPUT_SLOT).getItem() == output.getItem();

    }
    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = inv.getStackInSlot(OUTPUT_SLOT).isEmpty() ? 64 : inv.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
        int currentCount = inv.getStackInSlot(OUTPUT_SLOT).getCount();

        return maxCount >= currentCount + count;
    }
    private boolean canInsetAmountIntoOutputSlot(int count) {
        return this.inv.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.inv.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();

    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
