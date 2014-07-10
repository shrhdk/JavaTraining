/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex17_04;

public interface Resource {
    void use(Object key, Object... args);
    void release();
}
