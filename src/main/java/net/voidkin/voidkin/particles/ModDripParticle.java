package net.voidkin.voidkin.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.voidkin.voidkin.fluid.ModFluids;
import org.jetbrains.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
public class ModDripParticle extends TextureSheetParticle {
    public boolean isGlowing = false;
    private final Fluid type;
    //public final SpriteSet spriteSet;

    protected ModDripParticle(ClientLevel pLevel, double pX, double pY, double pZ, Fluid pFluid) {
        super(pLevel, pX, pY, pZ);
        this.type = pFluid;
        this.gravity = 0.75F;
        this.friction = 0.999F;
        this.yd = (double)(this.random.nextFloat() * 0.4F + 0.05F);
        this.quadSize *= this.random.nextFloat() * 2.0F + 0.2F;
        this.lifetime = (int)(16.0 / (Math.random() * 0.8 + 0.2));

    }
    protected Fluid getType() {
        return this.type;
    }
    public int getLightColor(float pPartialTick) {
        return this.isGlowing ? 240 : super.getLightColor(pPartialTick);
    }

    protected void preMoveUpdate() {
        if (this.lifetime-- <= 0) {
            this.remove();
        }

    }
    protected void postMoveUpdate(){}
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        this.preMoveUpdate();
        if (!this.removed) {
            this.yd -= (double)this.gravity;
            this.move(this.xd, this.yd, this.zd);
            this.postMoveUpdate();
            if (!this.removed) {
                this.xd *= 0.9800000190734863;
                this.yd *= 0.9800000190734863;
                this.zd *= 0.9800000190734863;
                if (this.type != ModFluids.SOURCE_BLOOD.get()) {
                    BlockPos $$0 = BlockPos.containing(this.x, this.y, this.z);
                    FluidState $$1 = this.level.getFluidState($$0);
                    if ($$1.getType() == this.type && this.y < (double)((float)$$0.getY() + $$1.getHeight(this.level, $$0))) {
                        this.remove();
                    }

                }
            }
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }
    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel world,
                                       double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            // You can pass null or a Fluid if you want to reuse DripParticle fluid logic.
            ModDripParticle p = new ModDripParticle(world, x, y, z, Fluids.EMPTY);
            p.setSpriteFromAge(this.sprites);
            return p;
        }
    }



    @OnlyIn(Dist.CLIENT)
    public class DrippingBloodParticle extends TextureSheetParticle {

        private final SpriteSet sprites;

        protected DrippingBloodParticle(ClientLevel level, double x, double y, double z,
                                        SpriteSet sprites) {
            super(level, x, y, z);
            this.sprites = sprites;
            this.setSpriteFromAge(sprites);

            this.gravity = 0.0F;
            this.lifetime = 20 + this.random.nextInt(20); // hang time
            this.xd = this.yd = this.zd = 0;
        }

        @Override
        public void tick() {
            super.tick();

            // After hanging long enough → turn into falling particle
            if (!this.removed && this.age > this.lifetime * 0.6F) {
                this.level.addParticle(
                        ModParticles.FALLING_BLOOD.get(),
                        this.x, this.y - 0.02D, this.z,
                        0, 0, 0);
                this.remove();
            }
        }

        @Override
        public ParticleRenderType getRenderType() {
            return null;
        }
    }
    @OnlyIn(Dist.CLIENT)
    public static class DripHangParticle extends TextureSheetParticle {
        private final ParticleOptions fallingParticle;
        //private final SpriteSet sprites;
            public boolean isGlowing;


        DripHangParticle(ClientLevel pLevel, double pX, double pY, double pZ, Fluid pType, @Nullable ParticleOptions pFallingParticle) {
            super(pLevel, pX, pY, pZ);
            this.fallingParticle = pFallingParticle;
            this.gravity = 0.0F;
            this.lifetime = 20 + this.random.nextInt(20);
            this.xd = this.yd = this.zd = 0;
        }

        protected void preMoveUpdate() {
            if (this.lifetime-- <= 0) {
                this.remove();
                //this.level.addParticle(this.fallingParticle, this.x, this.y, this.z, this.xd, this.yd, this.zd);
            }

        }
        @Override
        public int getLightColor(float pPartialTick) {
            return this.isGlowing ? 240 : super.getLightColor(pPartialTick);
        }

            @Override
            public void tick() {
                super.tick();

                // After hanging long enough → turn into falling particle
                if (!this.removed && this.age > this.lifetime * 0.6F) {
                    this.level.addParticle(
                            fallingParticle,
                            this.x, this.y - 0.02D, this.z,
                            0, 0, 0);
                    this.remove();
                }
            }
        protected void postMoveUpdate() {
            this.xd *= 0.02;
            this.yd *= 0.02;
            this.zd *= 0.02;
        }

            @Override
            public ParticleRenderType getRenderType() {
                return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
            }
            public static class Provider implements ParticleProvider<SimpleParticleType> {
                private final SpriteSet sprites;

                public Provider(SpriteSet sprites) { this.sprites = sprites; }

                @Override
                public Particle createParticle(SimpleParticleType type, ClientLevel level,
                                               double x, double y, double z, double vx, double vy, double vz) {
                    DripHangParticle p = new DripHangParticle(level, x, y, z, Fluids.EMPTY, ModParticles.DRIP_FALL.get());
                    //p.setColor(0.51171875F, 0.0F, 0F);
                    p.setSpriteFromAge(this.sprites);
                    return p;
                }
            }
        }
    @OnlyIn(Dist.CLIENT)
    public static class FallAndLandParticle extends TextureSheetParticle {
        public final ParticleOptions landParticle;
        public boolean isGlowing;

        FallAndLandParticle(ClientLevel pLevel, double pX, double pY, double pZ, Fluid pType, @Nullable ParticleOptions pLandParticle) {
            super(pLevel, pX, pY, pZ);
            this.landParticle = pLandParticle;
            this.gravity=0.06F;
            this.xd=0;
            this.zd=0;
            this.lifetime = 40;
        }
        @Override
        public int getLightColor(float pPartialTick) {
            return this.isGlowing ? 240 : super.getLightColor(pPartialTick);
        }
        @Override
        public void tick() {
            super.tick();

            // landing detection
            if (this.onGround) {
                level.addParticle(landParticle,
                        this.x, this.y, this.z,
                        0, 0, 0);
                this.remove();
            }
        }

        protected void postMoveUpdate() {
            if (this.onGround) {
                this.remove();
                //this.level.addParticle(this.landParticle, this.x, this.y, this.z, 0.0, 0.0, 0.0);
            }

        }

        @Override
        public ParticleRenderType getRenderType() {
            return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
        }
        public static class Provider implements ParticleProvider<SimpleParticleType> {
            private final SpriteSet sprites;

            public Provider(SpriteSet sprites) { this.sprites = sprites; }

            @Override
            public Particle createParticle(SimpleParticleType type, ClientLevel level,
                                           double x, double y, double z, double vx, double vy, double vz) {
                FallAndLandParticle p = new FallAndLandParticle(level, x, y, z, Fluids.EMPTY, ModParticles.DRIP_LAND.get());
                //p.setColor(0.51171875F, 0.0F, 1F);
                p.setSpriteFromAge(this.sprites);
                return p;
            }
        }
    }
    @OnlyIn(Dist.CLIENT)
    public static class DripLandParticle extends TextureSheetParticle {
        public boolean isGlowing;
        DripLandParticle(ClientLevel p_106102_, double p_106103_, double p_106104_, double p_106105_, Fluid p_106106_) {
            super(p_106102_, p_106103_, p_106104_, p_106105_);
            this.lifetime = 10;//(int)(16.0 / (Math.random() * 0.8 + 0.2));
            this.gravity=0;
        }
        @Override
        public int getLightColor(float pPartialTick) {
            return this.isGlowing ? 240 : super.getLightColor(pPartialTick);
        }
        @Override
        public void tick() {
            super.tick();
            this.alpha = 1.0F - ((float)this.age / (float)this.lifetime);
        }

        @Override
        public ParticleRenderType getRenderType() {
            return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
        }

        public static class Provider implements ParticleProvider<SimpleParticleType> {
            private final SpriteSet sprites;

            public Provider(SpriteSet sprites) { this.sprites = sprites; }

            @Override
            public Particle createParticle(SimpleParticleType type, ClientLevel lvl,
                                           double x, double y, double z, double vx, double vy, double vz) {
                DripLandParticle p = new DripLandParticle(lvl, x, y, z, Fluids.EMPTY);
                //p.setColor(0.51171875F, 0.0F, 0F);
                p.setSpriteFromAge(this.sprites);
                return p;
            }
        }

    }

    public static TextureSheetParticle createBloodDripHangParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            DripHangParticle $$8 = new DripHangParticle(pLevel, pX, pY, pZ, ModFluids.SOURCE_BLOOD.get(),ModParticles.FALLING_BLOOD.get());
            $$8.isGlowing = false;
