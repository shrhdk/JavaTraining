package ex20_02;

import java.io.*;

public class TranslateByte extends FilterInputStream {

    private final byte from, to;

    public TranslateByte(InputStream inputStream, byte from, byte to) {
        super(inputStream);
        this.from = from;
        this.to = to;
    }

    @Override
    public int read() throws java.io.IOException {
        int b = super.read();
        if(b == from) {
            b = to;
        }
        return b;
    }

    @Override
    public int read(byte[] bytes, int offset, int count) throws IOException {
        int numOfRead = super.read(bytes, offset, count);

        for(int i = offset; i < offset + numOfRead; i++) {
            if(bytes[i] == from) {
                bytes[i] = to;
            }
        }

        return numOfRead;
    }
}
