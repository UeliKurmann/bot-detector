package ch.javacamp.botdetector.impl.utils;

import java.util.Objects;
import java.util.Set;

public class Sets {

    @SafeVarargs
    public static <T> Set<T> unmodifiableSetOf(final T... elements) {
        Objects.requireNonNull(elements);
        return Set.of(elements);
    }
}
