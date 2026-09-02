package net.voidkin.voidkin.item.custom;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.voidkin.voidkin.item.ModItems;
import net.voidkin.voidkin.util.ModTags;

import java.util.List;

public class EternalGobletItem extends Item {
    public EternalGobletItem(Properties properties) {
        super(properties);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        List<AreaEffectCloud> list = level.getEntitiesOfClass(AreaEffectCloud.class, player.getBoundingBox().inflate((double)2.0F), (p_350149_) -> p_350149_ != null && p_350149_.isAlive() && p_350149_.getOwner() instanceof EnderDragon);
        ItemStack itemstack = player.getItemInHand(hand);
        if (!list.isEmpty()) {
            AreaEffectCloud areaeffectcloud = (AreaEffectCloud)list.get(0);
            areaeffectcloud.setRadius(areaeffectcloud.getRadius() - 0.5F);
            level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_FILL_DRAGONBREATH, SoundSource.NEUTRAL, 1.0F, 2.0F);
            level.gameEvent(player, GameEvent.FLUID_PICKUP, player.position());
            if (player instanceof ServerPlayer) {
                ServerPlayer serverplayer = (ServerPlayer)player;
                CriteriaTriggers.PLAYER_INTERACTED_WITH_ENTITY.trigger(serverplayer, itemstack, areaeffectcloud);
            }

            return InteractionResultHolder.sidedSuccess(this.turnBottleIntoItem(itemstack, player, ModItems.ETERNAL_GOBLET_DRAGON_BREATH.toStack()), level.isClientSide());
        } else {
            BlockHitResult blockhitresult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
            if (blockhitresult.getType() == HitResult.Type.MISS) {
                return InteractionResultHolder.pass(itemstack);
            } else {
                if (blockhitresult.getType() == HitResult.Type.BLOCK) {
                    BlockPos blockpos = blockhitresult.getBlockPos();
                    if (!level.mayInteract(player, blockpos)) {
                        return InteractionResultHolder.pass(itemstack);
                    }

                    if(level.getFluidState(blockpos).is(FluidTags.WATER)){
                        level.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1.0F, -2.0F);
                        level.gameEvent(player,GameEvent.FLUID_PICKUP, blockpos);
                        return InteractionResultHolder.sidedSuccess(this.turnBottleIntoItem(itemstack, player, ModItems.ETERNAL_GOBLET_WATER.toStack()), level.isClientSide());
                    }
                    if(level.getFluidState(blockpos).is(ModTags.Fluids.BLOOD_FLUIDS)){
                        level.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1.0F, -2.0F);
                        level.gameEvent(player,GameEvent.FLUID_PICKUP, blockpos);
                        return InteractionResultHolder.sidedSuccess(this.turnBottleIntoItem(itemstack, player, ModItems.ETERNAL_GOBLET_BLOOD.toStack()), level.isClientSide());
                    }
                    if(level.getFluidState(blockpos).is(ModTags.Fluids.VOID_FLUIDS)){
                        level.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_FILL_DRAGONBREATH, SoundSource.NEUTRAL, 1.0F, -2.0F);
                        level.gameEvent(player,GameEvent.FLUID_PICKUP, blockpos);
                        return InteractionResultHolder.sidedSuccess(this.turnBottleIntoItem(itemstack, player, ModItems.ETERNAL_GOBLET_ABYSS.toStack()), level.isClientSide());
                    }
                    if(level.getFluidState(blockpos).is(ModTags.Fluids.DEITY_FLUIDS)){
                        level.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.EXPERIENCE_BOTTLE_THROW, SoundSource.NEUTRAL, 1.0F, -2.0F);
                        level.gameEvent(player,GameEvent.FLUID_PICKUP, blockpos);
                        return InteractionResultHolder.sidedSuccess(this.turnBottleIntoItem(itemstack, player, ModItems.ETERNAL_GOBLET_DEITY.toStack()), level.isClientSide());
                    }
                }

                return InteractionResultHolder.pass(itemstack);
            }
        }
    }

    protected ItemStack turnBottleIntoItem(ItemStack bottleStack, Player player, ItemStack filledBottleStack) {
        player.awardStat(Stats.ITEM_USED.get(this));
        return ItemUtils.createFilledResult(new ItemStack(this), player, filledBottleStack);
    }
}
