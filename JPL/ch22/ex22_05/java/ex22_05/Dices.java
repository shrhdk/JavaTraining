/*
 * Copyright(C) 2014 Hideki Shiro
 */

// Q. どの乱数発生メソッドを使用するかは、問題ンとなりますか
// A. nextGaussian以外はすべて内部でnextを使用しているので、nextGaussian以外であれば変わりない。
// ただし、nextBooleanは除く。

package ex22_05;

import java.util.Random;

public class Dices {

    private final int NUM_OF_FACETS = 6;
    private final int numOfDice;
    private final Random random = new java.util.Random();

    public Dices(int numOfDice) {
        this.numOfDice = numOfDice;
    }

    public int minSum() {
        return numOfDice * 1;
    }

    public int maxSum() {
        return numOfDice * NUM_OF_FACETS;
    }

    public int play() {
        int sum = 0;
        for (int i = 0; i < numOfDice; i++) {
            sum += random.nextInt(NUM_OF_FACETS) + 1;
        }
        return sum;
    }

    public double theoreticalProbability(int s) {
        if (s < minSum() || maxSum() < s) {
            return 0.0;
        }

        // サイコロ - Wikipedia 確率 任意の合計値
        // http://ja.wikipedia.org/wiki/%E3%82%B5%E3%82%A4%E3%82%B3%E3%83%AD#.E4.BB.BB.E6.84.8F.E3.81.AE.E5.90.88.E8.A8.88.E5.80.A4
        int imax = (s - numOfDice) / NUM_OF_FACETS;
        long cmb = 0;
        for (int i = 0; i <= imax; i++) {
            cmb += combination(s - NUM_OF_FACETS * i - 1, numOfDice - 1) * combination(numOfDice, i) * Math.pow(-1, i);
        }
        return cmb / Math.pow(NUM_OF_FACETS, numOfDice);
    }

    private static long combination(int n, int r) {
        return fact(n) / (fact(n - r) * fact(r));
    }

    private static long fact(int n) {
        long p = 1;
        for(int i = 1; i <= n; i++) {
            p *= i;
        }
        return p;
    }
}
