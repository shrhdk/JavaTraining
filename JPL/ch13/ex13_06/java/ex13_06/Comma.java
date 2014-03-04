package ex13_06;

import java.util.regex.Pattern;

public class Comma {
	public static String insert(int num, String delimiter, int width) {
		if(width <= 0)
			throw new IllegalArgumentException("width must be 1 and over.");
		else if(delimiter == null || delimiter.equals(""))
			throw new IllegalArgumentException("delimiter can't be null and empty.");
		
		String str = String.valueOf(num);
		String pattern = String.format("(?<=[0-9])(?=([0-9]{%d})+$)", width);
		return Pattern.compile(pattern).matcher(str).replaceAll(delimiter);
	}
}
