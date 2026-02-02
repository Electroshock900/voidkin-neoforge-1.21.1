package net.voidkin.voidkin.entity.variants;

import java.util.Arrays;
import java.util.Comparator;

public enum WarTurtleVariant {
    PLAINS(0),
    DESERT(1),
    VOID(2);
    private static final WarTurtleVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(WarTurtleVariant::getId)).toArray(WarTurtleVariant[]::new);
    private final int id;

    WarTurtleVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static WarTurtleVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
