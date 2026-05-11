package ch.javacamp.botdetector.impl.utils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * A simple thread-safe LRU cache implementation.
 * Uses a LinkedHashMap with access-order for LRU eviction.
 * All operations are synchronized since access-order LinkedHashMap
 * treats get() as a structural modification.
 */
public class Cache<Key, Value> {

    private final int maxSize;
    private final LinkedHashMap<Key, ValueHolder<Value>> backend;

    private Cache(int maxSize) {
        this.maxSize = maxSize;
        this.backend = new LinkedHashMap<Key, ValueHolder<Value>>(maxSize, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Key, ValueHolder<Value>> eldest) {
                return size() > maxSize;
            }
        };
    }

    public static <K, V> Cache<K, V> create(int maxCacheSize) {
        return new Cache<>(maxCacheSize);
    }

    public synchronized Value getOrPut(Key key, Supplier<Value> supplier) {
        ValueHolder<Value> holder = backend.get(key);
        if (holder != null) {
            return holder.value;
        }
        Value value = supplier.get();
        backend.put(key, new ValueHolder<>(value));
        return value;
    }

    public synchronized int size() {
        return backend.size();
    }

    private static class ValueHolder<V> {
        final V value;
        ValueHolder(V value) {
            this.value = value;
        }
    }
}
