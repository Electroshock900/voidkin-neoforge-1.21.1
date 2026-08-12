package net.voidkin.voidkin.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public record TransmutationRecipe(
        ResourceLocation id,
        Ingredient input,
        ItemStack output
) {
    public class TransmutationRecipeCodec {
        public static final Codec<TransmutationRecipe> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        Ingredient.CODEC.fieldOf("input").forGetter(TransmutationRecipe::input),

                        ItemStack.CODEC.fieldOf("output").forGetter(TransmutationRecipe::output)
                ).apply(instance, (input, output) ->
                        new TransmutationRecipe(null, input, output)
                )
        );
    }
}
