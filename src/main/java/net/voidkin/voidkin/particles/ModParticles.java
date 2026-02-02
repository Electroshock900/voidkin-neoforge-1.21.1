package net.voidkin.voidkin.particles;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import com.mojang.serialization.Decoder;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.voidkin.voidkin.Voidkin;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;


import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.Supplier;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, Voidkin.MODID);


    public static final Supplier<SimpleParticleType> CANDY_CANE_FLAME_PARTICLES =
            PARTICLE_TYPES.register("candy_cane_flame_particles", () -> new SimpleParticleType(false));

    public static final Supplier<SimpleParticleType> DEATH_SKULLS =
            PARTICLE_TYPES.register("death_skulls", ()->new SimpleParticleType(false));


    public static final Supplier<SimpleParticleType> SMALL_VOID_FLAME =
            PARTICLE_TYPES.register("void_flame_small", ()-> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> VOID_FLAME =
            PARTICLE_TYPES.register("void_flame", ()-> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> ANTI_VOID =
            PARTICLE_TYPES.register("anti_void", ()-> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> SMALL_ANTI_VOID =
            PARTICLE_TYPES.register("anti_void_small", ()-> new SimpleParticleType(false));

    public static final Supplier<SimpleParticleType> GHOSTLY_FLAME =
            PARTICLE_TYPES.register("ghostly_flame", ()-> new SimpleParticleType(false));


    public static final Supplier<SimpleParticleType> SOUL_FLAME =
            PARTICLE_TYPES.register("soul_flame", ()-> new SimpleParticleType(false));


    public static final Supplier<SimpleParticleType> DRIP = PARTICLE_TYPES.register("drip_drip", ()-> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> DRIP_FALL = PARTICLE_TYPES.register("drip_fall", ()-> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> DRIP_LAND = PARTICLE_TYPES.register("drip_land", ()-> new SimpleParticleType(false));

    public static final Supplier<SimpleParticleType> DRIPPING_BLOOD = PARTICLE_TYPES.register("blood_dripping", ()-> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> FALLING_BLOOD = PARTICLE_TYPES.register("blood_falling", ()-> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> LANDING_BLOOD = PARTICLE_TYPES.register("blood_landing", ()-> new SimpleParticleType(false));

    public static final Supplier<SimpleParticleType> DRIPPING_DEITY_BLOOD = PARTICLE_TYPES.register("deity_blood_dripping", ()-> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> FALLING_DEITY_BLOOD = PARTICLE_TYPES.register("deity_blood_falling", ()-> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> LANDING_DEITY_BLOOD = PARTICLE_TYPES.register("deity_blood_landing", ()-> new SimpleParticleType(false));


   /* @OnlyIn(Dist.CLIENT)
    static class MutableSpriteSet implements SpriteSet {
        private List<TextureAtlasSprite> sprites;

        @Override
        public TextureAtlasSprite get(int pParticleAge, int pParticleMaxAge) {
            return this.sprites.get(pParticleAge * (this.sprites.size() - 1) / pParticleMaxAge);
        }

        @Override
        public TextureAtlasSprite get(RandomSource pRandom) {
            return this.sprites.get(pRandom.nextInt(this.sprites.size()));
        }

        public void rebind(List<TextureAtlasSprite> pSprites) {
            this.sprites = ImmutableList.copyOf(pSprites);
        }
    }
   @Deprecated
    public <T extends ParticleOptions> void register2(ParticleType<T> pParticleType, ParticleProvider.Sprite<T> pSprite) {
        this.register2(
                pParticleType,
                p_272320_ -> (p_272323_, p_272324_, p_272325_, p_272326_, p_272327_, p_272328_, p_272329_, p_272330_) -> {
                    TextureSheetParticle texturesheetparticle = pSprite.createParticle(
                            p_272323_, p_272324_, p_272325_, p_272326_, p_272327_, p_272328_, p_272329_, p_272330_
                    );
                    if (texturesheetparticle != null) {
                        texturesheetparticle.pickSprite(p_272320_);
                    }

                    return texturesheetparticle;
                }
        );
    }

    @Deprecated
    public <T extends ParticleOptions> void register2(ParticleType<T> pParticleType, ParticleEngine.SpriteParticleRegistration<T> pParticleMetaFactory) {
        ModParticles.MutableSpriteSet particleengine$mutablespriteset = new ModParticles.MutableSpriteSet();
        this.spriteSets.put(BuiltInRegistries.PARTICLE_TYPE.getKey(pParticleType), particleengine$mutablespriteset);
        this.providers.put(BuiltInRegistries.PARTICLE_TYPE.getKey(pParticleType), pParticleMetaFactory.create(particleengine$mutablespriteset));
    }*/


    private static SimpleParticleType register(String pKey, boolean pOverrideLimiter) {
        return Registry.register(BuiltInRegistries.PARTICLE_TYPE, pKey, new SimpleParticleType(pOverrideLimiter));
    }

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}

