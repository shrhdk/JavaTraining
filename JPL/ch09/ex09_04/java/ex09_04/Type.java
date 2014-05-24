package ex09_04;

public class Type {
    public static void main(String[] args) {
        int i = 3;

        print(3 << 2L - 1);
        print((3L << 2) - 1);
        print(10 < 12 == 6 > 17);
        print(10 << 12 == 6 >> 17);
        print(13.5e-1 % Float.POSITIVE_INFINITY);
        print(Double.POSITIVE_INFINITY - Float.NEGATIVE_INFINITY);
        print(0.0 / -0.0 == -0.0 / 0.0);
        print(Integer.MAX_VALUE + Integer.MIN_VALUE);
        print(Long.MAX_VALUE + 5);
        print((short) 5 + (byte) 10);
        print(i < 15 ? 1.72e3f : 0);
        print(i++ + i++ + --i);
    }

    private static int num = 1;

    private static void print(Object expression) {
        System.out.printf("%2d %-8s %s%n", num++, expression.getClass().getSimpleName(), expression);
    }
}
