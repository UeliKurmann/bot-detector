package ch.javacamp.botdetector.impl.verifiers;

import ch.javacamp.botdetector.IdentificationRule;
import ch.javacamp.botdetector.impl.utils.Sets;

import java.util.Set;

/**
 * Identifies Meta's AI training crawler (Meta-ExternalAgent).
 * Distinct from facebookexternalhit which is for link previews.
 */
public class MetaAIIdentification implements IdentificationRule {

    private static final Set<String> domains = Sets.unmodifiableSetOf("facebook.com", "meta.com", "fbsv.net");

    @Override
    public boolean matches(final String userAgent) {
        return userAgent.contains("meta-externalagent");
    }

    @Override
    public Set<String> domainSuffixes() {
        return domains;
    }

    @Override
    public String name() {
        return "meta-externalagent";
    }
}

