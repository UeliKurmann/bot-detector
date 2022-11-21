package ch.javacamp.botdetector.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class IPResolverTest {

    @Test
    @DisplayName("Happy Flow")
    public void t1() {
        final IPResolver testee = new IPResolver();
        final Optional<String> actual = testee.getDomainName("78.47.249.96");
        Assertions.assertThat(actual).hasValue("ms.smartness.ch");
    }

}
