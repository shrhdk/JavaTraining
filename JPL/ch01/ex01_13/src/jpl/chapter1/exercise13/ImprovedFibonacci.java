package jpl.chapter1.exercise13;

public class ImprovedFibonacci {

	static final int MAX_INDEX = 9;

	/**
	 * �����v�f��'*'��t���āA�t�B�{�i�b�`����̍ŏ��̕��̗v�f��\������
	 */
	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		String mark;

		System.out.printf("%d: %d\n", 1, lo);
		for (int i = 2; i <= MAX_INDEX; i++) {
			if (hi % 2 == 0)
				mark = " *";
			else
				mark = "";
			System.out.printf("%d: %d%s\n", i, hi, mark);
			hi = lo + hi;
			lo = hi - lo;
		}
	}

}
