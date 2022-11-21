package ch.javacamp.botdetector;

public class BotDescription {

    public static final BotDescription UNKNOWN = BotDescription.create("unknown-bot", Verification.UNKNOWN);

    public enum Verification {NOT_VERIFIED, VERIFIED, DECEIVED, UNKNOWN}

    private final String name;

    private final Verification verification;

    private BotDescription(final String name, final Verification verification) {
        this.name = name;
        this.verification = verification;
    }

    public static BotDescription create(final String name, final Verification verification) {
        return new BotDescription(name, verification);
    }

    public String name() {
        return name;
    }

    public Verification verification() {
        return verification;
    }

}