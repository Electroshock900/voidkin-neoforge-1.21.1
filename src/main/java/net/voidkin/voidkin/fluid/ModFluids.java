package net.voidkin.voidkin.fluid;

import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.voidkin.voidkin.Voidkin;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.voidkin.voidkin.block.ModBlocks;
import net.voidkin.voidkin.item.ModItems;

import java.util.function.Supplier;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(BuiltInRegistries.FLUID, Voidkin.MODID);

    public static final Supplier<FlowingFluid> SOURCE_SOAP_WATER = FLUIDS.register("soap_water_fluid",
            () -> new BaseFlowingFluid.Source(ModFluids.SOAP_WATER_FLUID_PROPERTIES));
    public static final Supplier<FlowingFluid> FLOWING_SOAP_WATER = FLUIDS.register("flowing_soap_water",
            () -> new BaseFlowingFluid.Flowing(ModFluids.SOAP_WATER_FLUID_PROPERTIES));

    public static final Supplier<FlowingFluid> SOURCE_ENDER_BLOOD = FLUIDS.register("ender_blood_fluid",
            () -> new BaseFlowingFluid.Source(ModFluids.ENDER_FLUID_PROPERTIES));
    public static final Supplier<FlowingFluid> FLOWING_ENDER_BLOOD = FLUIDS.register("flowing_ender_blood",
            () -> new BaseFlowingFluid.Flowing(ModFluids.ENDER_FLUID_PROPERTIES));

    public static final Supplier<FlowingFluid> SOURCE_BLOOD = FLUIDS.register("blood_fluid",
            () -> new BaseFlowingFluid.Source(ModFluids.BLOOD_FLUID_PROPERTIES));
    public static final Supplier<FlowingFluid> FLOWING_BLOOD = FLUIDS.register("flowing_blood_water",
            () -> new BaseFlowingFluid.Flowing(ModFluids.BLOOD_FLUID_PROPERTIES));

    public static final Supplier<FlowingFluid> SOURCE_DEITY_BLOOD = FLUIDS.register("deity_blood_fluid",
            () -> new BaseFlowingFluid.Source(ModFluids.DEITY_BLOOD_FLUID_PROPERTIES));
    public static final Supplier<FlowingFluid> FLOWING_DEITY_BLOOD = FLUIDS.register("flowing_deity_blood_fluid",
            () -> new BaseFlowingFluid.Flowing(ModFluids.DEITY_BLOOD_FLUID_PROPERTIES));

    public static final Supplier<FlowingFluid> SOURCE_DARK_ESSENCE = FLUIDS.register("dark_essence_fluid",
            ()-> new BaseFlowingFluid.Source(ModFluids.DARK_ESSENCE_FLUID_PROPERTIES));
    public static final Supplier<FlowingFluid> FLOWING_DARK_ESSENCE = FLUIDS.register("flowing_dark_essence",
            ()-> new BaseFlowingFluid.Flowing(ModFluids.DARK_ESSENCE_FLUID_PROPERTIES));

    public static final Supplier<FlowingFluid> SOURCE_VOID_LAVA = FLUIDS.register("void_lava_fluid",
            ()-> new BaseFlowingFluid.Source(ModFluids.VOID_LAVA_FLUID_PROPERTIES));
    public static final Supplier<FlowingFluid> FLOWING_VOID_LAVA = FLUIDS.register("flowing_void_lava",
            ()-> new BaseFlowingFluid.Flowing(ModFluids.VOID_LAVA_FLUID_PROPERTIES));



    public static final BaseFlowingFluid.Properties SOAP_WATER_FLUID_PROPERTIES = new BaseFlowingFluid.Properties(
            ModFluidTypes.SOAP_WATER_FLUID_TYPE2, SOURCE_SOAP_WATER, FLOWING_SOAP_WATER)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.SOAP_WATER_BLOCK)
            .bucket(ModItems.SOAP_WATER_BUCKET);
    public static final BaseFlowingFluid.Properties ENDER_FLUID_PROPERTIES = new BaseFlowingFluid.Properties(
            ModFluidTypes.ENDER_FLUID_TYPE, SOURCE_ENDER_BLOOD, FLOWING_ENDER_BLOOD)
            .slopeFindDistance(12).levelDecreasePerBlock(2).block(ModBlocks.ENDER_BLOOD_BLOCK)
            .bucket(ModItems.ENDER_BLOOD_BUCKET);
    public static final BaseFlowingFluid.Properties BLOOD_FLUID_PROPERTIES = new BaseFlowingFluid.Properties(
            ModFluidTypes.BLOOD_FLUID_TYPE, SOURCE_BLOOD, FLOWING_BLOOD)
            .slopeFindDistance(12).levelDecreasePerBlock(2).block(ModBlocks.BLOOD_BLOCK)
            .bucket(ModItems.BLOOD_BUCKET);
    public static final BaseFlowingFluid.Properties DEITY_BLOOD_FLUID_PROPERTIES = new BaseFlowingFluid.Properties(
            ModFluidTypes.DEITY_BLOOD_FLUID_TYPE, SOURCE_DEITY_BLOOD, FLOWING_DEITY_BLOOD)
            .slopeFindDistance(13).levelDecreasePerBlock(3).block(ModBlocks.DEITY_BLOOD_BLOCK)
            .bucket(ModItems.DEITY_BLOOD_BUCKET);
    public static final BaseFlowingFluid.Properties DARK_ESSENCE_FLUID_PROPERTIES = new BaseFlowingFluid.Properties(
            ModFluidTypes.DARK_ESSENCE_FLUID_TYPE, SOURCE_DARK_ESSENCE, FLOWING_DARK_ESSENCE)
            .slopeFindDistance(45).levelDecreasePerBlock(3).block(ModBlocks.DARK_ESSENCE_BLOCK)
            .bucket(ModItems.DARK_ESSENCE_BUCKET);
    public static final BaseFlowingFluid.Properties VOID_LAVA_FLUID_PROPERTIES = new BaseFlowingFluid.Properties(
            ModFluidTypes.VOID_LAVA_FLUID_TYPE, SOURCE_VOID_LAVA, FLOWING_VOID_LAVA)
            .slopeFindDistance(64).tickRate(13).levelDecreasePerBlock(1).block(ModBlocks.VOID_LAVA_BLOCK);




    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
