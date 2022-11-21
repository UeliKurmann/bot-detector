package ch.javacamp.botdetector.impl.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IOUtils {

    public static Stream<String> lines(final String resource) {
        Objects.requireNonNull(resource);
        try (InputStream is = IOUtils.class.getClassLoader().getResourceAsStream(resource)) {
            return lines(is);
        } catch (final IOException e) {
            throw new RuntimeException("Could not read resource.", e);
        }
    }

    public static Stream<String> lines(final InputStream is) throws IOException {
        final List<String> result;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            result = reader.lines().collect(Collectors.toList());
        }
        return result.stream();
    }

}
