package net.voidkin.voidkin.chests;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

public class ModChestBlockItem extends BlockItem {

    protected ModChestsTypes type;

    protected Boolean trapped;

    public ModChestBlockItem(Block block, Properties properties, ModChestsTypes type, Boolean trapped) {
        super(block, properties);

        this.type = type;
        this.trapped = trapped;
    }

    public ModChestsTypes getType() {
        return this.type;
    }

    public Boolean getTrapped() {
        return this.trapped;
    }

    public static final class ModChestRender implements IClientItemExtensions {

        public static final ModChestRender INSTANCE = new ModChestRender();

        @Override
        public BlockEntityWithoutLevelRenderer getCustomRenderer() {
            return new ModChestItemStackRenderer();
        }
    }
}
