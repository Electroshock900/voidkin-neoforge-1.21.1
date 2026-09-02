package net.voidkin.voidkin.entity.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.animal.horse.Donkey;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.EventHooks;
import net.voidkin.voidkin.block.ModBlocks;
import net.voidkin.voidkin.entity.ModEntities;
import net.voidkin.voidkin.entity.custom.cheesehorse.AbstractCheeseHorse;
import net.voidkin.voidkin.entity.custom.cheesehorse.AbstractChestedCheeseHorse;

import javax.annotation.Nullable;

public class MeatDonkey extends AbstractChestedCheeseHorse {
    public MeatDonkey(EntityType<? extends MeatDonkey> entityType, Level level) {
        super(entityType, level);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.DONKEY_AMBIENT;
    }

    protected SoundEvent getAngrySound() {
        return SoundEvents.DONKEY_ANGRY;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.DONKEY_DEATH;
    }

    @Nullable
    protected SoundEvent getEatingSound() {
        return SoundEvents.DONKEY_EAT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.DONKEY_HURT;
    }

    public boolean canMate(Animal otherAnimal) {
        if (otherAnimal == this) {
            return false;
        } else {
            return !(otherAnimal instanceof MeatDonkey) && !(otherAnimal instanceof CheeseHorse) ? false : this.canParent() && ((AbstractCheeseHorse)otherAnimal).canParent();
        }
    }

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        boolean flag = !this.isBaby() && this.isTamed() && player.isSecondaryUseActive();
        if (!this.isVehicle() && !flag) {
            ItemStack itemstack = player.getItemInHand(hand);

            if (!itemstack.isEmpty()) {
                if (this.isFood(itemstack)) {
                    return this.fedFood(player, itemstack);
                }
                if(itemstack.is(ModBlocks.ANTI_CACTUS.asItem())){
                    if(this.level().isClientSide()) {
                        return InteractionResult.CONSUME;
                    } else {
                        if (!player.getAbilities().instabuild) {
                            itemstack.shrink(1);
                        }

                        if (!EventHooks.onAnimalTame(this, player)) {
                            super.tame(player);
                            this.navigation.recomputePath();
                            this.setTarget(null);
                            this.level().broadcastEntityEvent(this, (byte)7);

                            //toggleSitting();
                        }


                        return InteractionResult.SUCCESS;
                    }
                }

                if (!this.isTamed()) {
                    this.makeMad();
                    return InteractionResult.sidedSuccess(this.level().isClientSide);
                }
            }

            return super.mobInteract(player, hand);
        } else {
            return super.mobInteract(player, hand);
        }
    }

    protected void playJumpSound() {
        this.playSound(SoundEvents.DONKEY_JUMP, 0.4F, 1.0F);
    }

    @Nullable
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        EntityType<? extends AbstractCheeseHorse> entitytype = otherParent instanceof CheeseHorse ? ModEntities.HOAGIE_MULE.get() : ModEntities.MEAT_DONKEY.get();
        AbstractCheeseHorse abstracthorse = (AbstractCheeseHorse)entitytype.create(level);
        if (abstracthorse != null) {
            this.setOffspringAttributes(otherParent, abstracthorse);
        }

        return abstracthorse;
    }
}
