/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex21_01;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class LineSorterTest {
    @Test
    public void sortLines() throws IOException {
        // Source
        Reader raw = new InputStreamReader(getClass().getResourceAsStream("raw.txt"));
        Reader sorted = new InputStreamReader(getClass().getResourceAsStream("sorted.txt"));

        // Expected
        LineReader sortedLineReader = new LineReader(sorted);
        List<String> expectedList = new ArrayList<String>();
        String line;
        while ((line = sortedLineReader.readLine()) != null) {
            expectedList.add(line);
        }
        String[] expected = expectedList.toArray(new String[0]);

        // Actual
        String[] actual = LineSorter.sortLines(raw);

        // Assertion
        assertThat(actual, is(expected));
    }
}
