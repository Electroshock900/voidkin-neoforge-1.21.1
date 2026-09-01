package net.voidkin.voidkin.entity.custom;

import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.neoforge.event.EventHooks;
import net.voidkin.voidkin.block.ModBlocks;
import net.voidkin.voidkin.entity.ModEntities;
import net.voidkin.voidkin.entity.custom.cheesehorse.AbstractCheeseHorse;
import net.voidkin.voidkin.entity.custom.cheesehorse.CheeseMarkings;
import net.voidkin.voidkin.entity.variants.CheeseHorseVariant;
import net.voidkin.voidkin.item.ModItems;

import javax.annotation.Nullable;
import java.util.Objects;

public class CheeseHorse extends AbstractCheeseHorse{
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT;
    private static final EntityDimensions BABY_DIMENSIONS;

    public CheeseHorse(EntityType<? extends CheeseHorse> entityType, Level level) {
        super(entityType, level);
    }

    protected void randomizeAttributes(RandomSource random) {
        AttributeInstance var10000 = this.getAttribute(Attributes.MAX_HEALTH);
        Objects.requireNonNull(random);
        var10000.setBaseValue((double)generateMaxHealth(random::nextInt)+5D);
        var10000 = this.getAttribute(Attributes.MOVEMENT_SPEED);
        Objects.requireNonNull(random);
        var10000.setBaseValue(generateSpeed(random::nextDouble));
        var10000 = this.getAttribute(Attributes.JUMP_STRENGTH);
        Objects.requireNonNull(random);
        var10000.setBaseValue(generateJumpStrength(random::nextDouble)+0.5D);
    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_ID_TYPE_VARIANT, 0);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", this.getTypeVariant());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setTypeVariant(compound.getInt("Variant"));
    }

    private void setTypeVariant(int typeVariant) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, typeVariant);
    }

    private int getTypeVariant() {
        return (Integer)this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    private void setVariantAndMarkings(CheeseHorseVariant variant, CheeseMarkings marking) {
        this.setTypeVariant(variant.getId() & 255 | marking.getId() << 8 & '\uff00');
    }

    public CheeseHorseVariant getVariant() {
        return CheeseHorseVariant.byId(this.getTypeVariant() & 255);
    }

    public void setVariant(Variant variant) {
        this.setTypeVariant(variant.getId() & 255 | this.getTypeVariant() & -256);
    }

    public CheeseMarkings getMarkings() {
        return CheeseMarkings.byId((this.getTypeVariant() & '\uff00') >> 8);
    }

    public void containerChanged(Container invBasic) {
        ItemStack itemstack = this.getBodyArmorItem();
        super.containerChanged(invBasic);
        ItemStack itemstack1 = this.getBodyArmorItem();
        if (this.tickCount > 20 && this.isBodyArmorItem(itemstack1) && itemstack != itemstack1) {
            this.playSound(SoundEvents.HORSE_ARMOR, 0.5F, 1.0F);
        }

    }

    protected void playGallopSound(SoundType soundType) {
        super.playGallopSound(soundType);
        if (this.random.nextInt(10) == 0) {
            this.playSound(SoundEvents.HORSE_BREATHE, soundType.getVolume() * 0.6F, soundType.getPitch() * 1.2f);
        }

    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.HORSE_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.HORSE_DEATH;
    }

    @Nullable
    protected SoundEvent getEatingSound() {
        return SoundEvents.HORSE_EAT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.HORSE_HURT;
    }

    protected SoundEvent getAngrySound() {
        return SoundEvents.HORSE_ANGRY;
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

    public boolean canMate(Animal otherAnimal) {
        if (otherAnimal == this) {
            return false;
        } else {
            return !(otherAnimal instanceof Donkey) && !(otherAnimal instanceof CheeseHorse) ? false : this.canParent() && ((AbstractCheeseHorse)otherAnimal).canParent();
        }
    }

    @Nullable
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {

            CheeseHorse horse = (CheeseHorse)otherParent;
            CheeseHorse horse1 = (CheeseHorse) ModEntities.CHEESE_HORSE.get().create(level);
            if (horse1 != null) {
                int i = this.random.nextInt(9);
                CheeseHorseVariant variant;
                if (i < 4) {
                    variant = this.getVariant();
                } else if (i < 8) {
                    variant = horse.getVariant();
                } else {
                    variant = (CheeseHorseVariant) Util.getRandom(CheeseHorseVariant.values(), this.random);
                }

                int j = this.random.nextInt(5);
                CheeseMarkings markings;
                if (j < 2) {
                    markings = this.getMarkings();
                } else if (j < 4) {
                    markings = horse.getMarkings();
                } else {
                    markings = (CheeseMarkings)Util.getRandom(CheeseMarkings.values(), this.random);
                }

                horse1.setVariantAndMarkings(variant, markings);
                this.setOffspringAttributes(otherParent, horse1);
            }

            return horse1;

    }

    public boolean canUseSlot(EquipmentSlot slot) {
        return true;
    }

    public boolean isBodyArmorItem(ItemStack stack) {
        Item var3 = stack.getItem();
        if (var3 instanceof AnimalArmorItem animalarmoritem) {
            if (animalarmoritem.getBodyType() == AnimalArmorItem.BodyType.EQUESTRIAN) {
                return true;
            }
        }

        return false;
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        RandomSource randomsource = level.getRandom();
        CheeseHorseVariant variant;
        if (spawnGroupData instanceof CheeseHorse.CheeseHorseGroupData) {
            variant = ((CheeseHorse.CheeseHorseGroupData)spawnGroupData).variant;
        } else {
            variant = (CheeseHorseVariant)Util.getRandom(CheeseHorseVariant.values(), randomsource);
            spawnGroupData = new CheeseHorse.CheeseHorseGroupData(variant);
        }

        this.setVariantAndMarkings(variant, (CheeseMarkings)Util.getRandom(CheeseMarkings.values(), randomsource));
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }

    public EntityDimensions getDefaultDimensions(Pose pose) {
        return this.isBaby() ? BABY_DIMENSIONS : super.getDefaultDimensions(pose);
    }

    static {
        DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(CheeseHorse.class, EntityDataSerializers.INT);
        BABY_DIMENSIONS = ModEntities.CHEESE_HORSE.get().getDimensions().withAttachments(EntityAttachments.builder().attach(EntityAttachment.PASSENGER, 0.0F, ModEntities.CHEESE_HORSE.get().getHeight() + 0.125F, 0.0F)).scale(1.2F);
    }

    public static class CheeseHorseGroupData extends AgeableMob.AgeableMobGroupData {
        public final CheeseHorseVariant variant;

        public CheeseHorseGroupData(CheeseHorseVariant variant) {
            super(true);
            this.variant = variant;
        }
    }
}