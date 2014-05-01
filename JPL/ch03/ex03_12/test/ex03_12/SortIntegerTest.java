/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex03_12;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SortIntegerTest {
    @Test
    public void sort() {
        Integer[] data = new Integer[]{60, 9, 48, 23, 10, 12, 9, 28, 54, 72};

        // Sort and get metrics
        SortInteger sortInteger = new SortInteger();
        sortInteger.sort(data);
        SortMetrics metrics = sortInteger.getMetrics();

        // Expectations
        long expectedProbeCnt = 0;
        long expectedCompareCnt = (1 + data.length - 1) * (data.length - 1) / 2; // Sum of 1, 2, ..., data.length - 1
        long expectedSwapCnt = 18;

        // Assertion
        assertThat(metrics.probeCnt, is(expectedProbeCnt));
        assertThat(metrics.compareCnt, is(expectedCompareCnt));
        assertThat(metrics.swapCnt, is(expectedSwapCnt));
    }
}
