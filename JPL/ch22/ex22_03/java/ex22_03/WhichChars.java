/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex22_03;

import java.util.*;

public class WhichChars {
    private Map<Character, BitSet> used = new TreeMap<Character, BitSet>();

    public WhichChars(String str) {
        for (int i = 0; i < str.length(); i++) {
            Character ch = str.charAt(i);
            Character upper = (char) (ch & 0xF0);
            Character lower = (char) (ch & 0x0F);

            if (!used.containsKey(upper)) {
                used.put(upper, new BitSet());
            }

            used.get(upper).set(lower);
        }
    }

    @Override
    public String toString() {
        StringBuilder desc = new StringBuilder("[");
        for (Map.Entry<Character, BitSet> entry : used.entrySet()) {
            Character upper = entry.getKey();
            BitSet bitSet = entry.getValue();
            for(int lower = bitSet.nextSetBit(0); 0 <= lower; lower = bitSet.nextSetBit(lower + 1)) {
                desc.append((char)(upper | lower));
            }
        }
        desc.append("]");
        return desc.toString();
    }
}
