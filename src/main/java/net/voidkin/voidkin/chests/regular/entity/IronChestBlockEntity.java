package net.voidkin.voidkin.chests.regular.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.voidkin.voidkin.chests.*;

public class IronChestBlockEntity extends AbstractModChestBlockEntity {
    public IronChestBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModChestsBlockEntityTypes.IRON_CHEST.get(), blockPos, blockState, ModChestsTypes.IRON, ModChestsBlocks.IRON_CHEST::get);
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return ModChestMenu.createIronContainer(i,inventory,this);
    }
}
