package net.voidkin.voidkin.entity.variants;

import java.util.Arrays;
import java.util.Comparator;

public enum WarTortoiseHybridVariant {
    SHADOW(0),
    BONE_VOID(1),
    VOID(2);
    private static final WarTortoiseHybridVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(WarTortoiseHybridVariant::getId)).toArray(WarTortoiseHybridVariant[]::new);
    private final int id;

    WarTortoiseHybridVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static WarTortoiseHybridVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
