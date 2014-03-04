package ex16_05;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ClassAnnotations {
    public static void main(String[] args) {
        try {
            Class<?> c = Class.forName(args[0]);

            printAnnotations(c);
            printAnnotations(c.getDeclaredConstructors());
            printAnnotations(c.getDeclaredFields());
            printAnnotations(c.getDeclaredMethods());
        } catch (ClassNotFoundException e) {
            System.out.println("unknown class: " + args[0]);
        }
    }

    public static void printAnnotations(AnnotatedElement element) {
        printAnnotations(element.getAnnotations());

        if (element instanceof Class<?>) {
            System.out.println(((Class) element).getName());
        } else if (element instanceof Member) {
            System.out.println(((Member) element).getName());
        }

        System.out.println();
    }

    public static void printAnnotations(AnnotatedElement[] elements) {
        for (AnnotatedElement element : elements) {
            printAnnotations(element);
        }
    }

    public static void printAnnotations(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
    }
}
