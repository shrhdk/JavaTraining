package jpl.chapter1.exercise3;

public class Fibonacci {

	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		System.out.println("�l��50�����̃t�B�{�i�b�`����");
		System.out.println(lo);
		while (hi < 50) {
			System.out.println(hi);
			hi = lo + hi; // �V����hi
			lo = hi - lo; // �V����lo��(���v-�Â�lo1)�A���Ȃ킿�Â�hi
		}
	}

}
