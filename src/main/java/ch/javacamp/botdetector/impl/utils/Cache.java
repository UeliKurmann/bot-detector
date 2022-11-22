package ch.javacamp.botdetector.impl.utils;

import java.util.Comparator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

public class Cache<Key extends Comparable<Key>, Value> {

    private final int cacheSize;
    private final ConcurrentHashMap<Key, CachedElement> backend = new ConcurrentHashMap<>();
    private final ExecutorService async = Executors.newSingleThreadExecutor();

    private final AtomicLong access = new AtomicLong();
    private final AtomicLong create = new AtomicLong();

    private Cache(int maxCacheSize){
        this.cacheSize = maxCacheSize;
    }

    public static <K extends Comparable<K>, V> Cache<K, V> create(int maxCacheSize){
        return new Cache<>(maxCacheSize);
    }

    public Value getOrPut(Key key, Supplier<Value> supplier){
        access.incrementAndGet();
        CachedElement element = backend.computeIfAbsent(key, x -> create(key, supplier.get()));
        element.tick();
        evict();
        return element.value;
    }

    private void evict(){
        if(backend.size() > (cacheSize * 0.9)){

            async.submit(() -> {
                if(backend.size() < (cacheSize * 0.9)){
                    return;
                }
                backend.values().stream()
                        .sorted(Comparator.comparingLong((CachedElement x) -> x.lastAccessed))
                        .limit((int)(cacheSize * 0.8))
                        .forEach(x -> backend.remove(x.key));
            });

        }
    }

    private CachedElement create(Key key, Value value){
        create.incrementAndGet();
        return new CachedElement(key, value);
    }

    public double cacheRatio(){
        return (1-(double)create.get()/(double)access.get())* 100;
    }

    private class CachedElement {
        private final Key key;
        private final Value value;
        private long lastAccessed;

        private CachedElement(Key key, Value value){
            this.key = key;
            this.value = value;
            this.lastAccessed = System.currentTimeMillis();
        }

        public void tick(){
            this.lastAccessed = System.currentTimeMillis();
        }
    }


}
