package net.voidkin.voidkin.chests;

import net.voidkin.voidkin.Voidkin;

import static net.voidkin.voidkin.chests.ModChestsTypes.*;

public enum ModChestsUpgradeType {

        IRON_TO_OBSIDIAN(IRON, OBSIDIAN),
        IRON_TO_GOLD(IRON, GOLD),
        GOLD_TO_DIAMOND(GOLD, DIAMOND),
        COPPER_TO_IRON(COPPER, IRON),
        DIAMOND_TO_CRYSTAL(DIAMOND, CRYSTAL),
        WOOD_TO_IRON(WOOD, IRON),
        WOOD_TO_COPPER(WOOD, COPPER),
        DIAMOND_TO_OBSIDIAN(DIAMOND, OBSIDIAN);

        public final String name;
        public final ModChestsTypes source;
        public final ModChestsTypes target;

        ModChestsUpgradeType(ModChestsTypes source, ModChestsTypes target) {
            this.name = Voidkin.toEnglishName(this.name());
            this.source = source;
            this.target = target;
        }

        public boolean canUpgrade(ModChestsTypes from) {
            return from == this.source;
        }

}
