package interpret;

import java.lang.reflect.Method;
import java.util.Comparator;

public class MethodNameComparator implements Comparator<Method> {
    @Override
    public int compare(Method method1, Method method2) {
        String name1 = method1.getName();
        String name2 = method2.getName();
        return name1.compareTo(name2);
    }
}
