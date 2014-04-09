package ex20_02;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.io.*;

public class TranslateByteTest {

    @Test
    public void translate() throws IOException {
        byte[] raw = "abracadabra!".getBytes("US-ASCII");
        byte[] result = new byte[raw.length];

        ByteArrayInputStream src = new ByteArrayInputStream(raw);

        TranslateByte translateByte = new TranslateByte(src, (byte) 'b', (byte) 'B');
        translateByte.read(result);

        byte[] expect = "aBracadaBra!".getBytes("US-ASCII");
        assertThat(result, is(expect));
    }
}
