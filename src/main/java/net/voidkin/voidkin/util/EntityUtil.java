package net.voidkin.voidkin.util;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import javax.annotation.Nullable;

public class EntityUtil {
    public static boolean properlyApplyCustomDamageSource(ServerLevel level, Mob entity, Entity victim, DamageSource source, @Nullable SoundEvent flingSound) {
        float f = (float) entity.getAttributeValue(Attributes.ATTACK_DAMAGE);
        if (entity.level() instanceof ServerLevel serverlevel) {
            f = EnchantmentHelper.modifyDamage(serverlevel, entity.getWeaponItem(), entity, source, f);
        }

        boolean flag = victim.hurtMarked;//(level, source, f);
        if (flag) {
            /*float f1 = getKnockback(entity, victim, source);
            if (f1 > 0.0F && victim instanceof LivingEntity livingentity) {
                if (flingSound != null) {
                    entity.playSound(flingSound, 1.0F, 1.0F);
                }
                livingentity.knockback(f1 * 0.5F, Mth.sin(entity.getYRot() * Mth.DEG_TO_RAD), -Mth.cos(entity.getYRot() * Mth.DEG_TO_RAD));
                entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.6D, 1.0D, 0.6D));
            }*/
            EnchantmentHelper.doPostAttackEffects(level, victim, source);
            entity.setLastHurtMob(entity);
        }

        return flag;
    }
}
