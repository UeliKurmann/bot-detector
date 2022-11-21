package ch.javacamp.botdetector.impl.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class IOUtilsTest {

    @Test
    public void t() {
        final long count = IOUtils.lines("test-bots/bots-small.txt").count();
        Assertions.assertThat(count).isEqualTo(5);
    }

}
