package jpl.chapter1.exercise2;

public class HelloWorldError {

	@SuppressWarnings("null")
	public static void main(String[] args) {
		String helloWorldError = null;
		System.out.println(helloWorldError.toString()); // NullPointerException is expected
	}

}
