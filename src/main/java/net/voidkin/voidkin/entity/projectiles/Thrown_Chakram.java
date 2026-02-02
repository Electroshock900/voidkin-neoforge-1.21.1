package net.voidkin.voidkin.entity.projectiles;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.item.enchantment.Enchantments;
import net.voidkin.voidkin.damage_types.ModDamageTypes;
import net.voidkin.voidkin.entity.ModEntities;
import net.voidkin.voidkin.item.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.voidkin.voidkin.damage_sources.ModDamageSources;


import javax.annotation.Nullable;
import java.util.Objects;

public class Thrown_Chakram extends AbstractArrow {
    private static final EntityDataAccessor<Byte> ID_LOYALTY = SynchedEntityData.defineId(Thrown_Chakram.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Boolean> ID_FOIL = SynchedEntityData.defineId(Thrown_Chakram.class, EntityDataSerializers.BOOLEAN);

    private ItemStack ChakramItem = new ItemStack(ModItems.CHAKRAM.get());

    private boolean dealtDamage;

    public int clientSideReturnChakramTickCount;

    public Thrown_Chakram(EntityType<Thrown_Chakram> thrownChakramEntityType, Level level) {
        super(ModEntities.CHAKRAM.get(),level);
    }
    public Thrown_Chakram(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.CHAKRAM.get(), pLevel);

    }
    public Thrown_Chakram(LivingEntity shooter, Level level){
        super(ModEntities.CHAKRAM.get(), shooter, level, new ItemStack(ModItems.CHAKRAM.get()),new ItemStack(ModItems.CHAKRAM.get()));
    }
    public Thrown_Chakram(Level level, LivingEntity livingEntity, ItemStack itemStack) {
        super(ModEntities.CHAKRAM.get(), livingEntity, level,new ItemStack(ModItems.CHAKRAM.get()), new ItemStack(ModItems.CHAKRAM.get()));
        this.ChakramItem = itemStack.copy();
        this.entityData.set(ID_LOYALTY, this.getLoyaltyFromItem(itemStack));
        //this.entityData.set(ID_LOYALTY, (byte)EnchantmentHelper.getEnchantmentLevel(Enchantments.LOYALTY.getOrThrow(level),livingEntity));
        this.entityData.set(ID_FOIL, itemStack.hasFoil());
    }

    public Thrown_Chakram(EntityType<Thrown_Chakram> thrownChakramEntityType, LivingEntity shooter, Level world) {
        super(thrownChakramEntityType,shooter,world,new ItemStack(ModItems.CHAKRAM.get()), new ItemStack(ModItems.CHAKRAM.get()));
    }

    private byte getLoyaltyFromItem(ItemStack pStack) {
        return this.level() instanceof ServerLevel serverlevel ? (byte) Mth.clamp(EnchantmentHelper.getTridentReturnToOwnerAcceleration(serverlevel, pStack, this), 0, 127) : 0;
    }


