/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex22_01;

public class FloatArrayFormatter {

    private static final int LINE_WIDTH = 80;
    private static final int ITEM_WIDTH = "3.14e+00, ".length();
    private static final int MAX_COLUMN = LINE_WIDTH / ITEM_WIDTH;
    private static final String FORMAT_STRING = String.format("%%.%de, ", 2);

    public static void printArray(float[] array, int columns) {
        if(columns < 1 || MAX_COLUMN < columns) {
            throw new IllegalArgumentException(String.format("1 <= columns <= %d", MAX_COLUMN));
        }

        int column = 0;
        for(double item : array) {
            System.out.printf(FORMAT_STRING, item);
            if(column++ == MAX_COLUMN) {
                column = 0;
                System.out.println();
            }
        }
    }
}
