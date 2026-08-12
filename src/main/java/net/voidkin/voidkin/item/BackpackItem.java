package net.voidkin.voidkin.item;

import net.minecraft.core.NonNullList;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.voidkin.voidkin.datagen.ModDataComponents;
import net.voidkin.voidkin.item.component.BackpackContent;
import net.voidkin.voidkin.item.component.ItemBoundContainer;

public class BackpackItem extends Item {
    public BackpackItem(Properties properties, int size) {
        super(properties.component(
                ModDataComponents.BACKPACK_CONTENT,
                new BackpackContent(NonNullList.createWithCapacity(size))
        ));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (!level.isClientSide){
            ItemStack useItem = player.getItemInHand(usedHand);
            player.openMenu(new ItemBoundContainer<>(27, useItem, ModDataComponents.BACKPACK_CONTENT,BackpackContent::new));
            return InteractionResultHolder.success(useItem);
        }
        return super.use(level, player, usedHand);
    }
}
