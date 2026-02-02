package net.voidkin.voidkin.enchantments;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public class BeheadingEnchantment implements EnchantmentEntityEffect {
    /*@Override
    public float getDamageBonus(int level, MobType mobType, ItemStack enchantedItem) {
        return 6.5f * level;
        //super.getDamageBonus(level, mobType, enchantedItem);
    }*/

    @Override
    public void apply(ServerLevel pLevel, int pEnchantmentLevel, EnchantedItemInUse pItem, Entity pEntity, Vec3 pOrigin) {

    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return null;
    }
}
