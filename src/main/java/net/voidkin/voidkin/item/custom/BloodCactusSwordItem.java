package net.voidkin.voidkin.item.custom;

import net.voidkin.voidkin.entity.projectiles.Blood_Spine;
import net.voidkin.voidkin.util.ModMaterials;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class BloodCactusSwordItem extends SwordItem {
    public BloodCactusSwordItem(Tier p_43269_, int p_43270_, float p_43271_, Properties properties) {
        super(ModMaterials.CACTUS, properties.stacksTo(1)   );
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        player.startUsingItem(hand);

        return InteractionResultHolder.consume(player.getItemInHand(hand));
    }

    // Fire an arrow and play the animation when releasing the mouse button
    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity shooter, int ticksRemaining) {
        if (shooter instanceof Player player) {
            if (stack.getDamageValue() >= stack.getMaxDamage() - 1)
                return;

            // Add a cooldown so you can't fire rapidly
            player.getCooldowns().addCooldown(this, 5);

            if (!level.isClientSide) {
                /*Blood_Spine arrow = new Blood_Spine(level, player);
                arrow.tickCount = 35;
                for(int times = 0; times < 10; ++times) {
                    arrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, 1, 1);
                }


                arrow.setBaseDamage(7.5);
                arrow.isNoGravity();

                stack.hurtAndBreak(1, shooter, p -> p.broadcastBreakEvent(shooter.getUsedItemHand()));
                level.addFreshEntity(arrow);
*/
                // Trigger our animation
                // We could trigger this outside of the client-side check if only wanted the animation to play for the shooter
                // But we'll fire it on the server so all nearby players can see it
                //triggerAnim(player.json, GeoItem.getOrAssignId(stack, (ServerLevel)level), "shoot_controller", "shoot");
            }
        }
    }

    // Use vanilla animation to 'pull back' the pistol while charging it
    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.CROSSBOW;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000;
    }

}
