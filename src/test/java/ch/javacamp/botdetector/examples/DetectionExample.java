package ch.javacamp.botdetector.examples;

import ch.javacamp.botdetector.Assessment;
import ch.javacamp.botdetector.Bots;
import ch.javacamp.botdetector.RequestDescriptor;

public class DetectionExample {

    public static void main(String[] args) {
        RequestDescriptor descriptor = RequestDescriptor.create("66.249.79.195", "... Googlebot/2.1; ...");
        Assessment actual = Bots.detector().detect(descriptor);
        if(actual.isBot()){
            System.out.println("a bot...");
        }
    }
}
