package ex10_01;

public class Escaper {
	public static String escape(String source) {
		StringBuffer escaped = new StringBuffer();

		for (char ch : source.toCharArray()) {
			if (ch == '\n') {
				escaped.append("\\n");
			} else if (ch == '\t') {
				escaped.append("\\t");
			} else if (ch == '\b') {
				escaped.append("\\b");
			} else if (ch == '\r') {
				escaped.append("\\r");
			} else if (ch == '\f') {
				escaped.append("\\f");
			} else if (ch == '\\') {
				escaped.append("\\\\");
			} else if (ch == '\'') {
				escaped.append("\\\'");
			} else if (ch == '"') {
				escaped.append("\\\"");
			} else {
				escaped.append(ch);
			}
		}

		return escaped.toString();
	}
}
