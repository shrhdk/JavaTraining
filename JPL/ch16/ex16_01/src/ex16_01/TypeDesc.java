package ex16_01;

import java.lang.reflect.*;

public class TypeDesc {

	public static void main(String[] args) {
		TypeDesc desc = new TypeDesc();
		for (String name : args) {
			try {
				Class<?> startClass = Class.forName(name);
				desc.printType(startClass, 0, basic);
			} catch (ClassNotFoundException e) {
				System.err.println(e); // report the error
			}
		}
	}

	// デフォルトで標準出力に表示する
	private java.io.PrintStream out = System.out;

	// 型名にラベル付けする printType() で使用される
	private static String[] basic = { "class", "interface", "enum",
			"annotation" };
	private static String[] supercl = { "etextends", "implements" };
	private static String[] iFace = { null, "extends" };

	private void printType(Type type, int depth, String[] labels) {
		// 再帰呼び出し停止: スーパータイプが存在しない
		if (type == null)
			return;

		// Type をClass オブジェヲトに変換する
		Class<?> cls = null;
		if (type instanceof Class<?>)
			cls = (Class<?>) type;
		else if (type instanceof ParameterizedType)
			cls = (Class<?>) ((ParameterizedType) type).getRawType();
		else
			throw new Error("Unexpected non-class type");

		// この型を表示
		for (int i = 0; i < depth; i++)
			out.print(" ");
		int kind = cls.isAnnotation() ? 3 : cls.isEnum() ? 2 : cls
				.isInterface() ? 1 : 0;
		out.print(labels[kind] + " ");
		out.print(cls.getCanonicalName());

		// あれば、ジェネリック型パラメータを表示
		TypeVariable<?>[] params = cls.getTypeParameters();
		if (params.length > 0) {
			out.print('<');
			for (TypeVariable<?> param : params) {
				out.print(param.getName());
				out.print(", ");
			}
			out.println("\b\b>");
		} else
			out.println();

		// このクラスが実装しているすべてのインタフェースを表示
		Type[] interfaces = cls.getGenericInterfaces();
		for (Type iface : interfaces) {

			printType(iface, depth + 1, cls.isInterface() ? iFace : supercl);
		}

		// スーパクラスに対して再帰
		if (cls.getGenericSuperclass() != Object.class) {
			printType(cls.getGenericSuperclass(), depth + 1, supercl);
		}
	}
}