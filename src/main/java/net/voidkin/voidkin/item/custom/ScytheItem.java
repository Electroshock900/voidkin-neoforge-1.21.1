package net.voidkin.voidkin.item.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;

public class ScytheItem extends TieredItem {

    public ScytheItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity livingEntity, LivingEntity entity) {
        //livingEntity.attackAnim;
        return super.hurtEnemy(stack, livingEntity, entity);

    }
}
