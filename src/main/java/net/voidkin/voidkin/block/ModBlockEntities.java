package net.voidkin.voidkin.block;

import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.voidkin.voidkin.Voidkin;
//import net.voidkin.voidkin.block.custom.c.AltarBE;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import net.voidkin.voidkin.block.blockentity.*;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
        DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Voidkin.MODID);
/**
    public static final Supplier<BlockEntityType<AbyssalContainerEntity>> ABYSSAL_CONTAINER =
            BLOCK_ENTITIES.register("abyssal_container", () ->
                    BlockEntityType.Builder.of(AbyssalContainerEntity::new,
                            ModBlocks.ABYSSAL_CONTAINER.get()).build(null));**/
    /**public static final Supplier<BlockEntityType<AbyssalChestEntity>> CHEST =
        BLOCK_ENTITIES.register("chest", () ->
                BlockEntityType.Builder.of(AbyssalChestEntity::new,
                ModBlocks.CHEST.get()
        ).build(null));**/
    public static final Supplier<BlockEntityType<PolisherBlockEntity>> POLISHER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("polisher_block_entity", () ->
                    BlockEntityType.Builder.of(PolisherBlockEntity::new,
                            ModBlocks.POLISHER.get()).build(null));

    public static final Supplier<BlockEntityType<SBlockEntity>> S_BLOCK_ENTITY =
                    BLOCK_ENTITIES.register("s_block_entity", () -> BlockEntityType.Builder.of(
                                    SBlockEntity::new,
                            ModBlocks.SBLOCK.get()).build(null));
/*
    public static final Supplier<BlockEntityType<DomBE>> DOM_BE =
            BLOCK_ENTITIES.register("dom", ()->
                    BlockEntityType.Builder.of(DomBE::new,
                            ModBlocks.DOM_BLOCK.get()).build(null));

        public static final Supplier<BlockEntityType<SubBE>> SUB_BE =
            BLOCK_ENTITIES.register("sub", ()->
                    BlockEntityType.Builder.of(SubBE::new,
                            ModBlocks.SUB_BLOCK.get()).build(null));
*/

/**
    /**public static final Supplier<BlockEntityType<VoidAltarBlockEntity>> VOID_ALTAR =
            BLOCK_ENTITIES.register("void_altar", ()->
                    BlockEntityType.Builder.of(VoidAltarBlockEntity::new,
                            ModBlocks.VOID_ALTAR.get()).build(null));

    public static final Supplier<BlockEntityType<VoidPedestalBlockEntity>> VOID_PEDESTAL =
            BLOCK_ENTITIES.register("void_pedestal", ()->
                    BlockEntityType.Builder.of(VoidPedestalBlockEntity::new,
                            ModBlocks.VOID_PEDESTAL.get()).build(null));
**/
    public static final Supplier<BlockEntityType<PedestalBlockEntity>> PEDESTAL =
            BLOCK_ENTITIES.register("pedestal", ()->
                    BlockEntityType.Builder.of(PedestalBlockEntity::new,
                            ModBlocks.PEDESTAL.get()).build(null));



    public static final Supplier<BlockEntityType<CrystallizerBlockEntity>> CRYSTALLIZER =
            BLOCK_ENTITIES.register("crystallizer", () -> BlockEntityType.Builder.of(
                    CrystallizerBlockEntity::new, ModBlocks.CRYSTALLIZER.get()).build(null));



    public static final Supplier<BlockEntityType<ModSignBlockEntity>> MOD_SIGN =
            BLOCK_ENTITIES.register("mod_sign", () ->
                    BlockEntityType.Builder.of(ModSignBlockEntity::new,
                            ModBlocks.DARK_SIGN.get(), ModBlocks.DARK_WALL_SIGN.get(),
                            ModBlocks.BLOOD_SIGN.get(), ModBlocks.BLOOD_WALL_SIGN.get(),
                            ModBlocks.VOID_SIGN.get(), ModBlocks.VOID_WALL_SIGN.get()
                            ).build(null));

    public static final Supplier<BlockEntityType<ModHangingSignBlockEntity>> MOD_HANGING_SIGN =
            BLOCK_ENTITIES.register("mod_hanging_sign", () ->
                    BlockEntityType.Builder.of(ModHangingSignBlockEntity::new,
                            ModBlocks.DARK_HANGING_SIGN.get(), ModBlocks.DARK_WALL_HANGING_SIGN.get(),
                                ModBlocks.BLOOD_HANGING_SIGN.get(), ModBlocks.BLOOD_WALL_HANGING_SIGN.get(),
                            ModBlocks.VOID_HANGING_SIGN.get(), ModBlocks.VOID_WALL_HANGING_SIGN.get()
                            ).build(null));

    //public static final Supplier<BlockEntityType<?>> CRATE_ABYSS = register("crate_abyss", ModBlocks.CRATE_ABYSS, AbyssalCrateEntity::new);

    private static Supplier<BlockEntityType<?>> register(String registryname, Supplier<Block> block,
                                                               BlockEntityType.BlockEntitySupplier<?> supplier) {
        return BLOCK_ENTITIES.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
    }

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
