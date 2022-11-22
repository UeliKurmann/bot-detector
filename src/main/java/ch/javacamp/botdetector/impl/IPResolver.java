package ch.javacamp.botdetector.impl;

import ch.javacamp.botdetector.impl.utils.Cache;

import java.net.InetAddress;
import java.util.Objects;
import java.util.Optional;

public class IPResolver {

    private final Cache<String, Optional<String>> cache;

    public IPResolver() {
        this(2000);
    }

    public IPResolver(final int cacheSize) {
        this.cache = Cache.create(cacheSize);
    }

    public Optional<String> getDomainName(final String ipAddress) {
        return cache.getOrPut(ipAddress, () -> getByIp(ipAddress));
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
