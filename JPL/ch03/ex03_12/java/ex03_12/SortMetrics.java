/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex03_12;

public class SortMetrics implements Cloneable {
    public long probeCnt;
    public long compareCnt;
    public long swapCnt;

    public void init() {
        probeCnt = swapCnt = compareCnt = 0;
    }

    public String toString() {
        return probeCnt + " probes " + compareCnt + " compares " + swapCnt + " swaps";
    }

    /** This class support clone */
    public SortMetrics clone() {
        try {
            return (SortMetrics) super.clone();
        } catch (CloneNotSupportedException e) {
            // Can't happen. This class and Object are cloneable
            throw new InternalError(e.toString());
        }
    }
}