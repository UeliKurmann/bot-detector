package ch.javacamp.botdetector.impl.utils;

public class StopWatch {

    private final long start;

    private StopWatch() {
        this.start = System.currentTimeMillis();
    }

    public static StopWatch createStarted() {
        return new StopWatch();
    }

    public long elapsedMilliseconds() {
        return System.currentTimeMillis() - start;
    }

}
