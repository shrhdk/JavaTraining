package jpl.chapter4.exercise3;

public interface List<T> {

	T get(int index);

	void add(T value);

	void add(T value, int index);

	void delete(int index);

	void clear();

	int size();

}
