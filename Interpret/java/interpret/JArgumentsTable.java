package interpret;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class JArgumentsTable extends JTable {

    // Constants

    private final String[] columnNames = new String[]{"Type", "Value"};

    // Data

    private Class<?>[] types = new Class<?>[0];
    private Object[] objects = new Object[0];

    // API

    public JArgumentsTable() {
        setModel(new ArgumentsTableModel());
        getColumn(columnNames[0]).setCellRenderer(new ClassColumnRenderer());
        getColumn(columnNames[1]).setCellRenderer(new ValueCell());
        getColumn(columnNames[1]).setCellEditor(new ValueCell());
    }

    public void setClass(Class<?>... types) {
        if (types == null) {
            this.types = new Class<?>[0];
            objects = new Object[0];
        } else {
            this.types = types;
            objects = new Object[types.length];
        }

        // Set default values
        for(int i = 0; i < this.types.length; i++) {
            if (types[i] == byte.class || types[i] == Byte.class) {
                objects[i] = (byte)0;
            } else if (types[i] == short.class || types[i] == Short.class) {
                objects[i] = (short)0;
            } else if (types[i] == int.class || types[i] == Integer.class) {
                objects[i] = 0;
            } else if (types[i] == long.class || types[i] == Long.class) {
                objects[i] = 0L;
            } else if (types[i] == float.class || types[i] == Float.class) {
                objects[i] = (float)0;
            } else if (types[i] == double.class || types[i] == Double.class) {
                objects[i] = (double)0;
            } else if (types[i] == char.class || types[i] == Character.class) {
                objects[i] = ' ';
            } else if (types[i] == boolean.class || types[i] == Boolean.class) {
                objects[i] = false;
            } else if (types[i] == String.class) {
                objects[i] = "";
            } else if (types[i].isEnum()) {
                objects[i] = null;
            } else if (types[i].isArray()) {
                objects[i] = null;
            } else {
                objects[i] = null;
            }
        }

        updateUI();
    }

    public Object[] getValues() {
        return objects;
    }

    // Table Model

    private class ArgumentsTableModel extends AbstractTableModel {

        @Override
        public int getRowCount() {
            return types.length;
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int i) {
            return columnNames[i];
        }

        @Override
        public boolean isCellEditable(int i, int j) {
            switch (j) {
                case 0:
                    return false;
                case 1:
                    return true;
                default:
                    throw new AssertionError("");
            }
        }

        @Override
        public void setValueAt(Object value, int i, int j) {
            switch (j) {
                case 0:
                    break;
                case 1:
                    objects[i] = ((TypeValuePair) value).getValue();
                    break;
                default:
                    throw new AssertionError("");
            }
        }

        @Override
        public Object getValueAt(int i, int j) {
            switch (j) {
                case 0:
                    return types[i];
                case 1:
                    return new TypeValuePair(types[i], objects[i]);
                default:
                    throw new AssertionError("");
            }
        }
    }

    // Cell Renderer

    private class ClassColumnRenderer implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int i, int j) {
            return new JLabel(((Class<?>) value).getCanonicalName());
        }
    }
}
