package net.voidkin.voidkin.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.entity.custom.*;
import net.voidkin.voidkin.entity.ModEntities;

@EventBusSubscriber(modid= Voidkin.MODID,bus=EventBusSubscriber.Bus.MOD)
public class  ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntities.CACTUS_BUDDY.get(), Cactus_Buddy.setAttributes());
        event.put(ModEntities.EYEBALL_MONSTER.get(), Eyeball_Monster.setAttributes());
        event.put(ModEntities.LION_THING.get(), Lion_Thing.setAttributes());
        event.put(ModEntities.SKULL.get(), Skull_Entity.setAttributes());
        event.put(ModEntities.MINI_SKULL.get(), Mini_Skull_Entity.setAttributes());
        event.put(ModEntities.MANTA_RAY.get(), MantaRayEntity.setAttributes());
        event.put(ModEntities.HORNED_OWL.get(), HornedOwlEntity.setAttributes());
        event.put(ModEntities.OWL.get(), OwlEntity.setAttributes());
        event.put(ModEntities.PENGUIN.get(), PenguinEntity.setAttributes());
        event.put(ModEntities.WAR_TURTLE.get(), WarTurtle.createAttributes());
        event.put(ModEntities.WAR_TORTOISE.get(), WarTortoise.setAttributes());
        event.put(ModEntities.WAR_TORTOISE_HYBRID.get(), WarTortoiseHybrid.setAttributes());
    }

}
