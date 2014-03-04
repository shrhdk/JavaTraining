package Interpret;

public class Primitive {
    public static Object toObject(Class<?> class_, String value) {


        if(class_ == byte.class || class_ == Byte.class) {
            return Byte.parseByte(value);
        } else if(class_ == short.class || class_ == Short.class) {
            return Short.parseShort(value);
        } else if(class_ == int.class || class_ == Integer.class) {
            return Integer.parseInt(value);
        } else if(class_ == long.class || class_ == Long.class) {
            return Long.parseLong(value);
        } else if(class_ == float.class || class_ == Float.class) {
            return Float.parseFloat(value);
        } else if(class_ == double.class || class_ == Double.class) {
            return Double.parseDouble(value);
        } else if(class_ == char.class || class_ == Character.class) {
            return value.charAt(0);
        } else if(class_ == boolean.class || class_ == Boolean.class) {
            return Boolean.parseBoolean(value);
        } else if(class_ == String.class) {
            return value;
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
