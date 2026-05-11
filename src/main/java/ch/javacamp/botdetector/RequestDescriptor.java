package ch.javacamp.botdetector;

import static java.util.Optional.ofNullable;

public final class RequestDescriptor {

    private static final RequestDescriptor EMPTY = create("", "");

    private final String ip;
    private final String userAgent;
    private final String originalUserAgent;

    private RequestDescriptor(String ip, String userAgent) {
        this.ip = ip;
        this.originalUserAgent = ofNullable(userAgent).map(String::trim).orElse("");
        this.userAgent = this.originalUserAgent.toLowerCase();
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

    /**
     * Returns the lowercased user-agent string used for matching.
     */
    public String userAgent() {
        return this.userAgent;
    }

    /**
     * Returns the original user-agent string preserving the original casing.
     */
    public String originalUserAgent() {
        return this.originalUserAgent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestDescriptor that)) return false;
        return ip.equals(that.ip) && userAgent.equals(that.userAgent);
    }

    @Override
    public int hashCode() {
        return 31 * ip.hashCode() + userAgent.hashCode();
    }

    @Override
    public String toString() {
        return "RequestDescriptor[ip=%s, userAgent=%s]".formatted(ip, originalUserAgent);
    }
}
