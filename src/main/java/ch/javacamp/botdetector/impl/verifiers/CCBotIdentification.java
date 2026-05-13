package ch.javacamp.botdetector.impl.verifiers;

import ch.javacamp.botdetector.IdentificationRule;
import ch.javacamp.botdetector.impl.utils.Sets;

import java.util.Set;

/**
 * Identifies Common Crawl's CCBot.
 * Large open-source web corpus used for training many LLMs.
 */
public class CCBotIdentification implements IdentificationRule {

    private static final Set<String> domains = Sets.unmodifiableSetOf("commoncrawl.org");

    @Override
    public boolean matches(final String userAgent) {
        return userAgent.contains("ccbot");
    }

    @Override
    public Set<String> domainSuffixes() {
        return domains;
    }

    @Override
    public String name() {
        return "ccbot";
    }
}