//            $$8.gravity *= 0.01F;
//            $$8.lifetime = 100;
            //$$8.setColor(0.51171875F, 0.03125F, 0.890625F);
            $$8.setColor(0.51171875F, 0.0F, 0F);
            return $$8;
    }
    public static TextureSheetParticle createBloodDripFallParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            FallAndLandParticle $$8 = new FallAndLandParticle(pLevel, pX, pY, pZ, ModFluids.SOURCE_BLOOD.get(), ModParticles.LANDING_BLOOD.get());
            $$8.isGlowing = false;
            //$$8.gravity = 0.01F;
            //$$8.setColor(0.51171875F, 0.03125F, 0.890625F);
            $$8.setColor(0.51171875F, 0.0F, 0F);
            return $$8;
    }
    public static TextureSheetParticle createBloodDripLandParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            DripLandParticle $$8 = new DripLandParticle(pLevel, pX, pY, pZ, ModFluids.SOURCE_BLOOD.get());
            $$8.isGlowing = false;
            //$$8.lifetime = (int)(28.0 / (Math.random() * 0.8 + 0.2));
            $$8.setColor(0.51171875F, 0F, 0F);
            //$$8.setColor(0.42071875F, 0.03125F, 0.0890625F);
            return $$8;
    }

    public static TextureSheetParticle createDeityBloodDripHangParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            DripHangParticle $$8 = new DripHangParticle(pLevel, pX, pY, pZ, ModFluids.SOURCE_DEITY_BLOOD.get(), ModParticles.FALLING_DEITY_BLOOD.get());
            $$8.isGlowing = true;
