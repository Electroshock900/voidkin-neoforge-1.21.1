package net.voidkin.voidkin.entity.projectiles;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.voidkin.voidkin.entity.ModEntities;
import net.voidkin.voidkin.item.ModItems;

public class Blood_Spine extends AbstractArrow {

    public Blood_Spine(EntityType<? extends Blood_Spine> p_36858_, Level p_36859_) {
        super(p_36858_, p_36859_);
    }

    public Blood_Spine(Level level,LivingEntity shooter) {
        super(ModEntities.BLOODSPINE.get(), shooter, level, new ItemStack(ModItems.BLOOD_SPINE.get()), null);
    }

    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        //this.entityData.set(ID_EFFECT_COLOR, -1);
    }

    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            if (this.inGround) {

                if (this.inGroundTime % 5 == 0) {
                    this.makeParticle(1);
                    this.makeParticle2(1);
                }
            }
                    else {
                this.makeParticle(1);
                this.makeParticle2(4);
            }
        } else if (this.inGround && this.inGroundTime != 0 && /**!this.effects.isEmpty() &&**/ this.inGroundTime >= 600) {
            this.level().broadcastEntityEvent(this, (byte)0);
            this.discard();
            /**this.potion = Potions.EMPTY;
            this.effects.clear();
            this.entityData.set(ID_EFFECT_COLOR, -1);**/
        }

    }

    private void makeParticle(int p_36877_) {
        /**int i = this.getColor();
        if (i != -1 && p_36877_ > 0) {
            double d0 = (double)(i >> 16 & 255) / 255.0D;
            double d1 = (double)(i >> 8 & 255) / 255.0D;
            double d2 = (double)(i >> 0 & 255) / 255.0D;
**/
            for(int j = 0; j < p_36877_; ++j) {
                //this.level.addParticle(ParticleTypes.CRIT, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D),0,0,0);//, d0, d1, d2);
                this.level().addParticle(ParticleTypes.FLAME, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D),0,0,0);//, d0, d1, d2);
            }

        }
        private void makeParticle2(int p_36877_) {
        /**int i = this.getColor();
        if (i != -1 && p_36877_ > 0) {
            double d0 = (double)(i >> 16 & 255) / 255.0D;
            double d1 = (double)(i >> 8 & 255) / 255.0D;
            double d2 = (double)(i >> 0 & 255) / 255.0D;
**/
            for(int j = 0; j < p_36877_; ++j) {
                this.level().addParticle(ParticleTypes.LAVA, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D),0,0,0);//, d0, d1, d2);
                //this.level.addParticle(ParticleTypes.FALLING_LAVA, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D),0,0,0);//, d0, d1, d2);
            }

        }
        //}

    protected ItemStack getPickupItem() {
        //if (this.effects.isEmpty() && this.potion == Potions.EMPTY) {
            return ItemStack.EMPTY;//new ItemStack(ModItems.BLOOD_SPINE.get());
        }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(ModItems.BLOOD_SPINE.get());
    } /**else {
            ItemStack itemstack = new ItemStack(Items.TIPPED_ARROW);
            PotionUtils.setPotion(itemstack, this.potion);
            PotionUtils.setCustomEffects(itemstack, this.effects);
            if (this.fixedColor) {
                itemstack.getOrCreateTag().putInt("CustomPotionColor", this.getColor());
            }

            return itemstack;
        }
    }**/

}
