/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex22_02;

import java.util.Set;
import java.util.TreeSet;

public class WhichChars {
    private Set<Character> used = new TreeSet<Character>();

    public WhichChars(String str) {
        for(int i = 0; i < str.length(); i++) {
            used.add(str.charAt(i));
        }
    }

    @Override
    public String toString() {
        StringBuilder desc = new StringBuilder("[");
        for(Character ch : used) {
            desc.append(ch);
        }
        desc.append("]");
        return desc.toString();
    }
}
