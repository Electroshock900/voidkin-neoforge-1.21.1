package net.voidkin.voidkin.event;


import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.util.BlockSnapshot;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.block.custom.CongealedBloodBlock;
import net.voidkin.voidkin.enchantments.ModEnchantments;
import net.voidkin.voidkin.item.custom.BigShovelItem;
import net.voidkin.voidkin.item.custom.HammerItem;
import net.voidkin.voidkin.util.ModPotions;

import java.util.HashSet;
import java.util.Set;

import static net.minecraft.world.level.block.Blocks.LAVA;
import static net.voidkin.voidkin.block.ModBlocks.*;


@EventBusSubscriber(modid= Voidkin.MODID,bus = EventBusSubscriber.Bus.GAME)
public class ForgeEvents {
    /*@SubscribeEvent
    public static void buildBridge(TickEvent.PlayerTickEvent event){

    if (event.phase == TickEvent.Phase.END || event.player.level().isClientSide()) return;

    int level = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.BLOOD_WALKER.getOrThrow(event.player.level()), event.player);
            if (level > 0 && event.player.isShiftKeyDown()) {
                BlockState state = event.player.level().getBlockState(event.player.blockPosition().below());
                if (!state.isAir()) return;
                if (state.is(BLOOD_BLOCK.get())) {
                    LogUtils.getLogger().error("ITS BLOOD");
                    event.player.level().setBlockAndUpdate(event.player.blockPosition().below(), BLOOD_CONGEALED.get().defaultBlockState());
                }

                if (event.player.onGround()) {
                    BlockState blockstate = BLOOD_CONGEALED.get().defaultBlockState();
                    BlockState blockstateb = BLOOD_BLOCK.get().defaultBlockState();
                    Level pLevel = event.player.level();
                    Player pLiving = event.player;
                    BlockPos pPos = event.player.getOnPos().below();
                    //int el = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.BLOOD_WALKER.get(), event.player);

                    int i = Math.min(16, 2 + level);
                    BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                    for (BlockPos blockpos : BlockPos.betweenClosed(pPos.offset(-i, -1, -i), pPos.offset(i, -1, i))) {
                        if (blockpos.closerToCenterThan(pLiving.position(), (double) i)) {
                            blockpos$mutableblockpos.set(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
                            BlockState blockstate1 = pLevel.getBlockState(blockpos$mutableblockpos);
                            if (blockstate1.isAir()) {
                                BlockState blockstate2 = pLevel.getBlockState(blockpos);
                                if (blockstate2 == (CongealedBloodBlock.meltsInto()) && blockstate.canSurvive(pLevel, blockpos) &&
                                        pLevel.isUnobstructed(blockstate, blockpos, CollisionContext.empty()) &&
                                        !ForgeEventFactory.onBlockPlace(pLiving, BlockSnapshot.create(pLevel.dimension(), pLevel, blockpos), Direction.UP)) {
                                    LogUtils.getLogger().info("ALL WORKS");
                                    pLevel.setBlockAndUpdate(blockpos, blockstate);
                                    pLevel.scheduleTick(blockpos, BLOOD_CONGEALED.get(), Mth.nextInt(pLiving.getRandom(), 60, 120));
                                }
                            }
                        }
                    }
                }
            }
    int level2 = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.LAVA_WALKER.getOrThrow(event.player.level()), event.player);
            if (level2 > 0 && event.player.isShiftKeyDown()) {
                BlockState state = event.player.level().getBlockState(event.player.blockPosition().below());
                if (!state.isAir()) return;
                if (state.is(BLOOD_BLOCK.get())) {
                    LogUtils.getLogger().error("ITS BLOOD");
                    event.player.level().setBlockAndUpdate(event.player.blockPosition().below(), BLOOD_CONGEALED.get().defaultBlockState());
                }

                if (event.player.onGround()) {
                    BlockState blockstate = Blocks.OBSIDIAN.defaultBlockState();
                    BlockState blockstateb = LAVA.defaultBlockState();
                    Level pLevel = event.player.level();
                    Player pLiving = event.player;
                    BlockPos pPos = event.player.getOnPos().below();
                    //int el = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.BLOOD_WALKER.get(), event.player);

                    int i = Math.min(16, 2 + level2);
                    BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                    for (BlockPos blockpos : BlockPos.betweenClosed(pPos.offset(-i, -1, -i), pPos.offset(i, -1, i))) {
                        if (blockpos.closerToCenterThan(pLiving.position(), (double) i)) {
                            blockpos$mutableblockpos.set(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
                            BlockState blockstate1 = pLevel.getBlockState(blockpos$mutableblockpos);
                            if (blockstate1.isAir()) {
                                BlockState blockstate2 = pLevel.getBlockState(blockpos);
                                if (blockstate2 == (LAVA.defaultBlockState()) && blockstate.canSurvive(pLevel, blockpos) &&
                                        pLevel.isUnobstructed(blockstate, blockpos, CollisionContext.empty()) &&
                                        !ForgeEventFactory.onBlockPlace(pLiving, BlockSnapshot.create(pLevel.dimension(), pLevel, blockpos), Direction.UP)) {
                                    LogUtils.getLogger().info("ALL WORKS");
                                    pLevel.setBlockAndUpdate(blockpos, blockstate);
                                    pLevel.scheduleTick(blockpos, Blocks.OBSIDIAN, Mth.nextInt(pLiving.getRandom(), 60, 120));
                                }
                            }
                        }
                    }
                }
            }
    }
*/

