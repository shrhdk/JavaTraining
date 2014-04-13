package interpret;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;

public class FieldsTable extends JTable implements Parent {

    // Constants

    private static final String[] COLUMN_NAMES = new String[]{"Type", "Name", "Value"};

    // Data

    private Object object;
    private Field[] fields = new Field[0];

    // Temp

    private JFrame parentFrame;
    private int index;

    // API

    public FieldsTable() {
        setModel(new ArgumentsTableModel());
        getColumn(COLUMN_NAMES[0]).setCellRenderer(new TypeColumnRenderer());
        getColumn(COLUMN_NAMES[1]).setCellRenderer(new NameColumnRenderer());
        getColumn(COLUMN_NAMES[2]).setCellRenderer(new ObjectColumnRenderer());

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                int column = getSelectedColumn();
                index = getSelectedRow();
                Class<?> class_ = FieldsTable.this.fields[index].getType();

                // Launch Class Viewer
                if (column == 2 && !Utility.isPrimitive(class_)) {
                    Container container = getTopLevelAncestor();

                    if (container instanceof JFrame) {
                        parentFrame = (JFrame) container;
                        parentFrame.setEnabled(false);
                    }

                    ClassViewer classViewer = new ClassViewer(class_, FieldsTable.this);
                    classViewer.setVisible(true);
                }
            }
        });
    }

    @Override
    public void onSubFrameClose(Object value) {
        if (parentFrame != null) {
            parentFrame.setEnabled(true);
        }
        try {
            Utility.setField(object, fields[index], value);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
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
                    return Utility.isPrimitive(fields[i].getType()) && Utility.isSettableField(fields[i]);
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
                    if (Utility.isPrimitive(fields[i].getType())) {
                        try {
                            Utility.setField(object, fields[i], Utility.toObject(fields[i].getType(), (String) value));
                        } catch (Throwable e) {
                            Utility.showMessage(FieldsTable.this, e.toString());
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
                    return fields[i].getType();
                case 1:
                    return fields[i].getName();
                case 2:
                    try {
                        return Utility.getField(object, fields[i]);
                    } catch (Throwable e) {
                        return e;
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

    private class ObjectColumnRenderer implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int i, int j) {
            if (Utility.isPrimitive(fields[i].getType())) {
                JLabel label;
                if (value == null) {
                    label = new JLabel("");
                } else {
                    label = new JLabel(String.valueOf(value));
                }
                if (!Utility.isSettableField(fields[i])) {
                    label.setForeground(Color.gray);
                }
                return label;
            } else {
                return new JButton(String.valueOf(value));
            }
        }
    }
}
