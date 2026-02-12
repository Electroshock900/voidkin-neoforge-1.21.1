package net.voidkin.voidkin.item.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.EventHooks;
import net.voidkin.voidkin.entity.ModEntities;
import net.voidkin.voidkin.item.ModItems;
import net.voidkin.voidkin.item.projectiles.AresArrowItem;
import net.voidkin.voidkin.item.projectiles.Cactus_Spine_Item;

import java.util.List;
import java.util.function.Predicate;

public class CactusBowItem extends BowItem {
    public static final Predicate<ItemStack> ARROW_ONLY = (arrow) -> arrow.is(ModItems.CACTUS_SPINE.asItem());
    public static final int MAX_DRAW_DURATION = 20;
    public static final int DEFAULT_RANGE = 20;

    public CactusBowItem(Properties properties) {
        //super(ModMaterials.CACTUS,0,0.0F,properties);
        super(properties);
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return ARROW_ONLY;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        pPlayer.startUsingItem(pHand);
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        boolean flag = !pPlayer.getProjectile(itemstack).isEmpty();

        InteractionResultHolder<ItemStack> ret = EventHooks.onArrowNock(itemstack, pLevel, pPlayer, pHand, flag);
        if (ret != null) return ret;

        if (!pPlayer.getAbilities().instabuild && !flag) {
            return InteractionResultHolder.fail(itemstack);
        } else {
            pPlayer.startUsingItem(pHand);
            return InteractionResultHolder.consume(itemstack);
        }

     //   return InteractionResultHolder.consume(player.getItemInHand(hand));
    }
    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof Player player) {
            ItemStack itemstack = player.getProjectile(stack);
            if (!itemstack.isEmpty()) {
                int i = this.getUseDuration(stack, entityLiving) - timeLeft;
                i = EventHooks.onArrowLoose(stack, level, player, i, !itemstack.isEmpty());
                if (i < 0) return;
                float f = getPowerForTime(i);
                if (!((double)f < 0.1)) {
                    List<ItemStack> list = draw(stack, itemstack, player);
                    if (level instanceof ServerLevel serverlevel /**&& !list.isEmpty()**/) {

                            //AbstractArrow arrow = new AresArrow(ModEntities.ARESARROW.get(),level);
                            //arrow.setBaseDamage(13.0); // Stronger than normal
                            //arrow.setRemainingFireTicks(13); // Fire effect
                            this.shoot(serverlevel, player, player.getUsedItemHand(), stack, list, f * 3.0F, 1.0F, f == 1.0F, null);
                            //level.addFreshEntity(arrow);
                    }

                    level.playSound(
                            null,
                            player.getX(),
                            player.getY(),
                            player.getZ(),
                            SoundEvents.ARROW_SHOOT,
                            SoundSource.PLAYERS,
                            1.0F,
                            1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F
                    );

                    player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
        super.releaseUsing(stack, level, entityLiving, timeLeft);
    }

    /**@Override
    protected void shootProjectile(
            LivingEntity shooter, Projectile projectile, int index, float velocity, float inaccuracy, float angle, @javax.annotation.Nullable LivingEntity target
    ) {
        projectile.shootFromRotation(shooter, shooter.getXRot(), shooter.getYRot() + angle, 0.0F, velocity, inaccuracy);
    }**/
    @Override
    protected Projectile createProjectile(Level level, LivingEntity shooter, ItemStack weapon, ItemStack ammo, boolean isCrit) {
        Item var8 = ammo.getItem();
        Cactus_Spine_Item var10000;
        if (var8 instanceof Cactus_Spine_Item arrowitem1) {
            var10000 = arrowitem1;
        } else {
            var10000 = (Cactus_Spine_Item) ModItems.CACTUS_SPINE.asItem();
        }

        Cactus_Spine_Item arrowitem = var10000;
        AbstractArrow abstractarrow = arrowitem.createArrow(level, ammo, shooter, weapon);
        if (isCrit) {
            abstractarrow.setCritArrow(true);
        }

        return this.customArrow(abstractarrow, ammo, weapon);
    }

    /**
     * Gets the velocity of the arrow entity from the bow's charge
     */
    public static float getPowerForTime(int charge) {
        float f = (float)charge / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
       public UseAnim getUseAnimation (ItemStack p_41452_){
           return UseAnim.BOW;
       }

       @Override
       public int getUseDuration (ItemStack p_41454_, LivingEntity pEntity){
           return 72000;//super.getUseDuration(p_41454_);
       }

       public AbstractArrow customArrow (AbstractArrow arrow){
           return ModEntities.SPINE.get().create(arrow.level());
       }

       public int getDefaultProjectileRange () {
           return DEFAULT_RANGE;
       }

   }