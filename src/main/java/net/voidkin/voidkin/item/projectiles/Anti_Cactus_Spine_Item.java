package net.voidkin.voidkin.item.projectiles;

import net.voidkin.voidkin.entity.projectiles.Anti_Cactus_Spine;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class Anti_Cactus_Spine_Item extends Item {
    public Anti_Cactus_Spine_Item(Properties properties) {
        super(properties);
    }


    public void releaseUsing(ItemStack stack, Level level, LivingEntity shooter, int ticksRemaining) {
        if (shooter instanceof Player player) {
            if (stack.getDamageValue() >= stack.getMaxDamage() - 1)
                 return;

            // Add a cooldown so you can't fire rapidly
            player.getCooldowns().addCooldown(this, 5);

            if (!level.isClientSide) {
                /*Anti_Cactus_Spine arrow = new Anti_Cactus_Spine(level, player);
                arrow.tickCount = 35;

                arrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, 1, 1);
                arrow.setBaseDamage(3.5);
                arrow.isNoGravity();

                stack.hurtAndBreak(1, shooter, p -> p.broadcastBreakEvent(shooter.getUsedItemHand()));
                level.addFreshEntity(arrow);*/

                // Trigger our animation
                // We could trigger this outside of the client-side check if only wanted the animation to play for the shooter
                // But we'll fire it on the server so all nearby players can see it
                //triggerAnim(player.json, GeoItem.getOrAssignId(stack, (ServerLevel)level), "shoot_controller", "shoot");
            }
        }
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.SPEAR;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000;
    }


}
