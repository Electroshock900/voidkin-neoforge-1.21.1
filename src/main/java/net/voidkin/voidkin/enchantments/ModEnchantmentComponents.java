package net.voidkin.voidkin.enchantments;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.voidkin.voidkin.Voidkin;

public class ModEnchantmentComponents {
    public static final DeferredRegister<DataComponentType<?>> ENCHANTMENT_COMPONENT_TYPES = DeferredRegister.create(BuiltInRegistries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, Voidkin.MODID);


}
