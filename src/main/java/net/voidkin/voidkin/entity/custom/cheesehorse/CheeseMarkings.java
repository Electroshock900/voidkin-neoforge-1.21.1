package net.voidkin.voidkin.entity.custom.cheesehorse;

import java.util.Arrays;
import java.util.Comparator;

public enum CheeseMarkings {
    NONE(0),
    SALAMI(1),
    MARBLE(2),
    PARMESAN(3),
    BLUE_CHEESE(4);

    private static final CheeseMarkings[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(CheeseMarkings::getId)).toArray(CheeseMarkings[]::new);
    private final int id;



    CheeseMarkings(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static CheeseMarkings byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
