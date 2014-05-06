package ex20_04;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class LineReaderTest {

    private final String LINE_SEPARATOR = String.format("%n");

    @Test
    public void readLine() throws IOException {
        StringReader sr = new StringReader("12345" + LINE_SEPARATOR + "ABC" + LINE_SEPARATOR + "abc");
        LineReader lineReader = new LineReader(sr);

        String line1 = lineReader.readLine();
        String line2 = lineReader.readLine();
        String line3 = lineReader.readLine();

        assertThat(line1, is("12345" + LINE_SEPARATOR));
        assertThat(line2, is("ABC" + LINE_SEPARATOR));
        assertThat(line3, is("abc"));
    }

    @Test
    public void readOutOfLine() throws IOException {
        StringReader sr = new StringReader("12345" + LINE_SEPARATOR + "ABC" + LINE_SEPARATOR + "abc");
        LineReader lineReader = new LineReader(sr);

        String line1 = lineReader.readLine();
        String line2 = lineReader.readLine();
        String line3 = lineReader.readLine();
        String line4 = lineReader.readLine(); // No such line

        assertThat(line1, is("12345" + LINE_SEPARATOR));
        assertThat(line2, is("ABC" + LINE_SEPARATOR));
        assertThat(line3, is("abc"));
        assertThat(line4, nullValue());
    }
}
