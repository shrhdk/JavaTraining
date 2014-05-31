/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex23_01;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CommandExecutorTest {
    @Test
    public void test() throws Exception {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        // Act and Assert
        CommandExecutor.main(null);

        System.out.println("watagashi");
        assertThat(br.readLine(), is("hello watagashi"));

        System.out.println("pontabox");
        assertThat(br.readLine(), is("hello pontabox"));

        // Exit
        System.out.println("exit");
    }
}
