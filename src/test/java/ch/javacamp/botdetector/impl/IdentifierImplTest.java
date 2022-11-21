package ch.javacamp.botdetector.impl;

import ch.javacamp.botdetector.*;
import ch.javacamp.botdetector.impl.utils.StopWatch;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class IdentifierImplTest {

    @Test
    @DisplayName("Identification of googlebot.")
    public void t1() {
        final IdentifierImpl testee = new IdentifierImpl();
        final BotDescription description = testee.identify(
                "Mozilla/5.0 (Linux; Android 6.0.1; Nexus 5X Build/MMB29P) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.84 Mobile Safari/537.36 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)");
        Assertions.assertThat(description.name()).isEqualTo("googlebot");
    }

    @Test
    @DisplayName("Identification of bingbot.")
    public void t2() {
        final Assessment response = Assessment.create(Assessment.Classification.BOT, "-", RequestDescriptor.create("1.1.1.1", "(compatible; bingbot;...)"));
        final IdentifierImpl testee = new IdentifierImpl();
        final BotDescription description = testee.identify(response);
        Assertions.assertThat(description.name()).isEqualTo("bingbot");
    }

    @Test
    @DisplayName("Simple performance tests for 100_000 user-agents.")
    public void perfTest() throws IOException {

        final Identifier identifier = new IdentifierImpl();
        final StopWatch watch = StopWatch.createStarted();
        try (final Stream<String> stream = Files.lines(Paths.get("src/test/data", "ua-100000.txt"))) {

            stream.forEach(line -> {
                RequestDescriptor descriptor = TestUtils.parse(line);
                identifier.identify(descriptor.userAgent());
            });

        }
        Assertions.assertThat(watch.elapsedMilliseconds()).isLessThan(5000);
    }

}
