package net.voidkin.voidkin.block;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.block.custom.*;
import net.voidkin.voidkin.block.custom.signs.ModHangingSignBlock;
import net.voidkin.voidkin.block.custom.signs.ModStandingSignBlock;
import net.voidkin.voidkin.block.custom.signs.ModWallHangingSignBlock;
import net.voidkin.voidkin.block.custom.signs.ModWallSignBlock;
import net.voidkin.voidkin.block.types.*;
import net.voidkin.voidkin.sounds.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.PushReaction;
import net.voidkin.voidkin.fluid.ModFluids;
import net.voidkin.voidkin.item.ModItems;
import net.voidkin.voidkin.particles.ModParticles;
import net.voidkin.voidkin.util.ModWoodTypes;
import net.voidkin.voidkin.worldgen.tree.ModTreeGrowers;

import java.util.Optional;
import java.util.function.Supplier;


public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Voidkin.MODID);

    public static final DeferredBlock<Block> DARK_SHARD_ORE = registerBlock("dark_shard_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(2.0f,2.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> DEEPSLATE_DARK_SHARD_ORE = registerBlock("deepslate_dark_shard_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(2.0f,2.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> NETHER_DARK_SHARD_ORE = registerBlock("nether_dark_shard_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(2.0f,2.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> DARKNESS_BLOCK = registerBlock("darkness_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW).strength(2.0f,2.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> RAW_DARKNESS_BLOCK = registerBlock("raw_darkness_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW).strength(2.0f,2.0f).requiresCorrectToolForDrops()));


    public static final DeferredBlock<Block> BCR = registerBlock("bchickenraw",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SLIME_BLOCK)));
    public static final DeferredBlock<Block> BCC = registerBlock("bchickencooked",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SLIME_BLOCK)));
    public static final DeferredBlock<Block> BPR = registerBlock("bporkchopraw",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SLIME_BLOCK)));
    public static final DeferredBlock<Block> BPC = registerBlock("bporkchopcooked",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SLIME_BLOCK)));
    public static final DeferredBlock<Block> BBR = registerBlock("bbeefraw",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SLIME_BLOCK)));
    public static final DeferredBlock<Block> BBC = registerBlock("bbeefcooked",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SLIME_BLOCK)));
    public static final DeferredBlock<Block> BMR = registerBlock("bmuttonraw",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SLIME_BLOCK)));
    public static final DeferredBlock<Block> BMC = registerBlock("bmuttoncooked",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SLIME_BLOCK)));


    public static final DeferredBlock<Block> DEATH_BLOCK = registerBlock("death_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW)));
//CACTUS
    public static final DeferredBlock<Block> ANTI_CACTUS = registerBlock("anti_cactus",
            ()-> new CactusBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CACTUS)));
    public static final DeferredBlock<Block> DARK_CACTUS = registerBlock("dark_cactus",
            () -> new CactusBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CACTUS)));
    public static final DeferredBlock<Block> END_CACTUS = registerBlock("end_cactus",
            () -> new CactusBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CACTUS)));

    /**public static final DeferredBlock<Block> POTTED_ANTI_CACTUS = BLOCKS.register("potted_anti_cactus",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.ANTI_CACTUS,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_CACTUS).noOcclusion()));
    public static final DeferredBlock<Block> POTTED_DARK_CACTUS = BLOCKS.register("potted_dark_cactus",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.DARK_CACTUS,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_CACTUS).noOcclusion()));
    public static final DeferredBlock<Block> POTTED_END_CACTUS = BLOCKS.register("potted_end_cactus",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.END_CACTUS,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_CACTUS).noOcclusion()))**/;


