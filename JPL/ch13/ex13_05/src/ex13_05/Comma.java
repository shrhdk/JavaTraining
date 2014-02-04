package ex13_05;

import java.util.regex.Pattern;

public class Comma {
	public static String insert(int num) {
		return Pattern.compile("(?<=[0-9])(?=([0-9]{3})+$)")
				.matcher(String.valueOf(num)).replaceAll(",");
	}
}
