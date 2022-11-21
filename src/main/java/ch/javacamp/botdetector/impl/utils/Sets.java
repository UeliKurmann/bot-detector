package ch.javacamp.botdetector.impl.utils;

import java.util.*;

public class Sets {

    @SafeVarargs
    public static <T> Set<T> unmodifiableSetOf(final T... elements) {
        Objects.requireNonNull(elements);
        final Set<T> result = new HashSet<>(Arrays.asList(elements));
        return Collections.unmodifiableSet(result);
    }

}
