package ex13_04;

import java.util.ArrayList;
import java.util.List;

public class Parser {
	public static void parseSsv(String data) {
		List<Object> list = new ArrayList<Object>();

		String[] rows = data.split("\n");
		for (String row : rows) {
			String type = row.split(" ")[0];
			String value = row.split(" ")[1];

			if ("Byte".equals(type))
				list.add(Byte.parseByte(value));
			else if ("Short".equals(type))
				list.add(Short.parseShort(value));
			else if ("Integer".equals(type))
				list.add(Integer.parseInt(value));
			else if ("Long".equals(type))
				list.add(Long.parseLong(value));
			else if ("Float".equals(type))
				list.add(Float.parseFloat(value));
			else if ("Double".equals(type))
				list.add(Double.parseDouble(value));
			else if ("Character".equals(type))
				list.add(value.charAt(0));
			else if ("Boolean".equals(type))
				list.add(Boolean.parseBoolean(value));
		}

		for (Object item : list) {
			System.out.printf("%s : %s\n", item.getClass(), item);
		}
	}

	public static void main(String[] args) {
		parseSsv("Byte 123\nShort 456\nInteger 789\nLong 91011\nFloat 1.2\nDouble 3.4\nCharacter H\nBoolean true");
	}
}
