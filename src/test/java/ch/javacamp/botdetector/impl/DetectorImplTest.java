package ch.javacamp.botdetector.impl;

import ch.javacamp.botdetector.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class DetectorImplTest {

    private static final String userAgent1 = "Mozilla/5.0 (Linux; Android 6.0.1; Nexus 5X Build/MMB29P) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.84 Mobile Safari/537.36 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)";
    private static final String ip1 = "66.249.66.11";

    @Test
    @DisplayName("Detect Googlebot in user-agent string.")
    public void t1() {
        final Detector detector = Bots.detector();
        final Assessment response = detector.detect(ip1, userAgent1);
        Assertions.assertThat(response.type()).isEqualTo(Assessment.Classification.BOT);
    }

    @Test
    @DisplayName("Test a list of crawlers/bots.")
    public void t3() throws Exception {
        final Detector detector = Bots.detector();
        final AtomicInteger i = new AtomicInteger();
        Files.readAllLines(Paths.get("src/test/data", "crawlers.txt")).forEach(line -> {

            final Assessment response = detector.detect("0.0.0.0", line);
            if (response.type() == Assessment.Classification.BOT) {
                i.incrementAndGet();
            }
        });
        Assertions.assertThat(i.get()).isGreaterThan(12500);
    }


    @Test
    @Disabled
    @DisplayName("Test of a mixed user/bots file.")
    public void t5() throws IOException {
        final Detector detector = Bots.detector();
        final AtomicInteger bots = new AtomicInteger();
        final AtomicInteger users = new AtomicInteger();

        try (Stream<String> stream = Files.lines(Paths.get("src/test/data", "ua-week.txt"))) {
            stream.forEach(line -> {
                RequestDescriptor descriptor = TestUtils.parse(line);
                final Assessment response = detector.detect(descriptor);
                if (response.type() == Assessment.Classification.BOT) {
                    bots.incrementAndGet();
                } else {
                    users.incrementAndGet();
                }
            });
        }

        Assertions.assertThat(bots.get()).isGreaterThan(8700);
        Assertions.assertThat(users.get()).isGreaterThan(187000);
    }
}
