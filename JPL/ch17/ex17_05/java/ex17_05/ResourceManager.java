/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex17_05;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class ResourceManager {

    final ReferenceQueue<Object> queue = new ReferenceQueue<>();
    final Map<Reference<?>, Resource> refs = new HashMap<>();
    boolean isShutdown = false;

    public ResourceManager() {
        // ... Initialize resource ...
    }

    private synchronized void reap() {
        Reference<?> ref;
        while ((ref = queue.poll()) != null) {
            Resource res = null;
            synchronized (ResourceManager.this) {
                res = refs.get(ref);
                refs.remove(ref);
            }
            res.release();
            ref.clear();
        }
    }

    public synchronized void shutdown() {
        if (!isShutdown) {
            isShutdown = true;

            for (Map.Entry<Reference<?>, Resource> entry : refs.entrySet()) {
                entry.getValue().release(); // Rlease Resource
                entry.getKey().clear();     // Clear Reference
            }

            refs.clear();
        }
    }

    public synchronized Resource getResource(Object key) {
        if (isShutdown) {
            throw new IllegalStateException();
        }

        reap();


        final Resource res = new ResourceImpl(key);
        final Reference<?> ref = new PhantomReference<Object>(key, queue);
        refs.put(ref, res);
        return res;
    }

    private class ResourceImpl implements Resource {
        Reference<Object> keyRef;
        boolean needsRelease = false;

        ResourceImpl(Object key) {
            if (key == null) {
                throw new IllegalArgumentException();
            }

            keyRef = new WeakReference<Object>(key);

            // ... Configure External Resource ...

            needsRelease = true;
        }

        @Override
        public void use(Object key, Object... args) {
            if (key == null || key != keyRef.get()) {
                throw new IllegalArgumentException("wrong key");
            }

            // ... Use Resource ...
        }

        @Override
        public synchronized void release() {
            if (needsRelease) {
                needsRelease = false;

                // ... Release Resource ...
            }
        }
    }
}
