/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex21_03;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.*;

public class WeakValueMap<K, V> implements Map<K, V> {
    private final Map<K, Reference<V>> map = new HashMap<>();

    private synchronized void reap() {
        Iterator<Reference<V>> it = map.values().iterator();
        while(it.hasNext()) {
            Reference<V> ref = it.next();
            if(ref.get() == null) {
                ref.clear();
                it.remove();
            }
        }
    }

    @Override
    public int size() {
        // Do not reap, that cause unexpected ConcurrentModificationException

        int size = 0;
        for(Reference<V> vRef : map.values()) {
            if(vRef.get() != null) {
                size++;
            }
        }

        return size;
    }

    @Override
    public boolean isEmpty() {
        // Do not reap, that cause unexpected ConcurrentModificationException

        return size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        // Do not reap, that cause unexpected ConcurrentModificationException

        if(key == null) {
            return false;
        }

        final Reference<V> vRef = map.get(key);

        if(vRef == null) {
            return false;
        }

        if(vRef.get() == null) {
            return false;
        }

        return true;
    }

    @Override
    public boolean containsValue(Object value) {
        // Do not reap, that cause unexpected ConcurrentModificationException

        if(value == null) {
            return false;
        }

        // TODO: Synchronization
        for(Reference<V> vRef : map.values()) {
            if(value.equals(vRef.get())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public V get(Object key) {
        // Do not reap, that cause unexpected ConcurrentModificationException

        final Reference<V> ref = map.get(key);

        if(ref == null) {
            return null;
        }

        return map.get(key).get();
    }

    @Override
    public V put(K key, V value) {
        reap();

        Reference<V> ref = new WeakReference<V>(value);
        map.put(key, ref);

        return value;
    }

    @Override
    public V remove(Object key) {
        reap();

        if(!map.containsKey(key)) {
            return null;
        }

        return map.remove(key).get();
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        reap();
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<K> keySet() {
        reap();
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        reap();
        return new AbstractCollection<V>() {
            @Override
            public Iterator<V> iterator() {
                return new Iterator<V>() {
                    private final Iterator<Reference<V>> it = map.values().iterator();

                    @Override
                    public boolean hasNext() {
                        return it.hasNext();
                    }

                    @Override
                    public V next() {
                        return it.next().get();
                    }

                    @Override
                    public void remove() {
                        it.remove();
                    }
                };
            }

            @Override
            public int size() {
                return map.size();
            }
        };
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        reap();
        return new AbstractSet<Entry<K, V>>() {
            @Override
            public Iterator<Entry<K, V>> iterator() {
                return new Iterator<Entry<K, V>>() {
                    private final Iterator<Entry<K, Reference<V>>> it = map.entrySet().iterator();

                    @Override
                    public boolean hasNext() {
                        return it.hasNext();
                    }

                    @Override
                    public Entry<K, V> next() {
                        final Entry<K, Reference<V>> e = it.next();
                        return new AbstractMap.SimpleEntry<K, V>(e.getKey(), e.getValue().get());
                    }

                    @Override
                    public void remove() {
                        it.remove();
                    }
                };
            }

            @Override
            public int size() {
                return map.size();
            }
        };
    }
}
