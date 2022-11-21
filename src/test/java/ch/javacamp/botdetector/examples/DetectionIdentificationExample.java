package ch.javacamp.botdetector.examples;

import ch.javacamp.botdetector.BotDescription;
import ch.javacamp.botdetector.Bots;
import ch.javacamp.botdetector.RequestDescriptor;

import java.util.Optional;

public class DetectionIdentificationExample {

    public static void main(String[] args) {
        RequestDescriptor descriptor = RequestDescriptor.create("66.249.79.195", "... Googlebot/2.1; ...");
        Optional<BotDescription> actual = Bots.detector().detect(descriptor).identifyVerified();

        actual.ifPresent(a -> System.out.println(a.name()));
        actual.ifPresent(a -> System.out.println(a.verification()));
    }
}
