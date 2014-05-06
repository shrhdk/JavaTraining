/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex22_05;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DicesTest {

    private static final int NUM_OF_TRIALS = 100000;

    @Test
    public void test() {
        final Dices dices = new Dices(3);
        final int[] counts = new int[dices.maxSum() - dices.minSum() + 1];

        for(int i = 0; i < NUM_OF_TRIALS; i++) {
            counts[dices.play() - dices.minSum()]++;
        }

        System.out.println("Sum: experimental  theoretical  diff");
        for(int i = 0; i < counts.length; i++) {
            int dice = i + dices.minSum();
            double experimental = (double)counts[i] / NUM_OF_TRIALS;
            double theoretical = dices.theoreticalProbability(dice);
            double diff = experimental - theoretical;
            System.out.printf("%3d: %f      %f     %f%n", dice, experimental, theoretical, Math.abs(diff));
        }
    }

    @Test
    public void theoreticalProbability() {
        Dices dices = new Dices(3);
        assertThat(dices.theoreticalProbability(11), is(0.125));
    }
}
