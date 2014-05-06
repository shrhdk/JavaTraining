/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex20_05;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WordFinder {
    public static String[] find(File file, String word) throws IOException {
        try (
                FileReader fr = new FileReader(file);
                LineNumberReader lnr = new LineNumberReader(fr);
        ) {
            List<String> list = new ArrayList<>();
            int lineNumber = lnr.getLineNumber();
            String line;
            while ((line = lnr.readLine()) != null) {
                if (line.contains(word)) {
                    list.add(lineNumber + ": " + line);
                }
            }

            return list.toArray(new String[0]);
        }
    }
}
