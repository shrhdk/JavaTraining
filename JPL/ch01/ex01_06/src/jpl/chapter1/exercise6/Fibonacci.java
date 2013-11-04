package jpl.chapter1.exercise6;

public class Fibonacci {

	public static int MAX = 50;
	static final String TITLE = "値が" + MAX + "未満のフィボナッチ数列";
	
	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		System.out.println(TITLE);
		System.out.println(lo);
		while (hi < 50) {
			System.out.println(hi);
			hi = lo + hi; // 新しいhi
			lo = hi - lo; // 新しいloは(合計-古いlo1)、すなわち古いhi
		}
	}

}
