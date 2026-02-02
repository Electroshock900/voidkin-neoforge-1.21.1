package net.voidkin.voidkin.entity.variants;

import java.util.Arrays;
import java.util.Comparator;

public enum CheeseHorseVariant {
    SHADOW(0),
    BONE_VOID(1),
    VOID(2);
    private static final CheeseHorseVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(CheeseHorseVariant::getId)).toArray(CheeseHorseVariant[]::new);
    private final int id;

    CheeseHorseVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static CheeseHorseVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
