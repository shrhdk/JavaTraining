package jpl.chapter4.exercise4;

public interface Map<K, V> extends Collection {
	void get(K key);
	void set(K key, V value);
	void remove(K key);
}
