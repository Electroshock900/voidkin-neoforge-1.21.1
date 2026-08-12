package net.voidkin.voidkin.block.custom.fluids;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.item.ModItems;
import net.voidkin.voidkin.util.ModTags;

import java.util.List;

public class ShimmerFluidBlock extends LiquidBlock {
    public ShimmerFluidBlock(FlowingFluid fluid, Properties properties) {
        super(fluid, properties);
    }

    private static final String TAG_START_TIME = "TransmuteStart";
    private static final String TAG_RISING = "TransmuteRising";
    private static final int CHARGE_TIME = 40; // 2 seconds (20 ticks per second)

    private static void handleItemTransmutation(ItemEntity item, Level level, BlockPos pos) {
        ItemStack stack = item.getItem();
        CompoundTag tag = item.getPersistentData();

        // If this item is not eligible, ignore it
        if(!stack.is(ModTags.Items.VOIDKIN_CHANGEABLE)) return;
        /*if (!stack.is(Items.IRON_INGOT)) return;
        if (!stack.is(Items.GOLD_INGOT)) return;
        if (!stack.is(ModItems.CACTUS_SWORD)) return;
        if (!stack.is(ModItems.ANTI_CACTUS_SWORD)) return;*/

        // Start ritual if not started
        if (!tag.contains(TAG_START_TIME)) {
            tag.putInt(TAG_START_TIME, (int) level.getGameTime());
            tag.putBoolean(TAG_RISING, true);
        }

        long start = tag.getInt(TAG_START_TIME);
        long elapsed = level.getGameTime() - start;

        // Make the item rise slightly above the liquid
        if (tag.getBoolean(TAG_RISING)) {
            Vec3 posNow = item.position();
            double targetY = pos.getY() + 1.1; // hover above liquid

            if (posNow.y < targetY) {
                item.setDeltaMovement(0, 0.05, 0); // gentle lift
            } else {
                item.setDeltaMovement(0, 0, 0); // hold still
            }

            // Spawn ritual particles
            spawnRitualParticles(level, item);
        }

        // After 2 seconds, transmute
        if (elapsed >= CHARGE_TIME) {
            //if(stack)
            if(stack.is(ModItems.CACTUS_SWORD)){
                transmuteItem(item, level, ModItems.ANTI_CACTUS_SWORD);
            }
            if(stack.is(ModItems.ANTI_CACTUS_SWORD)){
                transmuteItem(item, level, ModItems.CACTUS_SWORD);
            }
            if(stack.is(Items.IRON_INGOT)){
                transmuteItem(item, level, Items.GOLD_INGOT);
            }
            if(stack.is(Items.GOLD_INGOT)){
                transmuteItem(item, level, Items.IRON_INGOT);
            }


            spawnRitualFinishedParticles(level, item);
        }
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        List<ItemEntity> items = level.getEntitiesOfClass(ItemEntity.class, new AABB(pos));

        for (ItemEntity item : items) {
            handleItemTransmutation(item, level, pos);
        }

        super.tick(state, level, pos, random);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof ItemEntity item) {
            List<ItemEntity> items = level.getEntitiesOfClass(ItemEntity.class, new AABB(pos));

            for (ItemEntity item2 : items) {
                handleItemTransmutation(item2, level, pos);
            }
        }
        super.entityInside(state, level, pos, entity);
    }

    private static void spawnRitualFinishedParticles(Level level, ItemEntity item){
        if (level.isClientSide) return;
        // Burst particles
        ((ServerLevel) level).sendParticles(
                ParticleTypes.SOUL_FIRE_FLAME,
                item.getX(), item.getY(), item.getZ(),
                20, 0.3, 0.3, 0.3, 0.02
        );
    }
    private static void spawnRitualParticles(Level level, ItemEntity item) {
        if (level.isClientSide) return;

        ((ServerLevel) level).sendParticles(
                ParticleTypes.ENCHANT, // swap for custom particle if you want
                item.getX(),
                item.getY() + 0.1,
                item.getZ(),
                6,      // count
                0.15,   // spread X
                0.1,    // spread Y
                0.15,   // spread Z
                0.01    // speed
        );
    }
    private static void transmuteItem(ItemEntity item, Level level, ItemLike output) {
        ItemStack stack = item.getItem();

        // Example: iron → gold
        ItemStack newStack = new ItemStack(output, stack.getCount());
        item.setItem(newStack);


        // Burst particles
        if(!level.isClientSide()){
        ((ServerLevel) level).sendParticles(
                ParticleTypes.FLAME,
                item.getX(), item.getY(), item.getZ(),
                20, 0.3, 0.3, 0.3, 0.02
        );
        }

        level.playSound(null, item.blockPosition(),
                SoundEvents.ANVIL_USE, SoundSource.BLOCKS, 1f, 1.2f);

        // Clear ritual tags
        CompoundTag tag = item.getPersistentData();


        tag.remove(TAG_START_TIME);
        tag.remove(TAG_RISING);
    }

    public static void convertItem(BlockPos pPos, BlockState pState, ItemEntity input, ItemStack result){
        //ItemStack inputItem = input.getItem();
        input.setItem(result);
    }

}
