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
import net.voidkin.voidkin.recipe.AltarRecipe;
import org.jetbrains.annotations.Nullable;

public class VoidAltarRecipeCategory implements IRecipeCategory<AltarRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "altar_crafting");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Voidkin.MODID,
            "textures/gui/altar_gui2.png");

    public static final RecipeType<AltarRecipe> SACRIFICE_RECIPE_TYPE =
            new RecipeType<>(UID, AltarRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public VoidAltarRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.VOID_ALTAR.get()));
    }

    @Override
    public RecipeType<AltarRecipe> getRecipeType() {
        return SACRIFICE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Altar");
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
    public void setRecipe(IRecipeLayoutBuilder builder, AltarRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT,80, 35).addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.INPUT,80, 7).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.INPUT,106, 7).addIngredients(recipe.getIngredients().get(2));
        builder.addSlot(RecipeIngredientRole.INPUT,52, 35).addIngredients(recipe.getIngredients().get(3));
        builder.addSlot(RecipeIngredientRole.INPUT,52, 7).addIngredients(recipe.getIngredients().get(4));
        builder.addSlot(RecipeIngredientRole.INPUT,106, 35).addIngredients(recipe.getIngredients().get(5));
        builder.addSlot(RecipeIngredientRole.INPUT,52, 62).addIngredients(recipe.getIngredients().get(6));
        builder.addSlot(RecipeIngredientRole.INPUT,80, 62).addIngredients(recipe.getIngredients().get(7));
        builder.addSlot(RecipeIngredientRole.INPUT,106, 62).addIngredients(recipe.getIngredients().get(8));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 120, 34).addItemStack(recipe.getResultItem(null));
    }
}