//DARK WOOD STUFF
    public static final DeferredBlock<Block> DARK_SAPLING = registerBlock("dark_sapling", () -> new SaplingBlock(ModTreeGrowers.DARK, BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_SAPLING)));
    public static final DeferredBlock<Block> DARK_LOG = registerBlock("dark_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_LOG).requiresCorrectToolForDrops().strength(5.0F)));
    public static final DeferredBlock<Block> DARK_WOOD = registerBlock("dark_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_WOOD).requiresCorrectToolForDrops().strength(5.0F)));
    public static final DeferredBlock<Block> DARK_LEAVES = registerBlock("dark_leaves", () -> new DeathLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_LEAVES)){
        //public static final DeferredBlock<Block> DARK_LEAVES2 = registerBlock("dark_leaves2", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_LEAVES)){
        @Override
        public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
            super.animateTick(blockState, level, blockPos, randomSource);
            if (randomSource.nextInt(4) == 0) {
                BlockPos blockpos = blockPos.below();
                BlockState blockstate = level.getBlockState(blockpos);
                if (!isFaceFull(blockstate.getCollisionShape(level, blockpos), Direction.UP)) {
                    ParticleUtils.spawnParticleBelow(level, blockPos, randomSource, ParticleTypes.CHERRY_LEAVES);
                    ParticleUtils.spawnParticleBelow(level, blockPos, randomSource, ParticleTypes.SOUL_FIRE_FLAME);
                }
            }
        }
    });

    public static final DeferredBlock<Block> STRIPPED_DARK_LOG = registerBlock("stripped_dark_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_DARK_OAK_LOG).requiresCorrectToolForDrops().strength(5.0F)));
    public static final DeferredBlock<Block> STRIPPED_DARK_WOOD = registerBlock("stripped_dark_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_DARK_OAK_WOOD).requiresCorrectToolForDrops().strength(5.0F)));
    public static final DeferredBlock<Block> DARK_PLANKS = registerBlock("dark_planks", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_PLANKS).requiresCorrectToolForDrops().strength(5.0F)));


    public static final DeferredBlock<Block> DARK_STAIRS = registerBlock("dark_stairs",
            () -> new StairBlock(ModBlocks.DARK_PLANKS.get().defaultBlockState(),
            BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_STAIRS) ));

    public static final DeferredBlock<Block> DARK_SLAB = registerBlock("dark_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_SLAB) ));

    public static final DeferredBlock<Block> DARK_BUTTON = registerBlock("dark_button",
            () -> new ButtonBlock(BlockSetType.DARK_OAK, 10, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BUTTON).sound(SoundType.WOOD) ));
    public static final DeferredBlock<Block> DARK_PRESSURE_PLATE = registerBlock("dark_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.DARK_OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> DARK_FENCE = registerBlock("dark_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_FENCE) ));
    public static final DeferredBlock<Block> DARK_FENCE_GATE = registerBlock("dark_fence_gate",
            () -> new FenceGateBlock(Optional.ofNullable(ModWoodTypes.DARK),BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.WOOD), Optional.of(SoundEvents.ANVIL_PLACE), Optional.of(SoundEvents.ANVIL_BREAK)));
    public static final DeferredBlock<Block> DARK_WALL = registerBlock("dark_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> DARK_DOOR = registerBlock("dark_door",
            () -> new DoorBlock(BlockSetType.DARK_OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.WOOD).noOcclusion()));
    public static final DeferredBlock<Block> DARK_TRAPDOOR = registerBlock("dark_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.DARK_OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.WOOD).noOcclusion()));


//BLOOD WOOD STUFF
    public static final DeferredBlock<Block> BLOOD_SAPLING = registerBlock("blood_sapling", () -> new SaplingBlock(ModTreeGrowers.BLOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_SAPLING)));
    public static final DeferredBlock<Block> BLOOD_LOG = registerBlock("blood_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_LOG).requiresCorrectToolForDrops().strength(5.0F)));
    public static final DeferredBlock<Block> BLOOD_WOOD = registerBlock("blood_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_WOOD).requiresCorrectToolForDrops().strength(5.0F)));
    public static final DeferredBlock<Block> STRIPPED_BLOOD_LOG = registerBlock("stripped_blood_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_DARK_OAK_LOG).requiresCorrectToolForDrops().strength(5.0F)));
    public static final DeferredBlock<Block> STRIPPED_BLOOD_WOOD = registerBlock("stripped_blood_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_DARK_OAK_WOOD).requiresCorrectToolForDrops().strength(5.0F)));
    public static final DeferredBlock<Block> BLOOD_PLANKS = registerBlock("blood_planks", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_PLANKS).requiresCorrectToolForDrops().strength(5.0F)));
    public static final DeferredBlock<Block> BLOOD_LEAVES = registerBlock("blood_leaves", () -> new DeathLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_LEAVES)));

    public static final DeferredBlock<Block> BLOOD_STAIRS = registerBlock("blood_stairs",
            () -> new StairBlock(ModBlocks.BLOOD_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_STAIRS) ));
    public static final DeferredBlock<Block> BLOOD_SLAB = registerBlock("blood_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_SLAB) ));
    public static final DeferredBlock<Block> BLOOD_DOOR = registerBlock("blood_door",
            ()-> new DoorBlock(BlockSetType.DARK_OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_DOOR)));

    public static final DeferredBlock<Block> BLOOD_BUTTON = registerBlock("blood_button",
            () -> new ButtonBlock(BlockSetType.DARK_OAK,10,BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BUTTON).sound(SoundType.WOOD)
                     ));
    public static final DeferredBlock<Block> BLOOD_PRESSURE_PLATE = registerBlock("blood_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.DARK_OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.WOOD)
                    ));

    public static final DeferredBlock<Block> BLOOD_FENCE = registerBlock("blood_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_FENCE) ));
    public static final DeferredBlock<Block> BLOOD_FENCE_GATE = registerBlock("blood_fence_gate",
            () -> new FenceGateBlock(Optional.ofNullable(ModWoodTypes.BLOOD),BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_FENCE_GATE).sound(SoundType.WOOD), Optional.of(SoundEvents.ANVIL_PLACE), Optional.of(SoundEvents.ANVIL_BREAK)));
    public static final DeferredBlock<Block> BLOOD_WALL = registerBlock("blood_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> BLOOD_TRAPDOOR = registerBlock("blood_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.DARK_OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.WOOD).noOcclusion()));

