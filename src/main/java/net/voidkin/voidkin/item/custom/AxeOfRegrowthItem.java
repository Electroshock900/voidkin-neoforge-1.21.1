package net.voidkin.voidkin.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AxeOfRegrowthItem extends AxeItem {
    public AxeOfRegrowthItem(Tier pTier, float pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        Player player = pContext.getPlayer();
        BlockPos pos = pContext.getClickedPos();
        BlockState state = level.getBlockState(pos);

        if (player != null && !level.isClientSide() && state.getBlock() == Blocks.DIRT) {
            // Turn dirt block into grass block
            level.addDestroyBlockEffect(pos, state);
            level.setBlockAndUpdate(pos, Blocks.GRASS_BLOCK.defaultBlockState());
            //player.getMainHandItem().hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(player.getUsedItemHand()));
            return InteractionResult.SUCCESS;
        }

        return super.useOn(pContext);
    }

    @Override
    public boolean mineBlock(ItemStack pItemStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pMiner) {
        if (pMiner instanceof Player player) {
             if (!pLevel.isClientSide) {
                if (pState.is(BlockTags.LOGS)) {
                    List<BlockPos> logsPos = new ArrayList<>();
                    List<BlockPos> leavesPos = new ArrayList<>();
                    breakTree(pLevel, pPos, player, logsPos, leavesPos);
                    plantSapling(pLevel, findTreeBase(pLevel, logsPos), player, getSaplingByLeaf(pLevel, leavesPos));
                }
            }
        }
        return super.mineBlock(pItemStack, pLevel, pState, pPos, pMiner);
    }

    private void breakTree(Level pLevel, BlockPos pPos, Player pPlayer, List<BlockPos> logsPos, List<BlockPos> leavesPos) {
        int remainingDurability = pPlayer.getMainHandItem().getMaxDamage() - pPlayer.getMainHandItem().getDamageValue();
        recursiveTreeBreak(pLevel, pPos, pPlayer, remainingDurability, logsPos, leavesPos);
        plantSapling(pLevel, findTreeBase(pLevel, logsPos), pPlayer, getSaplingByLeaf(pLevel, leavesPos));
    }

    private void recursiveTreeBreak(Level pLevel, BlockPos pPos, Player pPlayer, int remainingDurability, List<BlockPos> logsPos, List<BlockPos> leavesPos) {
        BlockState state = pLevel.getBlockState(pPos);
        Block block = state.getBlock();

        if (state.is(BlockTags.LEAVES) && leavesPos.isEmpty()) {
            leavesPos.add(pPos);
            pLevel.addDestroyBlockEffect(pPos,state);
        }

        if (block == Blocks.AIR || !state.is(BlockTags.LOGS)) {
            return; // Stop recursion if the block is not wood
        }

        logsPos.add(pPos);

        if (remainingDurability <= 0) {
            return;
        }

        // Deduct durability for each block broken
        //pPlayer.getMainHandItem().hurtAndBreak(1, pPlayer, (p) -> p.broadcastBreakEvent(pPlayer.getUsedItemHand()));
        remainingDurability = pPlayer.getMainHandItem().getMaxDamage() - pPlayer.getMainHandItem().getDamageValue();

        // Destroy the block and trigger block drops
        pLevel.destroyBlock(pPos, true, pPlayer);
        //pLevel.destroyBlock(pPos, true, pPlayer);

        // Continue recursively to neighboring wood blocks
        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            for (int yOffset = -1; yOffset <= 1; yOffset++) {
                for (int zOffset = -1; zOffset <= 1; zOffset++) {
                    BlockPos neighborPos = pPos.offset(xOffset, yOffset, zOffset);
                    if (!neighborPos.equals(pPos)) {
                        recursiveTreeBreak(pLevel, neighborPos, pPlayer, remainingDurability, logsPos, leavesPos);
                    }
                }
            }
        }
    }

    private List<BlockPos> findTreeBase(Level pLevel, List<BlockPos> logsPos) {
//        int minY = logsPos.stream().mapToInt(BlockPos::getY).min().orElse(0);
//
//        return logsPos.stream()
//                .filter(pos -> pos.getY() == minY)
//                .collect(Collectors.toList());

        List<Integer> ys = new ArrayList<>();
        List<BlockPos> treeBasePos = new ArrayList<>();

        for (BlockPos blockPos : logsPos) {
            ys.add(blockPos.getY());
        }

        int treeBaseY = Collections.min(ys);

        for (BlockPos blockPos : logsPos) {
            if (blockPos.getY() == treeBaseY) {
                treeBasePos.add(blockPos);
            }
        }

        return treeBasePos;
    }

    private Item getSaplingByLeaf(Level pLevel,List<BlockPos> leavesPos) {
        if (leavesPos.isEmpty()) {
            // Handle the case when leavesPos is empty
            return BuiltInRegistries.ITEM.get(ResourceLocation.withDefaultNamespace("air"));
        }

        BlockState state = pLevel.getBlockState(leavesPos.get(0));
        Block block = state.getBlock();

        String treeType = BuiltInRegistries.BLOCK.getKey(block).toString().replace("_leaves", "");
        String sapling;
        if (treeType.contains("azalea")) {
            sapling = treeType;
        } else {
            sapling = treeType+"_sapling";
        }

        return BuiltInRegistries.ITEM.get(ResourceLocation.tryParse(sapling));
    }

    private void plantSapling(Level pLevel, @NotNull List<BlockPos> treeBasePos, Player pPlayer, Item sapling) {
        if (pPlayer.getAbilities().instabuild || playerHasStackInInventory(pPlayer, sapling)) {
            NonNullList<ItemStack> inventory = pPlayer.getInventory().offhand;
            inventory.addAll(pPlayer.getInventory().items);
            for (ItemStack itemStack : inventory) {
                if (itemStack.is(sapling)) {
                    itemStack.shrink(treeBasePos.size()); // Shrink the actual stack in the player.json's inventory
                    for (BlockPos blockPos : treeBasePos) {
                        //pPlayer.displayClientMessage(Component.literal("Hello, blockPos = "+blockPos.toString()+", Block = "+Block.byItem(sapling)+", Item = "+sapling.toString()), false); // For debugging purposes
                        pLevel.setBlockAndUpdate(blockPos, Block.byItem(sapling).defaultBlockState()); // Use Block.byItem to get the block from the item
                    }
                    break; // Break the loop once the sapling is planted
                }
            }
        }
    }

    private boolean playerHasStackInInventory(Player pPlayer, Item pItem) {
        for (ItemStack itemStack : pPlayer.getInventory().offhand) {
            if (itemStack.is(pItem)) {
                return true;
            }
        }
        for (ItemStack itemStack : pPlayer.getInventory().items) {
            if (itemStack.is(pItem)) {
                return true;
            }
        }
        return false;
    }
}