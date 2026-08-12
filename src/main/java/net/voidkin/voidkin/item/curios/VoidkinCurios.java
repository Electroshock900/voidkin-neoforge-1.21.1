package net.voidkin.voidkin.item.curios;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.voidkin.voidkin.item.custom.QuiverItem;


import java.util.function.Supplier;

import static net.voidkin.voidkin.Voidkin.MODID;

public class VoidkinCurios {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, MODID);

   public static final Supplier<Item> AXEL_RING = ITEMS.register("axel_ring", ()-> new Axel_Ring(new Item.Properties().stacksTo(1)));
   public static final Supplier<Item> VOID_QUIVER = ITEMS.register("void_quiver2", ()-> new QuiverItem(new Item.Properties().stacksTo(3),54));




}
