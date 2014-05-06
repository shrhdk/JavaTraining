/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex20_06;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Map;

public class Keisan {

    private final Map<String, Integer> data = new HashMap<String, Integer>();

    public Keisan(Reader source) throws IOException {
        StreamTokenizer st = new StreamTokenizer(source);

        String var = null;
        int op = ' ';

        while (st.nextToken() != StreamTokenizer.TT_EOF) {
            switch (st.ttype) {
                case StreamTokenizer.TT_WORD:
                    var = st.sval;
                    break;
                case '=':
                case '+':
                case '-':
                    op = st.ttype;
                    break;
                case StreamTokenizer.TT_NUMBER:
                    int val = (int) st.nval;
                    if (var == null) {
                        throw new IOException();
                    }
                    switch (op) {
                        case '=':
                            data.put(var, val);
                            break;
                        case '+':
                            if (!data.containsKey(var)) {
                                throw new IOException();
                            }
                            data.put(var, data.get(var) + val);
                            break;
                        case '-':
                            if (!data.containsKey(var)) {
                                throw new IOException();
                            }
                            data.put(var, data.get(var) - val);
                            break;
                        default:
                            throw new IOException("Unknown op: " + op);
                    }
                    break;
            }
        }
    }

    public Map<String, Integer> getData() {
        return data;
    }
}
