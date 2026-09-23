package net.voidkin.voidkin.util;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.effect.ModEffects;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(BuiltInRegistries.POTION, Voidkin.MODID);

    public static final Holder<Potion> SPIDER_POTION = POTIONS.register("spider_potion",
            ()-> new Potion(new MobEffectInstance(ModEffects.SPIDER_EFFECT,1200)));

    public static final Holder<Potion> HEADLESS_POTION = POTIONS.register("headless_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.HEADLESS, 1200)));

    public static final Holder<Potion> HEADLESS_POTION2 = POTIONS.register("headless_potion2",
            () -> new Potion(new MobEffectInstance(ModEffects.HEADLESS, 900, 1)));

    public static void register(IEventBus eventBus){
        POTIONS.register(eventBus);
    }
}
