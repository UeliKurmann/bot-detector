package ch.javacamp.botdetector.impl.verifiers;

import ch.javacamp.botdetector.IdentificationRule;
import ch.javacamp.botdetector.impl.utils.Sets;

import java.util.Set;

/**
 * Identifies Anthropic's ClaudeBot.
 * Used for AI training data collection.
 */
public class ClaudeIdentification implements IdentificationRule {

    private static final Set<String> domains = Sets.unmodifiableSetOf("anthropic.com");

    @Override
    public boolean matches(final String userAgent) {
        return userAgent.contains("claudebot") || userAgent.contains("claude-web");
    }

    @Override
    public Set<String> domainSuffixes() {
        return domains;
    }

    @Override
    public String name() {
        return "claudebot";
    }
}

