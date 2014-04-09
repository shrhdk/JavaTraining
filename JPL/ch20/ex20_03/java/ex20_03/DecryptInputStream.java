package ex20_03;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DecryptInputStream extends FilterInputStream {

    private final byte key;

    protected DecryptInputStream(InputStream inputStream, byte key) {
        super(inputStream);
        this.key = key;
    }

    @Override
    public int read() throws IOException {
        int b;
        if ((b = super.read()) != -1) {
            return (byte) b ^ key;
        } else {
            return -1;
        }
    }

    @Override
    public int read(byte[] bytes, int offset, int count) throws IOException {
        int numOfRead = super.read(bytes, offset, count);
        for(int i = offset; i < offset + numOfRead; i++) {
            bytes[i] ^= key;
        }
        return numOfRead;
    }
}
