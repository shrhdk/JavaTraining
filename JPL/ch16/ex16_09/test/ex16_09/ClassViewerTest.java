/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex16_09;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ClassViewerTest {
    @Test
    public void test() {
        String expected = "private static final class Foo extends ArrayList<Integer> implements Cloneable, Comparable<Foo> {\n" +
                "    class Bar {\n" +
                "    }\n" +
                "}\n";

        String actual = ClassViewer.getClassDeclarationString(Foo.class);

        assertThat(actual, is(expected));
    }

    private static final class Foo extends ArrayList<Integer> implements Cloneable, Comparable<Foo> {

        class Bar {
        }

        @Override
        public int compareTo(Foo o) {
            throw new UnsupportedOperationException();
        }
    }
}
