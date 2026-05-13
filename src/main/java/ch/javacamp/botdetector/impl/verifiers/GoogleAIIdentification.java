package ch.javacamp.botdetector.impl.verifiers;

import ch.javacamp.botdetector.IdentificationRule;
import ch.javacamp.botdetector.impl.utils.Sets;

import java.util.Set;

/**
 * Identifies Google's AI-specific crawlers (Google-Extended, Googleother).
 * Used specifically for Gemini/Bard AI training, separate from regular Googlebot indexing.
 */
public class GoogleAIIdentification implements IdentificationRule {

    private static final Set<String> domains = Sets.unmodifiableSetOf("googlebot.com", "google.com");

    @Override
    public boolean matches(final String userAgent) {
        return userAgent.contains("google-extended") || userAgent.contains("googleother");
    }

    @Override
    public Set<String> domainSuffixes() {
        return domains;
    }

    @Override
    public String name() {
        return "google-ai";
    }
}

