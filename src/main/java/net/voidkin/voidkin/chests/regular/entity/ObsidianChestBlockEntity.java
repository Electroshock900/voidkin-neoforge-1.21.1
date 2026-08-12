package net.voidkin.voidkin.chests.regular.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.voidkin.voidkin.chests.*;

public class ObsidianChestBlockEntity extends AbstractModChestBlockEntity {
    public ObsidianChestBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModChestsBlockEntityTypes.OBSIDIAN_CHEST.get(), blockPos, blockState, ModChestsTypes.OBSIDIAN, ModChestsBlocks.OBSIDIAN_CHEST::get);
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return ModChestMenu.createObsidianContainer(i,inventory,this);
    }
}
