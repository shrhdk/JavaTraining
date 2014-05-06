/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex20_10;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Map;

public class WordCounter {
    public static Map<String, Integer> count(File file) throws IOException {
        final Map<String, Integer> counts = new HashMap<>();

        try (
                FileInputStream fis = new FileInputStream(file);
        ) {
            StreamTokenizer st = new StreamTokenizer(fis);

            while(st.nextToken() != StreamTokenizer.TT_EOF) {
                if(st.ttype == StreamTokenizer.TT_WORD) {
                    final String key = st.sval;

                    if(!counts.containsKey(key)) {
                        counts.put(key, 0);
                    }

                    counts.put(key, counts.get(key) + 1);
                }
            }
        }

        return counts;
    }
}
