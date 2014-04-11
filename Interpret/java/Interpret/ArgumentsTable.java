package interpret;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ArgumentsTable extends JTable {

    // Constants

    private final String[] columnNames = new String[]{"Type", "Value"};

    // Data

    private Class<?>[] classes;
    private Object[] objects;

    // API

    public ArgumentsTable() {
        setModel(new ArgumentsTableModel());
        getColumn(columnNames[0]).setCellRenderer(new ClassColumnRenderer());
        getColumn(columnNames[1]).setCellRenderer(new ObjectColumnRenderer());
    }

    public void setClass(Class<?>... classes) {
        this.classes = classes;
        if (classes == null) {
            objects = null;
        } else {
            objects = new Object[classes.length];
        }

        updateUI();
    }

    // Model

    private class ArgumentsTableModel extends AbstractTableModel {

        @Override
        public int getRowCount() {
            if (classes == null) {
                return 0;
            } else {
                assert classes.length == objects.length;
                return classes.length;
            }
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
                    return Utility.isPrimitive(classes[i]);
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
                    if (Utility.isPrimitive(classes[i])) {
                        try {
                            objects[i] = Utility.toObject(classes[i], (String) value);
                        } catch (Exception e) {
                            Utility.showMessage(ArgumentsTable.this, e.toString());
                        }
                    }
                    break;
                default:
                    throw new AssertionError("");
            }
        }

        @Override
        public Object getValueAt(int i, int j) {
            switch (j) {
                case 0:
                    return classes[i];
                case 1:
                    return objects[i];
                default:
                    throw new AssertionError("");
            }
        }
    }

    // Renderer

    private class ClassColumnRenderer implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int i, int j) {
            return new JLabel(((Class<?>) value).getCanonicalName());
        }
    }

    private class ObjectColumnRenderer implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int i, int j) {
            if (Utility.isPrimitive(classes[i])) {
                if (value == null) {
                    return new JLabel("");
                } else {
                    return new JLabel(String.valueOf(value));
                }
            } else {
                return new JButton(String.valueOf(value));
            }
        }
    }
}
