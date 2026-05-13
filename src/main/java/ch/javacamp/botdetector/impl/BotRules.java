package ch.javacamp.botdetector.impl;

import ch.javacamp.botdetector.IdentificationRule;
import ch.javacamp.botdetector.impl.verifiers.*;

import java.util.List;

class BotRules {

    public static final List<IdentificationRule> ALL_BOTS = List.of(
            // AI Crawlers (before general bots for specific matching)
            new OpenAIIdentification(),
            new ClaudeIdentification(),
            new PerplexityIdentification(),
            new BytespiderIdentification(),
            new CCBotIdentification(),
            new CohereIdentification(),
            new MetaAIIdentification(),
            new DiffbotIdentification(),
            new ImagesiftIdentification(),
            new GoogleAIIdentification(),
            // General search engine bots
            new GoogleIdentification(),
            new BingIdentification(),
            new AppleIdentification(),
            new Proximic(),
            new PetalIdentification(),
            new AmazonIdentification(),
            new BlexIdentification(),
            new CriteoIdentification(),
            new FacebookIdentification(),
            new GrapeshotIdentification(),
            new IasVaIdentification(),
            new InaDlWebIdentification(),
            new MegaindexIdentification(),
            new MJ12Identification(),
            new RiddlerIdentification(),
            new SeekportIdentification(),
            new SemrushIdentification(),
            new YandexIdentification(),
            new YetiIdentification()
    );
}
