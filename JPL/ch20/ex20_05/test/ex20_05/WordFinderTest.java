/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex20_05;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class WordFinderTest {
    @Test
    public void find() throws IOException {

        File file = new File("test/english.txt");
        String word = "Java";

        String[] result = WordFinder.find(file, word);

        System.out.printf("=== \"%s\" in \"%s\" ===%n", word, file.getName());
        for (String line : result) {
            System.out.println(line);
        }
    }
}
