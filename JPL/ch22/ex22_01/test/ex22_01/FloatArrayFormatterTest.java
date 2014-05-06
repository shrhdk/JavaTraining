/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex22_01;

import org.junit.Test;

import java.util.Random;

public class FloatArrayFormatterTest {
    @Test
    public void printArray() {
        final Random random = new Random();
        final float[] data = new float[100];

        for(int i = 0; i < data.length; i++) {
            data[i] = random.nextFloat();
        }

        FloatArrayFormatter.printArray(data, 8);
    }
}
