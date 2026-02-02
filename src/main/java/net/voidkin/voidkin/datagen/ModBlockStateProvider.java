package net.voidkin.voidkin.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.voidkin.voidkin.Voidkin;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;

import net.voidkin.voidkin.block.ModBlocks;

import java.util.function.Function;

//@EventBusSubscriber(modid= Voidkin.MODID,bus=EventBusSubscriber.Bus.MOD)
public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Voidkin.MODID, exFileHelper);
    }
    @SubscribeEvent
    @Override
    protected void registerStatesAndModels() {
        /*horizontalBlock(ModBlocks.CRYSTALLIZER.get(), models().orientable("voidkin:crystallizer",
                mcLoc("block/blast_furnace_side"),
                modLoc("block/crystallizer_front"),
                mcLoc("block/blast_furnace_top")));*/
        blockWithItem(ModBlocks.BLEEDING_DEITY_BLOCK);

        blockItem(ModBlocks.CRYSTALLIZER);

        /**blockWithItem(ModBlocks.DEATH_BLOCK);
        blockWithItem(ModBlocks.DEATH_PORTAL);**/
//ORES
        /**blockWithItem(ModBlocks.DARK_SHARD_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_DARK_SHARD_ORE);
        blockWithItem(ModBlocks.NETHER_DARK_SHARD_ORE);
        blockWithItem(ModBlocks.DARKNESS_BLOCK);
        blockWithItem(ModBlocks.RAW_DARKNESS_BLOCK);**/
//FOOD BLOCKS
        /**blockWithItem(ModBlocks.BBR);
        blockWithItem(ModBlocks.BBC);
        blockWithItem(ModBlocks.BCR);
        blockWithItem(ModBlocks.BCC);
        blockWithItem(ModBlocks.BMR);
        blockWithItem(ModBlocks.BMC);
        blockWithItem(ModBlocks.BPR);
        blockWithItem(ModBlocks.BPC);**/
//DARK BIOME STUFF
        //blockWithItem(ModBlocks.DARK_GRASS);
       /** blockWithItem(ModBlocks.DARK_DIRT);
        blockWithItem(ModBlocks.DARK_STONE);
        blockWithItem(ModBlocks.DARK_COBBLESTONE);
        blockWithItem(ModBlocks.BLOOD_STONE);
        blockWithItem(ModBlocks.BLOOD_COBBLESTONE);

        blockWithItem(ModBlocks.SOUND_BLOCK);
        blockWithItem(ModBlocks.DARK_PLANKS);
        blockWithItem(ModBlocks.BLOOD_PLANKS);**/
       //DARK WOOD STUFF
    /**
        stairsBlock(((StairBlock) ModBlocks.DARK_STAIRS.get()), blockTexture(ModBlocks.DARK_PLANKS.get()));
        slabBlock(((SlabBlock) ModBlocks.DARK_SLAB.get()), blockTexture(ModBlocks.DARK_PLANKS.get()), blockTexture(ModBlocks.DARK_PLANKS.get()));

        buttonBlock(((ButtonBlock) ModBlocks.DARK_BUTTON.get()), blockTexture(ModBlocks.DARK_PLANKS.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.DARK_PRESSURE_PLATE.get()), blockTexture(ModBlocks.DARK_PLANKS.get()));

        fenceBlock(((FenceBlock) ModBlocks.DARK_FENCE.get()), blockTexture(ModBlocks.DARK_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.DARK_FENCE_GATE.get()), blockTexture(ModBlocks.DARK_PLANKS.get()));
        wallBlock(((WallBlock) ModBlocks.DARK_WALL.get()), blockTexture(ModBlocks.DARK_PLANKS.get()));

        doorBlockWithRenderType(((DoorBlock) ModBlocks.DARK_DOOR.get()), modLoc("block/dark_door_bottom"), modLoc("block/dark_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.DARK_TRAPDOOR.get()), modLoc("block/dark_trapdoor"), true, "cutout");
**/
//BLOOD WOOD STUFF
        /**
        stairsBlock(((StairBlock) ModBlocks.BLOOD_STAIRS.get()), blockTexture(ModBlocks.BLOOD_PLANKS.get()));
        slabBlock(((SlabBlock) ModBlocks.BLOOD_SLAB.get()), blockTexture(ModBlocks.BLOOD_PLANKS.get()), blockTexture(ModBlocks.BLOOD_PLANKS.get()));

        buttonBlock(((ButtonBlock) ModBlocks.BLOOD_BUTTON.get()), blockTexture(ModBlocks.BLOOD_PLANKS.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.BLOOD_PRESSURE_PLATE.get()), blockTexture(ModBlocks.BLOOD_PLANKS.get()));

        fenceBlock(((FenceBlock) ModBlocks.BLOOD_FENCE.get()), blockTexture(ModBlocks.BLOOD_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.BLOOD_FENCE_GATE.get()), blockTexture(ModBlocks.BLOOD_PLANKS.get()));
        wallBlock(((WallBlock) ModBlocks.BLOOD_WALL.get()), blockTexture(ModBlocks.BLOOD_PLANKS.get()));

        doorBlockWithRenderType(((DoorBlock) ModBlocks.BLOOD_DOOR.get()), modLoc("block/blood_door_bottom"), modLoc("block/blood_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.BLOOD_TRAPDOOR.get()), modLoc("block/blood_trapdoor"), true, "cutout");
**/



 //makeStrawberryCrop((CropBlock) ModBlocks.STRAWBERRY_CROP.get(), "strawberry_stage", "strawberry_stage");
 //makeCornCrop(((CropBlock) ModBlocks.CORN_CROP.get()), "corn_stage_", "corn_stage_");


/**
    simpleBlockWithItem(ModBlocks.CATMINT.get(), models().cross(blockTexture(ModBlocks.CATMINT.get()).getPath(),
            blockTexture(ModBlocks.CATMINT.get())).renderType("cutout"));
    simpleBlockWithItem(ModBlocks.POTTED_CATMINT.get(), models().singleTexture("potted_catmint", ResourceLocation.parse("flower_pot_cross"), "plant",
            blockTexture(ModBlocks.CATMINT.get())).renderType("cutout"));

    simpleBlockWithItem(ModBlocks.LOTUS.get(), models().cross(blockTexture(ModBlocks.LOTUS.get()).getPath(),
            blockTexture(ModBlocks.LOTUS.get())).renderType("cutout"));
    simpleBlockWithItem(ModBlocks.POTTED_LOTUS.get(), models().singleTexture("potted_lotus", ResourceLocation.parse("flower_pot_cross"), "plant",
            blockTexture(ModBlocks.LOTUS.get())).renderType("cutout"));
**/


    //simpleBlockWithItem(ModBlocks.POLISHER.get(), new ModelFile.UncheckedModelFile(modLoc("block/polisher")));
    //simpleBlockWithItem(ModBlocks.VOID_ALTAR.get(), new ModelFile.UncheckedModelFile(modLoc("block/void_altar")));
//    simpleBlock(ModBlocks.AB.get(), new ModelFile.UncheckedModelFile(modLoc("block/void_altar")));
    //blockItem(ModBlocks.BLOOD_CONGEALED);
    //blockItem(ModBlocks.BLOOD_COAGULATED);

/**
        //DARK WOOD
        saplingBlock(ModBlocks.DARK_SAPLING);
        blockItem(ModBlocks.DARK_LOG);
        blockItem(ModBlocks.DARK_WOOD);
        blockItem(ModBlocks.STRIPPED_DARK_LOG);
        blockItem(ModBlocks.STRIPPED_DARK_WOOD);
        leavesBlock(ModBlocks.DARK_LEAVES);
        logBlock(((RotatedPillarBlock) ModBlocks.DARK_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.DARK_WOOD.get()), blockTexture(ModBlocks.DARK_LOG.get()), blockTexture(ModBlocks.DARK_LOG.get()));

        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_DARK_LOG.get()), blockTexture(ModBlocks.STRIPPED_DARK_LOG.get()),
                ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "block/stripped_dark_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_DARK_WOOD.get()), blockTexture(ModBlocks.STRIPPED_DARK_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_DARK_LOG.get()));
        signBlock((StandingSignBlock) ModBlocks.DARK_SIGN.get(), (WallSignBlock) ModBlocks.DARK_WALL_SIGN.get(),
                blockTexture(ModBlocks.DARK_PLANKS.get()));
        hangingSignBlock(ModBlocks.DARK_HANGING_SIGN.get(), ModBlocks.DARK_WALL_HANGING_SIGN.get(), blockTexture(ModBlocks.DARK_PLANKS.get()));

        //BLOOD WOOD
        saplingBlock(ModBlocks.BLOOD_SAPLING);
        blockItem(ModBlocks.BLOOD_LOG);
        blockItem(ModBlocks.BLOOD_WOOD);
        blockItem(ModBlocks.STRIPPED_BLOOD_LOG);
        blockItem(ModBlocks.STRIPPED_BLOOD_WOOD);
        leavesBlock(ModBlocks.BLOOD_LEAVES);
        logBlock(((RotatedPillarBlock) ModBlocks.BLOOD_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.BLOOD_WOOD.get()), blockTexture(ModBlocks.BLOOD_LOG.get()), blockTexture(ModBlocks.BLOOD_LOG.get()));

        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_BLOOD_LOG.get()), blockTexture(ModBlocks.STRIPPED_BLOOD_LOG.get()),
                ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "block/stripped_blood_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_BLOOD_WOOD.get()), blockTexture(ModBlocks.STRIPPED_BLOOD_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_BLOOD_LOG.get()));
        signBlock(((StandingSignBlock) ModBlocks.BLOOD_SIGN.get()), ((WallSignBlock) ModBlocks.BLOOD_WALL_SIGN.get()),
                blockTexture(ModBlocks.BLOOD_PLANKS.get()));
        hangingSignBlock(ModBlocks.BLOOD_HANGING_SIGN.get(), ModBlocks.BLOOD_WALL_HANGING_SIGN.get(), blockTexture(ModBlocks.BLOOD_PLANKS.get()));
**/
    }



    /*public void hangingSignBlock(Block signBlock, Block wallSignBlock, ResourceLocation texture) {
        ModelFile sign = models().sign(name(signBlock), texture);
        hangingSignBlock(signBlock, wallSignBlock, sign);
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ModelFile sign) {
        simpleBlock(signBlock, sign);
        simpleBlock(wallSignBlock, sign);
    }
    private String name(Block block) {
        return key(block).getPath();
    }
*/
    private void saplingBlock(DeferredBlock<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void leavesBlock(DeferredBlock<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }
/**
    public void makeBush(SweetBerryBushBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().cross(modelName + state.getValue(GojiBerryBushBlock.AGE),
                ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "block/" + textureName + state.getValue(GojiBerryBushBlock.AGE))).renderType("cutout"));

        return models;
    }

    public void makeCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((RadishCropBlock) block).getAgeProperty()),
                ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "block/" + textureName + state.getValue(((RadishCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }
**/

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("voidkin:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("voidkin:block/" + deferredBlock.getId().getPath() + appendix));
    }


}
