package net.voidkin.voidkin.entity.custom.cheesehorse;

import com.google.common.collect.UnmodifiableIterator;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.OldUsersConverter;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.ticks.ContainerSingleItem;
import net.neoforged.neoforge.common.CommonHooks;

import javax.annotation.Nullable;
import java.util.UUID;
import java.util.function.DoubleSupplier;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;

import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.voidkin.voidkin.entity.goals.RandomStandGoal;
import net.voidkin.voidkin.entity.goals.RunAroundLikeCrazyGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.voidkin.voidkin.menu.screens.custom.AbstractCheeseHorseMenu;


public class AbstractCheeseHorse extends TamableAnimal implements ContainerListener, HasCustomInventoryScreen, OwnableEntity, PlayerRideableJumping, Saddleable {
    public static final int EQUIPMENT_SLOT_OFFSET = 400;
    public static final int CHEST_SLOT_OFFSET = 499;
    public static final int INVENTORY_SLOT_OFFSET = 500;
    public static final double BREEDING_CROSS_FACTOR = 0.15;
    private static final float MIN_MOVEMENT_SPEED = (float)generateSpeed(() -> (double)0.0F);
    private static final float MAX_MOVEMENT_SPEED = (float)generateSpeed(() -> (double)1.0F);
    private static final float MIN_JUMP_STRENGTH = (float)generateJumpStrength(() -> (double)0.0F);
    private static final float MAX_JUMP_STRENGTH = (float)generateJumpStrength(() -> (double)1.0F);
    private static final float MIN_HEALTH = generateMaxHealth((p_272505_) -> 0);
    private static final float MAX_HEALTH = generateMaxHealth((p_272504_) -> p_272504_ - 1);
    private static final float BACKWARDS_MOVE_SPEED_FACTOR = 0.25F;
    private static final float SIDEWAYS_MOVE_SPEED_FACTOR = 0.5F;
    private static final Predicate<LivingEntity> PARENT_HORSE_SELECTOR = (p_30636_) -> p_30636_ instanceof AbstractCheeseHorse && ((AbstractCheeseHorse)p_30636_).isBred();
    private static final TargetingConditions MOMMY_TARGETING;
    private static final EntityDataAccessor<Byte> DATA_ID_FLAGS;
    private static final int FLAG_TAME = 2;
    private static final int FLAG_SADDLE = 4;
    private static final int FLAG_BRED = 8;
    private static final int FLAG_EATING = 16;
    private static final int FLAG_STANDING = 32;
    private static final int FLAG_OPEN_MOUTH = 64;
    public static final int INV_SLOT_SADDLE = 0;
    public static final int INV_BASE_COUNT = 1;
    private int eatingCounter;
    private int mouthCounter;
    private int standCounter;
    public int tailCounter;
    public int sprintCounter;
    protected boolean isJumping;
    protected SimpleContainer inventory;
    protected int temper;
    protected float playerJumpPendingScale;
    protected boolean allowStandSliding;
    private float eatAnim;
    private float eatAnimO;
    private float standAnim;
    private float standAnimO;
    private float mouthAnim;
    private float mouthAnimO;
    protected boolean canGallop = true;
    protected int gallopSoundCounter;
    @Nullable
    private UUID owner;
    private final Container bodyArmorAccess = new ContainerSingleItem() {
        public ItemStack getTheItem() {
            return AbstractCheeseHorse.this.getBodyArmorItem();
        }

        public void setTheItem(ItemStack p_323895_) {
            AbstractCheeseHorse.this.setBodyArmorItem(p_323895_);
        }

        public void setChanged() {
        }

        public boolean stillValid(Player p_324170_) {
            return p_324170_.getVehicle() == AbstractCheeseHorse.this || p_324170_.canInteractWithEntity(AbstractCheeseHorse.this, (double)4.0F);
        }
    };

    

