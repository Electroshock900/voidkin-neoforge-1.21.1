package net.voidkin.voidkin.item.projectiles;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.Item;
import net.voidkin.voidkin.entity.projectiles.Blood_Spine;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class Blood_Spine_Item extends Item {
    public Blood_Spine_Item(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player pPlayer, InteractionHand pUsedHand) {


        // Add a cooldown so you can't fire rapidly
        pPlayer.getCooldowns().addCooldown(this, 5);

        if (!level.isClientSide) {
            Blood_Spine arrow = new Blood_Spine(level, pPlayer);
            arrow.tickCount = 35;

            arrow.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0, 1, 1);
            level.addFreshEntity(arrow);

            // Trigger our animation
            // We could trigger this outside of the client-side check if only wanted the animation to play for the shooter
            // But we'll fire it on the server so all nearby players can see it
            //triggerAnim(player.json, GeoItem.getOrAssignId(stack, (ServerLevel)level), "shoot_controller", "shoot");
        }
        return super.use(level, pPlayer, pUsedHand);
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