//VOID WOOD STUFF
    public static final DeferredBlock<Block> VOID_SAPLING = registerBlock("void_sapling", () -> new SaplingBlock(ModTreeGrowers.VOID, BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_SAPLING)));
    public static final DeferredBlock<Block> VOID_LOG = registerBlock("void_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_LOG).requiresCorrectToolForDrops().strength(5.0F)));
    public static final DeferredBlock<Block> VOID_WOOD = registerBlock("void_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_WOOD).requiresCorrectToolForDrops().strength(5.0F)));
    public static final DeferredBlock<Block> STRIPPED_VOID_LOG = registerBlock("stripped_void_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_DARK_OAK_LOG).requiresCorrectToolForDrops().strength(5.0F)));
    public static final DeferredBlock<Block> STRIPPED_VOID_WOOD = registerBlock("stripped_void_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_DARK_OAK_WOOD).requiresCorrectToolForDrops().strength(5.0F)));
    public static final DeferredBlock<Block> VOID_PLANKS = registerBlock("void_planks", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_PLANKS).requiresCorrectToolForDrops().strength(5.0F)));
    public static final DeferredBlock<Block> VOID_LEAVES = registerBlock("void_leaves", () -> new DeathLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_LEAVES)){

        public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
            super.animateTick(blockState, level, blockPos, randomSource);
            if (randomSource.nextInt(4) == 0) {
                BlockPos blockpos = blockPos.below();
                BlockState blockstate = level.getBlockState(blockpos);
                if (!isFaceFull(blockstate.getCollisionShape(level, blockpos), Direction.UP)) {
                    ParticleUtils.spawnParticleBelow(level, blockPos, randomSource, ParticleTypes.DRAGON_BREATH);
                    //ParticleUtils.spawnParticleBelow(level, blockPos, randomSource, ParticleTypes.SOUL_FIRE_FLAME);
                }
            }
        }
    });

    public static final DeferredBlock<Block> VOID_STAIRS = registerBlock("void_stairs",
            () -> new StairBlock(ModBlocks.VOID_PLANKS.get().defaultBlockState(),                    BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_STAIRS) ));
    public static final DeferredBlock<Block> VOID_SLAB = registerBlock("void_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_SLAB) ));
    public static final DeferredBlock<Block> VOID_DOOR = registerBlock("void_door",
            ()-> new DoorBlock(BlockSetType.DARK_OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_DOOR)));

    public static final DeferredBlock<Block> VOID_BUTTON = registerBlock("void_button",
            () -> new ButtonBlock(BlockSetType.DARK_OAK, 10,BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BUTTON).sound(SoundType.WOOD)
                    ));
    public static final DeferredBlock<Block> VOID_PRESSURE_PLATE = registerBlock("void_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.DARK_OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> VOID_FENCE = registerBlock("void_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_FENCE) ));
    public static final DeferredBlock<Block> VOID_FENCE_GATE = registerBlock("void_fence_gate",
            () -> new FenceGateBlock(Optional.ofNullable(ModWoodTypes.VOID),BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.WOOD), Optional.of(SoundEvents.ANVIL_PLACE), Optional.of(SoundEvents.ANVIL_BREAK)));
    public static final DeferredBlock<Block> VOID_WALL = registerBlock("void_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> VOID_TRAPDOOR = registerBlock("void_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.DARK_OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.WOOD).noOcclusion()));





    //BUCKETS
    public static final DeferredBlock<LiquidBlock> SOAP_WATER_BLOCK = registerBlock("soap_water_block",
            () -> new LiquidBlock(ModFluids.SOURCE_SOAP_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable()));
    public static final DeferredBlock<LiquidBlock> ENDER_BLOOD_BLOCK = registerBlock("ender_blood_block",
            () -> new LiquidBlock(ModFluids.SOURCE_ENDER_BLOOD.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable()));
    public static final DeferredBlock<LiquidBlock> BLOOD_BLOCK = registerBlock("blood_block",
            () -> new LiquidBlock(ModFluids.SOURCE_BLOOD.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable()));
    public static final DeferredBlock<LiquidBlock> DARK_ESSENCE_BLOCK = registerBlock("dark_essence_block",
            () -> new LiquidBlock(ModFluids.SOURCE_DARK_ESSENCE.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable().pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<LiquidBlock> DEITY_BLOOD_BLOCK = registerBlock("deity_blood_block",
            () -> new LiquidBlock(ModFluids.SOURCE_DEITY_BLOOD.get(), BlockBehaviour.Properties.of().noLootTable().pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<LiquidBlock> VOID_LAVA_BLOCK = registerBlock("void_lava_block",
            () -> new LiquidBlock(ModFluids.SOURCE_VOID_LAVA.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable()));
    /*public static final DeferredBlock<LiquidBlock> ESSENCE_BLOCK = registerBlock("dark_essence_block",
            () -> new LiquidBlock(ModFluids.SOURCE_ESSENCE, BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable()));*/

    public static final DeferredBlock<Block> BLOOD_COAGULATED = registerBlock("blood_coagulated",
            ()-> new CoagulatedBloodBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> BLOOD_CONGEALED = registerBlock("blood_congealed",
            ()-> new CongealedBloodBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> BLEEDING_DEITY_BLOCK = registerBlock("bleeding_deity_block",
            ()-> new DeityBloodBlock(BlockBehaviour.Properties.of()));


//DARK BIOME STUFF
    public static final DeferredBlock<Block> DARK_GRASS = registerBlock("dark_grass_block",
            ()-> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK)));
    public static final DeferredBlock<Block> DARK_DIRT = registerBlock("dark_dirt",
            ()-> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)));
    public static final DeferredBlock<Block> DARK_STONE = registerBlock("dark_stone",
            ()-> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> DARK_COBBLESTONE = registerBlock("dark_cobblestone",
            ()-> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> BLOOD_STONE = registerBlock("blood_stone",
            ()-> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> BLOOD_COBBLESTONE = registerBlock("blood_cobblestone",
            ()-> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> VOID_STONE = registerBlock("void_stone",
            ()-> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> VOID_COBBLESTONE = registerBlock("void_cobblestone",
            ()-> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));



//SIGNS
    public static final DeferredBlock<Block> DARK_SIGN = BLOCKS.register("dark_sign",
            () -> new ModStandingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN), ModWoodTypes.DARK));
    public static final DeferredBlock<Block> DARK_WALL_SIGN = BLOCKS.register("dark_wall_sign",
            () -> new ModWallSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN), ModWoodTypes.DARK));
    public static final DeferredBlock<Block> DARK_HANGING_SIGN = BLOCKS.register("dark_hanging_sign",
            () -> new ModHangingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN), ModWoodTypes.DARK));
    public static final DeferredBlock<Block> DARK_WALL_HANGING_SIGN = BLOCKS.register("dark_wall_hanging_sign",
            () -> new ModWallHangingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN), ModWoodTypes.DARK));

    public static final DeferredBlock<Block> BLOOD_SIGN = BLOCKS.register("blood_sign",
            () -> new ModStandingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN), ModWoodTypes.BLOOD));
    public static final DeferredBlock<Block> BLOOD_WALL_SIGN = BLOCKS.register("blood_wall_sign",
            () -> new ModWallSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN), ModWoodTypes.BLOOD));
    public static final DeferredBlock<Block> BLOOD_HANGING_SIGN = BLOCKS.register("blood_hanging_sign",
            () -> new ModHangingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN), ModWoodTypes.BLOOD));
    public static final DeferredBlock<Block> BLOOD_WALL_HANGING_SIGN = BLOCKS.register("blood_wall_hanging_sign",
            () -> new ModWallHangingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN), ModWoodTypes.BLOOD));

    public static final DeferredBlock<Block> VOID_SIGN = BLOCKS.register("void_sign",
            () -> new ModStandingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN), ModWoodTypes.BLOOD));
    public static final DeferredBlock<Block> VOID_WALL_SIGN = BLOCKS.register("void_wall_sign",
            () -> new ModWallSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN), ModWoodTypes.BLOOD));
    public static final DeferredBlock<Block> VOID_HANGING_SIGN = BLOCKS.register("void_hanging_sign",
            () -> new ModHangingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN), ModWoodTypes.BLOOD));
    public static final DeferredBlock<Block> VOID_WALL_HANGING_SIGN = BLOCKS.register("void_wall_hanging_sign",
            () -> new ModWallHangingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN), ModWoodTypes.BLOOD));
    /*public static final DeferredBlock<Block> VOID_CHEST = registerBlock("void_chest",
            ()-> new ModChestBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST),))
*/


    //TORCHS

    public static final DeferredBlock<Block> VOID_TORCH = BLOCKS.register("void_torch",
            ()-> new ModTorchBlock(BlockBehaviour.Properties.of(), ParticleTypes.SOUL_FIRE_FLAME){
        @Override
        public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
            double d0 = (double)pPos.getX() + 0.5D;
            double d1 = (double)pPos.getY() + 0.7D;
            double d2 = (double)pPos.getZ() + 0.5D;
            //pLevel.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            pLevel.addParticle(ModParticles.VOID_FLAME.get(), d0, d1, d2, 0.0D, -0.05D, 0.0D);
            //pLevel.addParticle(ModParticles.VOID_FLAME.get(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
            });

    public static final DeferredBlock<Block> VOID_WALL_TORCH = BLOCKS.register("void_torch_wall",
            ()-> new ModWallTorchBlock(BlockBehaviour.Properties.of(), ParticleTypes.SOUL_FIRE_FLAME) {
                @Override
                public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
                    double d0 = (double) pPos.getX() + 0.5D;
                    double d1 = (double) pPos.getY() + 0.7D;
                    double d2 = (double) pPos.getZ() + 0.5D;
                    //pLevel.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
                    pLevel.addParticle(ModParticles.VOID_FLAME.get(), d0, d1, d2, 0.0D, -0.05D, 0.0D);
                    //pLevel.addParticle(ModParticles.VOID_FLAME.get(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
                }
            });

    public static final DeferredBlock<Block> D_TORCH = BLOCKS.register("d_torch",
            ()-> new ModTorchBlock(BlockBehaviour.Properties.of(), ParticleTypes.SOUL){
                @Override
                public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
                    double d0 = (double)pPos.getX() + 0.5D;
                    double d1 = (double)pPos.getY() + 0.7D;
                    double d2 = (double)pPos.getZ() + 0.5D;
                    //pLevel.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
                    pLevel.addParticle(ModParticles.ANTI_VOID.get(), d0, d1, d2, 0.0D, -0.05D, 0.0D);
                    //pLevel.addParticle(ModParticles.VOID_FLAME.get(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
                }
            });

    public static final DeferredBlock<Block> D_WALL_TORCH = BLOCKS.register("d_torch_wall",
            ()-> new ModWallTorchBlock(BlockBehaviour.Properties.of(), ParticleTypes.SOUL) {
                @Override
                public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
                    double d0 = (double) pPos.getX() + 0.5D;
                    double d1 = (double) pPos.getY() + 0.7D;
                    double d2 = (double) pPos.getZ() + 0.5D;
                    //pLevel.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
                    pLevel.addParticle(ModParticles.ANTI_VOID.get(), d0, d1, d2, 0.0D, -0.05D, 0.0D);
                    //pLevel.addParticle(ModParticles.VOID_FLAME.get(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
                }
            });

    public static final DeferredBlock<Block> DRIPPER =
            registerBlock("dripper",
                    ()-> new BloodDripBlock(BlockBehaviour.Properties.of().randomTicks()));

    public static final DeferredBlock<Block> CRYSTALLIZER = registerBlock("crystallizer",
            () -> new CrystallizerBlock(BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops().randomTicks()));


    //ModParticles.VOID_FLAME.get()));

    /**public static final DeferredBlock<Block> ABYSSAL_CONTAINER = registerBlock("abyssal_container",
              ()-> new AbyssalContainer(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST),AbyssalContainerEntity::new));**/

    /**
     public static final DeferredBlock<CrusherBlock> CRUSHER = registerBlock("crusher",
     () -> new CrusherBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(5f, 20f)));
    **/
    /**
     public static final DeferredBlock<ExampleAdvancedBlock> EXAMPLE_ADVANCED_BLOCK = BLOCKS.register("example_advanced_block",
     () -> new ExampleAdvancedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ANVIL)
     //.mapColor(MapColor.TERRACOTTA_YELLOW)
     .strength(5.0f, 15f)
     ));
     **/

    //public static final DeferredBlock<Block> MOB_SLAYER = BLOCKS.register("mob_slayer",
    //() -> new MobSlayerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));**/
    public static final DeferredBlock<Block> SOUND_BLOCK = registerBlock("sound_block",
            ()-> new BloodDripBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(ModSounds.SOUND_BLOCK_SOUNDS).randomTicks()));


