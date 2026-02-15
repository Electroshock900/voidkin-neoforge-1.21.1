package net.voidkin.voidkin.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.block.ModBlocks;
import net.voidkin.voidkin.menu.screens.PedestalScreen;
import net.voidkin.voidkin.menu.screens.CrystallizerScreen;
import net.voidkin.voidkin.menu.screens.PolisherScreen;

import net.voidkin.voidkin.menu.screens.VoidAltarScreen;
import net.voidkin.voidkin.recipe.*;

import java.util.List;

@JeiPlugin
public class JEIVoidkinPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new CrystallizerRecipeCategory(
                registration.getJeiHelpers().getGuiHelper()));

        registration.addRecipeCategories(new PolisherRecipeCategory(
                registration.getJeiHelpers().getGuiHelper()));

        registration.addRecipeCategories(new VoidAltarRecipeCategory(
                registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        assert Minecraft.getInstance().level != null;
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<CrystallizerRecipe> crystallizerRecipes = recipeManager
                .getAllRecipesFor(ModRecipes.CRYSTALLIZER_TYPE.get()).stream().map(RecipeHolder::value).toList();
        registration.addRecipes(CrystallizerRecipeCategory.CRYSTALLIZER_RECIPE_RECIPE_TYPE, crystallizerRecipes);

        List<PolisherRecipe> polisherRecipes = recipeManager
                .getAllRecipesFor(ModRecipes.POLISHER_TYPE.get()).stream().map(RecipeHolder::value).toList();
        registration.addRecipes(PolisherRecipeCategory.POLISHER_RECIPE_RECIPE_TYPE, polisherRecipes);

        List<AltarRecipe> altarRecipes = recipeManager
                .getAllRecipesFor(ModRecipes.ALTAR_TYPE.get()).stream().map(RecipeHolder::value).toList();
        registration.addRecipes(VoidAltarRecipeCategory.SACRIFICE_RECIPE_TYPE, altarRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(CrystallizerScreen.class, 70, 30, 25, 20,
                CrystallizerRecipeCategory.CRYSTALLIZER_RECIPE_RECIPE_TYPE);
        registration.addRecipeClickArea(PolisherScreen.class, 70, 30, 25, 20,
                PolisherRecipeCategory.POLISHER_RECIPE_RECIPE_TYPE);
        registration.addRecipeClickArea(VoidAltarScreen.class, 12, 30, 25, 20,
                VoidAltarRecipeCategory.SACRIFICE_RECIPE_TYPE);
    }
    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.CRYSTALLIZER.get().asItem()),
                CrystallizerRecipeCategory.CRYSTALLIZER_RECIPE_RECIPE_TYPE);
    registration.addRecipeCatalyst(new ItemStack(ModBlocks.POLISHER.get().asItem()),
                PolisherRecipeCategory.POLISHER_RECIPE_RECIPE_TYPE);
    registration.addRecipeCatalyst(new ItemStack(ModBlocks.VOID_ALTAR.get().asItem()),
            VoidAltarRecipeCategory.SACRIFICE_RECIPE_TYPE);
    }
}