package ex20_03;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class EncryptOutputStream extends FilterOutputStream {

    private final byte key;

    public EncryptOutputStream(OutputStream outputStream, byte key) {
        super(outputStream);
        this.key = key;
    }

    @Override
    public void write(int b) throws IOException {
        super.write((byte)b ^ key);
    }
}
