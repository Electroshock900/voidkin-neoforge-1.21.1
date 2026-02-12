package net.voidkin.voidkin.item;

import net.minecraft.core.Direction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.voidkin.voidkin.block.ModBlocks;
import net.voidkin.voidkin.entity.ModEntities;
import net.voidkin.voidkin.entity.boats.ModBoatEntity;
import net.voidkin.voidkin.fluid.ModFluids;
import net.voidkin.voidkin.item.armor.HemogenesisBoots;
import net.voidkin.voidkin.item.armor.WarTortoiseArmor;
import net.voidkin.voidkin.item.armor.WarTortoiseHybridArmor;
import net.voidkin.voidkin.item.armor.WarTurtleArmor;
import net.voidkin.voidkin.item.custom.*;
import net.voidkin.voidkin.item.projectiles.Anti_Cactus_Spine_Item;
import net.voidkin.voidkin.item.projectiles.AresArrowItem;
import net.voidkin.voidkin.item.projectiles.Blood_Spine_Item;
import net.voidkin.voidkin.item.projectiles.Cactus_Spine_Item;
import net.voidkin.voidkin.sounds.ModSounds;
import net.minecraft.world.item.*;

import net.voidkin.voidkin.util.ModArmorMaterials;
import net.voidkin.voidkin.util.ModMaterials;


import static net.voidkin.voidkin.Voidkin.MODID;

public class ModItems {

    // Create a Deferred Register to hold Items which will all be registered under the "voidkin" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    //public static final DeferredRegister<Entity> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);
    public static final DeferredItem<Item> VOID_TORCH = ITEMS.register("void_torch", ()-> new StandingAndWallBlockItem(ModBlocks.VOID_TORCH.get(),ModBlocks.VOID_WALL_TORCH.get(), new Item.Properties(), Direction.DOWN));
    public static final DeferredItem<Item> D_TORCH = ITEMS.register("d_torch", ()-> new StandingAndWallBlockItem(ModBlocks.D_TORCH.get(),ModBlocks.D_WALL_TORCH.get(), new Item.Properties(), Direction.DOWN));

