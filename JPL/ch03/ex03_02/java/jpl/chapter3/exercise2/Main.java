package jpl.chapter3.exercise2;

public class Main {

	public static void main(String[] args) {

		Observer.add("Call Y constructor", 0, 0, 0);

		@SuppressWarnings("unused")
		Y y = new Y();

		Observer.Log[] logArray = Observer.getLogArray();
		System.out.println(Observer.Log.getHeaderString());
		System.out.println(Observer.Log.getSeparatorString());
		for (Observer.Log log : logArray) {
			System.out.println(log);
		}
	}

}
