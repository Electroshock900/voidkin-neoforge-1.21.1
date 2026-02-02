package net.voidkin.voidkin.enchantments;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record LightningStrikerEnchantment() implements EnchantmentEntityEffect {
    public static final MapCodec<LightningStrikerEnchantment> CODEC = MapCodec.unit(LightningStrikerEnchantment::new);
    /*

    @Override
    public boolean isDiscoverable() {
        return true;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }
*/
    @Override
    public void apply(ServerLevel pLevel, int pEnchantmentLevel, EnchantedItemInUse pItem, Entity pEntity, Vec3 pOrigin) {
        BlockPos position = pEntity.blockPosition();

            if(pEnchantmentLevel == 1) {
                EntityType.LIGHTNING_BOLT.spawn(pLevel, (ItemStack) null, null, position,
                        MobSpawnType.TRIGGERED, true, true);
            }

            if(pEnchantmentLevel == 2) {
                EntityType.LIGHTNING_BOLT.spawn(pLevel, (ItemStack) null, null, position,
                        MobSpawnType.TRIGGERED, true, true);
                EntityType.LIGHTNING_BOLT.spawn(pLevel, (ItemStack) null, null, position,
                        MobSpawnType.TRIGGERED, true, true);
            }
            if(pEnchantmentLevel == 3) {
                EntityType.LIGHTNING_BOLT.spawn(pLevel, (ItemStack) null, null, position,
                        MobSpawnType.TRIGGERED, true, true);
                EntityType.LIGHTNING_BOLT.spawn(pLevel, (ItemStack) null, null, position,
                        MobSpawnType.TRIGGERED, true, true);
                EntityType.LIGHTNING_BOLT.spawn(pLevel, (ItemStack) null, null, position,
                        MobSpawnType.TRIGGERED, true, true);
            }
            if(pEnchantmentLevel == 4) {
                EntityType.LIGHTNING_BOLT.spawn(pLevel, (ItemStack) null, null, position,
                        MobSpawnType.TRIGGERED, true, true);
                EntityType.LIGHTNING_BOLT.spawn(pLevel, (ItemStack) null, null, position,
                        MobSpawnType.TRIGGERED, true, true);
                EntityType.LIGHTNING_BOLT.spawn(pLevel, (ItemStack) null, null, position,
                        MobSpawnType.TRIGGERED, true, true);
                EntityType.LIGHTNING_BOLT.spawn(pLevel, (ItemStack) null, null, position,
                        MobSpawnType.TRIGGERED, true, true);
            }
        }


    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
