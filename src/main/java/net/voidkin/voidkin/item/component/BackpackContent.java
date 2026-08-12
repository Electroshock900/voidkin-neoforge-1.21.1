package net.voidkin.voidkin.item.component;

import com.mojang.serialization.Codec;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;

import javax.print.DocFlavor;
import java.util.function.Function;

public record BackpackContent(NonNullList<ItemStack> contents) implements ContainerAccessor{

    public static final Codec<BackpackContent> CODEC = NonNullList.codecOf(ItemStack.OPTIONAL_CODEC).xmap(BackpackContent::new, BackpackContent::contents);
    public static final StreamCodec<RegistryFriendlyByteBuf, BackpackContent> STREAM_CODEC = ItemStack.OPTIONAL_STREAM_CODEC.apply(ByteBufCodecs.list())
            .map(NonNullList::copyOf, Function.identity())
            .map(BackpackContent::new, BackpackContent::contents);
    @Override
    public NonNullList<ItemStack> getContent() {
        return contents;
    }
}
