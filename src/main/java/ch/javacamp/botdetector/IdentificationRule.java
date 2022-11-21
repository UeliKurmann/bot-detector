package ch.javacamp.botdetector;

import java.util.Set;

public interface IdentificationRule {

    /**
     * Matches the user-agent.
     *
     * @param userAgent the user-agent string.
     * @return true, if the given user-agent matches the rule.
     */
    boolean matches(String userAgent);

    /**
     * Verified domain suffixes of the bots. Used to verify the
     *
     * @return
     */
    Set<String> domainSuffixes();

    /**
     * The human-readable name of the bot.
     *
     * @return human-readable name of the bot.
     */
    String name();

}
