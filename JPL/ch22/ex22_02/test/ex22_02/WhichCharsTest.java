/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex22_02;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class WhichCharsTest {
    @Test
    public void test() {
        WhichChars whichChars = new WhichChars("Testing 1 2 3");
        assertThat(whichChars.toString(), is("[ 123Teginst]"));
    }
}
