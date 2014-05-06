/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex20_08;

import org.junit.Test;

import java.io.File;
import java.util.Random;

public class SectionViewerTest {
    @Test
    public void test() throws Exception {
        SectionViewer sv = new SectionViewer(new File("test/text.txt"));
        System.out.println(sv.getSection(new Random().nextInt(8)));
    }
}
