package ex09_02;

public class BitCounter {
	public static int bitCount(int value) {
		int count = 0;
		
		for(int i = 0; i < Integer.SIZE; i++) {
			if((value & (1 << i)) != 0)
				count++;
		}
		
		return count;
	}
}
