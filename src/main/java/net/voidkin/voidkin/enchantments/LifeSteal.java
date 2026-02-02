package net.voidkin.voidkin.enchantments;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;
import net.voidkin.voidkin.damage_sources.ModDamageSources;


public record LifeSteal() implements EnchantmentEntityEffect {
    public static MapCodec<LifeSteal> CODEC = MapCodec.unit(LifeSteal::new);


    @Override
    public void apply(ServerLevel pLevel, int pEnchantmentLevel, EnchantedItemInUse pItem, Entity pEntity, Vec3 pOrigin) {
        pItem.owner().heal(6.5f * (float) pEnchantmentLevel);

        pEntity.hurt(ModDamageSources.LIFE_STEAL, 6.5f * (float) pEnchantmentLevel);
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}

