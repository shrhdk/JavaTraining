package ex13_01;

public class MatchingCounter {
	public static int count(String string, char ch) {

		int count = 0;
		for (int start = 0; start < string.length(); start++) {
			start = string.indexOf(ch, start);
			if (start != -1)
				count++;
			else
				break;
		}
		return count;
	}
}
