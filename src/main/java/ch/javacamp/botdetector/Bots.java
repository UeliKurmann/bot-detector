package ch.javacamp.botdetector;

import ch.javacamp.botdetector.impl.DetectorImpl;
import ch.javacamp.botdetector.impl.IdentifierImpl;

/**
 * The Bot Detector classifies user-agents as Bots. Classified bots can be verified using reverse dns lookups.
 */
public class Bots {

    private static final Identifier IDENTIFICATION_INSTANCE = new IdentifierImpl();
    private static final Detector DETECTOR_INSTANCE = new DetectorImpl();

    /**
     * Returns an identifier to identify various different bots. Together with an IP address the genuineness
     * of the bot can be verified.
     * @return the identifier
     */
    public static Identifier identifier() {
        return IDENTIFICATION_INSTANCE;
    }

    /**
     * Returns a detector to assess user agents.
     * @return the assessment.
     */
    public static Detector detector() {
        return DETECTOR_INSTANCE;
    }


}
