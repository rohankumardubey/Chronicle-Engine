package net.openhft.chronicle.engine2.map;

import net.openhft.chronicle.engine2.api.map.MapEvent;
import net.openhft.chronicle.engine2.api.map.MapEventListener;
import net.openhft.chronicle.hash.impl.util.Objects;

import java.util.Optional;

/**
 * Created by peter on 22/05/15.
 */
public class InsertEvent<K, V> implements MapEvent<K, V> {
    private final K key;
    private final V value;

    private InsertEvent(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public static <K, V> InsertEvent<K, V> of(K key, V value) {
        return new InsertEvent<>(key, value);
    }

    public K key() {
        return key;
    }

    public V value() {
        return value;
    }

    @Override
    public void apply(MapEventListener<K, V> listener) {
        listener.insert(key, value);
    }

    @Override
    public int hashCode() {
        return Objects.hash("insert", key, value);
    }

    @Override
    public boolean equals(Object obj) {
        return Optional.ofNullable(obj)
                .filter(o -> o instanceof InsertEvent)
                .map(o -> (InsertEvent<K, V>) o)
                .filter(e -> Objects.equal(key, e.key))
                .filter(e -> Objects.equal(value, e.value))
                .isPresent();
    }

    @Override
    public String toString() {
        return "InsertEvent{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
