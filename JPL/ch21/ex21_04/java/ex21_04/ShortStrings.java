/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex21_04;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ShortStrings implements ListIterator<String> {

    private final ListIterator<String> strings;
    private final int maxLen;

    private int listSize = 0;
    private LastOperation lastOperation = LastOperation.NONE;

    private String previousShort;
    private String nextShort;

    public ShortStrings(ListIterator<String> strings, int maxLen) {
        this.strings = strings;
        this.maxLen = maxLen;
    }

    @Override
    public boolean hasNext() {
        switch (lastOperation) {
            case NONE:
                // NOP
                break;
            case NEXT:
                if (nextShort != null) {
                    return true;
                }
                break;
            case PREVIOUS:
                while (strings.hasNext()) {
                    nextShort = strings.next();
                    if (nextShort.length() <= maxLen) {
                        listSize++;
                    }
                }
                break;
            case SET:
                // NOP
                break;
        }

        lastOperation = LastOperation.NEXT;
        while (strings.hasNext()) {
            nextShort = strings.next();
            if (nextShort.length() <= maxLen) {
                listSize++;
                return true;
            }
        }
        nextShort = null;
        return false;
    }

    @Override
    public String next() {
        if (nextShort == null && !hasNext()) {
            throw new NoSuchElementException();
        }

        String temp = nextShort;
        nextShort = null;
        return temp;
    }

    @Override
    public boolean hasPrevious() {
        switch (lastOperation) {
            case NONE:
                // NOP
                break;
            case NEXT:
                while (strings.hasPrevious()) {
                    previousShort = strings.previous();
                    if (previousShort.length() <= maxLen) {
                        listSize--;
                        return true;
                    }
                }
                break;
            case PREVIOUS:
                if (previousShort != null) {
                    return true;
                }
                break;
            case SET:
                // NOP
                break;
        }

        lastOperation = LastOperation.PREVIOUS;
        while (strings.hasPrevious()) {
            previousShort = strings.previous();
            if (previousShort.length() <= maxLen) {
                listSize--;
                return true;
            }
        }
        previousShort = null;
        return false;
    }

    @Override
    public String previous() {
        if (previousShort == null && !hasPrevious()) {
            throw new NoSuchElementException();
        }

        String temp = previousShort;
        previousShort = null;
        return temp;
    }

    @Override
    public int nextIndex() {
        return hasNext() ? listSize - 1 : listSize;
    }

    @Override
    public int previousIndex() {
        return hasPrevious() ? listSize : -1;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void set(String string) {
        previousShort = null;
        nextShort = null;

        LastOperation snapShot = lastOperation;
        switch (snapShot) {
            case NONE:
                hasNext();
                break;
            case NEXT:
                hasPrevious();
                break;
            case PREVIOUS:
                hasNext();
                break;
            case SET:
                // NOP
                break;
        }

        lastOperation = LastOperation.SET;
        if (string.length() <= maxLen) {
            strings.set(string);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void add(String string) {
        // End of list is can't define when hasNext is false.
        throw new UnsupportedOperationException();
    }

    private enum LastOperation {
        NONE, NEXT, PREVIOUS, SET;
    }
}
