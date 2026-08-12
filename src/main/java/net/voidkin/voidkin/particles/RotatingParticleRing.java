package net.voidkin.voidkin.particles;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.level.Level;

public class RotatingParticleRing {
    private final Level level;
    private final BlockPos pos;
    private final ParticleOptions particle;

    private double radius;
    private int count;
    private double rotationSpeed; // radians per tick
    private double angleOffset = 0;

    public RotatingParticleRing(Level level, BlockPos pos, ParticleOptions particle,
                                double radius, int count, double rotationSpeed) {
        this.level = level;
        this.pos = pos;
        this.particle = particle;
        this.radius = radius;
        this.count = count;
        this.rotationSpeed = rotationSpeed;
    }

    public void tick() {
        if (!level.isClientSide) return;

        angleOffset += rotationSpeed;

        double cx = pos.getX() + 0.5;
        double cy = pos.getY() + 1.0;
        double cz = pos.getZ() + 0.5;

        for (int i = 0; i < count; i++) {
            double angle = (2 * Math.PI * i) / count + angleOffset;

            double x = cx + radius * Math.cos(angle);
            double z = cz + radius * Math.sin(angle);

            level.addParticle(particle, x, cy, z, 0, 0, 0);
        }
    }
}
