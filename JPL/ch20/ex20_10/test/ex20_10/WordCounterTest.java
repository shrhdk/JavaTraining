/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex20_10;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class WordCounterTest {
    @Test
    public void test() throws IOException {
        Map<String, Integer> counts = WordCounter.count(new File("test/english.txt"));

        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