    protected AbstractCheeseHorse(EntityType<? extends AbstractCheeseHorse> entityType, Level level) {
        super(entityType, level);
        this.createInventory();
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.2));
        this.goalSelector.addGoal(1, new RunAroundLikeCrazyGoal(this, 1.2));
        this.goalSelector.addGoal(2, new BreedGoal(this, (double)1.0F, AbstractCheeseHorse.class));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, (double)1.0F));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 0.7));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        if (this.canPerformRearing()) {
            this.goalSelector.addGoal(9, new RandomStandGoal(this));
        }

        this.addBehaviourGoals();
    }

    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(3, new TemptGoal(this, (double)1.25F, (p_335269_) -> p_335269_.is(ItemTags.HORSE_TEMPT_ITEMS), false));
    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_ID_FLAGS, (byte)0);
    }

    protected boolean getFlag(int flagId) {
        return ((Byte)this.entityData.get(DATA_ID_FLAGS) & flagId) != 0;
    }

    protected void setFlag(int flagId, boolean value) {
        byte b0 = (Byte)this.entityData.get(DATA_ID_FLAGS);
        if (value) {
            this.entityData.set(DATA_ID_FLAGS, (byte)(b0 | flagId));
        } else {
            this.entityData.set(DATA_ID_FLAGS, (byte)(b0 & ~flagId));
        }

    }

    public boolean isTamed() {
        return this.getFlag(2);
    }

    @Nullable
    public UUID getOwnerUUID() {
        return this.owner;
    }


    public void setOwnerUUID(@Nullable UUID uuid) {
        this.owner = uuid;
    }

    public boolean isJumping() {
        return this.isJumping;
    }

    public void setTamed(boolean tamed) {
        this.setFlag(2, tamed);
    }

    public void setIsJumping(boolean jumping) {
        this.isJumping = jumping;
    }

    public boolean handleLeashAtDistance(Entity leashHolder, float distance) {
        if (distance > 6.0F && this.isEating()) {
            this.setEating(false);
        }

        return true;
    }

    public boolean isEating() {
        return this.getFlag(16);
    }

    public boolean isStanding() {
        return this.getFlag(32);
    }

    public boolean isBred() {
        return this.getFlag(8);
    }

    public void setBred(boolean breeding) {
        this.setFlag(8, breeding);
    }

    public boolean isSaddleable() {
        return this.isAlive() && !this.isBaby() && this.isTamed();
    }

    public void equipSaddle(ItemStack stack, @Nullable SoundSource soundSource) {
        this.inventory.setItem(0, stack);
    }

    public void equipBodyArmor(Player player, ItemStack stack) {
        if (this.isBodyArmorItem(stack)) {
            this.setBodyArmorItem(stack.copyWithCount(1));
            stack.consume(1, player);
        }

    }

    public boolean isSaddled() {
        return this.getFlag(4);
    }

    public int getTemper() {
        return this.temper;
    }

    public void setTemper(int temper) {
        this.temper = temper;
    }

    public int modifyTemper(int addedTemper) {
        int i = Mth.clamp(this.getTemper() + addedTemper, 0, this.getMaxTemper());
        this.setTemper(i);
        return i;
    }

    public boolean isPushable() {
        return !this.isVehicle();
    }

    private void eating() {
        this.openMouth();
        if (!this.isSilent()) {
            SoundEvent soundevent = this.getEatingSound();
            if (soundevent != null) {
                this.level().playSound((Player)null, this.getX(), this.getY(), this.getZ(), soundevent, this.getSoundSource(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
            }
        }

    }

    public boolean causeFallDamage(float fallDistance, float multiplier, DamageSource source) {
        if (fallDistance > 1.0F) {
            this.playSound(SoundEvents.HORSE_LAND, 0.4F, 1.0F);
        }

        int i = this.calculateFallDamage(fallDistance, multiplier);
        if (i <= 0) {
            return false;
        } else {
            this.hurt(source, (float)i);
            if (this.isVehicle()) {
                for(Entity entity : this.getIndirectPassengers()) {
                    entity.hurt(source, (float)i);
                }
            }

            this.playBlockFallSound();
            return true;
        }
    }



    public static int getInventorySize(int columns) {
        return columns * 3 + 1;
    }
    public final int getInventorySize() {
        return getInventorySize(this.getInventoryColumns());
    }

    public void createInventory() {
        SimpleContainer simplecontainer = this.inventory;
        this.inventory = new SimpleContainer(14);
        if (simplecontainer != null) {
            simplecontainer.removeListener(this);
            int i = Math.min(simplecontainer.getContainerSize(), this.inventory.getContainerSize());

            for(int j = 0; j < i; ++j) {
                ItemStack itemstack = simplecontainer.getItem(j);
                if (!itemstack.isEmpty()) {
                    this.inventory.setItem(j, itemstack.copy());
                }
            }
        }

        this.inventory.addListener(this);
        this.syncSaddleToClients();
    }

    protected void syncSaddleToClients() {
        if (!this.level().isClientSide) {
            this.setFlag(4, !this.inventory.getItem(0).isEmpty());
        }

    }

    public void containerChanged(Container invBasic) {
        boolean flag = this.isSaddled();
        this.syncSaddleToClients();
        if (this.tickCount > 20 && !flag && this.isSaddled()) {
            this.playSound(this.getSaddleSoundEvent(), 0.5F, 1.0F);
        }

    }

    public boolean hurt(DamageSource source, float amount) {
        boolean flag = super.hurt(source, amount);
        if (flag && this.random.nextInt(3) == 0) {
            this.standIfPossible();
        }

        return flag;
    }

    protected boolean canPerformRearing() {
        return true;
    }

    @Nullable
    protected SoundEvent getEatingSound() {
        return null;
    }

    @Nullable
    protected SoundEvent getAngrySound() {
        return null;
    }

    protected void playStepSound(BlockPos pos, BlockState block) {
        if (!block.liquid()) {
            BlockState blockstate = this.level().getBlockState(pos.above());
            SoundType soundtype = block.getSoundType(this.level(), pos, this);
            if (blockstate.is(Blocks.SNOW)) {
                soundtype = blockstate.getSoundType(this.level(), pos, this);
            }

            if (this.isVehicle() && this.canGallop) {
                ++this.gallopSoundCounter;
                if (this.gallopSoundCounter > 5 && this.gallopSoundCounter % 3 == 0) {
                    this.playGallopSound(soundtype);
                } else if (this.gallopSoundCounter <= 5) {
                    this.playSound(SoundEvents.HORSE_STEP_WOOD, soundtype.getVolume() * 0.15F, soundtype.getPitch());
                }
            } else if (this.isWoodSoundType(soundtype)) {
                this.playSound(SoundEvents.HORSE_STEP_WOOD, soundtype.getVolume() * 0.15F, soundtype.getPitch());
            } else {
                this.playSound(SoundEvents.HORSE_STEP, soundtype.getVolume() * 0.15F, soundtype.getPitch());
            }
        }

    }

    private boolean isWoodSoundType(SoundType soundType) {
        return soundType == SoundType.WOOD || soundType == SoundType.NETHER_WOOD || soundType == SoundType.STEM || soundType == SoundType.CHERRY_WOOD || soundType == SoundType.BAMBOO_WOOD;
    }

    protected void playGallopSound(SoundType soundType) {
        this.playSound(SoundEvents.HORSE_GALLOP, soundType.getVolume() * 0.15F, soundType.getPitch());
    }

    public static AttributeSupplier.Builder createBaseAttributes() {
        return Mob.createMobAttributes().add(Attributes.JUMP_STRENGTH, 0.7).add(Attributes.MAX_HEALTH, (double)53.0F).add(Attributes.MOVEMENT_SPEED, (double)0.225F).add(Attributes.STEP_HEIGHT, (double)1.0F).add(Attributes.SAFE_FALL_DISTANCE, (double)6.0F).add(Attributes.FALL_DAMAGE_MULTIPLIER, (double)0.5F);
    }

    public int getMaxSpawnClusterSize() {
        return 6;
    }

    public int getMaxTemper() {
        return 100;
    }

    protected float getSoundVolume() {
        return 0.8F;
    }

    public int getAmbientSoundInterval() {
        return 400;
    }

    public void openCustomInventoryScreen(Player player) {
        if (!this.level().isClientSide && (!this.isVehicle() || this.hasPassenger(player)) && this.isTamed()) {
            player.openMenu(new SimpleMenuProvider((ix, inventory2, pPlayer2) ->
                    new AbstractCheeseHorseMenu(ix, inventory2,this.inventory, this,4),
                    this.getDisplayName()), buf -> {
                buf.writeUUID(getUUID());
            });
        }
        /*
        if (!this.level().isClientSide && (!this.isVehicle() || this.hasPassenger(player)) && this.isTamed()) {
            player.openHorseInventory(this, this.inventory);
        }*/

    }

    public InteractionResult fedFood(Player player, ItemStack stack) {
        boolean flag = this.handleEating(player, stack);
        if (flag) {
            stack.consume(1, player);
        }

        if (this.level().isClientSide) {
            return InteractionResult.CONSUME;
        } else {
            return flag ? InteractionResult.SUCCESS : InteractionResult.PASS;
        }
    }

    protected boolean handleEating(Player player, ItemStack stack) {
        boolean flag = false;
        float f = 0.0F;
        int i = 0;
        int j = 0;
        if (stack.is(Items.WHEAT)) {
            f = 2.0F;
            i = 20;
            j = 3;
        } else if (stack.is(Items.SUGAR)) {
            f = 1.0F;
            i = 30;
            j = 3;
        } else if (stack.is(Blocks.HAY_BLOCK.asItem())) {
            f = 20.0F;
            i = 180;
        } else if (stack.is(Items.APPLE)) {
            f = 3.0F;
            i = 60;
            j = 3;
        } else if (stack.is(Items.GOLDEN_CARROT)) {
            f = 4.0F;
            i = 60;
            j = 5;
            if (!this.level().isClientSide && this.isTamed() && this.getAge() == 0 && !this.isInLove()) {
                flag = true;
                this.setInLove(player);
            }
        } else if (stack.is(Items.GOLDEN_APPLE) || stack.is(Items.ENCHANTED_GOLDEN_APPLE) || stack.is(Items.MILK_BUCKET)) {
            f = 10.0F;
            i = 240;
            j = 10;
            if (!this.level().isClientSide && this.isTamed() && this.getAge() == 0 && !this.isInLove()) {
                flag = true;
                this.setInLove(player);
            }
        }

        if (this.getHealth() < this.getMaxHealth() && f > 0.0F) {
            this.heal(f);
            flag = true;
        }

        if (this.isBaby() && i > 0) {
            this.level().addParticle(ParticleTypes.HAPPY_VILLAGER, this.getRandomX((double)1.0F), this.getRandomY() + (double)0.5F, this.getRandomZ((double)1.0F), (double)0.0F, (double)0.0F, (double)0.0F);
            if (!this.level().isClientSide) {
                this.ageUp(i);
                flag = true;
            }
        }

        if (j > 0 && (flag || !this.isTamed()) && this.getTemper() < this.getMaxTemper() && !this.level().isClientSide) {
            this.modifyTemper(j);
            flag = true;
        }

        if (flag) {
            this.eating();
            this.gameEvent(GameEvent.EAT);
        }

        return flag;
    }

    protected void doPlayerRide(Player player) {
        this.setEating(false);
        this.setStanding(false);
        if (!this.level().isClientSide) {
            player.setYRot(this.getYRot());
            player.setXRot(this.getXRot());
            player.startRiding(this);
        }

    }

    public boolean isImmobile() {
        return super.isImmobile() && this.isVehicle() && this.isSaddled() || this.isEating() || this.isStanding();
    }

    public boolean isFood(ItemStack stack) {
        return stack.is(ItemTags.HORSE_FOOD);
    }

    private void moveTail() {
        this.tailCounter = 1;
    }

    protected void dropEquipment() {
        super.dropEquipment();
        if (this.inventory != null) {
            for(int i = 0; i < this.inventory.getContainerSize(); ++i) {
                ItemStack itemstack = this.inventory.getItem(i);
                if (!itemstack.isEmpty() && !EnchantmentHelper.has(itemstack, EnchantmentEffectComponents.PREVENT_EQUIPMENT_DROP)) {
                    this.spawnAtLocation(itemstack);
                }
            }
        }

    }

    public void aiStep() {
        if (this.random.nextInt(200) == 0) {
            this.moveTail();
        }

        super.aiStep();
        if (!this.level().isClientSide && this.isAlive()) {
            if (this.random.nextInt(900) == 0 && this.deathTime == 0) {
                this.heal(1.0F);
            }

            if (this.canEatGrass()) {
                if (!this.isEating() && !this.isVehicle() && this.random.nextInt(300) == 0 && this.level().getBlockState(this.blockPosition().below()).is(Blocks.GRASS_BLOCK)) {
                    this.setEating(true);
                }

                if (this.isEating() && ++this.eatingCounter > 50) {
                    this.eatingCounter = 0;
                    this.setEating(false);
                }
            }

            this.followMommy();
        }

    }

    protected void followMommy() {
        if (this.isBred() && this.isBaby() && !this.isEating()) {
            LivingEntity livingentity = this.level().getNearestEntity(AbstractCheeseHorse.class, MOMMY_TARGETING, this, this.getX(), this.getY(), this.getZ(), this.getBoundingBox().inflate((double)16.0F));
            if (livingentity != null && this.distanceToSqr(livingentity) > (double)4.0F) {
                this.navigation.createPath(livingentity, 0);
            }
        }

    }

    public boolean canEatGrass() {
        return true;
    }

    public void tick() {
        super.tick();
        if (this.mouthCounter > 0 && ++this.mouthCounter > 30) {
            this.mouthCounter = 0;
            this.setFlag(64, false);
        }

        if (this.isEffectiveAi() && this.standCounter > 0 && ++this.standCounter > 20) {
            this.standCounter = 0;
            this.setStanding(false);
        }

        if (this.tailCounter > 0 && ++this.tailCounter > 8) {
            this.tailCounter = 0;
        }

        if (this.sprintCounter > 0) {
            ++this.sprintCounter;
            if (this.sprintCounter > 300) {
                this.sprintCounter = 0;
            }
        }

        this.eatAnimO = this.eatAnim;
        if (this.isEating()) {
            this.eatAnim = this.eatAnim + (1.0F - this.eatAnim) * 0.4F + 0.05F;
            if (this.eatAnim > 1.0F) {
                this.eatAnim = 1.0F;
            }
        } else {
            this.eatAnim += (0.0F - this.eatAnim) * 0.4F - 0.05F;
            if (this.eatAnim < 0.0F) {
                this.eatAnim = 0.0F;
            }
        }

        this.standAnimO = this.standAnim;
        if (this.isStanding()) {
            this.eatAnim = 0.0F;
            this.eatAnimO = this.eatAnim;
            this.standAnim = this.standAnim + (1.0F - this.standAnim) * 0.4F + 0.05F;
            if (this.standAnim > 1.0F) {
                this.standAnim = 1.0F;
            }
        } else {
            this.allowStandSliding = false;
            this.standAnim += (0.8F * this.standAnim * this.standAnim * this.standAnim - this.standAnim) * 0.6F - 0.05F;
            if (this.standAnim < 0.0F) {
                this.standAnim = 0.0F;
            }
        }

        this.mouthAnimO = this.mouthAnim;
        if (this.getFlag(64)) {
            this.mouthAnim = this.mouthAnim + (1.0F - this.mouthAnim) * 0.7F + 0.05F;
            if (this.mouthAnim > 1.0F) {
                this.mouthAnim = 1.0F;
            }
        } else {
            this.mouthAnim += (0.0F - this.mouthAnim) * 0.7F - 0.05F;
            if (this.mouthAnim < 0.0F) {
                this.mouthAnim = 0.0F;
            }
        }

    }

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (!this.isVehicle() && !this.isBaby()) {
            if (this.isTamed() && player.isSecondaryUseActive()) {
                this.openCustomInventoryScreen(player);
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            } else {
                ItemStack itemstack = player.getItemInHand(hand);
                if (!itemstack.isEmpty()) {
                    InteractionResult interactionresult = itemstack.interactLivingEntity(player, this, hand);
                    if (interactionresult.consumesAction()) {
                        return interactionresult;
                    }

                    if (this.canUseSlot(EquipmentSlot.BODY) && this.isBodyArmorItem(itemstack) && !this.isWearingBodyArmor()) {
                        this.equipBodyArmor(player, itemstack);
                        return InteractionResult.sidedSuccess(this.level().isClientSide);
                    }
                }

                this.doPlayerRide(player);
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }
        } else {
            return super.mobInteract(player, hand);
        }
    }

    private void openMouth() {
        if (!this.level().isClientSide) {
            this.mouthCounter = 1;
            this.setFlag(64, true);
        }

    }

    public void setEating(boolean eating) {
        this.setFlag(16, eating);
    }

    public void setStanding(boolean standing) {
        if (standing) {
            this.setEating(false);
        }

        this.setFlag(32, standing);
    }

    @Nullable
    public SoundEvent getAmbientStandSound() {
        return this.getAmbientSound();
    }

    public void standIfPossible() {
        if (this.canPerformRearing() && this.isEffectiveAi()) {
            this.standCounter = 1;
            this.setStanding(true);
        }

    }

    public void makeMad() {
        if (!this.isStanding()) {
            this.standIfPossible();
            this.makeSound(this.getAngrySound());
        }

    }

    public boolean tameWithName(Player player) {
        this.setOwnerUUID(player.getUUID());
        this.setTamed(true);
        if (player instanceof ServerPlayer) {
            CriteriaTriggers.TAME_ANIMAL.trigger((ServerPlayer)player, this);
        }

        this.level().broadcastEntityEvent(this, (byte)7);
        return true;
    }

    protected void tickRidden(Player player, Vec3 travelVector) {
        super.tickRidden(player, travelVector);
        Vec2 vec2 = this.getRiddenRotation(player);
        this.setRot(vec2.y, vec2.x);
        this.yRotO = this.yBodyRot = this.yHeadRot = this.getYRot();
        if (this.isControlledByLocalInstance()) {
            if (travelVector.z <= (double)0.0F) {
                this.gallopSoundCounter = 0;
            }

            if (this.onGround()) {
                this.setIsJumping(false);
                if (this.playerJumpPendingScale > 0.0F && !this.isJumping()) {
                    this.executeRidersJump(this.playerJumpPendingScale, travelVector);
                }

                this.playerJumpPendingScale = 0.0F;
            }
        }

    }

    protected Vec2 getRiddenRotation(LivingEntity entity) {
        return new Vec2(entity.getXRot() * 0.5F, entity.getYRot());
    }

    protected Vec3 getRiddenInput(Player player, Vec3 travelVector) {
        if (this.onGround() && this.playerJumpPendingScale == 0.0F && this.isStanding() && !this.allowStandSliding) {
            return Vec3.ZERO;
        } else {
            float f = player.xxa * 0.5F;
            float f1 = player.zza;
            if (f1 <= 0.0F) {
                f1 *= 0.25F;
            }

            return new Vec3((double)f, (double)0.0F, (double)f1);
        }
    }

    protected float getRiddenSpeed(Player player) {
        return (float)this.getAttributeValue(Attributes.MOVEMENT_SPEED);
    }

    protected void executeRidersJump(float playerJumpPendingScale, Vec3 travelVector) {
        double d0 = (double)this.getJumpPower(playerJumpPendingScale);
        Vec3 vec3 = this.getDeltaMovement();
        this.setDeltaMovement(vec3.x, d0, vec3.z);
        this.setIsJumping(true);
        this.hasImpulse = true;
        CommonHooks.onLivingJump(this);
        if (travelVector.z > (double)0.0F) {
            float f = Mth.sin(this.getYRot() * ((float)Math.PI / 180F));
            float f1 = Mth.cos(this.getYRot() * ((float)Math.PI / 180F));
            this.setDeltaMovement(this.getDeltaMovement().add((double)(-0.4F * f * playerJumpPendingScale), (double)0.0F, (double)(0.4F * f1 * playerJumpPendingScale)));
        }

    }

    protected void playJumpSound() {
        this.playSound(SoundEvents.HORSE_JUMP, 0.4F, 1.0F);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("EatingHaystack", this.isEating());
        compound.putBoolean("Bred", this.isBred());
        compound.putInt("Temper", this.getTemper());
        compound.putBoolean("Tame", this.isTamed());
        if (this.getOwnerUUID() != null) {
            compound.putUUID("Owner", this.getOwnerUUID());
        }

        if (!this.inventory.getItem(0).isEmpty()) {
            compound.put("SaddleItem", this.inventory.getItem(0).save(this.registryAccess()));
        }

    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setEating(compound.getBoolean("EatingHaystack"));
        this.setBred(compound.getBoolean("Bred"));
        this.setTemper(compound.getInt("Temper"));
        this.setTamed(compound.getBoolean("Tame"));
        UUID uuid;
        if (compound.hasUUID("Owner")) {
            uuid = compound.getUUID("Owner");
        } else {
            String s = compound.getString("Owner");
            uuid = OldUsersConverter.convertMobOwnerIfNecessary(this.getServer(), s);
        }

        if (uuid != null) {
            this.setOwnerUUID(uuid);
        }

        if (compound.contains("SaddleItem", 10)) {
            ItemStack itemstack = (ItemStack)ItemStack.parse(this.registryAccess(), compound.getCompound("SaddleItem")).orElse(ItemStack.EMPTY);
            if (itemstack.is(Items.SADDLE)) {
                this.inventory.setItem(0, itemstack);
            }
        }

        this.syncSaddleToClients();
    }

    public boolean canMate(Animal otherAnimal) {
        return false;
    }

    public boolean canParent() {
        return !this.isVehicle() && !this.isPassenger() && this.isTamed() && !this.isBaby() && this.getHealth() >= this.getMaxHealth() && this.isInLove();
    }

    @Nullable
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return null;
    }

    protected void setOffspringAttributes(AgeableMob parent, AbstractCheeseHorse child) {
        this.setOffspringAttribute(parent, child, Attributes.MAX_HEALTH, (double)MIN_HEALTH, (double)MAX_HEALTH);
        this.setOffspringAttribute(parent, child, Attributes.JUMP_STRENGTH, (double)MIN_JUMP_STRENGTH, (double)MAX_JUMP_STRENGTH);
        this.setOffspringAttribute(parent, child, Attributes.MOVEMENT_SPEED, (double)MIN_MOVEMENT_SPEED, (double)MAX_MOVEMENT_SPEED);
    }

    private void setOffspringAttribute(AgeableMob parent, AbstractCheeseHorse child, Holder<Attribute> attribute, double min, double max) {
        double d0 = createOffspringAttribute(this.getAttributeBaseValue(attribute), parent.getAttributeBaseValue(attribute), min, max, this.random);
        child.getAttribute(attribute).setBaseValue(d0);
    }

    static double createOffspringAttribute(double value1, double value2, double min, double max, RandomSource random) {
        if (max <= min) {
            throw new IllegalArgumentException("Incorrect range for an attribute");
        } else {
            value1 = Mth.clamp(value1, min, max);
            value2 = Mth.clamp(value2, min, max);
            double d0 = 0.15 * (max - min);
            double d1 = Math.abs(value1 - value2) + d0 * (double)2.0F;
            double d2 = (value1 + value2) / (double)2.0F;
            double d3 = (random.nextDouble() + random.nextDouble() + random.nextDouble()) / (double)3.0F - (double)0.5F;
            double d4 = d2 + d1 * d3;
            if (d4 > max) {
                double d6 = d4 - max;
                return max - d6;
            } else if (d4 < min) {
                double d5 = min - d4;
                return min + d5;
            } else {
                return d4;
            }
        }
    }

    public float getEatAnim(float partialTick) {
        return Mth.lerp(partialTick, this.eatAnimO, this.eatAnim);
    }

    public float getStandAnim(float partialTick) {
        return Mth.lerp(partialTick, this.standAnimO, this.standAnim);
    }

    public float getMouthAnim(float partialTick) {
        return Mth.lerp(partialTick, this.mouthAnimO, this.mouthAnim);
    }

    public void onPlayerJump(int jumpPower) {
        if (this.isSaddled()) {
            if (jumpPower < 0) {
                jumpPower = 0;
            } else {
                this.allowStandSliding = true;
                this.standIfPossible();
            }

            if (jumpPower >= 90) {
                this.playerJumpPendingScale = 1.0F;
            } else {
                this.playerJumpPendingScale = 0.4F + 0.4F * (float)jumpPower / 90.0F;
            }
        }

    }

    public boolean canJump() {
        return this.isSaddled();
    }

    public void handleStartJump(int jumpPower) {
        this.allowStandSliding = true;
        this.standIfPossible();
        this.playJumpSound();
    }

    public void handleStopJump() {
    }

    protected void spawnTamingParticles(boolean tamed) {
        ParticleOptions particleoptions = tamed ? ParticleTypes.HEART : ParticleTypes.SMOKE;

        for(int i = 0; i < 7; ++i) {
            double d0 = this.random.nextGaussian() * 0.02;
            double d1 = this.random.nextGaussian() * 0.02;
            double d2 = this.random.nextGaussian() * 0.02;
            this.level().addParticle(particleoptions, this.getRandomX((double)1.0F), this.getRandomY() + (double)0.5F, this.getRandomZ((double)1.0F), d0, d1, d2);
        }

    }

    public void handleEntityEvent(byte id) {
        if (id == 7) {
            this.spawnTamingParticles(true);
        } else if (id == 6) {
            this.spawnTamingParticles(false);
        } else {
            super.handleEntityEvent(id);
        }

    }

    protected void positionRider(Entity passenger, Entity.MoveFunction callback) {
        super.positionRider(passenger, callback);
        if (passenger instanceof LivingEntity) {
            ((LivingEntity)passenger).yBodyRot = this.yBodyRot;
        }

    }

    protected static float generateMaxHealth(IntUnaryOperator operator) {
        return 15.0F + (float)operator.applyAsInt(8) + (float)operator.applyAsInt(9);
    }

    protected static double generateJumpStrength(DoubleSupplier supplier) {
        return (double)0.4F + supplier.getAsDouble() * 0.2 + supplier.getAsDouble() * 0.2 + supplier.getAsDouble() * 0.2;
    }

    protected static double generateSpeed(DoubleSupplier supplier) {
        return ((double)0.45F + supplier.getAsDouble() * 0.3 + supplier.getAsDouble() * 0.3 + supplier.getAsDouble() * 0.3) * (double)0.25F;
    }

    public boolean onClimbable() {
        return false;
    }

    public SlotAccess getSlot(int slot) {
        int i = slot - 400;
        if (i == 0) {
            return new SlotAccess() {
                public ItemStack get() {
                    return AbstractCheeseHorse.this.inventory.getItem(0);
                }

                public boolean set(ItemStack p_323682_) {
                    if (!p_323682_.isEmpty() && !p_323682_.is(Items.SADDLE)) {
                        return false;
                    } else {
                        AbstractCheeseHorse.this.inventory.setItem(0, p_323682_);
                        AbstractCheeseHorse.this.syncSaddleToClients();
                        return true;
                    }
                }
            };
        } else {
            int j = slot - 500 + 1;
            return j >= 1 && j < this.inventory.getContainerSize() ? SlotAccess.forContainer(this.inventory, j) : super.getSlot(slot);
        }
    }

    @Nullable
    public LivingEntity getControllingPassenger() {
        if (this.isSaddled()) {
            Entity entity = this.getFirstPassenger();
            if (entity instanceof Player) {
                return (Player)entity;
            }
        }

        return super.getControllingPassenger();
    }

    @Nullable
    private Vec3 getDismountLocationInDirection(Vec3 direction, LivingEntity passenger) {
        double d0 = this.getX() + direction.x;
        double d1 = this.getBoundingBox().minY;
        double d2 = this.getZ() + direction.z;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        UnmodifiableIterator var10 = passenger.getDismountPoses().iterator();

        while(var10.hasNext()) {
            Pose pose = (Pose)var10.next();
            blockpos$mutableblockpos.set(d0, d1, d2);
            double d3 = this.getBoundingBox().maxY + (double)0.75F;

            while(true) {
                double d4 = this.level().getBlockFloorHeight(blockpos$mutableblockpos);
                if ((double)blockpos$mutableblockpos.getY() + d4 > d3) {
                    break;
                }

                if (DismountHelper.isBlockFloorValid(d4)) {
                    AABB aabb = passenger.getLocalBoundsForPose(pose);
                    Vec3 vec3 = new Vec3(d0, (double)blockpos$mutableblockpos.getY() + d4, d2);
                    if (DismountHelper.canDismountTo(this.level(), passenger, aabb.move(vec3))) {
                        passenger.setPose(pose);
                        return vec3;
                    }
                }

                blockpos$mutableblockpos.move(Direction.UP);
                if ((double)blockpos$mutableblockpos.getY() < d3) {
                    break;
                }
            }
        }

        return null;
    }

    public Vec3 getDismountLocationForPassenger(LivingEntity livingEntity) {
        Vec3 vec3 = getCollisionHorizontalEscapeVector((double)this.getBbWidth(), (double)livingEntity.getBbWidth(), this.getYRot() + (livingEntity.getMainArm() == HumanoidArm.RIGHT ? 90.0F : -90.0F));
        Vec3 vec31 = this.getDismountLocationInDirection(vec3, livingEntity);
        if (vec31 != null) {
            return vec31;
        } else {
            Vec3 vec32 = getCollisionHorizontalEscapeVector((double)this.getBbWidth(), (double)livingEntity.getBbWidth(), this.getYRot() + (livingEntity.getMainArm() == HumanoidArm.LEFT ? 90.0F : -90.0F));
            Vec3 vec33 = this.getDismountLocationInDirection(vec32, livingEntity);
            return vec33 != null ? vec33 : this.position();
        }
    }

    protected void randomizeAttributes(RandomSource random) {
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        if (spawnGroupData == null) {
            spawnGroupData = new AgeableMob.AgeableMobGroupData(0.2F);
        }

        this.randomizeAttributes(level.getRandom());
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }

    public Container getInventory() {
        return this.inventory;
    }

    public boolean hasInventoryChanged(Container inventory) {
        return this.inventory != inventory;
    }

    public int getAmbientStandInterval() {
        return this.getAmbientSoundInterval();
    }

    protected Vec3 getPassengerAttachmentPoint(Entity entity, EntityDimensions dimensions, float partialTick) {
        return super.getPassengerAttachmentPoint(entity, dimensions, partialTick).add((new Vec3((double)0.0F, 0.15 * (double)this.standAnimO * (double)partialTick, -0.7 * (double)this.standAnimO * (double)partialTick)).yRot(-this.getYRot() * ((float)Math.PI / 180F)));
    }

    public final Container getBodyArmorAccess() {
        return this.bodyArmorAccess;
    }


    public int getInventoryColumns() {
        return 0;
    }

    static {
        MOMMY_TARGETING = TargetingConditions.forNonCombat().range((double)16.0F).ignoreLineOfSight().selector(PARENT_HORSE_SELECTOR);
        DATA_ID_FLAGS = SynchedEntityData.defineId(AbstractCheeseHorse.class, EntityDataSerializers.BYTE);
    }
}
