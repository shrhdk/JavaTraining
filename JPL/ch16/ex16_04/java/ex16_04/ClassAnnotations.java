package ex16_04;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public class ClassAnnotations {
	
	public static void main(String[] args) {
		try {
			Class<?> c = Class.forName(args[0]);
			System.out.println(c);
			
			Annotation[] annotations = c.getAnnotations();
			
			printAnnotations(annotations);
		} catch (ClassNotFoundException e) {
			System.out.println("unknown class: " + args[0]);
		}
	}
	
	public static void printAnnotations(Annotation[] annotations) {
		for(Annotation annotation : annotations) {
			System.out.println(annotation);
		}
	}
}
