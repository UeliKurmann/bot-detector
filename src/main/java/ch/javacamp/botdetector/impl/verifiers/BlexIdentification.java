package ch.javacamp.botdetector.impl.verifiers;

import ch.javacamp.botdetector.IdentificationRule;
import ch.javacamp.botdetector.impl.utils.Sets;

import java.util.Set;

public class BlexIdentification implements IdentificationRule {

    private static final Set<String> domains = Sets.unmodifiableSetOf("webmeup.com");

    @Override
    public boolean matches(final String userAgent) {
        return userAgent.contains("blexbot");
    }

    @Override
    public Set<String> domainSuffixes() {
        return domains;
    }

    @Override
    public String name() {
        return "blexbot";
    }
}
