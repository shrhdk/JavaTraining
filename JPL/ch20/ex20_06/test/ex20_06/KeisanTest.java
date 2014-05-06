/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex20_06;

import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class KeisanTest {
    @Test
    public void test() throws IOException {
        Reader source = new StringReader(
                String.format(
                        "hoge = 3%n" +
                                "piyo = -1%n" +
                                "hoge +  4%n" +
                                "fuga =  1%n" +
                                "hoge -  5%n" +
                                "fuga -  4%n" +
                                "piyo =  0%n"
                )
        );

        Map<String, Integer> expected = new HashMap<String, Integer>();
        expected.put("hoge", 3 + 4 - 5);
        expected.put("fuga", 1 - 4);
        expected.put("piyo", 0);

        Keisan keisan = new Keisan(source);

        assertThat(keisan.getData(), is(expected));
    }
}
