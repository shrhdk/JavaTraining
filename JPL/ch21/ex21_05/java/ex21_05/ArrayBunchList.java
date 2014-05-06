/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex21_05;

import java.util.AbstractList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ArrayBunchList<E> extends AbstractList<E> {

    private final E[][] arrays;
    private final int size;

    public ArrayBunchList(E[][] arrays) {
        this.arrays = arrays.clone();
        int s = 0;
        for (E[] array : arrays) {
            s += array.length;
        }
        size = s;
    }

    @Override
    public E get(int index) {
        int offset = 0;
        for (int i = 0; i < arrays.length; i++) {
            if (index < offset + arrays[i].length) {
                return arrays[i][index - offset];
            }
            offset += arrays[i].length;
        }
        throw new ArrayIndexOutOfBoundsException(index);
    }

    @Override
    public E set(int index, E value) {
        int offset = 0;
        for (int i = 0; i < arrays.length; i++) {
            if (index < offset + arrays[i].length) {
                E ret = arrays[i][index - offset];
                arrays[i][index - offset] = value;
                return ret;
            }
            offset += arrays[i].length;
        }
        throw new ArrayIndexOutOfBoundsException(index);
    }

    @Override
    public int size() {
        return size;
    }

    public ListIterator<E> listIterator() {
        return new ABLListIterator();
    }

    private class ABLListIterator implements ListIterator<E> {

        /**
         * E[0]   E[1]   E[2]   ... E[n-1]
         * ^      ^      ^      ^            ^
         * pos:  0      1      2     n-1           n == size()
         */
        private int pos = 0;
        private int array = 0;
        private int index = 0;

        private int lastArray = -1;
        private int lastIndex = -1;

        @Override
        public boolean hasNext() {
            return pos < size();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            pos++;

            lastArray = array;
            lastIndex = index;
            if (index == arrays[array].length - 1) {
                array++;
                index = 0;
            } else if (pos != 1) {
                index++;
            }

            return arrays[array][index];
        }

        @Override
        public boolean hasPrevious() {
            return 1 <= pos;
        }

        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }

            pos--;

            lastArray = array;
            lastIndex = index;
            E ret = arrays[array][index];
            if (array == 0 && index == 0) {
                index = 0;
            } else  if(index == 0) {
                array--;
                index = arrays[array].length - 1;
            } else {
                index--;
            }
            return ret;
        }

        @Override
        public int nextIndex() {
            if (hasNext()) {
                return pos;
            } else {
                return size();
            }
        }

        @Override
        public int previousIndex() {
            if (hasPrevious()) {
                return pos - 1;
            } else {
                return -1;
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(E e) {
            if(lastArray == -1 || lastIndex == -1) {
                throw new IllegalStateException();
            }

            arrays[lastArray][lastIndex] = e;
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }
    }
}
