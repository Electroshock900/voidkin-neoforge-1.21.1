package net.voidkin.voidkin.enchantments;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.item.enchantment.effects.EnchantmentLocationBasedEffect;
import net.minecraft.world.item.enchantment.effects.ReplaceDisk;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;



import static net.voidkin.voidkin.block.ModBlocks.*;
import static net.voidkin.voidkin.block.ModBlocks.BLOOD_CONGEALED;

public record BloodWalker() implements EnchantmentLocationBasedEffect {

    public int getMinCost(int pEnchantmentLevel) {
        return pEnchantmentLevel * 10;
    }

    public int getMaxCost(int pEnchantmentLevel) {
        return this.getMinCost(pEnchantmentLevel) + 15;
    }

    public int getMaxLevel() {
        return 1;
    }

    @Override
    public void onChangedBlock(ServerLevel pLevel, int pEnchantmentLevel, EnchantedItemInUse pItem, Entity pEntity, Vec3 pPos, boolean pApplyTransientEffects) {

    }

    @Override
    public MapCodec<? extends EnchantmentLocationBasedEffect> codec() {
        return ReplaceDisk.CODEC;
    }
/*
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class BridgeBuildingHandler {


        @SubscribeEvent
        public static void coagulate(TickEvent.PlayerTickEvent event) {
            if (event.phase == TickEvent.Phase.END || event.player.level().isClientSide()) return;

            int level = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.BLOOD_WALKER.getOrThrow(event.player.level()), event.player);

            int i = Math.min(16, 2 + level);
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
    }}*/

}
