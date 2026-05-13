package ch.javacamp.botdetector.impl.verifiers;

import ch.javacamp.botdetector.IdentificationRule;
import ch.javacamp.botdetector.impl.utils.Sets;

import java.util.Set;

/**
 * Identifies Cohere's web crawler.
 * Used for LLM training data by Cohere.
 */
public class CohereIdentification implements IdentificationRule {

    private static final Set<String> domains = Sets.unmodifiableSetOf("cohere.com");

    @Override
    public boolean matches(final String userAgent) {
        return userAgent.contains("cohere-ai");
    }

    @Override
    public Set<String> domainSuffixes() {
        return domains;
    }

    @Override
    public String name() {
        return "cohere-ai";
    }
}

