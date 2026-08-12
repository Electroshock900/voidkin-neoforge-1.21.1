package net.voidkin.voidkin.item.component;

import com.mojang.serialization.Codec;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;

import java.util.function.Function;

public record QuiverContent(NonNullList<ItemStack> contents) implements ContainerAccessor{

    public static final Codec<QuiverContent> CODEC = NonNullList.codecOf(ItemStack.OPTIONAL_CODEC).xmap(QuiverContent::new, QuiverContent::contents);
    public static final StreamCodec<RegistryFriendlyByteBuf, QuiverContent> STREAM_CODEC = ItemStack.OPTIONAL_STREAM_CODEC.apply(ByteBufCodecs.list())
            .map(NonNullList::copyOf, Function.identity())
            .map(QuiverContent::new, QuiverContent::contents);
    @Override
    public NonNullList<ItemStack> getContent() {
        return contents;
    }
}
