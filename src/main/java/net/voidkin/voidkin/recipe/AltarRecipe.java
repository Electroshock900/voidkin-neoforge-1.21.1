package net.voidkin.voidkin.recipe;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.voidkin.voidkin.recipe.recipeinput.AltarRecipeInput;

public record AltarRecipe(NonNullList<Ingredient> ingredients, ItemStack output) implements Recipe<AltarRecipeInput> {
    @Override
    public NonNullList<Ingredient> getIngredients() {
        /*NonNullList<Ingredient> list = NonNullList.create();
        list.add(inputItem);
        return list;*/
        return ingredients;
    }

    public ItemStack getItem(int i){
        if (i==0){
            return ingredients.get(0).getItems()[0];
        }
        return ingredients.get(i).getItems()[0];
    }

    @Override
    public boolean matches(AltarRecipeInput sacrificeInput, Level level) {
        if(level.isClientSide()) {
            return false;
        }
        boolean matches = true;
        matches = ingredients.get(0).test(sacrificeInput.mainPedestalItem());

        if(!ingredients.get(0).test(sacrificeInput.mainPedestalItem())){
            return false;
        }
        for (int i=1; i<ingredients.size(); i++){
            //ItemStack stack : pContainer.sidePedestalItems()
            //LogUtils.getLogger().debug("Pedestal: " + i +", ItemStack:"+sacrificeInput.sidepedestals().get(i-1));
            if(!ingredients.get(i).test(sacrificeInput.sidepedestals().get(i-1))){
                return false;
            }

        }


        return matches;

        //return inputItem.test(sacrificeInput.getItem(0));
    }

    @Override
    public ItemStack assemble(AltarRecipeInput sacrificeInput, HolderLookup.Provider provider) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.ALTAR_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.ALTAR_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<AltarRecipe> {
        public static final MapCodec<AltarRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredients")
                        .flatXmap(list -> {
                            Ingredient[] aingredient = list.stream().filter(thing1 -> !thing1.isEmpty()).toArray(Ingredient[]::new);
                            return DataResult.success(NonNullList.of(Ingredient.EMPTY, aingredient));

                        },
                                DataResult::success
                        ).forGetter(AltarRecipe::getIngredients),
                ItemStack.CODEC.fieldOf("result").forGetter(AltarRecipe::output)
        ).apply(inst, AltarRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, AltarRecipe> STREAM_CODEC =
            StreamCodec.of(Serializer::toNetwork, Serializer::fromNetwork);

            /*StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, AltarRecipe::inputItem,
                        ItemStack.STREAM_CODEC, AltarRecipe::output,
                        AltarRecipe::new);*/


        @Override
        public MapCodec<AltarRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, AltarRecipe> streamCodec() {
            return STREAM_CODEC;
        }
        private static AltarRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            /*
            String s = buffer.readUtf();
            CraftingBookCategory craftingbookcategory = buffer.readEnum(CraftingBookCategory.class);
            */
            int i = buffer.readVarInt();
            NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i, Ingredient.EMPTY);
            nonnulllist.replaceAll(p_327214_ -> Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
            ItemStack itemstack = ItemStack.STREAM_CODEC.decode(buffer);
            return new AltarRecipe(nonnulllist, itemstack);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, AltarRecipe recipe) {
            /*buffer.writeUtf(recipe.group);
            buffer.writeEnum(recipe.category);*/


            buffer.writeVarInt(recipe.ingredients.size());

            for (Ingredient ingredient : recipe.ingredients) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, ingredient);
            }

            ItemStack.STREAM_CODEC.encode(buffer, recipe.output);
        }

    }
}