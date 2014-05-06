/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex20_11;

import org.junit.Test;

import java.io.File;

public class SuffixFileNameFilterTest {
    @Test
    public void test() {
        File dir = new File("test");
        String[] files = dir.list(new SuffixFileNameFilter(".txt"));

        for(String file : files) {
            System.out.println(file);
        }
    }
}
