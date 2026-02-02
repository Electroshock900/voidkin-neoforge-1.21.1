package net.voidkin.voidkin.entity;

import net.voidkin.voidkin.Voidkin;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation CACTUS_BUDDY_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(Voidkin.MODID,"cactus_buddy_layer"),"main");
    public static final ModelLayerLocation EYEBALL_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(Voidkin.MODID,"eyeball_monster_layer"),"main");
    public static final ModelLayerLocation SKULL_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(Voidkin.MODID,"skull_layer"),"main");
    public static final ModelLayerLocation MINI_SKULL_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(Voidkin.MODID,"mini_skull_layer"),"main");
    public static final ModelLayerLocation LION_THING_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(Voidkin.MODID,"lion_thing_layer"),"main");
    public static final ModelLayerLocation MANTA_RAY_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(Voidkin.MODID,"manta_ray_layer"),"main");

    public static final ModelLayerLocation DARK_BOAT_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "boat/dark"), "main");
    public static final ModelLayerLocation DARK_CHEST_BOAT_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "chest_boat/dark"), "main");
    public static final ModelLayerLocation BLOOD_BOAT_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "boat/blood"), "main");
    public static final ModelLayerLocation BLOOD_CHEST_BOAT_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "chest_boat/blood"), "main");
    public static final ModelLayerLocation VOID_BOAT_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "boat/void"), "main");
    public static final ModelLayerLocation VOID_CHEST_BOAT_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "chest_boat/void"), "main");
}
