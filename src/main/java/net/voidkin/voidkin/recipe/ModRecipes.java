package net.voidkin.voidkin.recipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.voidkin.voidkin.Voidkin;
import net.minecraft.world.item.crafting.RecipeSerializer;


public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, Voidkin.MODID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, Voidkin.MODID);
/*
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<CandyCaneFurnaceRecipe>> CANDY_CANE_FURNACE_SERIALIZER =
            SERIALIZERS.register("candy_cane_furnace", () -> CandyCaneFurnaceRecipe.Serializer.INSTANCE);
    public static final DeferredHolder<RecipeType<?>, RecipeType<CandyCaneFurnaceRecipe>> CANDY_CANE_FURNACE_TYPE =
            TYPES.register("candy_cane_furnace", () -> new RecipeType<AltarRecipe>() {
                public String toString() { return "voidkin:candy_cane_furnace"; }
            });*/
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<PolisherRecipe>> POLISHER_SERIALIZER =
            SERIALIZERS.register("polisher", PolisherRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<PolisherRecipe>> POLISHER_TYPE =
            TYPES.register("polisher", () -> new RecipeType<PolisherRecipe>() {
                public String toString() { return "polisher"; }
            });
/**
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<AltarRecipe>> ALTAR_SERIALIZER =
            SERIALIZERS.register("altar_crafting", AltarRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<AltarRecipe>> ALTAR_TYPE =
            TYPES.register("altar_crafting", () -> new RecipeType<AltarRecipe>() {
                public String toString() { return "altar_crafting"; }
            });**/

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<CrystallizerRecipe>> CRYSTALLIZER_SERIALIZER =
            SERIALIZERS.register("crystallizing", CrystallizerRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<CrystallizerRecipe>> CRYSTALLIZER_TYPE =
            TYPES.register("crystallizing", () -> new RecipeType<CrystallizerRecipe>() {
                @Override
                public String toString() {
                    return "crystallizing";
                }
            });


    /**public static final DeferredHolder<RecipeType<?>, RecipeType<MultiblockRecipe>> MULTIBLOCK_TYPE =
            TYPES.register("multiblock", () -> new RecipeType<>() {
                public String toString() { return "voidkin:multiblock"; }
            });**/

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
