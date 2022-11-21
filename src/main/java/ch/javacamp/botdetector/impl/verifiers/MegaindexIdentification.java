package ch.javacamp.botdetector.impl.verifiers;

import ch.javacamp.botdetector.IdentificationRule;
import ch.javacamp.botdetector.impl.utils.Sets;

import java.util.Set;

public class MegaindexIdentification implements IdentificationRule {

    private static final Set<String> domains = Sets.unmodifiableSetOf("your-server.de");

    @Override
    public boolean matches(final String userAgent) {
        return userAgent.contains("megaindex.ru");
    }

    @Override
    public Set<String> domainSuffixes() {
        return domains;
    }

    @Override
    public String name() {
        return "megaindexbot";
    }
}
