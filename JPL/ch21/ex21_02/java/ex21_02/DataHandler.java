/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex21_02;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

public class DataHandler {
    private Map<File, byte[]> map = new WeakHashMap<>();

    byte[] readFile(File file) throws IOException {
        byte[] data = map.get(file);

        // Return cache data if there is cache.
        if (data != null) {
            return data;
        }

        // Read from file and cache it.
        data = readBytesFromFile(file);
        map.put(file, data);

        return data;
    }

    private byte[] readBytesFromFile(File file) throws IOException {
        byte[] data = new byte[1];
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while (fis.read(data) > 0) {
            baos.write(data);
        }
        baos.close();
        fis.close();
        data = baos.toByteArray();

        return data;
    }
}

