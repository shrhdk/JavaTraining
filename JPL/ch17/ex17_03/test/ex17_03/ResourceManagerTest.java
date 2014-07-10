/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex17_03;

import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

import java.lang.ref.Reference;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ResourceManagerTest {

    // given
    private Object key = new Object();
    private final ResourceManager rm = new ResourceManager();

    // Test case

    @Test(expected = IllegalArgumentException.class)
    public void getResourceWithNullKey() {
        rm.getResource(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void useWithNullKey() {
        final Resource res = rm.getResource(key);
        res.use(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void userWithInvalidKey() {
        final Resource res = rm.getResource(key);
        res.use(new Object());
    }

    @Test
    public void useWithValidKey() {
        final Resource res = rm.getResource(key);
        res.use(key);
    }

    @Test
    public void testReaperWillWork() {
        // when get resource
        final Resource res = rm.getResource(key);

        // then a reference of key will be queued
        assertThat(refsSize(rm), is(1));
        assertThat(isReleased(res), is(false));

        // when the key is garbage collected
        key = null;
        fullGC();

        // then reference will be reaped
        assertThat(refsSize(rm), is(0));
        assertThat(isReleased(res), is(true));
    }

    @Test
    public void testReaperWontWork() {
        // when get resource
        final Resource res = rm.getResource(key);

        // then a reference of key will be queued
        assertThat(refsSize(rm), is(1));
        assertThat(isReleased(res), is(false));

        // when the key is not garbage collected
        // key = null;
        fullGC();

        // then reference will not be reaped
        assertThat(refsSize(rm), is(1));
        assertThat(isReleased(res), is(false));
    }

    // Utility methods

    private static boolean isReleased(Resource res) {
        synchronized (res) {
            return !(boolean) Whitebox.getInternalState(res, "needsRelease");
        }
    }

    private static int refsSize(ResourceManager rm) {
        synchronized (rm) {
            final Map<Reference<?>, Resource> refs = (Map) (Whitebox.getInternalState(rm, "refs"));
            return refs.size();
        }
    }

    private static void fullGC() {
        final Runtime rt = Runtime.getRuntime();

        long isFree = rt.freeMemory();
        long wasFree;
        do {
            wasFree = isFree;
            rt.runFinalization();
            rt.gc();
            isFree = rt.freeMemory();
        } while(wasFree < isFree);
    }
}
