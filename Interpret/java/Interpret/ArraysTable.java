package interpret;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ArraysTable extends JTable implements Parent {

    // Constants

    private static final String[] COLUMN_NAMES = new String[]{"Index", "Value"};

    // Data

    private Object[] array;

    // Temp

    private JFrame parentFrame;
    private int index;

    // API

    public ArraysTable() {
        setModel(new ArraysTableModel());
        getColumn(COLUMN_NAMES[0]).setCellRenderer(new IndexColumnRenderer());
        getColumn(COLUMN_NAMES[1]).setCellRenderer(new ObjectColumnRenderer());

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                int column = getSelectedColumn();
                index = getSelectedRow();
                Class<?> class_ = array.getClass().getComponentType();

                // Launch Class Viewer
                if (column == 1 && !Utility.isPrimitive(class_)) {
                    Container container = getTopLevelAncestor();

                    if (container instanceof JFrame) {
                        parentFrame = (JFrame) container;
                        parentFrame.setEnabled(false);
                    }

                    ClassViewer classViewer = new ClassViewer(class_, ArraysTable.this);
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
        array[index] = value;
    }

    public void setArray(Object[] array) {
        this.array = array;

        updateUI();
    }

    // Table Model

    private class ArraysTableModel extends AbstractTableModel {

        @Override
        public int getRowCount() {
            return array.length;
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
                    return Utility.isPrimitive(array.getClass().getComponentType());
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
                    if (Utility.isPrimitive(array.getClass().getComponentType())) {
                        try {
                            array[i] = Utility.toObject(array.getClass().getComponentType(), (String) value);
                        } catch (Throwable e) {
                            Utility.showMessage(ArraysTable.this, e.toString());
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
                    return i;
                case 1:
                    return array[i];
                default:
                    throw new AssertionError("");
            }
        }
    }

    // Table Cell Renderer

    private class IndexColumnRenderer implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int i, int j) {
            return new JLabel(String.valueOf(value));
        }
    }

    private class ObjectColumnRenderer implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int i, int j) {
            if (Utility.isPrimitive(array.getClass().getComponentType())) {
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
