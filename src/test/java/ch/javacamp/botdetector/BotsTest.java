package ch.javacamp.botdetector;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static ch.javacamp.botdetector.BotDescription.Verification;
import static org.assertj.core.api.Assertions.assertThat;

public class BotsTest {

    private final String googleLine = "66.249.79.195 |#|\"Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)\"";

    @Test
    @DisplayName("Detect GoogletBot as bot without identification and verification.")
    public void t1() {
        Assessment actual = Bots.detector().detect(TestUtils.parse(googleLine));
        assertThat(actual.isBot()).isTrue();
    }

    @Test
    @DisplayName("Detect GoogletBot and identify it as GoogleBot.")
    public void t2() {
        Optional<BotDescription> actual = Bots.detector().detect(TestUtils.parse(googleLine)).identify();
        assertThat(actual).hasValueSatisfying(x -> assertThat(x.verification()).isEqualTo(Verification.NOT_VERIFIED));
        assertThat(actual).hasValueSatisfying(x -> assertThat(x.name()).isEqualTo("googlebot"));
    }

    @Test
    @DisplayName("Detect GoogletBot with identification and successful verification.")
    public void t3() {
        Optional<BotDescription> actual = Bots.detector().detect(TestUtils.parse(googleLine)).identifyVerified();
        assertThat(actual).hasValueSatisfying(x -> assertThat(x.verification()).isEqualTo(Verification.VERIFIED));
        assertThat(actual).hasValueSatisfying(x -> assertThat(x.name()).isEqualTo("googlebot"));
    }

    @Test
    @DisplayName("Detect Fake GoogletBot with identification and with failed verification.")
    public void t4() {
        String fakeGoogleBot = "118.193.69.162 |#|\"Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)\"";
        Optional<BotDescription> actual = Bots.detector().detect(TestUtils.parse(fakeGoogleBot)).identifyVerified();
        assertThat(actual).hasValueSatisfying(x -> assertThat(x.verification()).isEqualTo(Verification.DECEIVED));
        assertThat(actual).hasValueSatisfying(x -> assertThat(x.name()).isEqualTo("googlebot"));
    }
}
