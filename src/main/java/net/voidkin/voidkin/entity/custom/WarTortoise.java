package net.voidkin.voidkin.entity.custom;

import net.minecraft.Util;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.event.EventHooks;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.entity.variants.WarTortoiseVariant;
import net.voidkin.voidkin.item.armor.WarTortoiseArmor;
import net.voidkin.voidkin.menu.screens.custom.WarTortoiseMenu;
import net.voidkin.voidkin.entity.ModEntities;
import net.voidkin.voidkin.particles.ModParticles;
import org.jetbrains.annotations.Nullable;

public class WarTortoise extends TamableAnimal implements Saddleable,ContainerListener, HasCustomInventoryScreen {
    private static final EntityDataAccessor<Integer> VARIANT =
            SynchedEntityData.defineId(WarTortoise.class, EntityDataSerializers.INT);

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState sitDownAnimationState = new AnimationState();
    public final AnimationState sitPoseAnimationState = new AnimationState();
    public final AnimationState sitUpAnimationState = new AnimationState();

    public static final EntityDataAccessor<Long> LAST_POSE_CHANGE_TICK =
            SynchedEntityData.defineId(WarTortoise.class, EntityDataSerializers.LONG);

    private static final EntityDataAccessor<Boolean> HAS_TIER_1_CHEST =
            SynchedEntityData.defineId(WarTortoise.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> HAS_TIER_2_CHEST =
            SynchedEntityData.defineId(WarTortoise.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> HAS_TIER_3_CHEST =
            SynchedEntityData.defineId(WarTortoise.class, EntityDataSerializers.BOOLEAN);/*
    private static final EntityDataAccessor<Boolean> HAS_CANNONS =
            SynchedEntityData.defineId(WarTortoise.class, EntityDataSerializers.BOOLEAN);*/
    protected SimpleContainer inventory;

    private final int TIER_1_CHEST_SLOT = 2;
    private final int TIER_2_CHEST_SLOT = 3;
    private final int TIER_3_CHEST_SLOT = 4;

    public WarTortoise(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.createInventory();
    }

    public static AttributeSupplier setAttributes(){
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH,44D)
                .add(Attributes.ARMOR,12.0D)
                .add(Attributes.ARMOR_TOUGHNESS,13.3D)
                .add(Attributes.ATTACK_DAMAGE,3.0F)
                .add(Attributes.ATTACK_SPEED,1.0F)
                .add(Attributes.ATTACK_KNOCKBACK,1.0F)
                .add(Attributes.FOLLOW_RANGE, 24D)
                .add(Attributes.MOVEMENT_SPEED,0.2F).build();
    }

    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(1, new FloatGoal(this));
        //this.goalSelector.addGoal(2,new CactusBuddyMeleeAttackGoal(this,1.0D,true));
        this.goalSelector.addGoal(3,new BreedGoal(this, 1.12D));
        this.goalSelector.addGoal(3,new TemptGoal(this,2, Ingredient.of(Blocks.CACTUS),false));
        //this.goalSelector.addGoal(2, new MeleeAttackGoal(this,1.2D,false));
        //this.goalSelector.addGoal(1,new RangedAttackGoal(this,3.0D,30,3.0F));
        this.goalSelector.addGoal(2,new RandomStrollGoal(this, 1.0D) );
        this.goalSelector.addGoal(2,new RandomSwimmingGoal(this, 1.0D,1) );
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Mob.class, 10, true, false, (p_29932_) -> {
            return p_29932_ instanceof Creeper;
        }));

    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return ModEntities.WAR_TORTOISE.get().create(serverLevel);
    }
    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(Blocks.CACTUS.asItem());
    }
    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }
    /* TAMABLE*/
    @Override
    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        Item item = itemstack.getItem();

        Item itemForTaming = Items.APPLE;

        if(item == itemForTaming && !isTame()) {
            if(this.level().isClientSide()) {
                return InteractionResult.CONSUME;
            } else {
                if (!pPlayer.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                if (!EventHooks.onAnimalTame(this, pPlayer)) {
                    super.tame(pPlayer);
                    this.navigation.recomputePath();
                    this.setTarget(null);
                    this.level().broadcastEntityEvent(this, (byte)7);

                    toggleSitting();
                }

                return InteractionResult.SUCCESS;
            }
        }

        //else if(isTame() && pHand == InteractionHand.MAIN_HAND && ){

        else if(isTame() && pHand == InteractionHand.MAIN_HAND && !isFood(itemstack) && !pPlayer.isSecondaryUseActive()) {
            if (item instanceof WarTortoiseArmor) {
                if (!hasArmorOn()) {
                    this.inventory.setItem(0, itemstack);
                    pPlayer.getItemInHand(pHand).getItem().getDefaultInstance().shrink(1);
                    pPlayer.level().addParticle(ParticleTypes.FLAME, this.getRandomX(2d), this.getRandomY(), this.getRandomZ(2d), 0, 0, 0);
                    pPlayer.level().addParticle(ParticleTypes.FLAME, this.getRandomX(2d), this.getRandomY(), this.getRandomZ(2d), 0, 0, 0);
                    pPlayer.level().addParticle(ModParticles.VOID_FLAME.get(), this.getRandomX(2d), this.getRandomY(), this.getRandomZ(2d), 0, 0, 0);
                    pPlayer.level().addParticle(ModParticles.DEATH_SKULLS.get(), this.getRandomX(2d), this.getRandomY(), this.getRandomZ(2d), 0, 0, 0);
                    pPlayer.level().addParticle(ModParticles.DRIPPING_DEITY_BLOOD.get(), this.getRandomX(2d), this.getRandomY(), this.getRandomZ(2d), 0, 0, 0);
                }else{
                    Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(0, 64));
                }
            }
            /*else if (item instanceof ){//ItemStack(Blocks.CHEST.asItem()){
                if (!hasTier1Chest()) {
                    setChest(TIER_1_CHEST_SLOT, true);
                }if (!hasTier2Chest()&& hasTier1Chest()) {
                    setChest(TIER_2_CHEST_SLOT, true);
                }if (!hasTier3Chest() &&hasTier2Chest() && hasTier1Chest()) {
                    setChest(TIER_3_CHEST_SLOT, true);
                }
            }*/
            toggleSitting();
            return InteractionResult.SUCCESS;
        } else if (this.isTame()) {
            this.openCustomInventoryScreen(pPlayer);
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        return super.mobInteract(pPlayer, pHand);
    }
    public boolean isSitting() {
        return this.entityData.get(LAST_POSE_CHANGE_TICK) < 0L;
    }

    public boolean isInPoseTransition() {
        long i = this.getPoseTime();
        return i < (long)(this.isSitting() ? 40 : 52);
    }

    public boolean isVisuallySitting() {
        return this.getPoseTime() < 0L != this.isSitting();
    }

    private boolean isVisuallySittingDown() {
        return this.isSitting() && this.getPoseTime() < 40L && this.getPoseTime() >= 0L;
    }

    public void resetLastPoseChangeTick(long pLastPoseChangeTick) {
        this.entityData.set(LAST_POSE_CHANGE_TICK, pLastPoseChangeTick);
    }

    private void resetLastPoseChangeTickToFullStand(long pLastPoseChangedTick) {
        this.resetLastPoseChangeTick(Math.max(0L, pLastPoseChangedTick - 52L - 1L));
    }

    public long getPoseTime() {
        return this.level().getGameTime() - Math.abs(this.entityData.get(LAST_POSE_CHANGE_TICK));
    }

    @Override
    public boolean isSaddleable() {
        return true;
    }

    @Override
    public void equipSaddle(ItemStack itemStack, @Nullable SoundSource soundSource) {

    }

    @Override
    public boolean isSaddled() {
        return false;
    }

    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 40;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
        if (this.isVisuallySitting()) {
            this.sitUpAnimationState.stop();
            if (this.isVisuallySittingDown()) {
                this.sitDownAnimationState.startIfStopped(this.tickCount);
                this.sitPoseAnimationState.stop();
            } else {
                this.sitDownAnimationState.stop();
                this.sitPoseAnimationState.startIfStopped(this.tickCount);
            }
        } else {
            this.sitDownAnimationState.stop();
            this.sitPoseAnimationState.stop();
            this.sitUpAnimationState.animateWhen(this.isInPoseTransition() && this.getPoseTime() >= 0L, this.tickCount);
        }

    }

    /* VARIANTS */

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(VARIANT, 0);
        pBuilder.define(LAST_POSE_CHANGE_TICK, 0L);

        pBuilder.define(HAS_TIER_1_CHEST, false);
        pBuilder.define(HAS_TIER_2_CHEST, false);
        pBuilder.define(HAS_TIER_3_CHEST, false);

    }

    private int getTypeVariant() {
        return this.entityData.get(VARIANT);
    }

    public WarTortoiseVariant getVariant() {
        return WarTortoiseVariant.byId(this.getTypeVariant() & 255);
    }

    private void setVariant(WarTortoiseVariant variant) {
        this.entityData.set(VARIANT, variant.getId() & 255);
    }


    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Variant", this.getTypeVariant());
        pCompound.putLong("LastPoseTick", this.entityData.get(LAST_POSE_CHANGE_TICK));

        ListTag listtag = new ListTag();
        for (int x = 0; x < this.inventory.getContainerSize(); x++) {
            ItemStack itemstack = this.inventory.getItem(x);
            if (!itemstack.isEmpty()) {
                CompoundTag compoundtag = new CompoundTag();
                compoundtag.putByte("Slot", (byte)(x));
                listtag.add(itemstack.save(this.registryAccess(), compoundtag));
            }
        }
        pCompound.put("Items", listtag);
    }
    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.entityData.set(VARIANT, pCompound.getInt("Variant"));
        long i = pCompound.getLong("LastPoseTick");
        if (i < 0L) {
            this.setPose(Pose.SITTING);
        }
        this.resetLastPoseChangeTick(i);

        this.createInventory();
        ListTag listtag = pCompound.getList("Items", 10);
        for (int x = 0; x < listtag.size(); x++) {
            CompoundTag compoundtag = listtag.getCompound(x);
            int j = compoundtag.getByte("Slot") & 255;
            if (j < this.inventory.getContainerSize()) {
                this.inventory.setItem(j, ItemStack.parse(this.registryAccess(), compoundtag).orElse(ItemStack.EMPTY));
            }
        }
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty,
                                        MobSpawnType pSpawnType, @Nullable SpawnGroupData pSpawnGroupData) {
        WarTortoiseVariant variant = Util.getRandom(WarTortoiseVariant.values(), this.random);
        this.setVariant(variant);
        this.resetLastPoseChangeTickToFullStand(pLevel.getLevel().getGameTime());
        return super.finalizeSpawn(pLevel, pDifficulty, pSpawnType, pSpawnGroupData);
    }
    /*Tamable*/
    public void toggleSitting() {
        if (this.isSitting()) {
            standUp();
        } else {
            sitDown();
        }
    }

    public void sitDown() {
        if (!this.isSitting()) {
            this.makeSound(SoundEvents.CAMEL_SIT);
            this.setPose(Pose.SITTING);
            this.gameEvent(GameEvent.ENTITY_ACTION);
            this.resetLastPoseChangeTick(-this.level().getGameTime());
        }

        setOrderedToSit(true);
        setInSittingPose(true);
    }

    public void standUp() {
        if (this.isSitting()) {
            this.makeSound(SoundEvents.CAMEL_STAND);
            this.setPose(Pose.STANDING);
            this.gameEvent(GameEvent.ENTITY_ACTION);
            this.resetLastPoseChangeTick(this.level().getGameTime());
        }

        setOrderedToSit(false);
        setInSittingPose(false);
    }

    /* INVENTORY */

    @Override
    public void containerChanged(Container container) {
        if(container.getItem(TIER_1_CHEST_SLOT).is(Items.CHEST) && !hasTier1Chest()) {
            setChest(TIER_1_CHEST_SLOT, true);
        }
        if(container.getItem(TIER_2_CHEST_SLOT).is(Items.CHEST) && !hasTier2Chest()) {
            setChest(TIER_2_CHEST_SLOT, true);
        }
        if(container.getItem(TIER_3_CHEST_SLOT).is(Items.CHEST) && !hasTier3Chest()) {
            setChest(TIER_3_CHEST_SLOT, true);
        }

        if(!container.getItem(TIER_1_CHEST_SLOT).is(Items.CHEST) && hasTier1Chest()) {
            setChest(TIER_1_CHEST_SLOT, false);
            dropChestInventory(TIER_1_CHEST_SLOT);
        }
        if(!container.getItem(TIER_2_CHEST_SLOT).is(Items.CHEST) && hasTier2Chest()) {
            setChest(TIER_2_CHEST_SLOT, false);
            dropChestInventory(TIER_2_CHEST_SLOT);
        }
        if(!container.getItem(TIER_3_CHEST_SLOT).is(Items.CHEST) && hasTier3Chest()) {
            setChest(TIER_3_CHEST_SLOT, false);
            dropChestInventory(TIER_3_CHEST_SLOT);
        }

 if(container.getItem(0).getItem() instanceof WarTortoiseArmor) {
 setBodyArmorItem(container.getItem(0));
 }
 if(container.getItem(0).isEmpty() && isWearingBodyArmor()) {
 setBodyArmorItem(ItemStack.EMPTY);
 }
/*
 if(!container.getItem(1).isEmpty()) {
 this.entityData.set(DYE_STACK, container.getItem(1));
 }
 if(container.getItem(1).isEmpty()) {
 this.entityData.set(DYE_STACK, ItemStack.EMPTY);
 }*/
    }

    private void dropChestInventory(int slot) {
        if(slot == TIER_1_CHEST_SLOT) {
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(5, 64));
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(6, 64));
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(7, 64));
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(8, 64));
        }

        if(slot == TIER_2_CHEST_SLOT) {
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(9, 64));
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(10, 64));
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(11, 64));
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(12, 64));
        }

        if(slot == TIER_3_CHEST_SLOT) {
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(13, 64));
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(14, 64));
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(15, 64));
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(16, 64));
        }
    }

    public boolean hasTier1Chest() {
        return this.entityData.get(HAS_TIER_1_CHEST);
    }

    public boolean hasTier2Chest() {
        return this.entityData.get(HAS_TIER_2_CHEST);
    }

    public boolean hasTier3Chest() {
        return this.entityData.get(HAS_TIER_3_CHEST);
    }

    public void setChest(int slot, boolean chested) {
        if(slot == TIER_1_CHEST_SLOT) {
            this.entityData.set(HAS_TIER_1_CHEST, chested);
        } else if(slot == TIER_2_CHEST_SLOT) {
            this.entityData.set(HAS_TIER_2_CHEST, chested);
        } else if(slot == TIER_3_CHEST_SLOT) {
            this.entityData.set(HAS_TIER_3_CHEST, chested);
        } else {
            Voidkin.LOGGER.error("Can't give chest to a Slot that doesn't exist!");
        }
    }

    protected void createInventory() {
        SimpleContainer simplecontainer = this.inventory;
        this.inventory = new SimpleContainer(this.getInventorySize());
        if (simplecontainer != null) {
            simplecontainer.removeListener(this);
            int i = Math.min(simplecontainer.getContainerSize(), this.inventory.getContainerSize());

            for (int j = 0; j < i; j++) {
                ItemStack itemstack = simplecontainer.getItem(j);
                if (!itemstack.isEmpty()) {
                    this.inventory.setItem(j, itemstack.copy());
                }
            }
        }

        this.inventory.addListener(this);
    }

    public final int getInventorySize() {
        return getInventorySize(4);
    }

    public static int getInventorySize(int columns) {
        return columns * 3 + 5;
    }

    public boolean hasInventoryChanged(Container inventory) {
        return this.inventory != inventory;
    }

    @Override
    public void openCustomInventoryScreen(Player player) {
        if (!this.level().isClientSide && (!this.isVehicle() || this.hasPassenger(player)) && this.isTame()) {
            ServerPlayer serverPlayer = (ServerPlayer) player;
            if (player.containerMenu != player.inventoryMenu) {
                player.closeContainer();
            }

            serverPlayer.openMenu(new SimpleMenuProvider((ix, playerInventory, playerEntityx) ->
                    new WarTortoiseMenu(ix, playerInventory, this.inventory, this, 4), this.getDisplayName()), buf -> {
                buf.writeUUID(getUUID());
            });
        }
    }
    /* ARMOR */
    @Override
    protected void actuallyHurt(DamageSource damageSource, float damageAmount) {
        if (!this.canArmorAbsorb(damageSource)) {
            super.actuallyHurt(damageSource, damageAmount);
        } else {
            ItemStack itemstack = this.getBodyArmorItem();
            itemstack.hurtAndBreak(Mth.ceil(damageAmount), this, EquipmentSlot.BODY);

            if(itemstack.getItem() instanceof WarTortoiseArmor wartortoiseArmorItem) {
                int damagereducton = wartortoiseArmorItem.getDefense() / 2; // depends on what armor
                super.actuallyHurt(damageSource, Math.max(0, damageAmount - damagereducton));
            }
        }
    }

    private boolean canArmorAbsorb(DamageSource damageSource) {
        return this.hasArmorOn() && !damageSource.is(DamageTypeTags.BYPASSES_WOLF_ARMOR);
    }

    public boolean hasArmorOn() {
        return isWearingBodyArmor();
    }
}
