/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex21_01;

import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class LineSorter {
    public static String[] sortLines(Reader reader) throws IOException {
        List<String> sorted = new LinkedList<String>();

        LineReader lineReader = new LineReader(reader);

        String line;
        while ((line = lineReader.readLine()) != null) {
            int index = Collections.binarySearch(sorted, line);

            if (index < 0) {
                index = -index - 1;
            }

            sorted.add(index, line);
        }

        return sorted.toArray(new String[0]);
    }
}
