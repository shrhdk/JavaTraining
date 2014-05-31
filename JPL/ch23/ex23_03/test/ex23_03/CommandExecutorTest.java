/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex23_03;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CommandExecutorTest {

    private ByteArrayOutputStream baos;

    @Before
    public void setUp() {
        baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
    }

    @Test
    public void test() {
        // Arrange
        String[] given = new String[] {"test/hello.rb", "watagashi", "pontabox"};
        String expected = String.format("1: hello %s%n2: hello %s%n", given[1], given[2]);

        // Act
        CommandExecutor.main(given);

        // Assert
        assertThat(baos.toString(), is(expected));
    }

    @Test
    public void destroyOnEXIT() {
        // Arrange
        String[] given = new String[] {"test/hello.rb", "watagashi", "EXIT", "pontabox"};
        String expected = String.format("1: hello %s%n2: hello %s%n", given[1], given[2]); // pontabox won't output, because it is after EXIT

        // Act
        CommandExecutor.main(given);

        // Assert
        assertThat(baos.toString(), is(expected));
    }
}
