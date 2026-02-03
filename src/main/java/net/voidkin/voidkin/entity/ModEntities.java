package net.voidkin.voidkin.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.entity.boats.ModBoatEntity;
import net.voidkin.voidkin.entity.boats.ModBoats;
import net.voidkin.voidkin.entity.boats.ModChestBoatEntity;

import net.voidkin.voidkin.entity.projectiles.*;
import net.voidkin.voidkin.entity.boats.ModBoats.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.voidkin.voidkin.entity.custom.*;
import net.voidkin.voidkin.item.ModItems;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ModEntities {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Voidkin.MODID);
/**
    public static Supplier<EntityType<Thrown_Chakram>> CHAKRAM = ENTITY_TYPES.register("chakram",
            ()->EntityType.Builder.of((EntityType.EntityFactory<Thrown_Chakram>) Thrown_Chakram::new, MobCategory.MISC)
                    .sized(2F,2F)
                    .build(DeathMod.MODID + "chakram"));**/
public static Supplier<EntityType<AresArrow>> ARESARROW = ENTITY_TYPES.register("aresarrow",
        ()->EntityType.Builder.of((EntityType.EntityFactory<AresArrow>) AresArrow::new, MobCategory.MISC)
                .sized(0.5F, 0.5F)
                .clientTrackingRange(4)
                .updateInterval(20)
                .build(Voidkin.MODID + "aresarrow"));


    public static final Supplier<EntityType<Thrown_Chakram>> CHAKRAM = ENTITY_TYPES.register("chakram",
            () -> EntityType.Builder.<Thrown_Chakram>of( Thrown_Chakram::new,
                            MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4)
                    .updateInterval(20).build(ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "chakram").toString()));


    public static Supplier<EntityType<Cactus_Spine>> SPINE = ENTITY_TYPES.register("spine",
            ()->EntityType.Builder.of((EntityType.EntityFactory<Cactus_Spine>) Cactus_Spine::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build(Voidkin.MODID + "spine"));

    public static Supplier<EntityType<Anti_Cactus_Spine>> ANTI_SPINE = ENTITY_TYPES.register("anti_spine",
            ()->EntityType.Builder.of((EntityType.EntityFactory<Anti_Cactus_Spine>) Anti_Cactus_Spine::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build(Voidkin.MODID + "spine"));
    public static Supplier<EntityType<Blood_Spine>> BLOODSPINE = ENTITY_TYPES.register("blood_spine",
            ()->EntityType.Builder.of((EntityType.EntityFactory<Blood_Spine>) Blood_Spine::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build(Voidkin.MODID + "blood_spine"));

    public static final Supplier<EntityType<TomahawkProjectileEntity>> TOMAHAWK =
            ENTITY_TYPES.register("tomahawk", () -> EntityType.Builder.<TomahawkProjectileEntity>of(TomahawkProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5f, 1.15f).build("tomahawk"));



    //Cactus Buddy
    public static final Supplier<EntityType<Cactus_Buddy>> CACTUS_BUDDY =
            ENTITY_TYPES.register("cactus_buddy", () -> EntityType.Builder.of(Cactus_Buddy::new, MobCategory.MISC)
                            .sized(0.4f, 0.4f)
                            .build("cactus_buddy")
            );

    public static final Supplier<EntityType<Skull_Entity>> SKULL =
            ENTITY_TYPES.register("skull",
                    () -> EntityType.Builder.of(Skull_Entity::new, MobCategory.MONSTER)
                            .sized(1.0f, 1.0f)
                            .build(ResourceLocation.fromNamespaceAndPath(Voidkin.MODID,"skull").toString())
            );


    public static final Supplier<EntityType<Mini_Skull_Entity>> MINI_SKULL =
            ENTITY_TYPES.register("mini_skull",
                    ()-> EntityType.Builder.of(Mini_Skull_Entity::new, MobCategory.MONSTER)
                            .sized(1.0F,1.0F)
                            .build(ResourceLocation.fromNamespaceAndPath(Voidkin.MODID,"mini_skull").toString())
            );

    public static final Supplier<EntityType<Eyeball_Monster>> EYEBALL_MONSTER =
            ENTITY_TYPES.register("eyeball_monster",
                    ()-> EntityType.Builder.of(Eyeball_Monster::new, MobCategory.MONSTER)
                            .sized(1f,1f)
                            .build(ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "eyeball_monster").toString())
            );
    public static final Supplier<EntityType<Lion_Thing>> LION_THING =
            ENTITY_TYPES.register("lion_thing",
                    ()-> EntityType.Builder.of(Lion_Thing::new,MobCategory.MONSTER)
                            .sized(1f,1f)
                            .build(ResourceLocation.fromNamespaceAndPath(Voidkin.MODID,"lion_thing").toString())
            );

    public static final Supplier<EntityType<MantaRayEntity>> MANTA_RAY =
            ENTITY_TYPES.register("manta_ray",() ->
                    EntityType.Builder.of(MantaRayEntity::new,MobCategory.WATER_CREATURE)
                            .sized(2f,1f)
                            .build(ResourceLocation.fromNamespaceAndPath(Voidkin.MODID,"manta_ray").toString())
                            );
    public static final Supplier<EntityType<OwlEntity>> OWL =
            ENTITY_TYPES.register("owl",() ->
                    EntityType.Builder.of(OwlEntity::new,MobCategory.AMBIENT)
                            .sized(1f,2f)
                            .build(ResourceLocation.fromNamespaceAndPath(Voidkin.MODID,"owl").toString())
                            );
    public static final Supplier<EntityType<PenguinEntity>> PENGUIN =
            ENTITY_TYPES.register("penguin",() ->
                    EntityType.Builder.of(PenguinEntity::new,MobCategory.AMBIENT)
                            .sized(1f,2f)
                            .build(ResourceLocation.fromNamespaceAndPath(Voidkin.MODID,"penguin").toString())
                            );

    public static final Supplier<EntityType<WarTortoise>> WAR_TORTOISE =
            ENTITY_TYPES.register("war_tortoise", ()->
                    EntityType.Builder.of(WarTortoise::new, MobCategory.MISC)
                            .sized(2f,1.3f)
                            .passengerAttachments(1.33375F)
                            .build("war_tortoise")
            );
    public static final Supplier<EntityType<WarTortoiseHybrid>> WAR_TORTOISE_HYBRID =
            ENTITY_TYPES.register("war_tortoise_hybrid", ()->
                    EntityType.Builder.of(WarTortoiseHybrid::new, MobCategory.MISC)
                            .sized(2f,1.3f)
                            .passengerAttachments(1.33375F)
                            .build("war_tortoise_hybrid")
            );

    public static final Supplier<EntityType<WarTurtle>> WAR_TURTLE =
            ENTITY_TYPES.register("war_turtle", ()->
                    EntityType.Builder.of(WarTurtle::new, MobCategory.MISC)
                            .sized(2f,1.5f)
                            .passengerAttachments(1.33375F)
                            .build("war_turtle")
            );

    public static final Supplier<EntityType<ModBoatEntity>> MOD_BOAT =
            ENTITY_TYPES.register("mod_boat", () -> EntityType.Builder.<ModBoatEntity>of(ModBoatEntity::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build("mod_boat"));
    public static final Supplier<EntityType<ModChestBoatEntity>> MOD_CHEST_BOAT =
            ENTITY_TYPES.register("mod_chest_boat", () -> EntityType.Builder.<ModChestBoatEntity>of(ModChestBoatEntity::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build("mod_chest_boat"));


    public static void register(IEventBus eventbus) {
        ENTITY_TYPES.register(eventbus);}
}

