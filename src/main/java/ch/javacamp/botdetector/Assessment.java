package ch.javacamp.botdetector;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import static ch.javacamp.botdetector.Bots.identifier;

public class Assessment {

    public enum Classification {NO_BOT, BOT}
    public static final Assessment NO_BOT = Assessment.create(Classification.NO_BOT, "", RequestDescriptor.empty());
    private final Classification type;
    private final String pattern;
    private final RequestDescriptor descriptor;

    private Assessment(final Classification type, final String pattern, RequestDescriptor descriptor) {
        Objects.requireNonNull(descriptor);
        this.type = type;
        this.pattern = pattern;
        this.descriptor = descriptor;
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
        return type() == Classification.BOT;
    }

    public <R> Optional<R> mapIfBot(final Function<? super Assessment, ? extends R> mapper) {
        if (isBot()) {
            return Optional.of(this).map(mapper);
        } else {
            return Optional.empty();
        }
    }

    public Optional<BotDescription> identify(){
        return mapIfBot(identifier()::identify);
    }

    public Optional<BotDescription> identifyVerified(){
        return mapIfBot(identifier()::identifyVerified);
    }
}
