/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex17_05;

import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

import java.lang.ref.Reference;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

public class ResourceManagerTest {

    // given
    private Object key1 = new Object();
    private Object key2 = new Object();
    private Object key3 = new Object();
    private final ResourceManager rm = new ResourceManager();

    // Test case

    @Test
    public void testReaperAtGetResource() {
        final Resource res1 = rm.getResource(key1);
        final Resource res2 = rm.getResource(key2);

        assertThat(resourcesOf(rm), hasItem(res1));
        assertThat(resourcesOf(rm), hasItem(res2));

        key1 = null;
        fullGC();

        key2 = null;
        fullGC();

        assertThat(resourcesOf(rm), hasItem(res1));
        assertThat(resourcesOf(rm), hasItem(res2));

        final Resource res3 = rm.getResource(key3);

        assertThat(resourcesOf(rm), not(hasItem(res1)));
        assertThat(resourcesOf(rm), not(hasItem(res2)));
        assertThat(resourcesOf(rm), hasItem(res3));
    }

    @Test
    public void testReaperAtShutdown() {
        final Resource res1 = rm.getResource(key1);

        assertThat(resourcesOf(rm), hasItem(res1));

        rm.shutdown();

        assertThat(resourcesOf(rm), not(hasItem(res1)));
    }

    // Utility methods

    private static Collection<Resource> resourcesOf(ResourceManager rm) {
        synchronized (rm) {
            final Map<Reference<?>, Resource> refs = (Map) (Whitebox.getInternalState(rm, "refs"));
            return new HashMap<Reference<?>, Resource>(refs).values();
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
        } while (wasFree < isFree);
    }
}
