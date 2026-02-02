package net.voidkin.voidkin.entity.custom;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.PathType;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.Nullable;

public class PenguinEntity extends Animal {//implements WaterAnimal {

    //private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(PenguinEntity.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    /*public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;*/

    @Override
    public void tick(){
        super.tick();
        if(this.level().isClientSide()){

        }
    }

    private void setupAnimationStates(){
        if(this.idleAnimationTimeout<=0){
            this.idleAnimationTimeout = this.random.nextInt(40)+80;
            this.idleAnimationState.start(this.tickCount);
        }else{
            --this.idleAnimationTimeout;
        }
        /*if(this.isAttacking()&&attackAnimationTimeout<=0){
            attackAnimationTimeout = 20;
            attackAnimationState.start(this.tickCount);
        }else{
            --this.attackAnimationTimeout;
        }

        if(!this.isAttacking()){
            attackAnimationState.stop();
        }*/
    }
    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if(this.getPose() == Pose.STANDING){
            f = Math.min(pPartialTick * 6f, 1f);

        }else{f=0f;}
        this.walkAnimation.update(f,0.2f);

    }

    /*public void setAttacking(boolean attacking){
        this.entityData.set(ATTACKING,attacking);
    }

    public boolean isAttacking(){
        return this.entityData.get(ATTACKING);
    }

     */

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        //this.entityData.set(ATTACKING,false);
    }
    public PenguinEntity(EntityType<? extends PenguinEntity> pType, Level level) {
        super(pType, level);
        this.setPathfindingMalus(PathType.WATER, 0.0F);
        this.moveControl = new PenguinMoveControl(this);
        //this.step(1.0F);
        //this.moveControl = new OwlEntity.FishMoveControl(this);
    }


    public static AttributeSupplier setAttributes(){
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH,4D)
                .add(Attributes.FOLLOW_RANGE, 24D)
                .add(Attributes.MOVEMENT_SPEED,0.5F)
                .add(Attributes.FLYING_SPEED, 0.5F)
                //.add(Attributes.)
                //.add(Attributes.JUMP_STRENGTH, 2.0F)
                .build();
    }
    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(0, new PanicGoal(this,2.0D));
        this.goalSelector.addGoal(1, new FloatGoal(this));
        //this.goalSelector.addGoal(1,new RangedAttackGoal(this,3.0D,30,3.0F));
        //this.goalSelector.addGoal(2,new CactusBuddyMeleeAttackGoal(this,1.0D,true));
        //this.goalSelector.addGoal(2, new MeleeAttackGoal(this,1.2D,false));
        this.goalSelector.addGoal(2,new RandomStrollGoal(this, 1.0D) );
        this.goalSelector.addGoal(2,new RandomSwimmingGoal(this, 2.0D,14) );

        this.goalSelector.addGoal(3,new BreedGoal(this, 1.12D));
        this.goalSelector.addGoal(3,new TemptGoal(this,2, Ingredient.of(Items.SNOWBALL), false));

        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        /**
         this.targetSelector.addGoal(1, new HurtByTargetGoal(this));

         this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Mob.class, 10, true, false, (p_29932_) -> {
            return p_29932_ instanceof Creeper;
        //this.goalSelector.addGoal(3, new );
        }));**/

    }
    @Override
    public boolean canBeLeashed() {
        return false;//super.canBeLeashed(p_27406_);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(Tags.Items.FOODS_RAW_FISH);
    }

    static class PenguinMoveControl extends MoveControl {
        private final PenguinEntity penguin;

        PenguinMoveControl(PenguinEntity p_30286_) {
            super(p_30286_);
            this.penguin = p_30286_;
        }

        private void updateSpeed() {
            if (this.penguin.isInWater()) {
                this.penguin.setDeltaMovement(this.penguin.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
                //if (!this.penguin.getHomePos().closerToCenterThan(this.penguin.position(), 16.0D)) {
                    this.penguin.setSpeed(Math.max(this.penguin.getSpeed() / 2.0F, 0.08F));
                //}

                if (this.penguin.isBaby()) {
                    this.penguin.setSpeed(Math.max(this.penguin.getSpeed() / 3.0F, 0.06F));
                }
            } else if (this.penguin.onGround()) {
                this.penguin.setSpeed(Math.max(this.penguin.getSpeed() / 2.0F, 0.06F));
            }

        }

        public void tick() {
            this.updateSpeed();
            if (this.operation == Operation.MOVE_TO && !this.penguin.getNavigation().isDone()) {
                double d0 = this.wantedX - this.penguin.getX();
                double d1 = this.wantedY - this.penguin.getY();
                double d2 = this.wantedZ - this.penguin.getZ();
                double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                if (d3 < (double)1.0E-5F) {
                    this.mob.setSpeed(0.0F);
                } else {
                    d1 /= d3;
                    float f = (float)(Mth.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                    this.penguin.setYRot(this.rotlerp(this.penguin.getYRot(), f, 90.0F));
                    this.penguin.yBodyRot = this.penguin.getYRot();
                    float f1 = (float)(this.speedModifier * this.penguin.getAttributeValue(Attributes.MOVEMENT_SPEED));
                    this.penguin.setSpeed(Mth.lerp(0.125F, this.penguin.getSpeed(), f1));
                    this.penguin.setDeltaMovement(this.penguin.getDeltaMovement().add(0.0D, (double)this.penguin.getSpeed() * d1 * 0.1D, 0.0D));
                }
            } else {
                this.penguin.setSpeed(0.0F);
            }
        }
    }


}
