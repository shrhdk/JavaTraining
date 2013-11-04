package jpl.chapter1.exercise16;

import java.io.IOException;

public class BadDataSetException extends Exception {
    private static final long serialVersionUID = 1L;
    public final String setName;
    public final IOException internalIOException;

    public BadDataSetException(String setName, IOException e) {
	this.setName = setName;
	this.internalIOException = e;
    }
}
