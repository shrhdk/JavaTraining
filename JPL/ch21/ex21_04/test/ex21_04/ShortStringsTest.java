/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex21_04;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ShortStringsTest {

    List<String> strings = Arrays.asList("6hello", "6world", "9hogehoge", "10piyopiyo", "7yahho-", "2a");
    ListIterator listIterator;

    @Before
    public void setup() {
        strings = Arrays.asList("6hello", "6world", "9hogehoge", "10piyopiyo", "7yahho-", "2a");
        listIterator = strings.listIterator();
    }

    @Test
    public void hasNext() {
        // "6hello", "6world", "2a"
        ShortStrings shortStrings = new ShortStrings(listIterator, 6);

        assertThat(shortStrings.hasNext(), is(true)); // "6hello"
        shortStrings.next();
        assertThat(shortStrings.hasNext(), is(true)); // "6world"
        shortStrings.next();
        assertThat(shortStrings.hasNext(), is(true)); // "2a"
        shortStrings.next();
        assertThat(shortStrings.hasNext(), is(false)); // Nothing
    }

    @Test
    public void nextIndex() {
        // "6hello", "6world", "2a"
        ShortStrings shortStrings = new ShortStrings(listIterator, 6);

        assertThat(shortStrings.nextIndex(), is(0)); // 0 "6hello"
        shortStrings.next();
        assertThat(shortStrings.nextIndex(), is(1)); // 1 "6hello"
        shortStrings.next();
        assertThat(shortStrings.nextIndex(), is(2)); // 2 "6hello"
        shortStrings.next();
        assertThat(shortStrings.nextIndex(), is(3)); // 3 Nothing
    }

    @Test
    public void hasPrevious() {
        // "6hello", "6world", "2a"
        ShortStrings shortStrings = new ShortStrings(listIterator, 6);

        shortStrings.next(); // "6hello"
        shortStrings.next(); // "6world"
        shortStrings.next(); // "2a"

        assertThat(shortStrings.hasPrevious(), is(true)); // "2a"
        shortStrings.previous();
        assertThat(shortStrings.hasPrevious(), is(true)); // "6world"
        shortStrings.previous();
        assertThat(shortStrings.hasPrevious(), is(true)); // "6hello"
        shortStrings.previous();

        assertThat(shortStrings.hasPrevious(), is(false)); // Nothing
    }

    @Test
    public void previousIndex() {
        // "6hello", "6world", "2a"
        ShortStrings shortStrings = new ShortStrings(listIterator, 6);

        shortStrings.next(); // "6hello"
        shortStrings.next(); // "6world"
        shortStrings.next(); // "2a"

        assertThat(shortStrings.previousIndex(), is(2)); // 2 "6hello"
        shortStrings.previous();
        assertThat(shortStrings.previousIndex(), is(1)); // 1 "6hello"
        shortStrings.previous();
        assertThat(shortStrings.previousIndex(), is(0)); // 0 "6hello"
        shortStrings.previous();
        assertThat(shortStrings.previousIndex(), is(-1)); // -1 Nothing
    }

    @Test
    public void nextWithoutHasNext() {
        // "6hello", "6world", "2a"
        ShortStrings shortStrings = new ShortStrings(listIterator, 6);

        assertThat(shortStrings.next(), is("6hello"));
        assertThat(shortStrings.next(), is("6world"));
        assertThat(shortStrings.next(), is("2a"));
    }

    @Test
    public void previousWithoutHasPrevious() {
        // "6hello", "6world", "2a"
        ShortStrings shortStrings = new ShortStrings(listIterator, 6);

        shortStrings.next(); // "6hello"
        shortStrings.next(); // "6world"
        shortStrings.next(); // "2a"

        assertThat(shortStrings.previous(), is("2a"));
        assertThat(shortStrings.previous(), is("6world"));
        assertThat(shortStrings.previous(), is("6hello"));
    }

    @Test(expected = NoSuchElementException.class)
    public void nextCauseNoSuchElementException() {
        // "6hello", "6world", "2a"
        ShortStrings shortStrings = new ShortStrings(listIterator, 6);

        shortStrings.next(); // "6hello"
        shortStrings.next(); // "6world"
        shortStrings.next(); // "2a"
        shortStrings.next(); // throws NoSuchElementException

    }

    @Test(expected = NoSuchElementException.class)
    public void previousCauseNoSuchElementException() {
        // "6hello", "6world", "2a"
        ShortStrings shortStrings = new ShortStrings(listIterator, 6);

        shortStrings.previous(); // throws NoSuchElementException
    }

    @Test(expected = UnsupportedOperationException.class)
    public void remove() {
        // "6hello", "6world", "2a"
        ShortStrings shortStrings = new ShortStrings(listIterator, 6);

        shortStrings.remove();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void add() {
        // "6hello", "6world", "2a"
        ShortStrings shortStrings = new ShortStrings(listIterator, 6);

        shortStrings.add("hoge");
    }
}
