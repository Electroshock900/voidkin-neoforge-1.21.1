package net.voidkin.voidkin.recipe.recipeinput;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

import java.util.List;

public record AltarRecipeInput(ItemStack mainPedestalItem, List<ItemStack> sidepedestals) implements RecipeInput {
    @Override
    public ItemStack getItem(int pIndex) {
        if(pIndex == 0){
            return mainPedestalItem;
        }
        return sidepedestals.get(pIndex-1);
    }

    @Override
    public int size() {
        return 9;
    }


}
