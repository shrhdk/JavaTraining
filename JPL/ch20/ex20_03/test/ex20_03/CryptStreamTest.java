package ex20_03;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class CryptStreamTest {

    @Test
    public void encryptAndDecrypt() throws IOException {
        final byte key = (byte) 0xAA;

        final byte[] plain = "hello world".getBytes("US-ASCII");
        final byte[] encrypted;
        final byte[] decrypted = new byte[plain.length];

        // Encrypt
        ByteArrayOutputStream encryptedStream = new ByteArrayOutputStream();
        EncryptOutputStream encryptor = new EncryptOutputStream(encryptedStream, key);
        encryptor.write(plain);
        encrypted = encryptedStream.toByteArray();

        // Decrypt
        ByteArrayInputStream encryptedInputStream = new ByteArrayInputStream(encrypted);
        DecryptInputStream decryptor = new DecryptInputStream(encryptedInputStream, key);
        decryptor.read(decrypted);

        // Assertion
        assertThat(encrypted, is(not(plain)));
        assertThat(decrypted, is(plain));
    }
}
