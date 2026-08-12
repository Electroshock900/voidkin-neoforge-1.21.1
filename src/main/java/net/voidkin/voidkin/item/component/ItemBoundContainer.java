package net.voidkin.voidkin.item.component;

import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.voidkin.voidkin.Voidkin;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;
import java.util.function.Supplier;

public class ItemBoundContainer<C extends ContainerAccessor> extends SimpleContainer implements MenuProvider {
    private final ItemStack owner;
    private final Supplier<DataComponentType<C>> typeSupplier;
    private final Function<NonNullList<ItemStack>, C> containerConstructer;

    public ItemBoundContainer(int size, ItemStack owner, Supplier<DataComponentType<C>> typeSupplier, Function<NonNullList<ItemStack>, C> containerConstructer) {
        super(size);
        this.owner = owner;
        this.typeSupplier = typeSupplier;
        this.containerConstructer = containerConstructer;
        C c = owner.get(typeSupplier);
        if (c != null) {
            NonNullList<ItemStack> content = c.getContent();
            if (content.size() != size)
                Voidkin.LOGGER.warn("Content size does not equal container size");
                Voidkin.LOGGER.warn("Content Size: "+ content.size() + ", Size: " + size );
            for (int i = 0; i < Math.min(content.size(), size); i++) {
                this.setItem(i, content.get(i));
            }
        }
    }

    @Override
    public void stopOpen(Player player) {
        owner.set(typeSupplier, containerConstructer.apply(this.getItems()));
        super.stopOpen(player);
    }

    @Override
    public Component getDisplayName() {
        return owner.getHoverName();
    }


    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        if (this.getContainerSize() == 27) {
            return ChestMenu.threeRows(i, inventory, this);
        }
        if(this.getContainerSize() == 54){
            return ChestMenu.sixRows(i, inventory, this);
        }
        return ChestMenu.oneRow(i, inventory);
    }


}
