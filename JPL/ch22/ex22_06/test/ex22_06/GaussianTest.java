/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex22_06;

import org.junit.Test;

import java.util.Random;

public class GaussianTest {

    private final int NUM_OF_TRIALS = 100000;
    private final double UNIT = 0.01;
    private final Random random = new Random();


    @Test
    public void test() {

        long[] counts = new long[(int)(2 / UNIT)];

        for (int i = 0; i < NUM_OF_TRIALS; i++) {
            double a = normalizedGaussian() + 1;
            int index = (int) (a / UNIT);
            counts[index]++;
        }

        for (int i = 0; i < counts.length; i++) {
            System.out.printf("%f:", i * UNIT - 1);
            printAster(counts[i]);
        }
    }

    /**
     * @return Gaussian random value (-1 <= value <= 1)
     */
    private double normalizedGaussian() {
        double a = random.nextGaussian() * 0.1;
        if (a < -1) {
            a = -1;
        } else if (1 < a) {
            a = 1;
        }
        return a;
    }

    public static void printAster(long num) {
        for (long i = 0; i < num; i++) {
            System.out.print("*");
        }
        System.out.println();
    }
}
