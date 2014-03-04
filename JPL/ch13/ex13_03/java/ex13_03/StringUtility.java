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
				// �J�n������������Ȃ�
				break;
			} else if (endPos == -1) {
				// �I��������������Ȃ�
				list.add(from.substring(startPos));
				break;
			} else if (endPos < startPos) {
				// �J�n�������I�������̌�ɂ���
				endPos = startPos;
				continue;
			} else {
				// 1�J�n�����ƏI����������������
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
