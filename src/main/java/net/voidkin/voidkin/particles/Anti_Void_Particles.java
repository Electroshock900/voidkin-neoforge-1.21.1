package net.voidkin.voidkin.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Anti_Void_Particles extends RisingParticle {
    protected Anti_Void_Particles(ClientLevel pLevel, double pX, double pY, double pZ,
                                  SpriteSet spriteSet, double pXSpeed, double pYSpeed, double pZSpeed) {
        super(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);
        this.friction = 0.8f;
        this.lifetime = 6;
        this.xd = pXSpeed;
        this.yd = pYSpeed;
        this.zd = pZSpeed;
        //this.hasPhysics = true;
        this.setSpriteFromAge(spriteSet);
        this.rCol=1f;
        this.gCol=1f;
        this.bCol=1f;

        this.setSpriteFromAge(spriteSet);
    }

    public void move(double pX, double pY, double pZ) {
        this.setBoundingBox(this.getBoundingBox().move(pX, pY, pZ));
        this.setLocationFromBoundingbox();
    }

    public float getQuadSize(float pScaleFactor) {
        float f = ((float)this.age + pScaleFactor) / (float)this.lifetime;
        return this.quadSize * (1.0F - f * f * 0.5F);
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Provider(SpriteSet pSprites) {
            this.sprite = pSprites;
        }

        @Override
        @Nullable
        public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            //return new Anti_Void_Particles(pLevel, pX, pY, pZ, this.sprite, pXSpeed, pYSpeed, pZSpeed);
            Anti_Void_Particles flameparticle = new Anti_Void_Particles(pLevel, pX, pY, pZ, this.sprite, pXSpeed, pYSpeed, pZSpeed);
            flameparticle.pickSprite(this.sprite);
            return flameparticle;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class SmallFlameProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public SmallFlameProvider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double pX, double pY, double pZ,
                                       double pXSpeed, double pYSpeed, double pZSpeed) {
                    Anti_Void_Particles vf = new Anti_Void_Particles(level, pX, pY, pZ, this.spriteSet, pXSpeed, pYSpeed, pZSpeed);
            vf.pickSprite(this.spriteSet);
            vf.scale(0.5F);
            return vf;
        }
    }
}
