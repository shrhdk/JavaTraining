package ex16_03;

import java.lang.reflect.*;

public class ClassContents {
	public static void main(String[] args) {
		try {
			Class<?> c = Class.forName(args[0]);
			System.out.println(c);
			
			printMembers(c.getDeclaredFields());
			printMembers(c.getDeclaredConstructors());
			printMembers(c.getDeclaredMethods());
			
			printMembers(c.getFields(), c);
			printMembers(c.getConstructors(), c);
			printMembers(c.getMethods(), c);
			
		} catch (ClassNotFoundException e) {
			System.out.println("unknown class: " + args[0]);
		}
	}
	
	private static void printMembers(Member[] mems) {
		printMembers(mems, null);
	}
	
	private static void printMembers(Member[] mems, Class<?> declared) {
		for (Member m : mems) {
			if (m.getDeclaringClass() == Object.class || m.getDeclaringClass() == declared)
				continue;
			String decl = m.toString();
			System.out.print(" ");
			System.out.println(strip(decl, "java.lang."));
		}
	}

	private static String strip(String value, String prefixToRemove) {
		String pattern = "(?<=\\W*)" + prefixToRemove.replaceAll("\\.", "\\\\.") + "(?=\\w+)";
		//System.out.println("value: " + value +", pattern: " + pattern);
		return value.replaceAll(pattern, "");
	}
}
