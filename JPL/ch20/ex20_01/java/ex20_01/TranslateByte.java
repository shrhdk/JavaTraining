package ex20_01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TranslateByte {

    public static void main(String[] args) throws IOException {
        byte from = (byte) args[0].charAt(0);
        byte to = (byte) args[1].charAt(0);

        translate(from, to, System.in, System.out);
    }

    public static void translate(byte from, byte to, InputStream src, OutputStream dst) throws IOException {
        int b;
        while ((b = src.read()) != -1) {
            if (b == from) {
                b = to;
            }
            dst.write(b);
        }
    }
}
