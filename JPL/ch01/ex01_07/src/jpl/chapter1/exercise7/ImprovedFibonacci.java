package jpl.chapter1.exercise7;

public class ImprovedFibonacci {

	static final int MAX_INDEX = 9;

	/**
	 * �����v�f��'*'��t���āA�t�B�{�i�b�`����̍ŏ��̕��̗v�f��\������
	 */
	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		String mark;

		System.out.println(MAX_INDEX + ": " + lo);
		for (int i = MAX_INDEX - 1; 1 <= i; i--) {
			if (hi % 2 == 0)
				mark = " *";
			else
				mark = "";
			System.out.println(i + ": " + hi + mark);
			hi = lo + hi;
			lo = hi - lo;
		}
	}

}
