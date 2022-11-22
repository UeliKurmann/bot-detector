package ch.javacamp.botdetector.impl.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HashsTest {

    @Test
    @DisplayName("Happy Flow")
    public void t1() {
        final byte[] actual = Hashs.md5("happy-flow");
        final byte[] expected = new byte[]{76, -89, 119, 113, -114, -37, -114, -15, 110, -37, -109, 111, -77, -44, -51, -110};
        Assertions.assertThat(actual).isEqualTo(expected);
    }

}
