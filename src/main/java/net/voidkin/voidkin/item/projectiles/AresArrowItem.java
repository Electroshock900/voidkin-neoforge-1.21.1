package net.voidkin.voidkin.item.projectiles;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;
import net.voidkin.voidkin.entity.ModEntities;
import net.voidkin.voidkin.entity.projectiles.AresArrow;

import javax.annotation.Nullable;

public class AresArrowItem extends Item implements ProjectileItem {
    public AresArrowItem(Properties properties) {
        super(new Item.Properties().stacksTo(64));
    }


    public AbstractArrow createArrow(Level world, ItemStack stack, LivingEntity shooter, @Nullable ItemStack weapon){
        return new AresArrow(world, shooter, stack.copyWithCount(1), weapon);
    }

    @Override
    public Projectile asProjectile(Level world, Position pos, ItemStack stack, Direction direction) {
        AbstractArrow jeweledarrow = new AresArrow(world, pos.x(), pos.y(), pos.z(), stack.copyWithCount(1),null);
        jeweledarrow.pickup = AbstractArrow.Pickup.ALLOWED;
        return jeweledarrow;
    }

}
