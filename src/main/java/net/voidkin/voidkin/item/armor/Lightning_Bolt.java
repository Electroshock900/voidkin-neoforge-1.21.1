package net.voidkin.voidkin.item.armor;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class Lightning_Bolt extends Item {
    public Lightning_Bolt(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (!context.getLevel().isClientSide()) {
            Level world = context.getLevel();
            Vec3 playerPos = context.getPlayer().position();
            spawnLightningAtPos(playerPos, world, context);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    public void spawnLightningAtPos(Vec3 sourcePos, Level world, UseOnContext context) {
        // Get the player's horizontal facing direction
        Vec3 playerLook = context.getPlayer().getLookAngle();

        // Normalize the direction vector
        playerLook = playerLook.normalize();

        // Calculate the horizontal offset (adjust this value for desired distance)
        double horizontalDistance = 5.0; // Adjust this value for the desired distance
        Vec3 horizontalOffset = playerLook.scale(horizontalDistance);

        // Determine the target position
        Vec3 targetPos = sourcePos.add(horizontalOffset);

        // Spawn the lightning bolt horizontally
        LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, world);
        lightningBolt.setPos(targetPos.x, targetPos.y, targetPos.z);
        world.addFreshEntity(lightningBolt);
    }
}
