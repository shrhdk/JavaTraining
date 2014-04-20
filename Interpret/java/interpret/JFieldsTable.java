package interpret;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.lang.reflect.Field;

public class JFieldsTable extends JTable {

    // Constants

    private static final String[] COLUMN_NAMES = new String[]{"Type", "Name", "Value"};

    // Data

    private Object object;
    private Field[] fields = new Field[0];

    // API

    public JFieldsTable() {
        setModel(new ArgumentsTableModel());
        getColumn(COLUMN_NAMES[0]).setCellRenderer(new TypeColumnRenderer());
        getColumn(COLUMN_NAMES[1]).setCellRenderer(new NameColumnRenderer());
        getColumn(COLUMN_NAMES[2]).setCellRenderer(new ValueCell());
        getColumn(COLUMN_NAMES[2]).setCellEditor(new ValueCell());
    }

    public void setObject(Object object) {
        this.object = object;
        if (object == null) {
            fields = new Field[0];
        } else {
            fields = object.getClass().getDeclaredFields();
        }

        updateUI();
    }

    // Table Model

    private class ArgumentsTableModel extends AbstractTableModel {

        @Override
        public int getRowCount() {
            return fields.length;
        }

        @Override
        public int getColumnCount() {
            return COLUMN_NAMES.length;
        }

        @Override
        public String getColumnName(int i) {
            return COLUMN_NAMES[i];
        }

        @Override
        public boolean isCellEditable(int i, int j) {
            switch (j) {
                case 0:
                    return false;
                case 1:
                    return false;
                case 2:
                    return Utility.isSettableField(fields[i]);
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
                    break;
                case 2:
                    try {
                        Utility.setField(object, fields[i], ((TypeValuePair) value).getValue());
                    } catch (Throwable e) {
                        e.printStackTrace();
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
                    return fields[i].getType();
                case 1:
                    return fields[i].getName();
                case 2:
                    try {
                        return new TypeValuePair(fields[i].getType(), Utility.getField(object, fields[i]));
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                default:
                    throw new AssertionError("");
            }
        }
    }

    // Table Cell Renderer

    private class TypeColumnRenderer implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int i, int j) {
            return new JLabel(fields[i].getType().getCanonicalName());
        }
    }

    private class NameColumnRenderer implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int i, int j) {
            return new JLabel(fields[i].getName());
        }
    }
}
