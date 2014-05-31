/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex23_01;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CommandExecutorTest {

    private ByteArrayOutputStream baos;

    @Before
    public void setUp() {
        baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
    }

    private static void setIn(String str) {
        InputStream is = new ByteArrayInputStream(str.getBytes());
        System.setIn(is);
    }

    @Test
    public void test() throws Exception {
        // Given, Expected
        String[] given = new String[]{"watagashi", "pontabox", "exit"};
        String expected = String.format("hello %s%nhello %s%n", given[0], given[1]);

        // Arrange
        setIn(String.join("\n", given) + "\n");

        // Act
        CommandExecutor.main(new String[]{});

        // Assert
        assertThat(baos.toString(), is(expected));
    }
}
