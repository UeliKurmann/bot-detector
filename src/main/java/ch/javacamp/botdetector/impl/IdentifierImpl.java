package ch.javacamp.botdetector.impl;

import ch.javacamp.botdetector.Assessment;
import ch.javacamp.botdetector.BotDescription;
import ch.javacamp.botdetector.IdentificationRule;
import ch.javacamp.botdetector.Identifier;

import java.util.List;
import java.util.Objects;

public class IdentifierImpl implements Identifier {

    private final List<IdentificationRule> identificationRules;
    private final BotVerifierImpl verifier;

    public IdentifierImpl() {
        identificationRules = BotRules.ALL_BOTS;
        verifier = new BotVerifierImpl();
    }

    @Override
    public BotDescription identify(final Assessment response) {
        Objects.requireNonNull(response);
        return identificationRules.stream()//
                .filter(x -> x.matches(response.descriptor().userAgent())) //
                .map(x -> BotDescription.create(x.name(), BotDescription.Verification.NOT_VERIFIED))//
                .findFirst() //
                .orElse(BotDescription.UNKNOWN);
    }

    @Override
    public BotDescription identifyVerified(Assessment response) {
        return verifier.verify(response.descriptor().ip(), response.descriptor().userAgent());
    }


}
