package jpl.chapter1.exercise6;

public class Fibonacci {

	public static int MAX = 50;
	static final String TITLE = "�l��" + MAX + "�����̃t�B�{�i�b�`����";
	
	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		System.out.println(TITLE);
		System.out.println(lo);
		while (hi < 50) {
			System.out.println(hi);
			hi = lo + hi; // �V����hi
			lo = hi - lo; // �V����lo��(���v-�Â�lo1)�A���Ȃ킿�Â�hi
		}
	}

}
