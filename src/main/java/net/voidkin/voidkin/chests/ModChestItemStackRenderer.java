package net.voidkin.voidkin.chests;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.voidkin.voidkin.chests.regular.entity.*;
import net.voidkin.voidkin.chests.trapped.entity.*;

@OnlyIn(Dist.CLIENT)
public class ModChestItemStackRenderer extends BlockEntityWithoutLevelRenderer {

    private final BlockEntityRenderDispatcher blockEntityRenderDispatcher;

    public ModChestItemStackRenderer() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());

        this.blockEntityRenderDispatcher = Minecraft.getInstance().getBlockEntityRenderDispatcher();
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        Item item = stack.getItem();
        if (item instanceof ModChestBlockItem ironChestBlockItem) {
            BlockEntity modelToUse;
            boolean trapped = ironChestBlockItem.getTrapped();
            ModChestsTypes type = ironChestBlockItem.getType();

            switch (type) {
                //case GOLD -> modelToUse = trapped ? new TrappedGoldChestBlockEntity(BlockPos.ZERO, ModChestsBlocks.TRAPPED_GOLD_CHEST.get().defaultBlockState()) : new GoldChestBlockEntity(BlockPos.ZERO, ModChestsBlocks.GOLD_CHEST.get().defaultBlockState());
                //case DIAMOND -> modelToUse = trapped ? new TrappedDiamondChestBlockEntity(BlockPos.ZERO, ModChestsBlocks.TRAPPED_DIAMOND_CHEST.get().defaultBlockState()) : new DiamondChestBlockEntity(BlockPos.ZERO, ModChestsBlocks.DIAMOND_CHEST.get().defaultBlockState());
                //case COPPER -> modelToUse = trapped ? new TrappedCopperChestBlockEntity(BlockPos.ZERO, ModChestsBlocks.TRAPPED_COPPER_CHEST.get().defaultBlockState()) : new CopperChestBlockEntity(BlockPos.ZERO, ModChestsBlocks.COPPER_CHEST.get().defaultBlockState());
                //case CRYSTAL -> modelToUse = trapped ? new TrappedCrystalChestBlockEntity(BlockPos.ZERO, ModChestsBlocks.TRAPPED_CRYSTAL_CHEST.get().defaultBlockState()) : new CrystalChestBlockEntity(BlockPos.ZERO, ModChestsBlocks.CRYSTAL_CHEST.get().defaultBlockState());
                case OBSIDIAN -> modelToUse = trapped ? new TrappedObsidianChestBlockEntity(BlockPos.ZERO, ModChestsBlocks.TRAPPED_OBSIDIAN_CHEST.get().defaultBlockState()) : new ObsidianChestBlockEntity(BlockPos.ZERO, ModChestsBlocks.OBSIDIAN_CHEST.get().defaultBlockState());
                //case DIRT -> modelToUse = trapped ? new TrappedDirtChestBlockEntity(BlockPos.ZERO, ModChestsBlocks.TRAPPED_DIRT_CHEST.get().defaultBlockState()) : new DirtChestBlockEntity(BlockPos.ZERO, ModChestsBlocks.DIRT_CHEST.get().defaultBlockState());
                default -> modelToUse = trapped ? new TrappedIronChestBlockEntity(BlockPos.ZERO, ModChestsBlocks.TRAPPED_IRON_CHEST.get().defaultBlockState()) : new IronChestBlockEntity(BlockPos.ZERO, ModChestsBlocks.IRON_CHEST.get().defaultBlockState());
            }

            this.blockEntityRenderDispatcher.renderItem(modelToUse, poseStack, buffer, packedLight, packedOverlay);
        } else {
            super.renderByItem(stack, displayContext, poseStack, buffer, packedLight, packedOverlay);
        }
    }
}