//            $$8.gravity *= 0.01F;
//            $$8.lifetime = 100;
            //$$8.setColor(0.51171875F, 0.03125F, 0.890625F);
            $$8.setColor(1F, 0.854901961F, 0.380392157F);
            return $$8;
    }
    public static TextureSheetParticle createDeityBloodDripFallParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            FallAndLandParticle $$8 = new FallAndLandParticle(pLevel, pX, pY, pZ, ModFluids.SOURCE_DEITY_BLOOD.get(), ModParticles.LANDING_DEITY_BLOOD.get());
            $$8.isGlowing = true;
//            $$8.gravity *= 0.01F;
//            $$8.lifetime = 100;
            //$$8.setColor(0.51171875F, 0.03125F, 0.890625F);
            $$8.setColor(1F, 0.854901961F, 0.380392157F);
            return $$8;
    }
    public static TextureSheetParticle createDeityBloodDripLandParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            DripLandParticle $$8 = new DripLandParticle(pLevel, pX, pY, pZ, ModFluids.SOURCE_DEITY_BLOOD.get());
            $$8.isGlowing = true;
//            $$8.gravity *= 0.01F;
//            $$8.lifetime = 100;
            //$$8.setColor(0.51171875F, 0.03125F, 0.890625F);
            $$8.setColor(1F, 0.854901961F, 0.380392157F);
            return $$8;
    }



}
