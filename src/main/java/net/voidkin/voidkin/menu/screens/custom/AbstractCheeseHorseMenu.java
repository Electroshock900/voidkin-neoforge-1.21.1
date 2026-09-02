package net.voidkin.voidkin.menu.screens.custom;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.voidkin.voidkin.entity.custom.CheeseHorse;
import net.voidkin.voidkin.entity.custom.cheesehorse.AbstractCheeseHorse;
import net.voidkin.voidkin.menu.ModMenuTypes;

import java.util.List;
import java.util.UUID;

public class AbstractCheeseHorseMenu extends AbstractContainerMenu {

    private Container cheeseHorseContainer;
    public Container cheeseHorseBodyArmorContainer;
    public AbstractCheeseHorse cheeseHorse;


    public AbstractCheeseHorseMenu(int containerId, Inventory inventory, Container cheeseHorseContainer, final AbstractCheeseHorse cheeseHorse, int columns) {
        super(ModMenuTypes.CHEESE_HORSE_MENU.get(), containerId);
        this.cheeseHorseContainer = cheeseHorseContainer;
        this.cheeseHorseBodyArmorContainer = cheeseHorse.getBodyArmorAccess();
        this.cheeseHorse = cheeseHorse;
        int i = 3;
        cheeseHorseContainer.startOpen(inventory.player);
        int j = -18;
        this.addSlot(new Slot(cheeseHorseContainer, 0, 8, 18) {
            public boolean mayPlace(ItemStack p_39677_) {
                return p_39677_.is(Items.SADDLE) && !this.hasItem() && cheeseHorse.isSaddleable();
            }

            public boolean isActive() {
                return cheeseHorse.isSaddleable();
            }
        });
        this.addSlot(new Slot(this.cheeseHorseBodyArmorContainer, 0, 8, 36) {
            public boolean mayPlace(ItemStack p_39690_) {
                return cheeseHorse.isBodyArmorItem(p_39690_);
            }

            public boolean isActive() {
                return cheeseHorse.canUseSlot(EquipmentSlot.BODY);
            }
        });
        if (columns > 0) {
            for (int k = 0; k < 3; ++k) {
                for (int l = 0; l < columns; ++l) {
                    this.addSlot(new Slot(cheeseHorseContainer, 1 + l + k * columns, 80 + l * 18, 18 + k * 18));
                }
            }
        }

        for (int i1 = 0; i1 < 3; ++i1) {
            for (int k1 = 0; k1 < 9; ++k1) {
                this.addSlot(new Slot(inventory, k1 + i1 * 9 + 9, 8 + k1 * 18, 102 + i1 * 18 + -18));
            }
        }

        for (int j1 = 0; j1 < 9; ++j1) {
            this.addSlot(new Slot(inventory, j1, 8 + j1 * 18, 142));
        }

    }

    // With Help from https://github.com/Mrbysco/ChocoCraft4/tree/arch/1.21
    // Under MIT LICENSE
    public static AbstractCheeseHorseMenu create(int i, Inventory inventory, FriendlyByteBuf friendlyByteBuf) {
        UUID uuid = friendlyByteBuf.readUUID();
        List<AbstractCheeseHorse> wheels = inventory.player.level().getEntitiesOfClass(AbstractCheeseHorse.class,
                inventory.player.getBoundingBox().inflate(16), test -> test.getUUID().equals(uuid));
        AbstractCheeseHorse cheeseHorse = wheels.isEmpty() ? null : wheels.getFirst();
        return new AbstractCheeseHorseMenu(i, inventory, new SimpleContainer(28), cheeseHorse, 4);
    }
    public boolean stillValid(Player player) {
        return !this.cheeseHorse.hasInventoryChanged(this.cheeseHorseContainer) && this.cheeseHorseContainer.stillValid(player) && this.cheeseHorseBodyArmorContainer.stillValid(player) && this.cheeseHorse.isAlive() && player.canInteractWithEntity(this.cheeseHorse, (double) 4.0F);
    }

    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = (Slot) this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            int i = this.cheeseHorseContainer.getContainerSize() + 1;
            if (index < i) {
                if (!this.moveItemStackTo(itemstack1, i, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.getSlot(1).mayPlace(itemstack1) && !this.getSlot(1).hasItem()) {
                if (!this.moveItemStackTo(itemstack1, 1, 2, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.getSlot(0).mayPlace(itemstack1)) {
                if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (i <= 1 || !this.moveItemStackTo(itemstack1, 2, i, false)) {
                int j = i + 27;
                int k = j + 9;
                if (index >= j && index < k) {
                    if (!this.moveItemStackTo(itemstack1, i, j, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= i && index < j) {
                    if (!this.moveItemStackTo(itemstack1, j, k, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!this.moveItemStackTo(itemstack1, j, j, false)) {
                    return ItemStack.EMPTY;
                }

                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    public void removed(Player player) {
        super.removed(player);
        this.cheeseHorseContainer.stopOpen(player);
    }
}