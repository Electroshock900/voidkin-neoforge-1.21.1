package net.voidkin.voidkin.chests;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.voidkin.voidkin.chests.inventory.DirtChestSlot;

import javax.annotation.Nullable;

public class ModChestMenu extends AbstractContainerMenu {

    private final Container container;

    private final ModChestsTypes chestType;

    private ModChestMenu(@Nullable MenuType<?> menuType, int containerId, Inventory playerInventory) {
        this(menuType, containerId, playerInventory, new SimpleContainer(ModChestsTypes.WOOD.size), ModChestsTypes.WOOD);
    }

    public static ModChestMenu createIronContainer(int containerId, Inventory playerInventory) {
        return new ModChestMenu(ModChestsMenuTypes.IRON_CHEST.get(), containerId, playerInventory, new SimpleContainer(ModChestsTypes.IRON.size), ModChestsTypes.IRON);
    }

    public static ModChestMenu createIronContainer(int containerId, Inventory playerInventory, Container inventory) {
        return new ModChestMenu(ModChestsMenuTypes.IRON_CHEST.get(), containerId, playerInventory, inventory, ModChestsTypes.IRON);
    }

    public static ModChestMenu createGoldContainer(int containerId, Inventory playerInventory) {
        return new ModChestMenu(ModChestsMenuTypes.GOLD_CHEST.get(), containerId, playerInventory, new SimpleContainer(ModChestsTypes.GOLD.size), ModChestsTypes.GOLD);
    }

    public static ModChestMenu createGoldContainer(int containerId, Inventory playerInventory, Container inventory) {
        return new ModChestMenu(ModChestsMenuTypes.GOLD_CHEST.get(), containerId, playerInventory, inventory, ModChestsTypes.GOLD);
    }

    public static ModChestMenu createDiamondContainer(int containerId, Inventory playerInventory) {
        return new ModChestMenu(ModChestsMenuTypes.DIAMOND_CHEST.get(), containerId, playerInventory, new SimpleContainer(ModChestsTypes.DIAMOND.size), ModChestsTypes.DIAMOND);
    }

    public static ModChestMenu createDiamondContainer(int containerId, Inventory playerInventory, Container inventory) {
        return new ModChestMenu(ModChestsMenuTypes.DIAMOND_CHEST.get(), containerId, playerInventory, inventory, ModChestsTypes.DIAMOND);
    }

    public static ModChestMenu createCrystalContainer(int containerId, Inventory playerInventory) {
        return new ModChestMenu(ModChestsMenuTypes.CRYSTAL_CHEST.get(), containerId, playerInventory, new SimpleContainer(ModChestsTypes.CRYSTAL.size), ModChestsTypes.CRYSTAL);
    }

    public static ModChestMenu createCrystalContainer(int containerId, Inventory playerInventory, Container inventory) {
        return new ModChestMenu(ModChestsMenuTypes.CRYSTAL_CHEST.get(), containerId, playerInventory, inventory, ModChestsTypes.CRYSTAL);
    }

    public static ModChestMenu createCopperContainer(int containerId, Inventory playerInventory) {
        return new ModChestMenu(ModChestsMenuTypes.COPPER_CHEST.get(), containerId, playerInventory, new SimpleContainer(ModChestsTypes.COPPER.size), ModChestsTypes.COPPER);
    }

    public static ModChestMenu createCopperContainer(int containerId, Inventory playerInventory, Container inventory) {
        return new ModChestMenu(ModChestsMenuTypes.COPPER_CHEST.get(), containerId, playerInventory, inventory, ModChestsTypes.COPPER);
    }

    public static ModChestMenu createObsidianContainer(int containerId, Inventory playerInventory) {
        return new ModChestMenu(ModChestsMenuTypes.OBSIDIAN_CHEST.get(), containerId, playerInventory, new SimpleContainer(ModChestsTypes.OBSIDIAN.size), ModChestsTypes.OBSIDIAN);
    }

    public static ModChestMenu createObsidianContainer(int containerId, Inventory playerInventory, Container inventory) {
        return new ModChestMenu(ModChestsMenuTypes.OBSIDIAN_CHEST.get(), containerId, playerInventory, inventory, ModChestsTypes.OBSIDIAN);
    }

    public static ModChestMenu createDirtContainer(int containerId, Inventory playerInventory) {
        return new ModChestMenu(ModChestsMenuTypes.DIRT_CHEST.get(), containerId, playerInventory, new SimpleContainer(ModChestsTypes.DIRT.size), ModChestsTypes.DIRT);
    }

    public static ModChestMenu createDirtContainer(int containerId, Inventory playerInventory, Container inventory) {
        return new ModChestMenu(ModChestsMenuTypes.DIRT_CHEST.get(), containerId, playerInventory, inventory, ModChestsTypes.DIRT);
    }

    protected ModChestMenu(@Nullable MenuType<?> menuType, int containerId, Inventory playerInventory, Container inventory, ModChestsTypes chestType) {
        super(menuType, containerId);

        checkContainerSize(inventory, chestType.size);

        this.container = inventory;
        this.chestType = chestType;

        inventory.startOpen(playerInventory.player);

        if (chestType == ModChestsTypes.DIRT) {
            this.addSlot(new DirtChestSlot(inventory, 0, 12 + 4 * 18, 8 + 2 * 18));
        } else {
            for (int chestRow = 0; chestRow < chestType.getRowCount(); chestRow++) {
                for (int chestCol = 0; chestCol < chestType.rowLength; chestCol++) {
                    this.addSlot(new Slot(inventory, chestCol + chestRow * chestType.rowLength, 12 + chestCol * 18, 18 + chestRow * 18));
                }
            }
        }

        int leftCol = (chestType.xSize - 162) / 2 + 1;

        for (int playerInvRow = 0; playerInvRow < 3; playerInvRow++) {
            for (int playerInvCol = 0; playerInvCol < 9; playerInvCol++) {
                this.addSlot(new Slot(playerInventory, playerInvCol + playerInvRow * 9 + 9, leftCol + playerInvCol * 18, chestType.ySize - (4 - playerInvRow) * 18 - 10));
            }

        }

        for (int hotHarSlot = 0; hotHarSlot < 9; hotHarSlot++) {
            this.addSlot(new Slot(playerInventory, hotHarSlot, leftCol + hotHarSlot * 18, chestType.ySize - 24));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return this.container.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();

            if (index < this.chestType.size) {
                if (!this.moveItemStackTo(itemstack1, this.chestType.size, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, this.chestType.size, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    @Override
    public void removed(Player playerIn) {
        super.removed(playerIn);
        this.container.stopOpen(playerIn);
    }

    public Container getContainer() {
        return this.container;
    }

    //@OnlyIn(Dist.CLIENT)
    public ModChestsTypes getChestType() {
        return this.chestType;
    }
}