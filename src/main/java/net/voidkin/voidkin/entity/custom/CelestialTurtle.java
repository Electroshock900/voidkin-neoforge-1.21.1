package net.voidkin.voidkin.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.Tags;
import net.voidkin.voidkin.entity.ModEntities;
import org.jetbrains.annotations.Nullable;

public class CelestialTurtle extends Animal implements FlyingAnimal {
    public CelestialTurtle(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new FlyingMoveControl(this, 10, false);
    }

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    @Override
    public void tick(){
        super.tick();
        if(this.level().isClientSide()){
            setupAnimationStates();
        }
    }

    private void setupAnimationStates(){
        if(this.idleAnimationTimeout<=0){
            this.idleAnimationTimeout = this.random.nextInt(40)+80;
            this.idleAnimationState.start(this.tickCount);
        }else{
            --this.idleAnimationTimeout;
        }

    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if(this.getPose() == Pose.STANDING){
            f = Math.min(pPartialTick * 6f, 1f);

        }else{f=0f;}
        this.walkAnimation.update(f,0.2f);

    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return false;
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob ageableMob) {
        return ModEntities.CELESTIAL_TURTLE.get().create(pLevel);
    }


    public static AttributeSupplier setAttributes(){
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH,13D)
                .add(Attributes.FOLLOW_RANGE, 24D)
                .add(Attributes.MOVEMENT_SPEED,0.3F)
                .add(Attributes.FLYING_SPEED, 0.47F)
                //.add(Attributes.)
                //.add(Attributes.JUMP_STRENGTH, 2.0F)
                .build();
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(1, new FloatGoal(this));
        //this.goalSelector.addGoal(2,new CactusBuddyMeleeAttackGoal(this,1.0D,true));
        //this.goalSelector.addGoal(2,new OwlEntity.OwlWanderGoal(this, 2.0D));
        this.goalSelector.addGoal(2,new RandomStrollGoal(this, 1.0D) );
        this.goalSelector.addGoal(3,new BreedGoal(this, 1.12D));
        this.goalSelector.addGoal(3,new TemptGoal(this,2, Ingredient.of(Tags.Items.SEEDS), false));

        //this.goalSelector.addGoal(2, new MeleeAttackGoal(this,1.2D,false));
        //this.goalSelector.addGoal(1,new RangedAttackGoal(this,3.0D,30,3.0F));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        //this.goalSelector.addGoal(2,new RandomSwimmingGoal(this, 2.0D,14) );
        //this.goalSelector.addGoal(2, new FishSwimGoal(this));
        /**
         this.targetSelector.addGoal(1, new HurtByTargetGoal(this));

         this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Mob.class, 10, true, false, (p_29932_) -> {
         return p_29932_ instanceof Creeper;
         //this.goalSelector.addGoal(3, new );
         }));**/

    }
    @Override
    public boolean canBeLeashed() {
        return true;//super.canBeLeashed(p_27406_);
    }
    @Override
    public boolean isFlying() {
        return !this.onGround();
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
    }
}
