package net.voidkin.voidkin.sounds;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.util.DeferredSoundType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.voidkin.voidkin.Voidkin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;


public class ModSounds {
    public static DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, Voidkin.MODID);

    public static final Supplier<SoundEvent> SOUND_BLOCK_BREAK = registerSoundEvents("sound_block_break");
    public static final Supplier<SoundEvent> SOUND_BLOCK_STEP = registerSoundEvents("sound_block_step");
    public static final Supplier<SoundEvent> SOUND_BLOCK_FALL = registerSoundEvents("sound_block_fall");
    public static final Supplier<SoundEvent> SOUND_BLOCK_PLACE = registerSoundEvents("sound_block_place");
    public static final Supplier<SoundEvent> SOUND_BLOCK_HIT = registerSoundEvents("sound_block_hit");

    public static final Supplier<SoundEvent> CHAINSAW_PULL = registerSoundEvents("chainsaw_pull");
    public static final Supplier<SoundEvent> CHAINSAW_CUT = registerSoundEvents("chainsaw_cut");

    public static final Supplier<SoundEvent> BAR_BRAWL = registerSoundEvents("bar_brawl");
    public static final ResourceKey<JukeboxSong> BAR_BRAWL_KEY = createSong("bar_brawl");

    public static final Supplier<SoundEvent> GRAVITY = registerSoundEvents("gravity");
    public static final ResourceKey<JukeboxSong> GRAVITY_KEY = createSong("gravity");

    public static final Supplier<SoundEvent> DAISIES = registerSoundEvents("daisies");
    public static final ResourceKey<JukeboxSong> DAISIES_KEY = createSong( "daisies");

    public static final Supplier<SoundEvent> UPSIDE_DOWN = registerSoundEvents("upside_down");
    public static final ResourceKey<JukeboxSong> UPSIDE_DOWN_KEY = createSong("upside_down");

    public static final Supplier<SoundEvent> LOSER_BABY = registerSoundEvents("loser_baby");
    public static final ResourceKey<JukeboxSong> LOSER_BABY_KEY = createSong("loser_baby");

    public static final Supplier<SoundEvent> LOSIN_STREAK = registerSoundEvents("losin_streak");
    public static final ResourceKey<JukeboxSong> LOSIN_STREAK_KEY = createSong("losin_streak");

    public static final Supplier<SoundEvent> HUSK_LOVE = registerSoundEvents("love_in_a_bottle");
    public static final ResourceKey<JukeboxSong> HUSK_LOVE_KEY = createSong( "love_in_a_bottle");

    public static final Supplier<SoundEvent> ALASTOR = registerSoundEvents("alastor_mashup");
    public static final ResourceKey<JukeboxSong> ALASTOR_KEY = createSong("alastor_mashup");

    public static final Supplier<SoundEvent> BRIGHTER = registerSoundEvents("brighter");
    public static final ResourceKey<JukeboxSong> BRIGHTER_KEY = createSong("brighter");

    public static final DeferredSoundType SOUND_BLOCK_SOUNDS = new DeferredSoundType(1f, 1f,
            ModSounds.SOUND_BLOCK_BREAK, ModSounds.SOUND_BLOCK_STEP, ModSounds.SOUND_BLOCK_PLACE,
            ModSounds.SOUND_BLOCK_HIT, ModSounds.SOUND_BLOCK_FALL);


    private static ResourceKey<JukeboxSong> createSong(String name){
        return ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, name));
    }

    private static Supplier<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, name)));
    }
    public static void register(IEventBus eventBus){SOUND_EVENTS.register(eventBus);}
    private static Holder.Reference<SoundEvent> registerForHolder(String pName) {
        return registerForHolder(ResourceLocation.withDefaultNamespace(pName));
    }

    private static Holder.Reference<SoundEvent> registerForHolder(ResourceLocation pName) {
        return registerForHolder(pName, pName);
    }

    private static Holder.Reference<SoundEvent> registerForHolder(ResourceLocation pName, ResourceLocation pLocation) {
        return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, pName, SoundEvent.createVariableRangeEvent(pLocation));
    }

}
