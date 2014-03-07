package ex10_04;

// 練習問題9.3のiやjのforループをwhile文で書きなおした
//
// しかし通常はこのようにはしない。
// 何故ならば、特定の変数を規則的に動かすことが目的のループであれば、
// その変数の初期値、継続条件、次の値の計算が一箇所に集まっているforループの方が意図が明瞭になる。
// また対象の変数のスコープをループ内に収めることができる。
// while文では初期値、継続条件、次の値の計算がブロックの両端に散らばるため、意図を把握しづらい。

public class PascalTriangle {
	private static final int DEPTH = 12;

	public static int[][] make(int depth) {
		int[][] data = new int[depth][];

		int i = 0;
		while (i < depth) {
			data[i] = new int[i + 1];
			int j = 0;
			while (j < i + 1) {
				data[i][j] = (j == 0 || j == i) ? 1 : data[i - 1][j - 1] + data[i - 1][j];
				j++;
			}
			i++;
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
