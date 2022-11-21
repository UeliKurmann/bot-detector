package ch.javacamp.botdetector.impl;

import ch.javacamp.botdetector.IdentificationRule;
import ch.javacamp.botdetector.impl.verifiers.*;

import java.util.ArrayList;
import java.util.List;

class BotRules {

    public static List<IdentificationRule> ALL_BOTS = new ArrayList<>();

    static {
        ALL_BOTS.add(new GoogleIdentification());
        ALL_BOTS.add(new BingIdentification());
        ALL_BOTS.add(new AppleIdentification());
        ALL_BOTS.add(new Proximic());
        ALL_BOTS.add(new PetalIdentification());
        ALL_BOTS.add(new AmazonIdentification());
        ALL_BOTS.add(new BlexIdentification());
        ALL_BOTS.add(new CriteoIdentification());
        ALL_BOTS.add(new FacebookIdentification());
        ALL_BOTS.add(new GrapeshotIdentification());
        ALL_BOTS.add(new IasVaIdentification());
        ALL_BOTS.add(new InaDlWebIdentification());
        ALL_BOTS.add(new MegaindexIdentification());
        ALL_BOTS.add(new MJ12Identification());
        ALL_BOTS.add(new RiddlerIdentification());
        ALL_BOTS.add(new SeekportIdentification());
        ALL_BOTS.add(new SemrushIdentification());
        ALL_BOTS.add(new YandexIdentification());
        ALL_BOTS.add(new YetiIdentification());
    }

}
