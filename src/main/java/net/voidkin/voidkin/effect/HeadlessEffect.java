package net.voidkin.voidkin.effect;

import com.mojang.logging.LogUtils;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.DimensionTransition;
import net.voidkin.voidkin.particles.ModParticles;

public class HeadlessEffect extends MobEffect {
    public HeadlessEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    public HeadlessEffect(MobEffectCategory category, int color, ParticleOptions particle) {
        super(category, color, ModParticles.VOID_FLAME.get());
    }

    @Override
    public void onEffectStarted(LivingEntity livingEntity, int amplifier) {
        super.onEffectStarted(livingEntity, amplifier);
        livingEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS));

    }

    @Override
    public void onEffectAdded(LivingEntity livingEntity, int amplifier) {
        super.onEffectAdded(livingEntity, amplifier);
        String f = "AMPLIFIER AT " + amplifier;
        LogUtils.getLogger().debug(f);
        ServerLevel serverLevel = livingEntity.getServer().getLevel(livingEntity.level().dimension());
        //livingEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS));
        if (!livingEntity.level().isClientSide) {
           if(amplifier>0) {

               livingEntity.level().explode(livingEntity, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), amplifier * 15, Level.ExplosionInteraction.MOB);

           }
        }


    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        livingEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS));
        livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 30, amplifier * 10));

        livingEntity.level().addParticle(ModParticles.FALLING_BLOOD.get(),
                livingEntity.getX(),
                livingEntity.getY(),
                livingEntity.getZ(),
                0d,0d,0d
        );


        return super.applyEffectTick(livingEntity, amplifier);
    }
}
