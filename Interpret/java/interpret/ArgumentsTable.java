package interpret;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ArgumentsTable extends JTable implements Parent {

    // Constants

    private final String[] columnNames = new String[]{"Type", "Value"};

    // Data

    private JFrame parentFrame;
    private Class<?>[] classes = new Class<?>[0];
    private Object[] objects = new Object[0];

    // Temp

    int index;

    // API

    public ArgumentsTable() {
        setModel(new ArgumentsTableModel());
        getColumn(columnNames[0]).setCellRenderer(new ClassColumnRenderer());
        getColumn(columnNames[1]).setCellRenderer(new ObjectColumnRenderer());

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                int column = getSelectedColumn();
                index = getSelectedRow();
                Class<?> class_ = ArgumentsTable.this.classes[index];

                // Launch Class Viewer
                if (column == 1 && !Utility.isPrimitive(class_)) {
                    Container container = getTopLevelAncestor();

                    if (container instanceof JFrame) {
                        parentFrame = (JFrame) container;
                        parentFrame.setEnabled(false);
                    }

                    ClassViewer classViewer = new ClassViewer(class_, ArgumentsTable.this);
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
        objects[index] = value;
    }

    public void setClass(Class<?>... classes) {
        if (classes == null) {
            this.classes = new Class<?>[0];
            objects = new Object[0];
        } else {
            this.classes = classes;
            objects = new Object[classes.length];
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
            return classes.length;
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

    // Cell Renderer

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
