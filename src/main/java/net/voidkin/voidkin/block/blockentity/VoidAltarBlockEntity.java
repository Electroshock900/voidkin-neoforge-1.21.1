package net.voidkin.voidkin.block.blockentity;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.voidkin.voidkin.block.ModBlockEntities;
import net.voidkin.voidkin.menu.VoidAltarMenu;
import net.voidkin.voidkin.particles.ModParticles;
import net.voidkin.voidkin.recipe.AltarRecipe;
import net.voidkin.voidkin.recipe.ModRecipes;
import net.voidkin.voidkin.recipe.recipeinput.AltarRecipeInput;
import net.voidkin.voidkin.block.ModBlocks;
import net.voidkin.voidkin.item.ModItems;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector2i;

import java.util.List;
import java.util.Optional;

import static org.openjdk.nashorn.internal.codegen.OptimisticTypesPersistence.load;

public class VoidAltarBlockEntity extends BlockEntity implements MenuProvider {
    public static List<Vector2i> offsets = List.of(
            new Vector2i(-2,-2),
            new Vector2i(0,-3),
            new Vector2i(2,-2),
            new Vector2i(-3,0),
            //new Vector2i(0,0),
            new Vector2i(3,0),
            new Vector2i(-2,2),
            new Vector2i(0,3),
            new Vector2i(2,2));

    public final ItemStackHandler itemHandler = new ItemStackHandler(9) {
        @Override
        public void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide) {
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
            }
        }
    };

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    //private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;

    public VoidAltarBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.VOID_ALTAR.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> progress;
                        case 1 -> maxProgress;
                        default -> 0;


                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> progress = pValue;
                    case 1 -> maxProgress = pValue;

                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    public ItemStack getRenderStack() {
        if(itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty()) {
            return itemHandler.getStackInSlot(INPUT_SLOT);
        } else {
            return itemHandler.getStackInSlot(OUTPUT_SLOT);
        }
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        CompoundTag tag = super.getUpdateTag(pRegistries);
        tag.putInt("altar_main.progress", progress);
        tag.putInt("altar_main.max_progress", maxProgress);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider holders) {
        super.handleUpdateTag(tag, holders);
        progress = tag.getInt("altar_main.progress");
        maxProgress = tag.getInt("altar_main.max_progress");
    }

