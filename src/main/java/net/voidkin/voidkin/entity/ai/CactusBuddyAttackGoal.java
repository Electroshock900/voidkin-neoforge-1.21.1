package net.voidkin.voidkin.entity.ai;

import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;

public class CactusBuddyAttackGoal extends RangedAttackGoal {

    public CactusBuddyAttackGoal(RangedAttackMob mob, double speedModifier, int attackIntervalMin, int attackIntervalMax, float attackRadius) {
        super(mob, speedModifier, attackIntervalMin, attackRadius);
    }
}
