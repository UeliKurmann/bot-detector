package ch.javacamp.botdetector.impl.verifiers;

import ch.javacamp.botdetector.IdentificationRule;
import ch.javacamp.botdetector.impl.utils.Sets;

import java.util.Set;

/**
 * Identifies Diffbot's crawler.
 * Used for structured data extraction and knowledge graphs.
 */
public class DiffbotIdentification implements IdentificationRule {

    private static final Set<String> domains = Sets.unmodifiableSetOf("diffbot.com");

    @Override
    public boolean matches(final String userAgent) {
        return userAgent.contains("diffbot");
    }

    @Override
    public Set<String> domainSuffixes() {
        return domains;
    }

    @Override
    public String name() {
        return "diffbot";
    }
}

