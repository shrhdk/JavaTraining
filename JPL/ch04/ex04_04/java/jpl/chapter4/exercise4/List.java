package jpl.chapter4.exercise4;

public interface List<V> extends Collection {
	void get(int index);
	void add(V value);
	void add(V value, int index);
	void remove(int index);
}
