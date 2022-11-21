package ch.javacamp.botdetector.impl;

import java.net.InetAddress;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class IPResolver {

    private final ConcurrentMap<String, Optional<String>> cache = new ConcurrentHashMap<>();
    private final int cacheSize;

    public IPResolver() {
        this(2000);
    }

    public IPResolver(final int cacheSize) {
        this.cacheSize = cacheSize;
    }

    public Optional<String> getDomainName(final String ipAddress) {
        if (cache.size() > cacheSize) {
            cache.clear();
        }
        return cache.computeIfAbsent(ipAddress, this::getByIp);
    }

    private Optional<String> getByIp(final String ip) {
        Objects.requireNonNull(ip);
        final String ipCleaned = ip.trim().toLowerCase();
        try {
            final InetAddress inetAddr = InetAddress.getByName(ipCleaned);
            final String hostname = inetAddr.getCanonicalHostName();
            if (ipCleaned.equals(hostname)) {
                return Optional.empty();
            }

            // Todo: what about load balancing, short living mappings?
            if (ipCleaned.equals(InetAddress.getByName(hostname).getHostAddress())) {
                return Optional.of(hostname);
            }
        } catch (final Exception e) {
            // ignore all exceptions
        }

        return Optional.empty();
    }

}
