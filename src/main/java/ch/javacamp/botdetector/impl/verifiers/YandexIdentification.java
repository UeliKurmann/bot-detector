package ch.javacamp.botdetector.impl.verifiers;

import ch.javacamp.botdetector.IdentificationRule;
import ch.javacamp.botdetector.impl.utils.Sets;

import java.util.Set;

public class YandexIdentification implements IdentificationRule {

    private static final Set<String> domains = Sets.unmodifiableSetOf("yandex.com", "yandex.ru", "yandex.net");

    @Override
    public boolean matches(final String userAgent) {
        return userAgent.contains("yandexbot");
    }

    @Override
    public Set<String> domainSuffixes() {
        return domains;
    }

    @Override
    public String name() {
        return "yandexbot";
    }

}
