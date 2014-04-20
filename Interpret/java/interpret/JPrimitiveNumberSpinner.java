package interpret;

import javax.swing.*;

public class JPrimitiveNumberSpinner extends JSpinner {

    private final Class<? extends Number> primitiveNumberType;

    public JPrimitiveNumberSpinner(Class<? extends Number> primitiveNumberType, Number initialValue) {
        this.primitiveNumberType = primitiveNumberType;

        // Set model
        SpinnerModel model;
        if (primitiveNumberType == byte.class || primitiveNumberType == Byte.class) {
            model = new SpinnerNumberModel(initialValue.byteValue(), Byte.MIN_VALUE, Byte.MAX_VALUE, 1);
        } else if (primitiveNumberType == short.class || primitiveNumberType == Short.class) {
            model = new SpinnerNumberModel(initialValue.shortValue(), Short.MIN_VALUE, Short.MAX_VALUE, 1);
        } else if (primitiveNumberType == int.class || primitiveNumberType == Integer.class) {
            model = new SpinnerNumberModel(initialValue.intValue(), Integer.MIN_VALUE, Integer.MAX_VALUE, 1);
        } else if (primitiveNumberType == long.class || primitiveNumberType == Long.class) {
            model = new SpinnerNumberModel(initialValue.longValue(), Long.MIN_VALUE, Long.MAX_VALUE, 1);
        } else if (primitiveNumberType == float.class || primitiveNumberType == Float.class) {
            model = new SpinnerNumberModel(initialValue.floatValue(), 0, 0, 1);
        } else if (primitiveNumberType == double.class || primitiveNumberType == Double.class) {
            model = new SpinnerNumberModel(initialValue.doubleValue(), 0, 0, 1);
        } else {
            throw new IllegalArgumentException("type must be primitive or wrapper");
        }
        setModel(model);
    }

    public Number getPrimitiveValue() {
        if (primitiveNumberType == byte.class || primitiveNumberType == Byte.class) {
            return ((Number) getValue()).byteValue();
        } else if (primitiveNumberType == short.class || primitiveNumberType == Short.class) {
            return ((Number) getValue()).shortValue();
        } else if (primitiveNumberType == int.class || primitiveNumberType == Integer.class) {
            return ((Number) getValue()).intValue();
        } else if (primitiveNumberType == long.class || primitiveNumberType == Long.class) {
            return ((Number) getValue()).longValue();
        } else if (primitiveNumberType == float.class || primitiveNumberType == Float.class) {
            return ((Number) getValue()).floatValue();
        } else if (primitiveNumberType == double.class || primitiveNumberType == Double.class) {
            return ((Number) getValue()).doubleValue();
        } else {
            throw new IllegalArgumentException("type must be primitive or wrapper");
        }
    }
}