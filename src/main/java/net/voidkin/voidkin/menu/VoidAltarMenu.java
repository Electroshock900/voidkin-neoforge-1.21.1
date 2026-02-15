package net.voidkin.voidkin.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.voidkin.voidkin.block.blockentity.VoidAltarBlockEntity;
import net.voidkin.voidkin.block.blockentity.VoidPedestalBlockEntity;
import net.voidkin.voidkin.block.ModBlocks;
import org.joml.Vector2i;

import java.util.ArrayList;
import java.util.List;

public class VoidAltarMenu extends AbstractContainerMenu {
    public final VoidAltarBlockEntity blockEntity;
    public final VoidPedestalBlockEntity blockEntity2;
    private final Level level;
    private final ContainerData data;
    public VoidAltarMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(9));
    }

    public VoidAltarMenu(int pContainerId, Inventory inv, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.VOID_ALTAR_MENU.get(), pContainerId);
        checkContainerSize(inv, 9);
        this.blockEntity = new VoidAltarBlockEntity(entity.getBlockPos(),entity.getBlockState());
        this.blockEntity2 = new VoidPedestalBlockEntity(entity.getBlockPos(),entity.getBlockState());
        this.level = inv.player.level();
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        
            this.addSlot(new SlotItemHandler(this.blockEntity.itemHandler, 0, 80, 35));
            this.addSlot(new SlotItemHandler(this.blockEntity.itemHandler, 1, 80, 7));
            this.addSlot(new SlotItemHandler(this.blockEntity.itemHandler, 2, 106, 7));
            this.addSlot(new SlotItemHandler(this.blockEntity.itemHandler, 3, 52, 35));
            this.addSlot(new SlotItemHandler(this.blockEntity.itemHandler, 4, 52, 7));
            this.addSlot(new SlotItemHandler(this.blockEntity.itemHandler, 5, 106, 35));
            this.addSlot(new SlotItemHandler(this.blockEntity.itemHandler, 6, 52, 62));
            this.addSlot(new SlotItemHandler(this.blockEntity.itemHandler, 7, 80, 62));
            this.addSlot(new SlotItemHandler(this.blockEntity.itemHandler, 8, 106, 62));
                //this.addSlot(new SlotItemHandler(this.blockEntity.itemHandler, 1, 64, 59));
            
        addDataSlots(data);
    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }

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

    public List<ItemStack> getNeighborStacks(Level level, BlockPos pos) {
        List<ItemStack> stacks = new ArrayList<>();
        // Offsets for 8 surrounding blocks in the X/Z plane
        int[][] offsets = {
                {-2,-2},{0,-3},{2,-2}, {-3,0},
                /*{0,0}*/
                {3,0},{-2,2},{0,3},{2,2}
        };

        for (int[] offset : offsets) {
            BlockPos neighborPos = pos.offset(offset[0], 0, offset[1]);
            BlockEntity be = level.getBlockEntity(neighborPos);
            if (be != null) {
                //be.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
                    // Example: just grab slot 0
                    ItemStack stack = getItemFromPedestals();   //handler.getStackInSlot(0);
                    stacks.add(stack);
                //});
            }
        }
        return stacks;
    }
    public ItemStack getItemFromPedestals(){
        if(blockEntity2 instanceof VoidPedestalBlockEntity other){
            return other.getItem(0);
        }
        return ItemStack.EMPTY;
    }

    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);  // Max Progress
        int progressArrowSize = 26; // This is the height in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player.json inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player.json inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 9;  // must be the number of slots you have!
    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                pPlayer, ModBlocks.VOID_ALTAR.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }



}
