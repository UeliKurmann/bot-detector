package ch.javacamp.botdetector;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import static ch.javacamp.botdetector.Bots.identifier;

public final class Assessment {

    public enum Classification {NO_BOT, BOT}

    public static final Assessment NO_BOT = Assessment.create(Classification.NO_BOT, "", RequestDescriptor.empty());

    private final Classification type;
    private final String pattern;
    private final RequestDescriptor descriptor;

    private Assessment(final Classification type, final String pattern, RequestDescriptor descriptor) {
        this.type = Objects.requireNonNull(type);
        this.pattern = pattern;
        this.descriptor = Objects.requireNonNull(descriptor);
    }

    public static Assessment create(final Classification type, final String pattern, RequestDescriptor descriptor) {
        return new Assessment(type, pattern, descriptor);
    }

    public Classification type() {
        return type;
    }

    public Optional<String> pattern() {
        return Optional.ofNullable(this.pattern);
    }

    public RequestDescriptor descriptor() {
        return this.descriptor;
    }

    public boolean isBot() {
        return type == Classification.BOT;
    }

    public <R> Optional<R> mapIfBot(final Function<? super Assessment, ? extends R> mapper) {
        return isBot() ? Optional.ofNullable(mapper.apply(this)) : Optional.empty();
    }

    public Optional<BotDescription> identify() {
        return mapIfBot(identifier()::identify);
    }

    public Optional<BotDescription> identifyVerified() {
        return mapIfBot(identifier()::identifyVerified);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Assessment that)) return false;
        return type == that.type && Objects.equals(pattern, that.pattern) && descriptor.equals(that.descriptor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, pattern, descriptor);
    }

    @Override
    public String toString() {
        return "Assessment[type=%s, pattern=%s, descriptor=%s]".formatted(type, pattern, descriptor);
    }
}
