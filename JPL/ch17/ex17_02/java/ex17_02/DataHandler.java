package ex17_02;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.ref.*;

public class DataHandler {
    private WeakReference<File> lastFileReference;
    private WeakReference<byte[]> lastDataReference;

    byte[] readFile(File file) throws IOException {
        File lastFile;
        byte[] data;

        // Get lastFile from cache
        if (lastFileReference != null) {
            lastFile = lastFileReference.get();
        } else {
            lastFile = null;
        }

        // Return cache data if there is cache.
        if (lastFile != null && file.equals(lastFile)) {
            data = lastDataReference.get();
            if (data != null) {
                return data;
            }
        }

        // Read from file if there is no cache.
        data = readBytesFromFile(file);
        lastFileReference = new WeakReference<File>(file);
        lastDataReference = new WeakReference<byte[]>(data);
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

