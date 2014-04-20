package interpret;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.*;

public class Utility {

    public static Object construct(Constructor constructor, Object... args) throws Throwable {
        try {
            if (args == null) {
                return constructor.newInstance();
            } else {
                return constructor.newInstance(args);
            }
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }

    public static Object invoke(Object object, Method method, Object... args) throws Throwable {
        try {
            if (args == null) {
                return method.invoke(object);
            } else {
                return method.invoke(object, args);
            }
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
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
}
