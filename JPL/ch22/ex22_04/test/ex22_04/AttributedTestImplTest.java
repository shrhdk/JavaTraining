/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex22_04;

import org.junit.Before;
import org.junit.Test;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CountDownLatch;

public class AttributedTestImplTest {

    CountDownLatch latch;
    AttributedImpl<Integer> attributed;

    @Before
    public void setUp() {
        latch = new CountDownLatch(1);
        attributed = new AttributedImpl<Integer>();
    }

    @Test
    public void addAndNotify() throws Exception {
        attributed.addObserver(new Observer() {
            @Override
            public void update(Observable observable, Object o) {
                latch.countDown();
            }
        });
        attributed.add(new Attr<Integer>("hoge", 1));

        latch.await();
    }

    @Test
    public void removeAndNotify() throws Exception {
        attributed.add(new Attr<Integer>("hoge", 1));
        attributed.addObserver(new Observer() {
            @Override
            public void update(Observable observable, Object o) {
                latch.countDown();
            }
        });
        attributed.remove("hoge");

        latch.await();
    }
}
