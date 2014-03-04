package jpl.chapter1.exercise15;

public interface ObjectCollectionInterface extends Lookup {
	/** nameと寒暖付された値を削除する。 */
	public boolean remove(String name);

	/** objectをnameと関連付けて追加する。 */
	public boolean add(String name, Object value);
}
