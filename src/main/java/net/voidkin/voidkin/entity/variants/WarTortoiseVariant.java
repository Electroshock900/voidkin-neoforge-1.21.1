package net.voidkin.voidkin.entity.variants;

import java.util.Arrays;
import java.util.Comparator;

public enum WarTortoiseVariant {
    SHADOW(0),
    BONE_VOID(1),
    VOID(2);
    private static final WarTortoiseVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(WarTortoiseVariant::getId)).toArray(WarTortoiseVariant[]::new);
    private final int id;

    WarTortoiseVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static WarTortoiseVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
