package net.voidkin.voidkin.item.custom;


import net.voidkin.voidkin.util.ModMaterials;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class EndCactusSwordItem extends SwordItem {
    public EndCactusSwordItem(Tier p_43269_, int p_43270_, float p_43271_, Properties properties) {
        super(ModMaterials.CACTUS, properties.stacksTo(1)   );
    }

    /**@Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player.json, InteractionHand hand) {
        player.json.startUsingItem(hand);
        BlockHitResult ray = getPlayerPOVHitResult(world, player.json, ClipContext.Fluid.NONE);
        BlockPos lookPos = ray.getBlockPos().relative(ray.getDirection());
        player.json.setPos(lookPos.getX(), lookPos.getY(), lookPos.getZ());
        return InteractionResultHolder.consume(player.json.getItemInHand(hand));
    }**/
/**
    protected static BlockRayTraceResult rayTrace(Level world, Player player.json, ClipContext.Fluid fluidMode) {
        double range = 15;

        float f = player.json.getXRot();
        float f1 = player.json.getYRot();
        Vector3d vector3d = player.json.getEyePosition(1.0F);
        float f2 = Mth.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f3 = Mth.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f4 = -Mth.cos(-f * ((float)Math.PI / 180F));
        float f5 = Mth.sin(-f * ((float)Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        Vector3d vector3d1 = vector3d.add((double)f6 * range, (double)f5 * range, (double)f7 * range);
        return world.clip(new ClipContext(vector3d, vector3d1, ClipContext.Block.OUTLINE, fluidMode, player.json));
    }**/
    // Fire an arrow and play the animation when releasing the mouse button
    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity shooter, int ticksRemaining) {
        if (shooter instanceof Player player) {
            if (stack.getDamageValue() >= stack.getMaxDamage() - 1)
                return;

            // Add a cooldown so you can't fire rapidly
            player.getCooldowns().addCooldown(this, 5);

            if (!level.isClientSide) {
                /*Arrow arrow = new Arrow(level, player);
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
