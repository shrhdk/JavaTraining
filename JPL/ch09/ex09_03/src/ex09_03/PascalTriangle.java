package ex09_03;

public class PascalTriangle {

	private static final int DEPTH = 12;

	public static int[][] make(int depth) {
		int[][] data = new int[depth][];

		for (int i = 0; i < depth; i++) {
			data[i] = new int[i + 1];
			for (int j = 0; j < i + 1; j++) {
				// �_�����Z�q�Ə������Z�q���g���Ēl�̌v�Z��1�s�ɂ܂Ƃ߂܂����B
				data[i][j] = (j == 0 || j == i) ? 1 : data[i - 1][j - 1] + data[i - 1][j];
			}
		}

		return data;
	}

	public static void print(int[][] data) {
		for (int[] i : data) {
			for (int j : i) {
				System.out.printf("%3d ", j);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int[][] data = make(DEPTH);
		print(data);
	}
}
