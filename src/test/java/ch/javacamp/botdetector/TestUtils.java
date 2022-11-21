package ch.javacamp.botdetector;

public class TestUtils {

    public static RequestDescriptor parse(String line){
        final String[] tokens = line.split("\\|#\\|");
        return RequestDescriptor.create(tokens[0].trim(), tokens[1].trim().toLowerCase());
    }
}
