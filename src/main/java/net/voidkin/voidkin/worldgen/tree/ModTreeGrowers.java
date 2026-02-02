package net.voidkin.voidkin.worldgen.tree;

import net.minecraft.world.level.block.grower.TreeGrower;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.world.ModConfiguredFeatures;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower DARK = new TreeGrower(Voidkin.MODID + ":dark",
            Optional.empty(), Optional.of(ModConfiguredFeatures.DARK_TREE_KEY), Optional.empty());
    public static final TreeGrower BLOOD = new TreeGrower(Voidkin.MODID + ":blood",
            Optional.empty(), Optional.of(ModConfiguredFeatures.BLOOD_TREE_KEY), Optional.empty());
    public static final TreeGrower VOID = new TreeGrower(Voidkin.MODID + ":void",
            Optional.empty(), Optional.of(ModConfiguredFeatures.VOID_TREE_KEY), Optional.empty());
}