    @SubscribeEvent
    public static void onBrewingRecipeRegister(RegisterBrewingRecipesEvent event){
        PotionBrewing.Builder builder = event.getBuilder();
        builder.addMix(Potions.AWKWARD, Items.FERMENTED_SPIDER_EYE, ModPotions.SPIDER_POTION);
        builder.addMix(Potions.NIGHT_VISION, Items.FERMENTED_SPIDER_EYE, ModPotions.HEADLESS_POTION);
        builder.addMix(ModPotions.HEADLESS_POTION, Items.FERMENTED_SPIDER_EYE, ModPotions.HEADLESS_POTION2);
    }

    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    // Done with the help of https://github.com/CoFH/CoFHCore/blob/1.19.x/src/main/java/cofh/core/event/AreaEffectEvents.java
    // Don't be a jerk License
    @SubscribeEvent
    public static void onHammerUsage(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();

        if(mainHandItem.getItem() instanceof HammerItem hammer && player instanceof ServerPlayer serverPlayer) {
            BlockPos initialBlockPos = event.getPos();
            if(HARVESTED_BLOCKS.contains(initialBlockPos)) {
                return;
            }

            for(BlockPos pos : HammerItem.getBlocksToBeDestroyed(HammerItem.range, initialBlockPos, serverPlayer)) {
                if(pos == initialBlockPos || !hammer.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
                    continue;
                }

                HARVESTED_BLOCKS.add(pos);
                serverPlayer.gameMode.destroyBlock(pos);
                HARVESTED_BLOCKS.remove(pos);
            }
        }
    }
@SubscribeEvent
    public static void onShovelUsage(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();

        if(mainHandItem.getItem() instanceof BigShovelItem shovel && player instanceof ServerPlayer serverPlayer) {
            BlockPos initialBlockPos = event.getPos();
            if(HARVESTED_BLOCKS.contains(initialBlockPos)) {
                return;
            }

            for(BlockPos pos : BigShovelItem.getBlocksToBeDestroyed(BigShovelItem.range, initialBlockPos, serverPlayer)) {
                if(pos == initialBlockPos || !shovel.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
                    continue;
                }

                HARVESTED_BLOCKS.add(pos);
                serverPlayer.gameMode.destroyBlock(pos);
                HARVESTED_BLOCKS.remove(pos);
            }
        }
    }

}