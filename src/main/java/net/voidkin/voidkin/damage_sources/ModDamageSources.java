package net.voidkin.voidkin.damage_sources;

import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.voidkin.voidkin.damage_types.ModDamageTypes;

import javax.annotation.Nullable;

public class ModDamageSources {

    private final Registry<DamageType> DAMAGE_TYPES;
    public static DamageSource LIFE_STEAL;
    public static DamageSource CHAKRAM;
    public ModDamageSources(RegistryAccess registryAccess, Registry<DamageType> damageTypes){
        DAMAGE_TYPES=registryAccess.registryOrThrow(Registries.DAMAGE_TYPE);
        LIFE_STEAL = new DamageSource(registryAccess.lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(ModDamageTypes.LIFE_STEALS),null,null,null);
        CHAKRAM = new DamageSource(registryAccess.lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(ModDamageTypes.CHAKRAMS),null,null,null);
    }

    public DamageSource chakram(Entity pTrident, @Nullable Entity pThrower) {
        return this.source(ModDamageTypes.CHAKRAMS, pTrident, pThrower);
    }
    public DamageSource source(ResourceKey<DamageType> pDamageTypeKey) {
        return new DamageSource(this.DAMAGE_TYPES.getHolderOrThrow(pDamageTypeKey));
    }

    public DamageSource source(ResourceKey<DamageType> pDamageTypeKey, @Nullable Entity pEntity) {
        return new DamageSource(this.DAMAGE_TYPES.getHolderOrThrow(pDamageTypeKey), pEntity);
    }

    public DamageSource source(ResourceKey<DamageType> pDamageTypeKey, @Nullable Entity pCausingEntity, @Nullable Entity pDirectEntity) {
        return new DamageSource(this.DAMAGE_TYPES.getHolderOrThrow(pDamageTypeKey), pCausingEntity, pDirectEntity);
    }


/**
    public ModDamageSources(Holder<DamageType> pType, @Nullable Entity pDirectEntity, @Nullable Entity pCausingEntity, Registry<DamageTypes> damageTypes) {
        super(pType, pDirectEntity, pCausingEntity);
        DAMAGE_TYPES = damageTypes;
    }**/
}
