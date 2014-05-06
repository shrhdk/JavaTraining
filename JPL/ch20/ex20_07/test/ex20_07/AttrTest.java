/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex20_07;

import org.junit.Test;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AttrTest {
    @Test
    public void test() throws Exception {
        Object value = new Object();
        Attr attr1 = new Attr("hoge", "fuga");
        Attr attr2 = null;

        byte[] data;

        try (
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                DataOutputStream dos = new DataOutputStream(baos);
        ) {
            attr1.write(dos);
            data = baos.toByteArray();
        }

        try (
                ByteArrayInputStream bais = new ByteArrayInputStream(data);
                DataInputStream dis = new DataInputStream(bais);
        ) {
            attr2 = new Attr(dis);
        }

        assertThat(attr1, is(attr2));
    }
}