/*
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }*/

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.voidkin.void_altar");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new VoidAltarMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        pTag.put("altar_inventory", itemHandler.serializeNBT(pRegistries));
        pTag.putInt("altar_main.progress", progress);
        pTag.putInt("altar_main.max_progress", maxProgress);

        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    public void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        itemHandler.deserializeNBT(pRegistries,pTag.getCompound("altar_inventory"));
        progress = pTag.getInt("altar_main.progress");
        maxProgress = pTag.getInt("altar_main.max_progress");
    }
    private void clientTick(Level level, BlockPos pos, BlockState state) {
        int maxProgress2 = 78;
        if (this.progress > 0 && this.progress < maxProgress2) {
            LogUtils.getLogger().error("CLIENT TICK " + progress);
            LogUtils.getLogger().error("CLIENT TICK MAX " + maxProgress);

            spawnParticleRing(ParticleTypes.SOUL);
            /*level.addParticle(ParticleTypes.SOUL,
                    getBlockPos().getX() + 0.5,
                    getBlockPos().getY() + 1.1,
                    getBlockPos().getZ() + 0.5,
                    0,
                    0,
                    0);*/

        }
        //else{LogUtils.getLogger().debug("FAILED "+progress);}
    }


    public void tick(Level level, BlockPos pos, BlockState state) {


        if (level.isClientSide) {
            clientTick(level, pos, state);
        } else {
            serverTick(level, pos, state);

        }
    }
    private void serverTick(Level level, BlockPos pos, BlockState state) {

        if(itemHandler.getStackInSlot(0).isEmpty()){
            resetProgress();
            return;
        }
        // 1. Must have all pedestal blocks
        if (!hasPedestals()) return;

        // 2. Resolve recipe ONCE
        Optional<RecipeHolder<AltarRecipe>> recipeOpt = getCurrentRecipe();
        if (recipeOpt.isEmpty()) return;

        AltarRecipe recipe = recipeOpt.get().value();

        // 3. Main altar item must match
        if (!recipe.getIngredients().getFirst().test(this.itemHandler.getStackInSlot(0)) || itemHandler.getStackInSlot(0).isEmpty()) {
            resetProgress();
            return;
        }

        // 4. Pedestal items must match
        if (!pedestalsMatchRecipe(recipe)) {
            resetProgress();
            return;
        }

        // 5. Progress crafting
        increaseCraftingProgress();
        setChanged(level, pos, state);
        level.sendBlockUpdated(pos,state,state,3);

        if (hasProgressFinished()) {
            craft(recipe);
            resetProgress();
        }
    }

    private void resetProgress() {
        progress = 0;
        setChanged();
        level.sendBlockUpdated(worldPosition,getBlockState(),getBlockState(),3);
    }


    private boolean hasRecipe() {
        Optional<RecipeHolder<AltarRecipe>> recipe = getCurrentRecipe();
        Level level = this.getLevel();
        ItemStack result;
        if (hasPedestals()) {
            if (recipe.isEmpty()) {
                return false;
            }
            result = recipe.get().value().output();
        }else{return false;}

    result = recipe.get().value().output();
    return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private Optional<RecipeHolder<AltarRecipe>> getCurrentRecipe() {
        return this.level.getRecipeManager()
                .getRecipeFor(ModRecipes.ALTAR_TYPE.get(), new AltarRecipeInput(
                itemHandler.getStackInSlot(0),
                offsets.stream().map(offset -> {
                    if(hasPedestals()){
                        return((VoidPedestalBlockEntity) level.getBlockEntity(this.getBlockPos().offset(offset.x, 0, offset.y)))
                            .inventory.getStackInSlot(0);
                }else{
                    return null;}
                }).toList()
        ), level);
    }
    private Optional<RecipeHolder<AltarRecipe>> getCurrentRecipe2() {
        if (level == null) return Optional.empty();

        return level.getRecipeManager().getRecipeFor(
                ModRecipes.ALTAR_TYPE.get(),
                new AltarRecipeInput(
                        itemHandler.getStackInSlot(0),
                        offsets.stream()
                                .map(offset -> {
                                    BlockEntity be = level.getBlockEntity(worldPosition.offset(offset.x, 0, offset.y));
                                    if (be instanceof VoidPedestalBlockEntity pedestal) {
                                        return pedestal.inventory.getStackInSlot(0);
                                    }
                                    return ItemStack.EMPTY;
                                })
                                .toList()
                ),
                level
        );
    }
    private boolean pedestalsMatchRecipe(AltarRecipe recipe) {
        NonNullList<Ingredient> ingredients = recipe.getIngredients();

        // Ingredient[0] = main altar item
        int pedestalIngredientCount = ingredients.size() - 1;

        if (pedestalIngredientCount != offsets.size()) {
            return false;
        }

        for (int i = 0; i < offsets.size(); i++) {
            Vector2i offset = offsets.get(i);
            Ingredient ingredient = ingredients.get(i + 1);

            BlockEntity be = level.getBlockEntity(worldPosition.offset(offset.x, 0, offset.y));
            if (!(be instanceof VoidPedestalBlockEntity pedestal)) {
                return false;
            }

            ItemStack stack = pedestal.inventory.getStackInSlot(0);
            if (!ingredient.test(stack)) {
                return false;
            }
        }

        return true;
    }
    private void craft(AltarRecipe recipe) {
        // Remove main item
        this.itemHandler.setStackInSlot(0, ItemStack.EMPTY);
        //itemHandler.extractItem(0, 1, false);
        setChanged();
        level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
        //itemHandler.setStackInSlot(0,ItemStack.EMPTY);

        // Clear pedestal items
        for (Vector2i offset : offsets) {
            BlockEntity be = this.level.getBlockEntity(worldPosition.offset(offset.x, 0, offset.y));
            if (be instanceof VoidPedestalBlockEntity pedestal) {
                //pedestal.inventory.extractItem(0, 64, false);
                pedestal.clearContent();
                pedestal.inventory.setStackInSlot(0, ItemStack.EMPTY);
                pedestal.setChanged();
                level.sendBlockUpdated(
                        pedestal.getBlockPos(),
                        pedestal.getBlockState(),
                        pedestal.getBlockState(),
                        3
                );
            }
        }
        //spawnParticleRing(ParticleTypes.SOUL);
        // Insert result
        ItemStack result = recipe.output().copy();
        setChanged();
        itemHandler.insertItem(0, result, false);
        level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
    }

    private void removefromSides(){
        offsets.forEach(offset -> ((VoidPedestalBlockEntity) level.getBlockEntity(this.getBlockPos().offset(offset.x, 0, offset.y)))
                .inventory.setStackInSlot(0, ItemStack.EMPTY));

    }
    private boolean hasItemInPedestal(Vector2i pPos, ItemLike pItem){
        Optional<RecipeHolder<AltarRecipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()){
            return false;
        }
        NonNullList<Ingredient> ing = recipe.get().value().getIngredients();
        int i = 1;
        for (i = 0; i < ing.size(); i++) {
            //    itemStack = new ItemStack((ItemLike) ing.get(i));
            ItemStack itemStack = recipe.get().value().getItem(i);
        }
        ItemStack itemStack = recipe.get().value().getItem(i);
        return offsets.stream().allMatch(offset -> ((VoidPedestalBlockEntity) level.getBlockEntity(this.getBlockPos().offset(offset.x, 0, offset.y))).inventory.getStackInSlot(0)
                .is(itemStack.getItem()))

                ;


    }
    /*private boolean pedestalsItems2() {
        Optional<RecipeHolder<AltarRecipe>> recipe = getCurrentRecipe();

        NonNullList<Ingredient> ing;
        //assert recipe.isPresent();
          //  ing = recipe.get().value().getIngredients();

        ItemStack itemStack = ItemStack.EMPTY;
        //new ItemStack((ItemLike) ing.get(1));
        if(hasRecipe() && recipe.isPresent()) {
            ing = recipe.get().value().getIngredients();
            for (int i = 1; i < ing.size(); i++) {
                itemStack = new ItemStack(ing.get(i));
            }
        }
        ItemLike finalItemStack = itemStack.getItem();
        return offsets.stream().allMatch(offset -> hasItemInPedestal(offset, finalItemStack));

    }*/
    private boolean pedestalsItems() {
        Optional<RecipeHolder<AltarRecipe>> recipe = getCurrentRecipe();
        List<Ingredient> ingredients = recipe.get().value().getIngredients();

        // ingredients[0] is main altar item
        if (ingredients.size() - 1 != offsets.size()) return false;

        for (int i = 1; i < ingredients.size(); i++) {
            Vector2i offset = offsets.get(i - 1);
            BlockEntity be = level.getBlockEntity(worldPosition.offset(offset.x, 0, offset.y));

            if (!(be instanceof VoidPedestalBlockEntity pedestal)) return false;

            ItemStack stack = pedestal.inventory.getStackInSlot(0);
            if (!ingredients.get(i).test(stack)) return false;
        }
        return true;
    }

    public boolean hasPedestals(){
        //spawnParticleRing(ParticleTypes.DRAGON_BREATH);
        return offsets.stream().allMatch(offset ->
                {
                    assert this.level != null;
                    return level.getBlockState(this.getBlockPos().offset(offset.x, 0, offset.y))
                            .is(ModBlocks.VOID_PEDESTAL.get());
                }
        );
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftingProgress() {
        LogUtils.getLogger().error("Progress" + progress);
        progress++;

    }
    private void s(){}


    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }


    @Override
    public void onDataPacket(Connection connection, ClientboundBlockEntityDataPacket pkt, HolderLookup.Provider lookup) {
        //super.onDataPacket(connection, pkt, lookup);
        CompoundTag tag = pkt.getTag();
        if (tag != null) {
            loadAdditional(tag, this.getLevel().registryAccess());
        }
    }

    private void spawnParticleRing(ParticleOptions particle) {
        if (level == null) return;

        double cx = worldPosition.getX() + 0.5;
        double cy = worldPosition.getY() + 1.1;
        double cz = worldPosition.getZ() + 0.5;

        float progressRatio = (float) progress / maxProgress;
        float radius = 1.5f + Mth.sin(progressRatio * Mth.PI) * 0.4f;

        float time = level.getGameTime() * 0.1f;
        int count = 7;

        for (int i = 0; i < count; i++) {
            double angle = time + (Math.PI * 2 * i / count);

            level.addParticle(
                    particle,
                    cx + Math.cos(angle) * radius,
                    cy,
                    cz + Math.sin(angle) * radius,
                    0,
                    1,
                    0
            );
        }
    }
    private void spawnParticleRing2(ParticleOptions particle) {
        if (level == null) return;

        double centerX = getBlockPos().getX() + 0.5;
        double centerY = getBlockPos().getY() + 1.1;
        double centerZ = getBlockPos().getZ() + 0.5;

        // Progress-based animation
        float progressRatio = (float) progress / maxProgress;
        float radius = 1.5f + Mth.sin(progressRatio * Mth.PI) * 0.4f;

        // Rotation over time
        float time = (level.getGameTime() + level.getRandom().nextFloat()) * 0.1f;

        int particleCount = 24;

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

}
