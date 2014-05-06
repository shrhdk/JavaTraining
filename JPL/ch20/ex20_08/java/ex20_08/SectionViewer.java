/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex20_08;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SectionViewer {
    public List<Section> table = new ArrayList<>();
    public File file;

    public SectionViewer(File file) throws IOException {
        this.file = file;

        try (
                RandomAccessFile raf = new RandomAccessFile(file, "r");
        ) {
            // Construct Table of contents
            String line;
            long beginPos = 0;
            long endPos = 0;
            while ((line = raf.readLine()) != null) {
                if (line.startsWith("%% ")) {
                    if (beginPos != endPos) {
                        String header = line.substring(3);
                        table.add(new Section(header, beginPos, endPos));
                        beginPos = endPos;
                    }
                }
                endPos = raf.getFilePointer() - 1;
            }
        }
    }

    public String getSection(int i) throws IOException {
        if (i < 0 || table.size() <= i) {
            throw new IllegalArgumentException();
        }

        long begin = table.get(i).getBegin();
        long end = table.get(i).getEnd();
        byte[] data = new byte[(int) (end - begin + 1)];
        try (
                RandomAccessFile raf = new RandomAccessFile(file, "r");
        ) {
            raf.seek(begin);
            raf.read(data);
        }

        String section = "";
        try (
                ByteArrayInputStream bais = new ByteArrayInputStream(data);
                InputStreamReader isr = new InputStreamReader(bais);
        ) {
            char[] chars = new char[data.length / 2];
            isr.read(chars);
            return new String(chars);
        }
    }
}
