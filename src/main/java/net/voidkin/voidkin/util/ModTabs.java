package net.voidkin.voidkin.util;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.voidkin.voidkin.Voidkin;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.voidkin.voidkin.block.ModBlocks;
import net.voidkin.voidkin.item.ModItems;

import java.util.function.Supplier;

public class ModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Voidkin.MODID);


    public static final Supplier<CreativeModeTab> PSYCHO_TAB = CREATIVE_MODE_TABS.register("psycho_tab",
            ()-> CreativeModeTab.builder().icon(()->new ItemStack(ModItems.SMILE.get()))
                    .title(Component.translatable("creativetab.psycho_tab"))
                    .displayItems((pParameters, pOutput) ->{
                        pOutput.accept(ModBlocks.SBLOCK.get());
                        pOutput.accept(ModItems.FROSTBOOTS.get());
                        pOutput.accept(ModItems.CHAINSAW.get());
                        pOutput.accept(ModItems.BAR_BRAWL_MUSIC_DISC.get());
                        pOutput.accept(ModItems.DAISIES_MUSIC_DISC.get());
                        pOutput.accept(ModItems.GRAVITY_MUSIC_DISC.get());
                        pOutput.accept(ModItems.UPSIDE_DOWN_MUSIC_DISC.get());
                        pOutput.accept(ModItems.LOSER_BABY_MUSIC_DISC.get());
                        pOutput.accept(ModItems.LOSIN_STREAK_MUSIC_DISC.get());
                        pOutput.accept(ModItems.HUSK_LOVE_MUSIC_DISC.get());
                        pOutput.accept(ModItems.ALASTOR_MASHUP_MUSIC_DISC.get());
                        pOutput.accept(ModItems.BRIGHTER_MUSIC_DISC.get());
                        //pOutput.accept(ModBlocks.CRYSTALLIZER.get());
                        //pOutput.accept(ModBlocks.VOID_ALTAR.get());
                    })
                    .build());
    public static final Supplier<CreativeModeTab> DEATH_TAB = CREATIVE_MODE_TABS.register("death_tab",
            ()-> CreativeModeTab.builder().icon(()->new ItemStack(ModItems.DARK_SHARD.get()))
                    .title(Component.translatable("creativetab.voidless_tab"))
                    .displayItems((pParameters, pOutput) ->{
                        pOutput.accept(ModBlocks.DEATH_BLOCK.get());
                        pOutput.accept(ModItems.FIRE_CHARM.get());
//ORES AND BLOCKS OF ORES
                        pOutput.accept(ModItems.RAW_DARK_SHARD.get());
                        pOutput.accept(ModItems.DARK_SHARD.get());
                        pOutput.accept(ModBlocks.DARK_SHARD_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_DARK_SHARD_ORE.get());
                        pOutput.accept(ModBlocks.NETHER_DARK_SHARD_ORE.get());
                        pOutput.accept(ModBlocks.RAW_DARKNESS_BLOCK.get());
                        pOutput.accept(ModBlocks.DARKNESS_BLOCK.get());

//FOOD BLOCKS
                        pOutput.accept(ModBlocks.BBR.get());
                        pOutput.accept(ModBlocks.BBC.get());
                        pOutput.accept(ModBlocks.BPR.get());
                        pOutput.accept(ModBlocks.BPC.get());
                        pOutput.accept(ModBlocks.BMR.get());
                        pOutput.accept(ModBlocks.BMC.get());
                        pOutput.accept(ModBlocks.BCR.get());
                        pOutput.accept(ModBlocks.BCC.get());


//ARMORMENTS
                        pOutput.accept(ModItems.REGROWTHAXE.get());

                        pOutput.accept(ModItems.CACTUS_SWORD.get());
                        pOutput.accept(ModItems.ANTI_CACTUS_SWORD.get());
                        pOutput.accept(ModItems.BLOOD_CACTUS_SWORD.get());
                        pOutput.accept(ModItems.END_CACTUS_SWORD.get());
                        pOutput.accept(ModItems.CACTUS_SPINE.get());
                        pOutput.accept(ModItems.ANTI_SPINE.get());
                        pOutput.accept(ModItems.BLOOD_SPINE.get());

                        pOutput.accept(ModItems.SCYTHE.get());
                        pOutput.accept(ModItems.AMETHYST_HELMET.get());
                        pOutput.accept(ModItems.AMETHYST_CHESTPLATE.get());
                        pOutput.accept(ModItems.AMETHYST_LEGGINGS.get());
                        pOutput.accept(ModItems.AMETHYST_BOOTS.get());
                        pOutput.accept(ModItems.CHAKRAM.get());
                        pOutput.accept(ModItems.TURTLESHIELD.get());
                        pOutput.accept(ModItems.ARESBOW.get());
                        //pOutput.accept(ModItems.ARESARROW.get());
                        pOutput.accept(ModItems.BEETLEWINGS.get());
//SPAWN EGGS
                        pOutput.accept(ModItems.CBSE.get());
                        pOutput.accept(ModItems.EMSE.get());
                        pOutput.accept(ModItems.SSE.get());
                        pOutput.accept(ModItems.MSSE.get());
                        pOutput.accept(ModItems.LTSE.get());
                        pOutput.accept(ModItems.MRSE.get());
                        pOutput.accept(ModItems.OSE.get());
                        pOutput.accept(ModItems.PSE.get());
//PLANTS
                        pOutput.accept(ModBlocks.CATMINT.get());
                        pOutput.accept(ModBlocks.LOTUS.get());
//BOATS

                        pOutput.accept(ModItems.DARK_BOAT.get());
                        pOutput.accept(ModItems.DARK_CHEST_BOAT.get());
                        pOutput.accept(ModItems.BLOOD_BOAT.get());
                        pOutput.accept(ModItems.BLOOD_CHEST_BOAT.get());
                        pOutput.accept(ModItems.VOID_BOAT.get());
                        pOutput.accept(ModItems.VOID_CHEST_BOAT.get());
                        

                        pOutput.accept(ModBlocks.SBLOCK.get());
                        pOutput.accept(ModBlocks.POLISHER.get());
                        //pOutput.accept(ModBlocks.VOID_ALTAR.get());
                        //pOutput.accept(ModBlocks.VOID_PEDESTAL.get());
                        pOutput.accept(ModBlocks.PEDESTAL.get());
                        //pOutput.accept(ModBlocks.AB.get());

                        pOutput.accept(ModBlocks.DEATH_PORTAL.get());

                        //pOutput.accept(ModItems.CANDY_CANE_SUGAR.get());
                        //pOutput.accept(ModBlocks.CANDY_CANE_FURNACE.get());
                        pOutput.accept(ModBlocks.SOUND_BLOCK.get());
                       // pOutput.accept(ModItems.BAR_BRAWL_MUSIC_DISC.get());
                        //pOutput.accept(ModBlocks.SPECIAL_FURNACE.get());
/*
                        pOutput.accept(CurioVoidItems.AXEL_RING.get());
                        pOutput.accept(CurioVoidItems.WBR.get());
                        pOutput.accept(CurioVoidItems.SOUL_SKULL.get());*/
                        pOutput.accept(ModItems.SOUL_MUD.get());
                        pOutput.accept(ModItems.SOUL_COIN.get());
                    })
                    .build());

    public static final Supplier<CreativeModeTab> DEATH_NATURE = CREATIVE_MODE_TABS.register("death_nature_tab",
            ()-> CreativeModeTab.builder().icon(()->new ItemStack(ModBlocks.DARK_DIRT.get()))
                    .title(Component.translatable("creativetab.voidless_nature_tab"))
                    .displayItems((pParameters, pOutput) ->
                    {
                        pOutput.accept(ModBlocks.DARK_GRASS.get());
                        pOutput.accept(ModBlocks.DARK_DIRT.get());

                        pOutput.accept(ModBlocks.DARK_STONE.get());
                        pOutput.accept(ModBlocks.DARK_COBBLESTONE.get());
                        pOutput.accept(ModBlocks.BLOOD_STONE.get());
                        pOutput.accept(ModBlocks.BLOOD_COBBLESTONE.get());
                        pOutput.accept(ModBlocks.VOID_STONE.get());
                        pOutput.accept(ModBlocks.VOID_COBBLESTONE.get());

                        pOutput.accept(ModItems.VOID_TORCH.get());
                        pOutput.accept(ModBlocks.BLOOD_CONGEALED.get());
                        pOutput.accept(ModBlocks.BLOOD_COAGULATED.get());
                        pOutput.accept(ModBlocks.BLEEDING_DEITY_BLOCK.get());


                        pOutput.accept(ModBlocks.DARK_SAPLING.get());
                        pOutput.accept(ModBlocks.DARK_LOG.get());
                        pOutput.accept(ModBlocks.DARK_WOOD.get());
                        pOutput.accept(ModBlocks.STRIPPED_DARK_LOG.get());
                        pOutput.accept(ModBlocks.STRIPPED_DARK_WOOD.get());
                        pOutput.accept(ModBlocks.DARK_LEAVES.get());
                        pOutput.accept(ModBlocks.DARK_PLANKS.get());
                        pOutput.accept(ModBlocks.DARK_STAIRS.get());
                        pOutput.accept(ModBlocks.DARK_SLAB.get());
                        pOutput.accept(ModBlocks.DARK_BUTTON.get());
                        pOutput.accept(ModBlocks.DARK_FENCE.get());
                        pOutput.accept(ModBlocks.DARK_FENCE_GATE.get());
                        pOutput.accept(ModBlocks.DARK_DOOR.get());
                        pOutput.accept(ModBlocks.DARK_TRAPDOOR.get());
                        pOutput.accept(ModBlocks.DARK_PRESSURE_PLATE.get());
                        pOutput.accept(ModBlocks.DARK_WALL.get());
                        pOutput.accept(ModBlocks.DARK_SIGN.get());
                        pOutput.accept(ModBlocks.DARK_HANGING_SIGN.get());


                        pOutput.accept(ModBlocks.BLOOD_SAPLING.get());
                        pOutput.accept(ModBlocks.BLOOD_LOG.get());
                        pOutput.accept(ModBlocks.BLOOD_WOOD.get());
                        pOutput.accept(ModBlocks.STRIPPED_BLOOD_LOG.get());
                        pOutput.accept(ModBlocks.STRIPPED_BLOOD_WOOD.get());
                        pOutput.accept(ModBlocks.BLOOD_LEAVES.get());
                        pOutput.accept(ModBlocks.BLOOD_PLANKS.get());
                        pOutput.accept(ModBlocks.BLOOD_STAIRS.get());
                        pOutput.accept(ModBlocks.BLOOD_SLAB.get());
                        pOutput.accept(ModBlocks.BLOOD_BUTTON.get());
                        pOutput.accept(ModBlocks.BLOOD_FENCE.get());
                        pOutput.accept(ModBlocks.BLOOD_FENCE_GATE.get());
                        pOutput.accept(ModBlocks.BLOOD_DOOR.get());
                        pOutput.accept(ModBlocks.BLOOD_TRAPDOOR.get());
                        pOutput.accept(ModBlocks.BLOOD_PRESSURE_PLATE.get());
                        pOutput.accept(ModBlocks.BLOOD_WALL.get());
                        pOutput.accept(ModBlocks.BLOOD_SIGN.get());
                        pOutput.accept(ModBlocks.BLOOD_HANGING_SIGN.get());

                        pOutput.accept(ModBlocks.VOID_SAPLING.get());
                        pOutput.accept(ModBlocks.VOID_LOG.get());
                        pOutput.accept(ModBlocks.VOID_WOOD.get());
                        pOutput.accept(ModBlocks.STRIPPED_VOID_LOG.get());
                        pOutput.accept(ModBlocks.STRIPPED_VOID_WOOD.get());
                        pOutput.accept(ModBlocks.VOID_LEAVES.get());
                        pOutput.accept(ModBlocks.VOID_PLANKS.get());
                        pOutput.accept(ModBlocks.VOID_STAIRS.get());
                        pOutput.accept(ModBlocks.VOID_SLAB.get());
                        pOutput.accept(ModBlocks.VOID_BUTTON.get());
                        pOutput.accept(ModBlocks.VOID_FENCE.get());
                        pOutput.accept(ModBlocks.VOID_FENCE_GATE.get());
                        pOutput.accept(ModBlocks.VOID_DOOR.get());
                        pOutput.accept(ModBlocks.VOID_TRAPDOOR.get());
                        pOutput.accept(ModBlocks.VOID_PRESSURE_PLATE.get());
                        pOutput.accept(ModBlocks.VOID_WALL.get());
                        pOutput.accept(ModBlocks.VOID_SIGN.get());
                        pOutput.accept(ModBlocks.VOID_HANGING_SIGN.get());


                        pOutput.accept(ModBlocks.ANTI_CACTUS.get());
                        pOutput.accept(ModBlocks.DARK_CACTUS.get());
                        pOutput.accept(ModBlocks.BLOOD_CACTUS.get());
                        pOutput.accept(ModBlocks.END_CACTUS.get());

                        pOutput.accept(ModItems.BLOOD_BUCKET.get());
                        pOutput.accept(ModBlocks.BLOOD_COAGULATED.get());
                        pOutput.accept(ModBlocks.BLOOD_CONGEALED.get());

                    }).build());
    public static final Supplier<CreativeModeTab> ABYSS_TAB = CREATIVE_MODE_TABS.register("abyss_tab",
            ()-> CreativeModeTab.builder().icon(()->new ItemStack(ModBlocks.DARK_DIRT.get()))
                    .title(Component.translatable("creativetab.abyss_tab"))
                    .displayItems((pParameters, pOutput) ->
                    {

                        pOutput.accept(ModItems.ARESBOW.get());
                        pOutput.accept(ModItems.ARESARROW.get());
                        pOutput.accept(ModItems.ABYSS_BOOTS.get());
                        pOutput.accept(ModItems.BLOOD_BOOTS.get());

                        pOutput.accept(ModItems.BLOOD_BUCKET.get());
                        pOutput.accept(ModItems.DEITY_BLOOD_BUCKET.get());
                        pOutput.accept(ModItems.ENDER_BLOOD_BUCKET.get());
                        pOutput.accept(ModItems.DARK_ESSENCE_BUCKET.get());

                        pOutput.accept(ModItems.VOID_TORCH.get());
                        pOutput.accept(ModItems.D_TORCH.get());
                        pOutput.accept(ModBlocks.CRYSTALLIZER.get());
                        pOutput.accept(ModBlocks.PEDESTAL.get());

                        /**pOutput.accept(ModBlocks.VOID_ALTAR.get());
                        pOutput.accept(ModBlocks.VOID_PEDESTAL.get());**/
                    }).build());
    public static final Supplier<CreativeModeTab> BIOMECHANICAL_TAB = CREATIVE_MODE_TABS.register("biomechanical_tab",
            ()-> CreativeModeTab.builder()
                    .displayItems((pParameters, pOutput) ->
                    {
                        pOutput.accept(ModBlocks.BLOOD_COAGULATED.get());
                        pOutput.accept(ModBlocks.BLOOD_CONGEALED.get());
                    }).build());


    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
