package net.voidkin.voidkin.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.Structure;


import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

/**
 * @param hintStructureKey ResourceKey of a structure that extends the StructureHints interface, so that the correct hint book mob spawns
 //* @param enforcement      ResourceKey of the Enforcement that gets used whenever a player is in a restricted biome
 * @param multiplier       A value dictating how adverse the negative effect of a restricted area should be
 * @param lockedBiomeToast Item that is used as an icon for the notification that tells the player that the area is locked
 * @param advancements     List of advancements that are required to make a biome no longer restricted
 */

public record Restriction(@Nullable ResourceKey<Structure> hintStructureKey,
                          float multiplier, @Nullable ItemStack lockedBiomeToast, List<ResourceLocation> advancements) {


    public static Optional<Restriction> getRestrictionForBiome(Biome biome, Entity entity) {
        if (!(entity instanceof Player player))
            return Optional.empty();

        RegistryAccess access = entity.level().registryAccess();
        ResourceLocation biomeLocation = access.registryOrThrow(Registries.BIOME).getKey(biome);
        if (biomeLocation == null)
            return Optional.empty();

        return Optional.empty();
    }

    public static boolean isBiomeSafeFor(Biome biome, Entity entity) {
        return getRestrictionForBiome(biome, entity).isEmpty();
    }
}
