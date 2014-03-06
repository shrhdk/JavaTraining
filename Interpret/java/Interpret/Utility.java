package Interpret;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.*;

public class Utility {

    public static Object construct(Constructor constructor, Object... args) throws Throwable {
        if (args == null) {
            return constructor.newInstance();
        } else {
            return constructor.newInstance(args);
        }
    }

    public static Object invoke(Object object, Method method, Object... args) throws Throwable {
        if (args == null) {
            return method.invoke(object);
        } else {
            return method.invoke(object, args);
        }
    }

    public static Object getField(Object object, Field field) throws Throwable {
        field.setAccessible(true);
        return field.get(object);
    }

    public static void setField(Object object, Field field, Object value) throws Throwable {
        field.setAccessible(true);
        field.set(object, value);
    }

    public static boolean isSettableField(Field field) {
        int modifiers = field.getModifiers();
        return !(Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers));
    }

    public static void showMessage(Component owner, String message) {
        JOptionPane.showMessageDialog(owner, message);
    }

    public static boolean isPrimitive(Class<?> class_) {
        if (class_ == byte.class || class_ == Byte.class) {
            return true;
        } else if (class_ == short.class || class_ == Short.class) {
            return true;
        } else if (class_ == int.class || class_ == Integer.class) {
            return true;
        } else if (class_ == long.class || class_ == Long.class) {
            return true;
        } else if (class_ == float.class || class_ == Float.class) {
            return true;
        } else if (class_ == double.class || class_ == Double.class) {
            return true;
        } else if (class_ == char.class || class_ == Character.class) {
            return true;
        } else if (class_ == boolean.class || class_ == Boolean.class) {
            return true;
        } else if (class_ == String.class) {
            return true;
        } else {
            return false;
        }
    }

    public static Object toObject(Class<?> class_, String value) {
        if (class_ == byte.class || class_ == Byte.class) {
            return Byte.parseByte(value);
        } else if (class_ == short.class || class_ == Short.class) {
            return Short.parseShort(value);
        } else if (class_ == int.class || class_ == Integer.class) {
            return Integer.parseInt(value);
        } else if (class_ == long.class || class_ == Long.class) {
            return Long.parseLong(value);
        } else if (class_ == float.class || class_ == Float.class) {
            return Float.parseFloat(value);
        } else if (class_ == double.class || class_ == Double.class) {
            return Double.parseDouble(value);
        } else if (class_ == char.class || class_ == Character.class) {
            return value.charAt(0);
        } else if (class_ == boolean.class || class_ == Boolean.class) {
            return Boolean.parseBoolean(value);
        } else if (class_ == String.class) {
            return value;
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
