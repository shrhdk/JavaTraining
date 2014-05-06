/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex20_04;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class LineReader extends FilterReader {

    private final String LINE_SEPARATOR = String.format("%n");

    protected LineReader(Reader reader) {
        super(reader);
    }

    public String readLine() throws IOException {

        StringBuilder sb = new StringBuilder();

        for (int cnt = 0; ; cnt++) {
            int c = super.read();
            if (c != -1) {
                sb.append((char) c);
            } else {
                if (1 <= cnt) {
                    return sb.toString();
                } else if (cnt == 0) {
                    return null;
                }
            }

            boolean hasLineSeparator = false;
            if (LINE_SEPARATOR.length() <= sb.length()) {
                hasLineSeparator = true;
                for (int i = 1; i <= LINE_SEPARATOR.length(); i++) {
                    if (LINE_SEPARATOR.charAt(LINE_SEPARATOR.length() - i) != sb.charAt(sb.length() - i)) {
                        hasLineSeparator = false;
                        break;
                    }
                }
            }

            if (hasLineSeparator) {
                return sb.toString();
            }
        }
    }
}
