package net.voidkin.voidkin.item.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;

public record VoidGobletContext(boolean filled, @Nullable Fluid fluidType) {
    //public static final Codec<VoidGobletContext> CODEC = NonNullList.codecOf(FluidStack.OPTIONAL_CODEC).xmap(VoidGobletContext::new, VoidGobletContext::fluidType);
    //public static final StreamCodec<RegistryFriendlyByteBuf, VoidGobletContext> STREAM_CODEC;

    /*public static final Codec<VoidGobletContext> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.BOOL.fieldOf("filled").forGetter(VoidGobletContext::filled)
            ).apply(instance, VoidGobletContext::new));
*/
    /*public final boolean filled;
    public final Fluid fluidType;

    public VoidGobletContext( {
        this.filled = filled;
        this.fluidType = fluidType;
    }*/
    public static ItemStack createItemStack(Item item, Holder<Potion> potion) {
        ItemStack itemstack = new ItemStack(item);
        itemstack.set(DataComponents.POTION_CONTENTS, new PotionContents(potion));
        return itemstack;
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.filled,this.fluidType);
    }
    public boolean is(FluidType potion) {
        return this.fluidType.getFluidType()!=Fluids.EMPTY.getFluidType();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        } else {
            return obj instanceof VoidGobletContext vgc
                    && this.filled == vgc.filled
                    && this.fluidType == vgc.fluidType;
        }
    }
}
