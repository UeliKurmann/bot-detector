package ch.javacamp.botdetector.impl;

import ch.javacamp.botdetector.Assessment;
import ch.javacamp.botdetector.Detector;
import ch.javacamp.botdetector.RequestDescriptor;
import ch.javacamp.botdetector.impl.utils.Hashs;
import ch.javacamp.botdetector.impl.utils.IOUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;

public class DetectorImpl implements Detector {

    private static final int MAX_SIZE = 10000;
    private static final String CONTAINS_PATTERN = ".*";
    private final List<Pattern> botPatterns = new ArrayList<>();

    public DetectorImpl() {
        IOUtils.lines("bots.txt")//
                .map(String::toLowerCase)//
                .map(String::trim)//
                .map(p -> Pattern.compile(CONTAINS_PATTERN + p + CONTAINS_PATTERN))//
                .forEach(botPatterns::add);
    }

    @Override
    public Assessment detect(RequestDescriptor descriptor) {
        return checkUaString(descriptor);
    }

    private Assessment checkUaString(RequestDescriptor descriptor) {
        for (final Pattern pattern : botPatterns) {
            if (pattern.matcher(descriptor.userAgent()).matches()) {
                return Assessment.create(Assessment.Classification.BOT, pattern.pattern(), descriptor);
            }
        }
        return Assessment.NO_BOT;
    }

}
