package ch.javacamp.botdetector.impl.verifiers;

import ch.javacamp.botdetector.IdentificationRule;
import ch.javacamp.botdetector.impl.utils.Sets;

import java.util.Set;

public class InaDlWebIdentification implements IdentificationRule {

    private static final Set<String> domains = Sets.unmodifiableSetOf();

    @Override
    public boolean matches(final String userAgent) {
        return userAgent.contains("ina dlweb");
    }

    @Override
    public Set<String> domainSuffixes() {
        return domains;
    }

    @Override
    public String name() {
        return "INA dlweb";
    }
}
