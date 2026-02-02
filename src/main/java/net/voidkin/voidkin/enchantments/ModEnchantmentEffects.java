package net.voidkin.voidkin.enchantments;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.item.enchantment.effects.EnchantmentLocationBasedEffect;
import net.minecraft.world.item.enchantment.effects.ReplaceDisk;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.voidkin.voidkin.Voidkin;

import java.util.function.Supplier;

public class ModEnchantmentEffects {
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_ENCHANTMENT_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Voidkin.MODID);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> LIGHTNING_STRIKER =
            ENTITY_ENCHANTMENT_EFFECTS.register("lightning_striker", () -> LightningStrikerEnchantment.CODEC);

    public static final DeferredRegister<MapCodec<? extends EnchantmentLocationBasedEffect>> LOCATION_BASED_ENCHANTMENT_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_LOCATION_BASED_EFFECT_TYPE, Voidkin.MODID);

    public static final Supplier<MapCodec<? extends EnchantmentLocationBasedEffect>> BLOOD_WALKER =
            LOCATION_BASED_ENCHANTMENT_EFFECTS.register("blood_walker", ()-> ReplaceDisk.CODEC);
    public static final Supplier<MapCodec<? extends EnchantmentLocationBasedEffect>> LAVA_WALKER =
            LOCATION_BASED_ENCHANTMENT_EFFECTS.register("lava_walker", ()-> ReplaceDisk.CODEC);



    public static void register(IEventBus eventBus) {
        ENTITY_ENCHANTMENT_EFFECTS.register(eventBus);
        LOCATION_BASED_ENCHANTMENT_EFFECTS.register(eventBus);
    }
}
