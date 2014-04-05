package Interpret;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.*;

public class FieldList extends JScrollPane {

    // Data

    private final Dialog owner;
    private final Object object;
    private final Field[] fields;

    // Temp

    int i, j;

    // API

    public FieldList(Dialog owner, Object object) {
        if (owner == null)
            throw new IllegalArgumentException();
        else if (object == null)
            throw new IllegalArgumentException();

        this.owner = owner;
        this.object = object;
        this.fields = object.getClass().getDeclaredFields();

        setViewportView(table);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                i = table.getSelectedRow();
                j = table.getSelectedColumn();
                Class<?> type = FieldList.this.fields[i].getType();

                if (j == 0) {
                    return;
                } else if (j == 1) {
                    if (Utility.isSettableField(fields[i])) {
                        if (Utility.isPrimitive(type)) {
                            new PrimitiveClassEditor(FieldList.this.owner, type.getCanonicalName(), valueDialogListener, type);
                        } else {
                            new ClassViewer(FieldList.this.owner, valueDialogListener, type);
                        }
                    }
                }
            }
        });
    }

    // GUI

    private void setUpComponent() {
        if (fields == null || fields.length == 0) {
            setLayout(new GridLayout(1, 1));
            add(new Label("No field."));
        } else {
            setLayout(new GridLayout(fields.length, 2));
            for (int i = 0; i < fields.length; i++) {
                add(new Label(fields[i].getName()));
                add(new FieldForm(owner, object, fields[i]));
            }
        }
    }

    // GUI

    TableModel model = new AbstractTableModel() {

        private final String[] columnNames = new String[]{"Name", "Value"};

        @Override
        public int getRowCount() {
            return fields.length;
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
                    return fields[i].getName();
                case 1:
                    try {
                        return String.valueOf(Utility.getField(object, fields[i]));
                    } catch (Throwable throwable) {

                    }
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
            if (Utility.isPrimitive(fields[i].getType()) && return_ == null) {
                return;
            } else {
                try {
                    Utility.setField(object, fields[i], return_);
                } catch (Throwable throwable) {

                }
                table.setModel(model);
            }
        }
    };
}