    public static final DeferredItem<Item> CANDY_CANE_SUGAR = ITEMS.register("candy_cane_sugar", () -> new ModFuelItem(new Item.Properties(),640));
    public static final DeferredItem<Item> SMILE = ITEMS.register("smile", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FIRE_CHARM = ITEMS.register("fire_charm", () -> new Item(new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> RAW_DARK_SHARD = ITEMS.register("raw_dark_shard", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DARK_SHARD = ITEMS.register("dark_shard", () -> new Item(new Item.Properties()));

//Weapons and Shields
    public static final DeferredItem<Item> TURTLESHIELD = ITEMS.register("turtle_shield", () -> new ShieldItem(new Item.Properties()));

    public static final DeferredItem<Item> CACTUS_SWORD = ITEMS.register("cactus_sword", () -> new CactusSwordItem(ModMaterials.CACTUS,3,2.0F,new Item.Properties()));
    public static final DeferredItem<Item> ANTI_CACTUS_SWORD = ITEMS.register("anti_cactus_sword", () -> new AntiCactusSwordItem(ModMaterials.CACTUS,3,2.0F,new Item.Properties()));
    public static final DeferredItem<Item> BLOOD_CACTUS_SWORD = ITEMS.register("cactus_blood_sword", () -> new BloodCactusSwordItem(ModMaterials.CACTUS,3,2.0F,new Item.Properties()));
    public static final DeferredItem<Item> END_CACTUS_SWORD = ITEMS.register("cactus_end_sword", () -> new EndCactusSwordItem(ModMaterials.CACTUS,3,2.0F,new Item.Properties()));

    public static final DeferredItem<Item> CACTUS_BOW = ITEMS.register("cactus_bow", ()-> new CactusBowItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> CACTUS_SPINE = ITEMS.register("cactus_spine",()->new Cactus_Spine_Item(new Item.Properties().stacksTo(64)));
    public static final DeferredItem<Item> BLOOD_CACTUS_BOW = ITEMS.register("blood_cactus_bow", ()-> new BloodCactusBowItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> BLOOD_SPINE = ITEMS.register("blood_spine",()->new Blood_Spine_Item(new Item.Properties().stacksTo(64)));
    public static final DeferredItem<Item> ANTI_SPINE = ITEMS.register("anti_spine",()->new Anti_Cactus_Spine_Item(new Item.Properties().stacksTo(64)));

    public static final DeferredItem<Item> CHAKRAM = ITEMS.register("chakram", () -> new ChakramItem(ModMaterials.DARKNESS,0,6.0F,new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> SCYTHE = ITEMS.register("scythe", ()-> new SwordItem(ModMaterials.DARKNESS, new Item.Properties()));

    public static final DeferredItem<Item> ARESBOW = ITEMS.register("aresbow", ()-> new AresBowItem(new Item.Properties().fireResistant().stacksTo(1)));
    public static final DeferredItem<Item> ARESARROW = ITEMS.register("ares_arrow", ()-> new AresArrowItem(new Item.Properties().fireResistant().stacksTo(64)));

    public static final DeferredItem<Item> REGROWTHAXE = ITEMS.register("regrowth_axe", () -> new AxeOfRegrowthItem(ModMaterials.CACTUS,0,3, new Item.Properties()));
    public static final DeferredItem<Item> TOMAHAWK = ITEMS.register("tomahawk", () -> new TomahawkItem(new Item.Properties().stacksTo(16)));

    public static final DeferredItem<Item> CHAINSAW =
            ITEMS.registerItem("chainsaw", ChainsawItem::new, new Item.Properties().durability(32));

    public static final DeferredItem<Item> BEETLEWINGS = ITEMS.register("beetlewings", ()-> new BeetleWingsItem(new Item.Properties().durability(543).rarity(Rarity.UNCOMMON)));

    public static final DeferredItem<Item> SOUL_COIN = ITEMS.registerItem("soul_coin", Item::new, new Item.Properties());
    public static final DeferredItem<Item> SOUL_MUD = ITEMS.registerItem("soul_mud", Item::new, new Item.Properties());

//Spawn Eggs
    public static final DeferredItem<Item> CACTUS_EGG = ITEMS.register("cactus_egg", () -> new DeferredSpawnEggItem(ModEntities.CACTUS_BUDDY,0x649832,0xbabf95,new Item.Properties()));
    public static final DeferredItem<Item> EYEBALL_MONSTER_EGG = ITEMS.register("eyeball_monster_egg", () -> new DeferredSpawnEggItem(ModEntities.EYEBALL_MONSTER,0xffffff,0xf5ffc4,new Item.Properties()));
    public static final DeferredItem<Item> SKULL_EGG = ITEMS.register("skull_egg", () -> new DeferredSpawnEggItem(ModEntities.SKULL,0xffffff,0xffffff,new Item.Properties()));
    public static final DeferredItem<Item> MINI_SKULL_EGG = ITEMS.register("mini_skull_egg", () -> new DeferredSpawnEggItem(ModEntities.MINI_SKULL,0x432f02,0xff24ff,new Item.Properties()));
    public static final DeferredItem<Item> LION_THING_EGG = ITEMS.register("lion_thing_egg", () -> new DeferredSpawnEggItem(ModEntities.LION_THING,0x773d3d,0x2e2e2e,new Item.Properties()));
    public static final DeferredItem<Item> MANTA_RAY_EGG = ITEMS.register("manta_ray_egg", () -> new DeferredSpawnEggItem(ModEntities.MANTA_RAY,0x773d3d,0x2e2e2e,new Item.Properties()));
    public static final DeferredItem<Item> OWL_EGG = ITEMS.register("owl_egg", () -> new DeferredSpawnEggItem(ModEntities.OWL,0x773d3d,0xcba5a5,new Item.Properties()));
    public static final DeferredItem<Item> HORNED_OWL_EGG = ITEMS.register("horned_owl_egg", () -> new DeferredSpawnEggItem(ModEntities.HORNED_OWL,0x773d3d,0xcba5a5,new Item.Properties()));
    public static final DeferredItem<Item> PENGUIN_EGG = ITEMS.register("penguin_egg", () -> new DeferredSpawnEggItem(ModEntities.PENGUIN,0x473d77,0x756aa9,new Item.Properties()));


//Buckets
    public static final DeferredItem<Item> SOAP_WATER_BUCKET = ITEMS.register("soap_water_bucket",
            () -> new BucketItem(ModFluids.SOURCE_SOAP_WATER.get(),
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> ENDER_BLOOD_BUCKET = ITEMS.register("ender_blood_bucket",
            () -> new BucketItem(ModFluids.SOURCE_ENDER_BLOOD.get(),
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> BLOOD_BUCKET = ITEMS.register("blood_bucket",
            () -> new BucketItem(ModFluids.SOURCE_BLOOD.get(),
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
   public static final DeferredItem<Item> DEITY_BLOOD_BUCKET = ITEMS.register("deity_blood_bucket",
            () -> new BucketItem(ModFluids.SOURCE_DEITY_BLOOD.get(),
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> DARK_ESSENCE_BUCKET = ITEMS.register("dark_essense_bucket",
            () -> new BucketItem(ModFluids.SOURCE_DARK_ESSENCE.get(),
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));


//Armor
    public static final DeferredItem<Item> AMETHYST_HELMET = ITEMS.register("amethyst_helmet",
            () -> new ArmorItem(ModArmorMaterials.AMETHYST, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> AMETHYST_CHESTPLATE = ITEMS.register("amethyst_chestplate",
            () -> new ArmorItem(ModArmorMaterials.AMETHYST, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> AMETHYST_LEGGINGS = ITEMS.register("amethyst_leggings",
            () -> new ArmorItem(ModArmorMaterials.AMETHYST, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> AMETHYST_BOOTS = ITEMS.register("amethyst_boots",
            () -> new ArmorItem(ModArmorMaterials.AMETHYST, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final DeferredItem<Item> ABYSS_BOOTS = ITEMS.register("abyss_boots",
            () -> new ArmorItem(ModArmorMaterials.DARK, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final DeferredItem<Item> BLOOD_BOOTS = ITEMS.register("blood_boots",
            () -> new HemogenesisBoots(ModArmorMaterials.BLOOD, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final DeferredItem<Item> FROSTBOOTS = ITEMS.register("frost_boots",
            ()-> new ArmorItem(ArmorMaterials.IRON,ArmorItem.Type.BOOTS, new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> CACTUS_BOOTS = ITEMS.register("cactus_boots",
            () -> new ArmorItem(ModArmorMaterials.CACTUS, ArmorItem.Type.BOOTS, new Item.Properties()));

//Boats
    public static final DeferredItem<Item> DARK_BOAT = ITEMS.register("dark_boat",
            () -> new ModBoatItem(false, ModBoatEntity.Type.DARK, new Item.Properties()));
    public static final DeferredItem<Item> DARK_CHEST_BOAT = ITEMS.register("dark_chest_boat",
            () -> new ModBoatItem(true, ModBoatEntity.Type.DARK, new Item.Properties()));
    public static final DeferredItem<Item> BLOOD_BOAT = ITEMS.register("blood_boat",
            () -> new ModBoatItem(false, ModBoatEntity.Type.BLOOD, new Item.Properties()));
    public static final DeferredItem<Item> BLOOD_CHEST_BOAT = ITEMS.register("blood_chest_boat",
            () -> new ModBoatItem(true, ModBoatEntity.Type.BLOOD, new Item.Properties()));
    public static final DeferredItem<Item> VOID_BOAT = ITEMS.register("void_boat",
            () -> new ModBoatItem(false, ModBoatEntity.Type.VOID, new Item.Properties()));
    public static final DeferredItem<Item> VOID_CHEST_BOAT = ITEMS.register("void_chest_boat",
            () -> new ModBoatItem(true, ModBoatEntity.Type.VOID, new Item.Properties()));


//MusicDiscs
    public static final DeferredItem<Item> BAR_BRAWL_MUSIC_DISC = ITEMS.register("bar_brawl_music_disc",
            ()-> new Item(new Item.Properties().stacksTo(1).jukeboxPlayable(ModSounds.BAR_BRAWL_KEY)));
    public static final DeferredItem<Item> GRAVITY_MUSIC_DISC = ITEMS.register("gravity_music_disc",
            ()-> new Item(new Item.Properties().stacksTo(1).jukeboxPlayable(ModSounds.GRAVITY_KEY)));
    public static final DeferredItem<Item> DAISIES_MUSIC_DISC = ITEMS.register("daisies_music_disc",
            ()-> new Item(new Item.Properties().stacksTo(1).jukeboxPlayable(ModSounds.DAISIES_KEY)));
    public static final DeferredItem<Item> UPSIDE_DOWN_MUSIC_DISC = ITEMS.register("upside_down_music_disc",
            ()-> new Item(new Item.Properties().stacksTo(1).jukeboxPlayable(ModSounds.UPSIDE_DOWN_KEY)));
    public static final DeferredItem<Item> ALASTOR_MASHUP_MUSIC_DISC = ITEMS.register("alastor_mashup_music_disc",
            ()-> new Item(new Item.Properties().stacksTo(1).jukeboxPlayable(ModSounds.ALASTOR_KEY)));
    public static final DeferredItem<Item> LOSER_BABY_MUSIC_DISC = ITEMS.register("loser_baby_music_disc",
            ()-> new Item(new Item.Properties().stacksTo(1).jukeboxPlayable(ModSounds.LOSER_BABY_KEY)));
    public static final DeferredItem<Item> LOSIN_STREAK_MUSIC_DISC = ITEMS.register("losin_streak_music_disc",
            ()-> new Item(new Item.Properties().stacksTo(1).jukeboxPlayable(ModSounds.LOSIN_STREAK_KEY)));
    public static final DeferredItem<Item> HUSK_LOVE_MUSIC_DISC = ITEMS.register("husk_love_music_disc",
            ()-> new Item(new Item.Properties().stacksTo(1).jukeboxPlayable(ModSounds.HUSK_LOVE_KEY)));
    public static final DeferredItem<Item> BRIGHTER_MUSIC_DISC = ITEMS.register("brighter_music_disc",
            ()-> new Item(new Item.Properties().stacksTo(1).jukeboxPlayable(ModSounds.BRIGHTER_KEY)));

//Signs
    public static final DeferredItem<Item> DARK_SIGN = ITEMS.register("dark_sign",
            ()-> new SignItem(new Item.Properties().stacksTo(16), ModBlocks.DARK_SIGN.get(),ModBlocks.DARK_WALL_SIGN.get()));
    public static final DeferredItem<Item> DARK_HANGING_SIGN = ITEMS.register("dark_hanging_sign",
            ()-> new HangingSignItem(ModBlocks.DARK_HANGING_SIGN.get(),ModBlocks.DARK_WALL_HANGING_SIGN.get(),new Item.Properties().stacksTo(16)));
    public static final DeferredItem<Item> BLOOD_SIGN = ITEMS.register("blood_sign",
            ()-> new SignItem(new Item.Properties().stacksTo(16), ModBlocks.BLOOD_SIGN.get(),ModBlocks.BLOOD_WALL_SIGN.get()));
    public static final DeferredItem<Item> BLOOD_HANGING_SIGN = ITEMS.register("blood_hanging_sign",
            ()-> new HangingSignItem(ModBlocks.BLOOD_HANGING_SIGN.get(),ModBlocks.BLOOD_WALL_HANGING_SIGN.get(),new Item.Properties().stacksTo(16)));
    public static final DeferredItem<Item> VOID_SIGN = ITEMS.register("void_sign",
            ()-> new SignItem(new Item.Properties().stacksTo(16),ModBlocks.VOID_SIGN.get(),ModBlocks.VOID_WALL_SIGN.get()));
    public static final DeferredItem<Item> VOID_HANGING_SIGN = ITEMS.register("void_hanging_sign",
            ()-> new HangingSignItem(ModBlocks.VOID_HANGING_SIGN.get(),ModBlocks.VOID_WALL_HANGING_SIGN.get(),new Item.Properties().stacksTo(16)));



    public static final DeferredItem<Item> WAR_TORTOISE_ARMOR = ITEMS.register("war_tortoise_armor",
            ()-> new WarTortoiseArmor(ArmorMaterials.TURTLE, ArmorItem.Type.BODY, new Item.Properties()));
    public static final DeferredItem<Item> WAR_TORTOISE_HYBRID_ARMOR = ITEMS.register("war_tortoise_hybrid_armor",
            ()-> new WarTortoiseHybridArmor(ArmorMaterials.TURTLE, ArmorItem.Type.BODY, new Item.Properties()));
    public static final DeferredItem<Item> WAR_TORTOISE_HYBRID_ARMOR_IRON = ITEMS.register("war_tortoise_hybrid_armor_iron",
            ()-> new WarTortoiseHybridArmor(ArmorMaterials.TURTLE, ArmorItem.Type.BODY, new Item.Properties()));
    public static final DeferredItem<Item> WAR_TURTLE_ARMOR = ITEMS.register("iron_war_turtle_armor",
            ()-> new WarTurtleArmor(ArmorMaterials.TURTLE, ArmorItem.Type.BODY, new Item.Properties()));
    public static final DeferredItem<Item> NETHERITE_WAR_TURTLE_ARMOR = ITEMS.register("netherite_war_turtle_armor",
            ()-> new WarTurtleArmor(ArmorMaterials.NETHERITE, ArmorItem.Type.BODY, new Item.Properties()));




    public static void register(IEventBus eventbus) {ITEMS.register(eventbus);}

}