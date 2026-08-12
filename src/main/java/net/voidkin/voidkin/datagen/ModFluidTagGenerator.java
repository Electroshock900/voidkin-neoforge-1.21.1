package net.voidkin.voidkin.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.fluid.ModFluids;
import net.voidkin.voidkin.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModFluidTagGenerator extends FluidTagsProvider {
    public ModFluidTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, Voidkin.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        this.tag(ModTags.Fluids.VOID_FLUIDS).add(new Fluid[]{ModFluids.SOURCE_DEITY_BLOOD.get(), ModFluids.FLOWING_DEITY_BLOOD.get()});
        //this.tag(FluidTags.LAVA).add(new Fluid[]{Fluids.LAVA, Fluids.FLOWING_LAVA});
    }
}
