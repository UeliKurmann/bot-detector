package ch.javacamp.botdetector.impl.verifiers;

import ch.javacamp.botdetector.IdentificationRule;
import ch.javacamp.botdetector.impl.utils.Sets;

import java.util.Set;

public class SeekportIdentification implements IdentificationRule {

    private static final Set<String> domains = Sets.unmodifiableSetOf("007ac9.net");

    @Override
    public boolean matches(final String userAgent) {
        return userAgent.contains("seekport crawler");
    }

    @Override
    public Set<String> domainSuffixes() {
        return domains;
    }

    @Override
    public String name() {
        return "seekport crawler";
    }
}