//PLANTS
    public static final DeferredBlock<Block> CATMINT = registerBlock("catmint",
            () -> new FlowerBlock(MobEffects.LUCK, 5,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.ALLIUM).noOcclusion().noCollission()));
    public static final DeferredBlock<Block> POTTED_CATMINT = BLOCKS.register("potted_catmint",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.CATMINT,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.ALLIUM).noOcclusion().noCollission()));
    public static final DeferredBlock<Block> LOTUS = registerBlock("lotus",
            ()-> new FlowerBlock(MobEffects.ABSORPTION, 5,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.ALLIUM).noOcclusion().noCollission()));
    public static final DeferredBlock<Block> POTTED_LOTUS = BLOCKS.register("potted_lotus",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.LOTUS,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion()));


    //public static final DeferredBlock<DeathPortalBlock> DARKSIDE_PORTAL = registerBlock("darkside_portal", () -> new DeathPortalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_PORTAL).noLootTable()));


//FUNCTIONAL BLOCKS
    //public static final DeferredBlock<Block> CANDY_CANE_FURNACE = registerBlock("candy_cane_furnace", () -> new CandyCaneFurnaceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> SBLOCK = registerBlock("sblock",() -> new BlockS(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> POLISHER = registerBlock("polisher",() -> new PolisherBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion()));

    //public static final DeferredBlock<Block> DOM_BLOCK = registerBlock("dom_block",() -> new DomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion()));
    //public static final DeferredBlock<Block> SUB_BLOCK = registerBlock("sub_block",() -> new SubBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion()));
    /**
    public static final DeferredBlock<Block> VOID_ALTAR = registerBlock("void_altar",() -> new VoidAltarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion()));
    public static final DeferredBlock<Block> VOID_PEDESTAL = registerBlock("void_pedestal",() -> new VoidPedestalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion()));
**/
    public static final DeferredBlock<Block> PEDESTAL = registerBlock("pedestal",() -> new PedestalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion()));

    //public static final DeferredBlock<Block> SPECIAL_FURNACE = registerBlock("special_furnace",() -> new SpecialFurnaceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion()));
    //public static final DeferredBlock<Block> ABYSSALBOX = registerBlock("abyssal_container",()-> new AbyssalContainer(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));

    public static final DeferredBlock<Block> DEATH_PORTAL = registerBlock("deathportal",
            () -> new DeathPortalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_PORTAL).noLootTable().noOcclusion().noCollission()));




    //public static final DeferredBlock<Block> CRATE_ABYSS = BLOCKS.register("crate_ebony", () -> new AbyssalCrate());

    //public static final DeferredBlock<AbyssalChestBlock> CHEST = registerBlock("d_chest",
   //         ()-> new AbyssalChestBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST), AbyssalChestBlock::new));

    

/*
    public static final DeferredBlock<Block> DARK_CHEST = BLOCKS.register("dark_chest",
            ()-> new ChestBlock(BlockBehaviour.Properties.of()));*/



    private static FlowerPotBlock flowerPot(Block p_278261_, FeatureFlag... p_278322_) {
        BlockBehaviour.Properties blockbehaviour$properties = BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY);
        if (p_278322_.length > 0) {
            blockbehaviour$properties = blockbehaviour$properties.requiredFeatures(p_278322_);
        }

        return new FlowerPotBlock(p_278261_, blockbehaviour$properties);
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, Supplier<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void register(IEventBus eventbus) {BLOCKS.register(eventbus);}
    //public static void registerBlockItems(IEventBus eventbus) {BLOCKITEMS.register(eventbus);}
}



