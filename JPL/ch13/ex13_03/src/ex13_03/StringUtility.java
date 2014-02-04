package ex13_03;

import java.util.ArrayList;
import java.util.List;

public class StringUtility {
	public static String[] delimitedString(String from, char start, char end) {
		List<String> list = new ArrayList<String>();
		int startPos = 0, endPos = 0;
		for(;;) {
			startPos = from.indexOf(start, startPos);
			endPos = from.indexOf(end, endPos);
			if (startPos == -1) {
				// 開始文字が見つからない
				break;
			} else if (endPos == -1) {
				// 終了文字が見つからない
				list.add(from.substring(startPos));
				break;
			} else if (endPos < startPos) {
				// 開始文字が終了文字の後にある
				endPos = startPos;
				continue;
			} else {
				// 1開始文字と終了文字が見つかった
				list.add(from.substring(startPos, endPos + 1));
				startPos = endPos;
				continue;
			}
		}
		if (list.size() == 0)
			return null;
		else
			return list.toArray(new String[list.size()]);
	}
}
