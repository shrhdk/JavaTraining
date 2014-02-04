package ex13_02;

public class MatchingCounter {
	public static int count(String string, String sub) {

		int count = 0;
		for (int start = 0; start < string.length(); start += sub.length()) {
			start = string.indexOf(sub, start);
			if (start != -1)
				count++;
			else
				break;
		}
		return count;
	}
}
