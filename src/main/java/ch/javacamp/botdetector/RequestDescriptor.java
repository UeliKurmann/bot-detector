package ch.javacamp.botdetector;

import static java.util.Optional.ofNullable;

public class RequestDescriptor {

    private static final RequestDescriptor EMPTY = create("", "");

    private final String ip;
    private final String userAgent;

    private RequestDescriptor(String ip, String userAgent) {
        this.ip = ip;
        this.userAgent = ofNullable(userAgent).map(String::toLowerCase).map(String::trim).orElse("");
    }

    public static RequestDescriptor create(String ip, String userAgent) {
        return new RequestDescriptor(ip, userAgent);
    }

    public static RequestDescriptor empty() {
        return EMPTY;
    }

    public String ip() {
        return this.ip;
    }

    public String userAgent() {
        return this.userAgent;
    }

    @Override
    public String toString() {
        return String.format("[ip=%s; userAgent=%s]", ip, userAgent);
    }
}
