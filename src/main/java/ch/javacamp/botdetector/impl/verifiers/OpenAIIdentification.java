package ch.javacamp.botdetector.impl.verifiers;

import ch.javacamp.botdetector.IdentificationRule;
import ch.javacamp.botdetector.impl.utils.Sets;

import java.util.Set;

/**
 * Identifies OpenAI bots (GPTBot, ChatGPT-User, OAI-SearchBot).
 * Used for training data collection and ChatGPT browsing.
 */
public class OpenAIIdentification implements IdentificationRule {

    private static final Set<String> domains = Sets.unmodifiableSetOf("oai.azure.com", "openai.com");

    @Override
    public boolean matches(final String userAgent) {
        return userAgent.contains("gptbot")
                || userAgent.contains("chatgpt-user")
                || userAgent.contains("oai-searchbot");
    }

    @Override
    public Set<String> domainSuffixes() {
        return domains;
    }

    @Override
    public String name() {
        return "openai";
    }
}

