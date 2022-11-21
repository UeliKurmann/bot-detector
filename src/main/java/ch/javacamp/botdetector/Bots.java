package ch.javacamp.botdetector;

import ch.javacamp.botdetector.impl.DetectorImpl;
import ch.javacamp.botdetector.impl.IdentifierImpl;

public class Bots {

    private static final Identifier IDENTIFICATION_INSTANCE = new IdentifierImpl();
    static final Detector DETECTOR_INSTANCE = new DetectorImpl();

    public static Identifier identifier() {
        return IDENTIFICATION_INSTANCE;
    }

    public static Detector detector() {
        return DETECTOR_INSTANCE;
    }


}
