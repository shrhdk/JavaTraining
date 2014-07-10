/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex24_01;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.io.PrintStream;
import java.util.Locale;

import static org.mockito.Mockito.*;

public class GlobalHelloTest {

    private static final String[] ARG_0 = new String[]{};
    private static final String[] ARG_1 = new String[]{""};

    private PrintStream out;

    @Before
    public void setUp() {
        out = mock(PrintStream.class);
        System.setOut(out);
    }

    private void test(Locale locale, String hello, String goodbye) {
        // Arrange
        Locale.setDefault(locale);

        // Act
        GlobalHello.main(ARG_0);
        GlobalHello.main(ARG_1);

        // Verify
        InOrder inOrder = inOrder(out);
        inOrder.verify(out).println(hello);
        inOrder.verify(out).println(goodbye);
    }

    @Test
    public void ja() {
        test(Locale.JAPANESE, "こんにちは", "さようなら");
    }

    @Test
    public void fr_CA() {
        test(Locale.CANADA_FRENCH, "Bonjour", "Au revoir");
    }

    @Test
    public void en() {
        test(Locale.ENGLISH, "Hello", "Goodbye");

    }
}
