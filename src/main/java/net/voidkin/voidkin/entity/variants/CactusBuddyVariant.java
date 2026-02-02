package net.voidkin.voidkin.entity.variants;

import java.util.Arrays;
import java.util.Comparator;

public enum CactusBuddyVariant {
    REGULAR(0),
    BLOOD(1),
    DRY(2);
    private static final CactusBuddyVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(CactusBuddyVariant::getId)).toArray(CactusBuddyVariant[]::new);
    private final int id;

    CactusBuddyVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static CactusBuddyVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
