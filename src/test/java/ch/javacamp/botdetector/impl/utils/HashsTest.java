package ch.javacamp.botdetector.impl.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HashsTest {

    @Test
    @DisplayName("Happy Flow")
    public void t1() {
        final String actual = Hashs.md5("happy-flow");
        Assertions.assertThat(actual).isEqualTo("4CA777718EDB8EF16EDB936FB3D4CD92");
    }

}
