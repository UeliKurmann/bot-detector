package ch.javacamp.botdetector.impl.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class SetsTest {

    @Test
    public void t1() {
        final Set<String> set = Sets.unmodifiableSetOf("a", "b");
        Assertions.assertThat(set).contains("a", "b");
    }

}
