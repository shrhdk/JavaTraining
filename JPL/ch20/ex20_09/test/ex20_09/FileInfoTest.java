/*
 * Copyright(C) 2014 Hideki Shiro
 */
package ex20_09;

import org.junit.Test;

import java.io.IOException;

public class FileInfoTest {
    @Test
    public void showFileInfo() throws IOException {
        FileInfo.showFileInfo("test", "test/lena.png");
    }
}
