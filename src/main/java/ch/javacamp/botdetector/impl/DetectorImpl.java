package ch.javacamp.botdetector.impl;

import ch.javacamp.botdetector.Assessment;
import ch.javacamp.botdetector.Detector;
import ch.javacamp.botdetector.RequestDescriptor;
import ch.javacamp.botdetector.impl.utils.Cache;
import ch.javacamp.botdetector.impl.utils.IOUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class DetectorImpl implements Detector {

    private final List<Pattern> botPatterns = new ArrayList<>();
    private final Cache<String, Optional<String>> cache = Cache.create(10_000);

    public DetectorImpl() {
        IOUtils.lines("bots.txt")
                .map(String::toLowerCase)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Pattern::compile)
                .forEach(botPatterns::add);
    }

    @Override
    public Assessment detect(RequestDescriptor descriptor) {
        Optional<String> elem = cache.getOrPut(descriptor.userAgent(), () -> checkUaString(descriptor));
        return elem.map(x -> Assessment.create(Assessment.Classification.BOT, x, descriptor))
                .orElse(Assessment.NO_BOT);
    }

    private Optional<String> checkUaString(RequestDescriptor descriptor) {
        for (final Pattern pattern : botPatterns) {
            if (pattern.matcher(descriptor.userAgent()).find()) {
                return Optional.of(pattern.pattern());
            }
        }
        return Optional.empty();
    }
}
