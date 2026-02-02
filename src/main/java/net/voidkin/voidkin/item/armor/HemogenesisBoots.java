package net.voidkin.voidkin.item.armor;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.voidkin.voidkin.enchantments.ModEnchantments;

import static net.voidkin.voidkin.enchantments.ModEnchantments.BLOOD_WALKER;
import static net.voidkin.voidkin.block.ModBlocks.*;

public class HemogenesisBoots extends ArmorItem {
    public HemogenesisBoots(Holder<ArmorMaterial> pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);

    }
/**
    @EventBusSubscriber(bus = EventBusSubscriber.Bus.GAME)
    public static class BridgeBuildingHandler {


        @SubscribeEvent
        public static void coagulate(TickEvent.PlayerTickEvent event) {
            if (event.phase == TickEvent.Phase.END || event.player.level().isClientSide()) return;

            int level = EnchantmentHelper.getEnchantmentLevel(BLOOD_WALKER.getOrThrow(event.player.level()), event.player);

            int i = Math.min(24, 4 + level);
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
            LivingEntity pLiving = event.player;
            BlockPos pPos = event.player.blockPosition();
            Level pLevel = event.player.level();

            for (BlockPos blockpos : BlockPos.betweenClosed(pPos.offset(-i, -1, -i), pPos.offset(i, -1, i))) {
                if (blockpos.closerToCenterThan(pLiving.position(), (double) i)) {
                    blockpos$mutableblockpos.set(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
                    BlockState blockstate1 = pLevel.getBlockState(blockpos$mutableblockpos);
                    BlockState state = event.player.level().getBlockState(pPos);
                    if (level > 0 && blockstate1 == BLOOD_BLOCK.get().defaultBlockState()) {
                        //if (state.isAir()) {
                        BlockState state2 = event.player.level().getBlockState(pPos.below());
                        BlockState b2 = BLOOD_CONGEALED.get().defaultBlockState();
                        if (state2.isAir()) return;

                        event.player.level().setBlockAndUpdate(blockpos, b2);
                        //}
                    }
                    // your code here
                }
            }
        }}**/
}
