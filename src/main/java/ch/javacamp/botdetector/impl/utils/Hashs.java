package ch.javacamp.botdetector.impl.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class Hashs {

    public static byte[] md5(final String input) {
        Objects.requireNonNull(input);
        try {
            final MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            return md.digest();

        } catch (final NoSuchAlgorithmException e) {
            throw new IllegalStateException("no md5 hashing available.");
        }
    }

}
