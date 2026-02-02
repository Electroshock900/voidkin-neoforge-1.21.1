package net.voidkin.voidkin.menu.screens.custom;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.voidkin.voidkin.item.armor.WarTurtleArmor;
import net.voidkin.voidkin.menu.ModMenuTypes;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.voidkin.voidkin.entity.custom.WarTurtle;

import java.util.List;
import java.util.UUID;

    public class WarTurtleMenu extends AbstractContainerMenu {
        private Container warturtleContainer;
        public WarTurtle warturtle;

        // With Help from https://github.com/Mrbysco/ChocoCraft4/tree/arch/1.21
        // Under MIT LICENSE
        public static WarTurtleMenu create(int i, Inventory inventory, FriendlyByteBuf friendlyByteBuf) {
            UUID uuid = friendlyByteBuf.readUUID();
            List<WarTurtle> turtles = inventory.player.level().getEntitiesOfClass(WarTurtle.class,
                    inventory.player.getBoundingBox().inflate(16), test -> test.getUUID().equals(uuid));
            WarTurtle warturtle = turtles.isEmpty() ? null : turtles.getFirst();
            return new WarTurtleMenu(i, inventory, new SimpleContainer(28), warturtle, 4);
        }

        public WarTurtleMenu(int containerId, Inventory inventory, Container warturtleContainer, final WarTurtle warturtle, int columns) {
            super(ModMenuTypes.WAR_TURTLE_MENU.get(), containerId);
            this.warturtleContainer = warturtleContainer;
            this.warturtle = warturtle;
            warturtleContainer.startOpen(inventory.player);

            // Armor Slot
            this.addSlot(new Slot(warturtleContainer, 0, 8, 63) {
                @Override
                public boolean mayPlace(ItemStack pStack) {
                    return pStack.getItem() instanceof WarTurtleArmor;
                }
            });
            // Dye Slot
            this.addSlot(new Slot(warturtleContainer, 1, 44, 63) {
                @Override
                public boolean mayPlace(ItemStack pStack) {
                    return false;//warturtle.hasArmorOn() && Block.byItem(pStack.getItem()) instanceof WoolCarpetBlock;
                }

                @Override
                public int getMaxStackSize() {
                    return 1;
                }
            });

            // Chest Slot Tier 1
            this.addSlot(new Slot(warturtleContainer, 2, 72, 27) {
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return stack.is(Items.CHEST) && !warturtle.hasTier1Chest();
                }

                @Override
                public boolean mayPickup(Player player) {
                    return !warturtle.hasTier2Chest();
                }

                @Override
                public int getMaxStackSize() {
                    return 1;
                }
            });

            // Chest Slot Tier 2
            this.addSlot(new Slot(warturtleContainer, 3, 72, 45) {
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return stack.is(Items.CHEST) && warturtle.hasTier1Chest();
                }

                @Override
                public boolean mayPickup(Player player) {
                    return !warturtle.hasTier3Chest();
                }

                @Override
                public int getMaxStackSize() {
                    return 1;
                }
            });

            // Chest Slot Tier 3
            this.addSlot(new Slot(warturtleContainer, 4, 72, 63) {
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return stack.is(Items.CHEST) && warturtle.hasTier2Chest();
                }

                @Override
                public int getMaxStackSize() {
                    return 1;
                }
            });

            if (columns > 0) {
                for (int l = 0; l < columns; l++) {
                    this.addSlot(new Slot(warturtleContainer, 5 + l, 98 + l * 18, 27) {
                        @Override
                        public boolean isActive() {
                            return warturtle.hasTier1Chest();
                        }
                    });
                }
                for (int l = 0; l < columns; l++) {
                    this.addSlot(new Slot(warturtleContainer, 5 + l + columns, 98 + l * 18, 27 + 18){
                        @Override
                        public boolean isActive() {
                            return warturtle.hasTier2Chest();
                        }
                    });
                }
                for (int l = 0; l < columns; l++) {
                    this.addSlot(new Slot(warturtleContainer, 5 + l + 2 * columns, 98 + l * 18, 27 + 2 * 18){
                        @Override
                        public boolean isActive() {
                            return warturtle.hasTier3Chest();
                        }
                    });
                }
            }


            for (int i1 = 0; i1 < 3; i1++) {
                for (int k1 = 0; k1 < 9; k1++) {
                    this.addSlot(new Slot(inventory, k1 + i1 * 9 + 9, 8 + k1 * 18, 102 + i1 * 18 + -18));
                }
            }

            for (int j1 = 0; j1 < 9; j1++) {
                this.addSlot(new Slot(inventory, j1, 8 + j1 * 18, 142));
            }
        }

        /**
         * Determines whether supplied player can use this container
         */
        @Override
        public boolean stillValid(Player player) {
            return !this.warturtle.hasInventoryChanged(this.warturtleContainer)
                    && this.warturtleContainer.stillValid(player)
                    && this.warturtle.isAlive()
                    && player.canInteractWithEntity(this.warturtle, 4.0);
        }

        /**
         * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player inventory and the other inventory(s).
         */
        @Override
        public ItemStack quickMoveStack(Player player, int index) {
            ItemStack itemstack = ItemStack.EMPTY;
            Slot slot = this.slots.get(index);
            if (slot != null && slot.hasItem()) {
                ItemStack itemstack1 = slot.getItem();
                itemstack = itemstack1.copy();
                int i = this.warturtleContainer.getContainerSize() + 1;
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

        /**
         * Called when the container is closed.
         */
        @Override
        public void removed(Player player) {
            super.removed(player);
            this.warturtleContainer.stopOpen(player);
        }

}