    @Override
    protected ItemStack getPickupItem() {return new ItemStack(ModItems.CHAKRAM.get());}

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(ModItems.CHAKRAM.get());
    }
    //this.ChakramItem.copy();}


    public Thrown_Chakram(EntityType<? extends AbstractArrow> type, Level world, LivingEntity entity, ItemStack stack) {
        super(type, entity, world, stack, stack);
        this.ChakramItem = stack.copy();
        this.entityData.set(ID_LOYALTY, this.getLoyaltyFromItem(stack));
        //this.entityData.set(ID_LOYALTY, (byte) EnchantmentHelper.getEnchantmentLevel(Enchantments.LOYALTY.getOrThrow(world),entity));
        this.entityData.set(ID_FOIL, stack.hasFoil());
    }
    public Thrown_Chakram(Level pLevel, double pX, double pY, double pZ, ItemStack pPickupItemStack) {
        super(ModEntities.CHAKRAM.get(), pX, pY, pZ, pLevel, pPickupItemStack, pPickupItemStack);
        this.entityData.set(ID_LOYALTY, this.getLoyaltyFromItem(pPickupItemStack));
        this.entityData.set(ID_FOIL, pPickupItemStack.hasFoil());
    }


    public void tick() {
        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }

        if (this.inGroundTime % 5 == 0) {
            this.makeParticle(13);
        }
        Entity entity = this.getOwner();
        int i = this.entityData.get(ID_LOYALTY);
        if (i > 0 && (this.dealtDamage || this.isNoPhysics()) && entity != null) {
            if (!this.isAcceptibleReturnOwner()) {
                if (!this.level().isClientSide && this.pickup == AbstractArrow.Pickup.ALLOWED) {
                    this.spawnAtLocation(this.getPickupItem(), 0.1F);
                }

                this.discard();
            } else {
                this.setNoPhysics(true);
                Vec3 vec3 = entity.getEyePosition().subtract(this.position());
                this.setPosRaw(this.getX(), this.getY() + vec3.y * 0.015 * (double)i, this.getZ());
                if (this.level().isClientSide) {
                    this.yOld = this.getY();
                }

                double d0 = 0.05 * (double)i;
                this.setDeltaMovement(this.getDeltaMovement().scale(0.95).add(vec3.normalize().scale(d0)));
                if (this.clientSideReturnChakramTickCount == 0) {
                    this.playSound(SoundEvents.TRIDENT_RETURN, 10.0F, 1.0F);
                }

                this.clientSideReturnChakramTickCount++;
            }
        }

        super.tick();
    }

    private void makeParticle(int p_36877_) {
        /**int i = this.getTeamColor();
         if (i != -1 && p_36877_ > 0) {
         double d0 = (double)(i >> 16 & 255) / 255.0D;
         double d1 = (double)(i >> 8 & 255) / 255.0D;
         double d2 = (double)(i >> 0 & 255) / 255.0D;
         **/
        for(int j = 0; j < p_36877_; ++j) {
            this.level().addParticle(ParticleTypes.FLAME, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D),0,0,0);//, d0, d1, d2);
        }

    }
    private boolean isAcceptibleReturnOwner() {
        Entity entity = this.getOwner();
        if (entity != null && entity.isAlive()) {
            return !(entity instanceof ServerPlayer) || !entity.isSpectator();
        } else {
            return false;
        }
    }

    public boolean isFoil() {
        return this.entityData.get(ID_FOIL);
    }


    @Nullable
    protected EntityHitResult findHitEntity(Vec3 p_37575_, Vec3 p_37576_) {
        return this.dealtDamage ? null : super.findHitEntity(p_37575_, p_37576_);
    }

    protected void onHitEntity(EntityHitResult p_37573_) {
        Entity entity = p_37573_.getEntity();
        float f = 8.0F;


            Entity entity1 = this.getOwner();
            DamageSource damagesource = ModDamageTypes.getEntityDamageSource(this.level(),ModDamageTypes.CHAKRAMS,this.getOwner());
                    //this.damageSources().source(this, (Entity) (entity1 == null ? this : entity1));
        if(this.level() instanceof ServerLevel level) {
            if (entity instanceof LivingEntity livingentity) {
                f +=
                        EnchantmentHelper.modifyDamage(level,
                                this.ChakramItem,
                                livingentity,
                                damagesource,
                                3.0F);
            }
        }
            this.dealtDamage = true;
            SoundEvent soundevent = SoundEvents.TRIDENT_HIT;
            if (entity.hurt(damagesource, f)) {
                if (entity.getType() == EntityType.ENDERMAN) {
                    return;
                }
                if(this.level() instanceof ServerLevel level) {
                    if (entity instanceof LivingEntity livingEntity) {
                        //LivingEntity livingentity1 = (LivingEntity) entity;
                        //if (entity1 instanceof LivingEntity) {
                        EnchantmentHelper.doPostAttackEffects(level, livingEntity, ModDamageTypes.getEntityDamageSource(livingEntity.level(),ModDamageTypes.CHAKRAMS,this.getOwner()));
                        //EnchantmentHelper.doPostDamageEffects((LivingEntity)entity1, livingentity1);


                        this.doPostHurtEffects(livingEntity);
                    }

                }
            }

        this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01D, -0.1D, -0.01D));
        this.playSound(soundevent, 1.0F,  1.0F);
    }

    protected boolean tryPickup(Player player) {
        return super.tryPickup(player) || this.isNoPhysics() && this.ownedBy(player) && player.getInventory().add(this.getPickupItem());
    }


    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(ID_LOYALTY, (byte)0);
        pBuilder.define(ID_FOIL, false);
    }



    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.TRIDENT_HIT_GROUND;
    }

    public void playerTouch(Player p_37580_) {
        if (this.ownedBy(p_37580_) || this.getOwner() == null) {
            super.playerTouch(p_37580_);
        }

    }
    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        /*if (tag.contains("Eternal Flame", 10)) {
            this.ChakramItem = ItemStack(tag.getCompound("Eternal Flame"));
        }*/

        this.dealtDamage = tag.getBoolean("DealtDamage");
        this.entityData.set(ID_LOYALTY, this.getLoyaltyFromItem(this.getPickupItemStackOrigin()));
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        //tag.put("Eternal Flame", this.ChakramItem.save(tag.));
        tag.putBoolean("DealtDamage", this.dealtDamage);
    }

    //protected float getWaterInertia() {
      //  return 0.99F;
    //}


    public boolean shouldRender(double x, double y, double z) {
        return true;
    }


}

