package ch.javacamp.botdetector;

public interface Detector {

    /**
     * Convenience method. See {@link Detector#detect(RequestDescriptor)}
     *
     * @param ip        the ip address of the request.
     * @param userAgent the user-agent string of the request.
     * @return the assessment.
     */
    default Assessment detect(String ip, String userAgent) {
        return detect(RequestDescriptor.create(ip, userAgent));
    }

    /**
     * Detects bots by their given user-agent. The user-agent is not verified and only
     * matched against common patterns. Known Bots are detected. There is no guarantee,
     * that the bot itself tells the truth. Each request may set the user-agent it wants.
     *
     * @param descriptor the descriptor of the request.
     * @return the assessment. for further investigations use {@link Identifier}.
     */
    Assessment detect(RequestDescriptor descriptor);

}
