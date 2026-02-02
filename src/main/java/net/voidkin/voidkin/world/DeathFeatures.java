package net.voidkin.voidkin.world;

import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.voidkin.voidkin.Voidkin;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import net.voidkin.voidkin.world.features.DarkStonePillars;

import java.util.function.Supplier;

public class DeathFeatures{
public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BuiltInRegistries.FEATURE, Voidkin.MODID);

public static final Supplier<DarkStonePillars> DARK_STONE_PILLAR = FEATURES.register("dark_stone_pillar", () -> new DarkStonePillars(NoneFeatureConfiguration.CODEC));


}