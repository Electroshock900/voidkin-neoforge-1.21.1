package net.voidkin.voidkin.item.custom;

import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.voidkin.voidkin.datagen.ModDataComponents;
import net.voidkin.voidkin.item.component.ItemBoundContainer;
import net.voidkin.voidkin.item.component.QuiverContent;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class QuiverItem extends Item implements ICurioItem {
    public int containerSize;
    public QuiverItem(Properties properties, int size) {

        super(properties.component(
                ModDataComponents.QUIVER_CONTENT,
                new QuiverContent(NonNullList.createWithCapacity(size))
        ));
        this.containerSize = size;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (!level.isClientSide){
            ItemStack useItem = player.getItemInHand(usedHand);
            player.openMenu(new ItemBoundContainer<>(this.containerSize, useItem, ModDataComponents.QUIVER_CONTENT, QuiverContent::new));
            return InteractionResultHolder.success(useItem);
        }
        return super.use(level, player, usedHand);

    }

}
