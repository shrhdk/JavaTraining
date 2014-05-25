/*
 * Copyright(C) 2014 Hideki Shiro
 */

package ex16_09;

import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class ClassViewer {

    public static String getClassDeclarationString(Class class_) {
        return getClassDeclarationString(class_, 0);
    }

    private static String getClassDeclarationString(Class class_, int depth) {

        final StringBuilder str = new StringBuilder();

        //Indent
        str.append(getIndent(depth));

        // Modifier
        final String modifier = getModifierString(class_);
        if (modifier != null) {
            str.append(modifier);
            str.append(" ");
        }

        // Class Name and Generic Declaration
        str.append("class ");
        str.append(class_.getSimpleName());
        final String genericDeclaration = getGenericDeclarationString(class_);
        if (genericDeclaration != null) {
            str.append(genericDeclaration);
        }
        str.append(" ");

        // extends...
        final String extend = getSuperClassString(class_);
        if (extend != null) {
            str.append("extends ");
            str.append(extend);
            str.append(" ");
        }

        // implements...
        final String implement = getInterfaceString(class_);
        if (implement != null) {
            str.append("implements ");
            str.append(implement);
            str.append(" ");
        }

        str.append(String.format("{%n"));

        // Inner class
        Class<?>[] inners = class_.getDeclaredClasses();
        for (Class<?> inner : inners) {
            str.append(getClassDeclarationString(inner, depth + 1));
        }

        //Indent
        str.append(getIndent(depth));

        str.append(String.format("}%n"));

        return str.toString();
    }

    private static String getIndent(int width) {
        final StringBuilder str = new StringBuilder();
        for (int i = 0; i < width; i++) {
            str.append("    ");
        }
        return str.toString();
    }

    private static String getModifierString(Class class_) {
        if (class_.getModifiers() == 0) {
            return null;
        } else {
            return Modifier.toString(class_.getModifiers());
        }
    }

    private static String getGenericDeclarationString(Class class_) {
        final StringBuilder genericDeclaration = new StringBuilder();

        final TypeVariable<Class>[] typeVars = class_.getTypeParameters();

        if (typeVars.length == 0) {
            return null;
        } else {
            genericDeclaration.append("<");
            for (TypeVariable<Class> typeVar : typeVars) {
                genericDeclaration.append(typeVar.getName());
                genericDeclaration.append(", ");
            }
            genericDeclaration.delete(genericDeclaration.length() - 2, genericDeclaration.length()); // Delete last ", "
            genericDeclaration.append(">");

            return genericDeclaration.toString();
        }
    }

    private static String getSuperClassString(Class class_) {
        String extend;
        final Type super_ = class_.getGenericSuperclass();
        if (super_ == null) { // class_ is Object
            extend = null;
        } else if (super_.equals(Object.class)) {
            extend = null;
        } else if (super_ instanceof ParameterizedType) {
            extend = getParametrizedTypeString((ParameterizedType) super_);
        } else if (super_ instanceof Class<?>) {
            extend = ((Class<?>) super_).getSimpleName();
        } else {
            extend = null;
        }
        return extend;
    }

    private static String getInterfaceString(Class class_) {
        final StringBuilder implement = new StringBuilder();

        final Type[] interfaces = class_.getGenericInterfaces();

        if (interfaces.length == 0) {
            return null;
        } else {
            for (Type interface_ : interfaces) {
                if (interface_ instanceof ParameterizedType) {
                    implement.append(getParametrizedTypeString((ParameterizedType) interface_));
                } else if (interface_ instanceof Class<?>) {
                    implement.append(((Class<?>) interface_).getSimpleName());
                }
                implement.append(", ");
            }

            implement.delete(implement.length() - 2, implement.length()); // Delete last ", "

            return implement.toString();
        }
    }

    private static String getParametrizedTypeString(ParameterizedType parametrized) {
        StringBuilder str = new StringBuilder();
        str.append(((Class<?>) parametrized.getRawType()).getSimpleName());
        str.append("<");
        for (Type arg : parametrized.getActualTypeArguments()) {
            if (arg instanceof ParameterizedType) {
                str.append(getParametrizedTypeString((ParameterizedType) arg));
            } else if (arg instanceof Class<?>) {
                str.append(((Class<?>) arg).getSimpleName());
            }
            str.append(", ");
        }
        str.delete(str.length() - 2, str.length()); // Delete last ", "
        str.append(">");

        return str.toString();
    }
}
