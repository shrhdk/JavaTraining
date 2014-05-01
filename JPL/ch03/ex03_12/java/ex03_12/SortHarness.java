/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex03_12;

public abstract class SortHarness<T extends Comparable<T>> {

    private T[] values;
    private final SortMetrics curMetrics = new SortMetrics();

    public final SortMetrics sort(T[] data) {
        values = data;
        curMetrics.init();
        doSort();
        return getMetrics() ;
    }

    public final SortMetrics getMetrics() {
        return curMetrics.clone();
    }

    protected final int getDataLength() {
        return values.length;
    }

    protected final T probe(int i) {
        curMetrics.probeCnt++;
        return values[i];
    }

    protected final int compare(int i, int j) {
        curMetrics.compareCnt++;
        return values[i].compareTo(values[j]);
    }

    protected final void swap(int i, int j) {
        curMetrics.swapCnt++;
        T tmp = values[i];
        values[i] = values[j];
        values[j] = tmp;
    }

    protected abstract void doSort();
}