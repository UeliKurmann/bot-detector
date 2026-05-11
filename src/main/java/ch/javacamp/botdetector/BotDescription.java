package ch.javacamp.botdetector;

public record BotDescription(String name, Verification verification) {

    public static final BotDescription UNKNOWN = new BotDescription("unknown-bot", Verification.UNKNOWN);

    public enum Verification {NOT_VERIFIED, VERIFIED, DECEIVED, UNKNOWN}

    public static BotDescription create(final String name, final Verification verification) {
        return new BotDescription(name, verification);
    }
}