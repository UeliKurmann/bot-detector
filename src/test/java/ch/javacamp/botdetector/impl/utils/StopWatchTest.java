package ch.javacamp.botdetector.impl.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StopWatchTest {

    @Test
    @DisplayName("Happy Flow")
    void t1() throws Exception {
        final StopWatch testee = StopWatch.createStarted();
        Thread.sleep(3);
        Assertions.assertThat(testee.elapsedMilliseconds()).isGreaterThanOrEqualTo(3);
    }
}
