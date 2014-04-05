package Interpret;

import com.sun.deploy.util.DialogListener;
import com.sun.org.apache.xpath.internal.Arg;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ArgumentList extends JScrollPane {

    // Data

    private Dialog owner;
    private Class<?>[] types;
    private Object[] objects;

    // Temp

    int i, j;

    // API

    public ArgumentList(Dialog owner, Class<?>... types) {
        this.owner = owner;
        setTypes(types);

        this.setViewportView(table);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                i = table.getSelectedRow();
                j = table.getSelectedColumn();
                Class<?> type = ArgumentList.this.types[i];

                if(j == 0) {
                    return;
                } else if(j == 1) {
                    if(Utility.isPrimitive(type)) {
                        new PrimitiveClassEditor(ArgumentList.this.owner, type.getCanonicalName(), valueDialogListener, type);
                    } else {
                        new ClassViewer(ArgumentList.this.owner, valueDialogListener, type);
                    }
                }
            }
        });
    }

    public void setTypes(Class<?>... types) {

        if (types == null) {
            this.types = new Class<?>[0];
            this.objects = new Object[0];
        } else {
            this.types = types;
            this.objects = new Object[types.length];
        }

        table.repaint();
    }

    public Object[] getValues() {
        if (types == null || types.length == 0) {
            return null;
        } else {
            Object[] returnValues = new Object[types.length];
            for (int i = 0; i < types.length; i++) {
                returnValues[i] = objects[i];
            }
            return returnValues;
        }
    }

    // GUI

    TableModel model = new AbstractTableModel() {

        private final String[] columnNames = new String[]{"Type", "Value"};

        @Override
        public int getRowCount() {
            return types.length;
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public String getColumnName(int j) {
            return columnNames[j];
        }

        @Override
        public Object getValueAt(int i, int j) {
            switch (j) {
                case 0:
                    return types[i].getCanonicalName();
                case 1:
                    return String.valueOf(objects[i]);
                default:
                    throw new IndexOutOfBoundsException();
            }
        }
    };

    JTable table = new JTable(model);

    // Dialog is closed

    private ValueDialogListener valueDialogListener = new ValueDialogListener() {
        @Override
        public void onDialogClose(Object return_) {
            if(Utility.isPrimitive(types[i]) && return_ == null) {
                return;
            } else {
                objects[i] = return_;
                table.setModel(model);
            }
        }
    };
}
