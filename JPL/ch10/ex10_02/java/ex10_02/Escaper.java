package ex10_02;

public class Escaper {

	public static String escape(String source) {
		StringBuffer escaped = new StringBuffer();

		for (char ch : source.toCharArray()) {
			switch (ch) {
			case '\n':
				escaped.append("\\n");
				break;
			case '\t':
				escaped.append("\\t");
				break;
			case '\b':
				escaped.append("\\b");
				break;
			case '\r':
				escaped.append("\\r");
				break;
			case '\f':
				escaped.append("\\f");
				break;
			case '\\':
				escaped.append("\\\\");
				break;
			case '\'':
				escaped.append("\\\'");
				break;
			case '"':
				escaped.append("\\\"");
				break;
			default:
				escaped.append(ch);
			}
		}

		return escaped.toString();
	}
}
