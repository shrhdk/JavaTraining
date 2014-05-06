/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex22_04;

public interface Attributed<T> {
    void add(Attr<T> newAttr);

    Attr<T> find(String attrName);

    Attr<T> remove(String attrName);

    java.util.Iterator<Attr<T>> attrs();
}