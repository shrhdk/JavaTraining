package ex17_01;

public class Garbagecollection {

    private static final int NUM_OBJECTS = 65535;

    private static final Runtime rt = Runtime.getRuntime();
    private static Object[] objects = new Object[NUM_OBJECTS];

    public static void main(String[] args) {

        // Show initial free memory
        showFreeMemory("Initial");

        // Construct many objects and show free memory
        for(int i = 0; i < objects.length; i++) {
            objects[i] = new Object();
        }
        showFreeMemory("After construct many objects");

        // Garbage collect and show free memory
        for(int i = 0; i < objects.length; i++) {
            objects[i] = null;
        }
        rt.gc();
        showFreeMemory("After Garbage Collection");
    }

    private static void showFreeMemory(String label) {
        double freeMemoryMb = (double)rt.freeMemory() / 1024 / 1024;
        double totalMemoryMb = rt.totalMemory() / 1024 / 1024;
        System.out.println(String.format("%30s %3.0fMB / %3.0fMB (%2.0f%%)", label, freeMemoryMb, totalMemoryMb, freeMemoryMb / totalMemoryMb));
    }
}
