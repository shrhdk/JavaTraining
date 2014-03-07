package ex10_05;

public class CharPrinter {
	private static final char START = ' ';
	private static final char STOP = 'z';
	
	public static void between(char start, char stop) {
		for(char i = start; i <= stop; i++) {
			System.out.printf("%c ", i);
		}
	}
	
	public static void main(String[] args) {
		between(START, STOP);
	}
}
