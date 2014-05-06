/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex21_07;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

// ArrayListのサブクラスとすべきか、内部で使用すべきか
// →内部で使用する。
// ・ArrayListのinterfaceを直接叩かれるとStackを破壊される可能性がある。
// ・公開するinterfaceは最小限にしたい。

public class Stack<E> {
    private final List<E> list = new ArrayList<E>();

    public boolean empty() {
        return list.isEmpty();
    }

    public E peek() {
        if(empty()) {
            throw new EmptyStackException();
        }

        return list.get(list.size() - 1);
    }

    public E pop() {
        if(empty()) {
            throw new EmptyStackException();
        }

        return list.remove(list.size() - 1);
    }

    public E push(E item) {
        list.add(item);
        return item;
    }

    public int search(Object o) {
        int index = list.lastIndexOf(o);
        if(index == -1) {
            return -1;
        } else {
            return list.size() - index;
        }
    }
}
