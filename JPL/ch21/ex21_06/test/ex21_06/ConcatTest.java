/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex21_06;

import org.junit.Test;

import java.io.IOException;

public class ConcatTest {
    @Test
    public void main() throws IOException {
        Concat.main(new String[]{"test/hoge.txt", "test/fuga.txt"});
    }
}
