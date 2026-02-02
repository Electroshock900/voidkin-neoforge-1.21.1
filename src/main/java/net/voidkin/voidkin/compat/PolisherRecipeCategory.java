package net.voidkin.voidkin.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.block.ModBlocks;
import net.voidkin.voidkin.recipe.PolisherRecipe;
import org.jetbrains.annotations.Nullable;

public class PolisherRecipeCategory implements IRecipeCategory<PolisherRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "polisher");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Voidkin.MODID,
            "textures/gui/polisher_gui.png");

    public static final RecipeType<PolisherRecipe> POLISHER_RECIPE_RECIPE_TYPE =
            new RecipeType<>(UID, PolisherRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public PolisherRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.POLISHER.get()));
    }

    @Override
    public RecipeType<PolisherRecipe> getRecipeType() {
        return POLISHER_RECIPE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Polisher");
    }

    @Override
    public int getWidth() {
        return 176;
    }

    @Override
    public int getHeight() {
        return 85;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, PolisherRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 54, 34).addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 104, 34).addItemStack(recipe.getResultItem(null));
    }
}