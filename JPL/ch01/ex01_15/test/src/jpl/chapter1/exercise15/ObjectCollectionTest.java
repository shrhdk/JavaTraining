package jpl.chapter1.exercise15;

import org.junit.*;
import static org.junit.Assert.*;

import jpl.chapter1.exercise15.*;

public class ObjectCollectionTest {

    ObjectCollection collection;
    Object hoge;
    Object fuga;
    Object piyo;

    private void prepare() {
	collection = new ObjectCollection(3);
	hoge = new Object();
	fuga = new Object();
	piyo = new Object();
	collection.add("hoge", hoge);
	collection.add("fuga", fuga);
	collection.add("piyo", piyo);
    }

    private void teardown() {
	collection = null;
	hoge = null;
	fuga = null;
	piyo = null;
    }

    @Test
    public void testFindForFound() {
	prepare();

	Object hoge_ = collection.find("hoge");
	Object fuga_ = collection.find("fuga");
	Object piyo_ = collection.find("piyo");

	assertEquals(hoge, hoge_);
	assertEquals(fuga, fuga_);
	assertEquals(piyo, piyo_);

	teardown();
    }

    @Test
    public void testFindForNotFound() {
	prepare();

	Object result = collection.find("NameDoesNotExist");
	assertEquals(null, result);

	teardown();
    }

    @Test
    public void testFindForEmptyCollection() {
	ObjectCollection collection = new ObjectCollection(5);
	Object result = collection.find("NameDoesNotExist");

	assertEquals(null, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindForNameIsNull() {
	prepare();

	collection.find(null);

	teardown();
    }

    @Test
    public void testRemoveForFound() {
	prepare();

	boolean existed;
	Object object;

	existed = collection.remove("hoge");
	object = collection.find("hoge");
	assertEquals(true, existed);
	assertEquals(null, object);

	existed = collection.remove("fuga");
	object = collection.find("fuga");
	assertEquals(true, existed);
	assertEquals(null, object);

	existed = collection.remove("piyo");
	object = collection.find("piyo");
	assertEquals(true, existed);
	assertEquals(null, object);

	teardown();
    }

    @Test
    public void testRemoveForNotFound() {
	prepare();

	boolean existed = collection.remove("NameDoesNotExist");

	assertEquals(false, existed);
	assertEquals(hoge, collection.find("hoge"));
	assertEquals(fuga, collection.find("fuga"));
	assertEquals(piyo, collection.find("piyo"));

	teardown();
    }

    @Test
    public void testRemoveForEmptyCollection() {
	ObjectCollection collection = new ObjectCollection(5);
	boolean existed = collection.remove("NameDoesNotExist");

	assertEquals(false, existed);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveForNameIsNull() {
	prepare();

	collection.find(null);

	teardown();
    }

    @Test
    public void testAdd() {
	ObjectCollection collection = new ObjectCollection(5);
	Object hoge = new Object();
	collection.add("hoge", hoge);

	assertEquals(hoge, collection.find("hoge"));
    }

    @Test
    public void testAddForExistingName() {
	prepare();

	Object newHoge = new Object();
	collection.add("hoge", newHoge);
	Object found = collection.find("hoge");

	assertNotEquals(hoge, found);
	assertEquals(newHoge, found);

	teardown();
    }

    @Test
    public void testAddForFullCollection() {
	prepare();

	boolean succeeded = collection.add("NewItem", new Object());

	assertEquals(false, succeeded);
	assertEquals(hoge, collection.find("hoge"));
	assertEquals(fuga, collection.find("fuga"));
	assertEquals(piyo, collection.find("piyo"));

	teardown();
    }

    @Test
    public void testAddForValueIsNull() {
	ObjectCollection collection = new ObjectCollection(3);
	collection.add("hoge", null);

	assertEquals(null, collection.find("hoge"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddForNameIsNull() {
	prepare();

	collection.add(null, new Object());

	teardown();
    }
}
