package ex20_01;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.io.*;

public class TranslateByteTest {

    @Test
    public void translate() throws IOException {
        ByteArrayInputStream src = new ByteArrayInputStream("abracadabra!".getBytes("US-ASCII"));
        ByteArrayOutputStream dst = new ByteArrayOutputStream();

        TranslateByte.translate((byte) 'b', (byte) 'B', src, dst);

        byte[] expect = "aBracadaBra!".getBytes("US-ASCII");
        byte[] result = dst.toByteArray();

        assertThat(result, is(expect));
    }
}
