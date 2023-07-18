package ch.javacamp.botdetector.impl;

import ch.javacamp.botdetector.BotDescription;
import ch.javacamp.botdetector.Bots;
import ch.javacamp.botdetector.RequestDescriptor;
import ch.javacamp.botdetector.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class BotVerifierImplTest {

    @Test
    public void t6() throws IOException{

        AtomicInteger verified = new AtomicInteger();
        AtomicInteger deceived = new AtomicInteger();
        try (final Stream<String> stream = Files.lines(Paths.get("src/test/data/", "verification.txt"))) {
            stream.forEach(line -> {
                RequestDescriptor descriptor = TestUtils.parse(line);
                BotDescription bd = Bots.detector().detect(descriptor).mapIfBot(Bots.identifier()::identifyVerified).orElse(BotDescription.UNKNOWN);
                if(bd.verification() == BotDescription.Verification.VERIFIED){
                    verified.incrementAndGet();
                }else{
                    deceived.incrementAndGet();
                }
            });
        }
        Assertions.assertEquals(verified.get(), 3);
        Assertions.assertEquals(deceived.get(), 3);

    }

}
