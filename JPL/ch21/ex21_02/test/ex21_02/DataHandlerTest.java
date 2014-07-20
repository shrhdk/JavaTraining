/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex21_02;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class DataHandlerTest {
    @Test
    public void readFile() throws IOException {
        StopWatch stopWatch = new StopWatch();
        DataHandler dataHandler = new DataHandler();
        byte[] lena;

        // Read file
        stopWatch.start();
        lena = dataHandler.readFile(new File("test/lena.png"));
        stopWatch.stop();
        long initial = stopWatch.getElapsedTime();

        // Read file with cache
        stopWatch.start();
        lena = dataHandler.readFile(new File("test/lena.png"));
        stopWatch.stop();
        long withCache = stopWatch.getElapsedTime();

        // Clear cache.
        lena = null;
        Runtime.getRuntime().gc();

        // Read file after clear cache.
        stopWatch.start();
        lena = dataHandler.readFile(new File("test/lena.png"));
        stopWatch.stop();
        long withNoCache = stopWatch.getElapsedTime();

        // Assertion
        assertTrue(withCache < initial);
        assertTrue(withCache < withNoCache);
    }

    private static class StopWatch {

        private long startTime = -1;
        private long stopTime = -1;
        private long elapsedTime = -1;

        public void start() {
            startTime = System.currentTimeMillis();
            elapsedTime = -1;
        }

        public void stop() {
            stopTime = System.currentTimeMillis();

            if (startTime == -1) {
                throw new IllegalStateException("You can call this method after start.");
            }

            elapsedTime = stopTime - startTime;
            startTime = -1;
            stopTime = -1;
        }

        public long getElapsedTime() {
            if (elapsedTime == -1) {
                throw new IllegalStateException("You can call this method after start and stop.");
            }

            return elapsedTime;
        }
    }
}