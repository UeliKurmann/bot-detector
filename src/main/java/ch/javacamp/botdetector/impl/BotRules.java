package ch.javacamp.botdetector.impl;

import ch.javacamp.botdetector.IdentificationRule;
import ch.javacamp.botdetector.impl.verifiers.*;

import java.util.List;

class BotRules {

    public static final List<IdentificationRule> ALL_BOTS = List.of(
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
