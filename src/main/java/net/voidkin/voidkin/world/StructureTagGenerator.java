package net.voidkin.voidkin.world;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.StructureTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.voidkin.voidkin.Voidkin;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;
public class StructureTagGenerator extends StructureTagsProvider {
    // Add structures to this tag to show on the Magic Map, detected by worldgen features avoiding landmarks and progression lock behavior
    public static final TagKey<Structure> LANDMARK = TagKey.create(Registries.STRUCTURE, Voidkin.prefix("landmark"));

    public StructureTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper helper) {
        super(output, provider, Voidkin.MODID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider p_256087_) {
        this.tag(LANDMARK).add(
        );
    }
}