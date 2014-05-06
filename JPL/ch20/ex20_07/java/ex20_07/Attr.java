/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex20_07;

import java.io.*;

public class Attr {

    private final String name;
    private Object value = null;

    public Attr(DataInput in) throws IOException {
        name = in.readUTF();

        int length = in.readInt();
        byte[] data = new byte[length];
        in.readFully(data);
        value = toObject(data);
    }

    public Attr(String name, Object value) {
        if (name == null || name.equals(""))
            throw new IllegalArgumentException("name can't be null or empty.");

        this.name = name;
        this.value = value;
    }

    public Attr(String name) {
        this(name, null);
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public Object setValue(Object newValue) {
        Object oldValue = value;
        value = newValue;
        return oldValue;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + value.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Attr)) {
            return false;
        }

        Attr attr = (Attr) other;
        return name.equals(attr.name) && value.equals(attr.value);
    }

    @Override
    public String toString() {
        return name + "='" + value + "'";
    }

    public void write(DataOutput out) throws IOException {
        out.writeUTF(getName());
        byte[] data = toByteArray(value);
        out.writeInt(data.length);
        out.write(data);
    }

    private static byte[] toByteArray(Object object) throws IOException {
        byte[] array;
        try (
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutput oo = new ObjectOutputStream(baos);
        ) {
            oo.writeObject(object);
            array = baos.toByteArray();
        }

        return array;
    }

    private static Object toObject(byte[] array) throws IOException {
        Object object;
        try (
                ByteArrayInputStream bais = new ByteArrayInputStream(array);
                ObjectInput oi = new ObjectInputStream(bais);
        ) {
            try {
                object = oi.readObject();
            } catch (ClassNotFoundException e) {
                // Always Object
                throw new RuntimeException();
            }
        }
        return object;
    }
}
