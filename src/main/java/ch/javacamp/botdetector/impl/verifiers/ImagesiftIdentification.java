package ch.javacamp.botdetector.impl.verifiers;

import ch.javacamp.botdetector.IdentificationRule;
import ch.javacamp.botdetector.impl.utils.Sets;

import java.util.Set;

/**
 * Identifies ImagesiftBot by Hive AI.
 * Used for image classification and content moderation.
 */
public class ImagesiftIdentification implements IdentificationRule {

    private static final Set<String> domains = Sets.unmodifiableSetOf("imagesift.com");

    @Override
    public boolean matches(final String userAgent) {
        return userAgent.contains("imagesiftbot");
    }

    @Override
    public Set<String> domainSuffixes() {
        return domains;
    }

    @Override
    public String name() {
        return "imagesiftbot";
    }
}

