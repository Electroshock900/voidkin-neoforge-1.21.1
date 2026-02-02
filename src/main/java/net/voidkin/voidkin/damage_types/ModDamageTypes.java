package net.voidkin.voidkin.damage_types;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.util.EntityExcludedDamageSource;

import javax.annotation.Nullable;

public interface ModDamageTypes {

    /*
     * Store the RegistryKey of our DamageType into a new constant called CUSTOM_DAMAGE_TYPE
     * The Identifier in use here points to our JSON file we created earlier.
     */
    public static final ResourceKey<DamageType> LIFE_STEALS = register("life_steals");
    public static final ResourceKey<DamageType> CHAKRAMS = register("chakrams");

    private static ResourceKey<DamageType> register(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, name));
    }
    public static DamageSource getDamageSource(Level level, ResourceKey<DamageType> type, EntityType<?>... toIgnore) {
        return getEntityDamageSource(level, type, null, toIgnore);
    }

    public static DamageSource getEntityDamageSource(Level level, ResourceKey<DamageType> type, @Nullable Entity attacker, EntityType<?>... toIgnore) {
        return getIndirectEntityDamageSource(level, type, attacker, attacker, toIgnore);
    }

    public static DamageSource getIndirectEntityDamageSource(Level level, ResourceKey<DamageType> type, @Nullable Entity attacker, @Nullable Entity indirectAttacker, EntityType<?>... toIgnore) {
        return toIgnore.length > 0 ? new EntityExcludedDamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(type), toIgnore) : new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(type), attacker, indirectAttacker);
    }
    static void bootstrap(BootstrapContext<DamageType> pContext) {
        pContext.register(LIFE_STEALS, new DamageType("voidkin.life_steals", DamageScaling.ALWAYS, 2.0F, DamageEffects.HURT));
        pContext.register(CHAKRAMS, new DamageType(Voidkin.MODID + "chakrams", DamageScaling.ALWAYS, 13F, DamageEffects.BURNING));
    }

    }

/*
    public static DamageSource of(Level world, ResourceKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().get(Registries.DAMAGE_TYPE).entryOf(key));
    }
*/

