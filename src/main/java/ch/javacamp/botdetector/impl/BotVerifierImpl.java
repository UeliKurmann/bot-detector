package ch.javacamp.botdetector.impl;

import ch.javacamp.botdetector.BotDescription;
import ch.javacamp.botdetector.IdentificationRule;
import ch.javacamp.botdetector.impl.utils.Cache;

import java.util.List;

import static ch.javacamp.botdetector.BotDescription.Verification.DECEIVED;
import static ch.javacamp.botdetector.BotDescription.Verification.VERIFIED;

/**
 * Verifies bots using reverse DNS lookups.
 * Supported bots: see ch.javacamp.botdetector.impl.verifiers package.
 */
class BotVerifierImpl {

    private final List<IdentificationRule> rules;
    private final IPResolver resolver;
    private final Cache<String, BotDescription> cache;

    public BotVerifierImpl() {
        this.rules = BotRules.ALL_BOTS;
        this.resolver = new IPResolver();
        this.cache = Cache.create(50_000);
    }

    public BotDescription verify(final String ip, final String userAgent) {
        final String cacheKey = ip + "|" + userAgent.toLowerCase();
        return cache.getOrPut(cacheKey, () -> privateVerification(ip, userAgent.toLowerCase()));
    }

    private BotDescription privateVerification(final String ip, final String userAgent) {
        for (final IdentificationRule rule : rules) {
            if (rule.matches(userAgent)) {
                return resolver.getDomainName(ip)
                        .filter(d -> checkDomains(rule, d))
                        .map(d -> BotDescription.create(rule.name(), VERIFIED))
                        .orElse(BotDescription.create(rule.name(), DECEIVED));
            }
        }
        return BotDescription.UNKNOWN;
    }

    private boolean checkDomains(final IdentificationRule rule, final String hostname) {
        for (final String validDomainName : rule.domainSuffixes()) {
            if (hostname.endsWith(validDomainName)) {
                return true;
            }
        }
        return false;
    }
}
