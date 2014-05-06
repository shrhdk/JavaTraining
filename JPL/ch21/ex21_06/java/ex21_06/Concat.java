/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex21_06;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;

public class Concat {
    public static void main(String[] args) throws IOException {
        InputStream in; // 文字を読み込むべきストリーム
        if (args.length == 0) {
            in = System.in;
        } else {
            Enumeration<InputStream> files = new FileEnumeration(args);
            in = new SequenceInputStream(files);
        }
        int ch;
        while ((ch = in.read()) != -1) {
            System.out.write(ch);
        }
    }

    private static class FileEnumeration implements Enumeration<InputStream> {

        private InputStream inputStream;
        private final Iterator<String> fileName;

        public FileEnumeration(String[] fileNames) {
            fileName = Arrays.asList(fileNames).iterator();
        }

        @Override
        public boolean hasMoreElements() {
            return fileName.hasNext();
        }

        @Override
        public InputStream nextElement() {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                inputStream = new FileInputStream(fileName.next());
                return inputStream;
            } catch (IOException e) {
                throw new AssertionError(e.toString());
            }
        }
    }
}