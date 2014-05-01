/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex03_12;

public class SortInteger extends SortHarness<Integer> {
    @Override
    protected void doSort() {
        for(int i = 0; i < getDataLength(); i++) {
            for(int j = i + 1; j < getDataLength(); j++) {
                if(0 < compare(i, j)) {
                    swap(i, j);
                }
            }
        }
    }
}