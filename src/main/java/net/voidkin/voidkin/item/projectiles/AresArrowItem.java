package net.voidkin.voidkin.item.projectiles;

import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.voidkin.voidkin.entity.projectiles.AresArrow;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class AresArrowItem extends ArrowItem {
    public AresArrowItem(Properties properties) {
        super(properties);
    }
    public AbstractArrow createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
        AresArrow arrow = new AresArrow(pLevel, pShooter);
        //arrow.setEffectsFromItem(pStack);
        return arrow;
    }

    /*public boolean isInfinite(ItemStack stack, ItemStack bow, Player player) {
        int enchant = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, bow);
        return enchant <= 0 ? false : this.getClass() == AresArrowItem.class;
    }*/
    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        //player.json.eat(world,stack);

        //player.setSecondsOnFire(5);
        return InteractionResultHolder.consume(player.getItemInHand(hand));
    }
    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity shooter, int ticksRemaining) {
        super.releaseUsing(stack, level, shooter, ticksRemaining);
        //shooter.setSecondsOnFire(3);
        if (shooter instanceof Player player) {
            if (stack.getDamageValue() >= stack.getMaxDamage() - 1)
                return;

            // Add a cooldown so you can't fire rapidly
            player.getCooldowns().addCooldown(this, 5);

            if (!level.isClientSide) {
                AresArrow arrow = new AresArrow(level, player);

                arrow.tickCount = 35;

                arrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, 1, 1);
                arrow.setBaseDamage(13.5);
                arrow.isNoGravity();

                //stack.hurtAndBreak(1, shooter, p -> p.broadcastBreakEvent(shooter.getUsedItemHand()));
                level.addFreshEntity(arrow);

                // Trigger our animation
                // We could trigger this outside of the client-side check if only wanted the animation to play for the shooter
                // But we'll fire it on the server so all nearby players can see it
                //triggerAnim(player.json, GeoItem.getOrAssignId(stack, (ServerLevel)level), "shoot_controller", "shoot");
            }
        }
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack pStack, LivingEntity pEntity) {
        return 72000;//super.getUseDuration(p_41454_);
    }
}
