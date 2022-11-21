package ch.javacamp.botdetector.impl;

import ch.javacamp.botdetector.BotDescription;
import ch.javacamp.botdetector.IdentificationRule;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static ch.javacamp.botdetector.BotDescription.Verification.DECEIVED;
import static ch.javacamp.botdetector.BotDescription.Verification.VERIFIED;

/**
 * Current implementation can only verifiy the following bots:
 * <ul>
 * <li>Google Bot</li>
 * <li>Microsoft Bing Bot</li>
 * <li>Yandex</li>
 * <li>see ch.javacamp.impl.verifiers package.</li>
 * </ul>
 */
class BotVerifierImpl {

    private final List<IdentificationRule> rules = new ArrayList<>();
    private final IPResolver resolver = new IPResolver();
    private final ConcurrentMap<String, BotDescription> cache = new ConcurrentHashMap<>();

    public BotVerifierImpl() {
        rules.addAll(BotRules.ALL_BOTS);
    }

    public BotDescription verify(final String ip, final String userAgent) {
        if (cache.size() > 50000) {
            cache.clear();
        }
        return cache.computeIfAbsent(ip, x -> privateVerification(ip, userAgent.toLowerCase()));
    }

    private BotDescription privateVerification(final String ip, final String userAgent) {
        for (final IdentificationRule rule : rules) {
            if (rule.matches(userAgent)) {
                return resolver.getDomainName(ip)//
                        .filter(d -> this.checkDomains(rule, d))//
                        .map(d -> BotDescription.create(rule.name(), VERIFIED))//
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
