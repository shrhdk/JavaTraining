package jpl.chapter1.exercise15;

public interface ObjectCollectionInterface extends Lookup {
	/** name�Ɗ��g�t���ꂽ�l���폜����B */
	public boolean remove(String name);

	/** object��name�Ɗ֘A�t���Ēǉ�����B */
	public boolean add(String name, Object value);
}
