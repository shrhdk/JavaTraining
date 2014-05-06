/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex22_04;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;

public class AttributedImpl<T> extends Observable implements Attributed<T> {

    private final Map<String, Attr<T>> attrMap = new HashMap<String, Attr<T>>();

    @Override
    public void add(Attr<T> newAttr) {
        attrMap.put(newAttr.getName(), newAttr);
        setChanged();
        notifyObservers(attrMap);
    }

    @Override
    public Attr<T> find(String attrName) {
        return attrMap.get(attrName);
    }

    @Override
    public Attr<T> remove(String attrName) {
        Attr<T> ret = attrMap.remove(attrName);
        setChanged();
        notifyObservers(attrMap);
        return ret;
    }

    @Override
    public Iterator<Attr<T>> attrs() {
        return attrMap.values().iterator();
    }
}
