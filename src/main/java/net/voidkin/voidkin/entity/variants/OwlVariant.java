package net.voidkin.voidkin.entity.variants;

import java.util.Arrays;
import java.util.Comparator;

public enum OwlVariant {
    MUD(0),
    MUD_HORNED(1),
    BONE(2),
    BONE_HORNED(3);
    private static final OwlVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(OwlVariant::getId)).toArray(OwlVariant[]::new);
    private final int id;

    OwlVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static OwlVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
