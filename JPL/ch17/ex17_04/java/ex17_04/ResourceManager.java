/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex17_04;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class ResourceManager {

    final ReferenceQueue<Object> queue = new ReferenceQueue<>();
    final Map<Reference<?>, Resource> refs = new HashMap<>();
    final Thread reaper = new ReaperThread();
    boolean isShutdown = false;

    public ResourceManager() {
        reaper.start();

        // ... Initialize resource ...
    }

    public synchronized void shutdown() {
        if (!isShutdown) {
            isShutdown = true;
            reaper.interrupt();
        }
    }

    public synchronized Resource getResource(Object key) {
        if (isShutdown) {
            throw new IllegalStateException();
        }

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

    private class ReaperThread extends Thread {
        @Override
        public void run() {
            // Run until interrupted
            for (; ; ) {
                try {
                    final Reference<?> ref = queue.remove();
                    Resource res = null;
                    synchronized (ResourceManager.this) {
                        res = refs.get(ref);
                        refs.remove(ref);

                        if (interrupted() && refs.size() == 0) {
                            break;
                        }
                    }
                    res.release();
                    ref.clear();
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
