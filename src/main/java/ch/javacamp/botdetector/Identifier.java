package ch.javacamp.botdetector;

public interface Identifier {

    default BotDescription identify(String ua) {
        return identify(Assessment.create(Assessment.Classification.BOT, "", RequestDescriptor.create("0.0.0.0", ua)));
    }

    /**
     * Identifies the bot due to the user-agent string.
     *
     * @param assessment the assessment
     * @return description of the bot.
     */
    BotDescription identify(Assessment assessment);

    /**
     * Verifies the identification with reverse dns lookups.
     * @param assessment the assessment
     * @return description of the bot.
     */
    BotDescription identifyVerified(Assessment assessment);

}