package ch.javacamp.botdetector.impl.verifiers;

import ch.javacamp.botdetector.IdentificationRule;
import ch.javacamp.botdetector.impl.utils.Sets;

import java.util.Set;

/**
 * Identifies ByteDance's Bytespider bot.
 * Used by TikTok/ByteDance for crawling and AI training.
 */
public class BytespiderIdentification implements IdentificationRule {

    private static final Set<String> domains = Sets.unmodifiableSetOf("bytedance.com");

    @Override
    public boolean matches(final String userAgent) {
        return userAgent.contains("bytespider");
    }

    @Override
    public Set<String> domainSuffixes() {
        return domains;
    }

    @Override
    public String name() {
        return "bytespider";
    }
}

