package ex10_04;

// ���K���9.3��i��j��for���[�v��while���ŏ����Ȃ�����
//
// �������ʏ�͂��̂悤�ɂ͂��Ȃ��B
// ���̂Ȃ�΁A����̕ϐ����K���I�ɓ��������Ƃ��ړI�̃��[�v�ł���΁A
// ���̕ϐ��̏����l�A�p�������A���̒l�̌v�Z����ӏ��ɏW�܂��Ă���for���[�v�̕����Ӑ}�����ĂɂȂ�B
// �܂��Ώۂ̕ϐ��̃X�R�[�v�����[�v���Ɏ��߂邱�Ƃ��ł���B
// while���ł͏����l�A�p�������A���̒l�̌v�Z���u���b�N�̗��[�ɎU��΂邽�߁A�Ӑ}��c�����Â炢�B

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
