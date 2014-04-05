package Interpret;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;

public class ArrayList extends JList {

    // Data

    private final Dialog owner;
    private final Class<?> class_;
    private final int length;
    private final Object objects;

    // Temp

    int lastSelectedIndex;

    // API

    public ArrayList(Dialog owner, Class<?> class_, int length) {
        if (owner == null)
            throw new IllegalArgumentException();

        setModel(arrayListModel);

        this.owner = owner;
        this.class_ = class_;
        this.length = length;
        objects = Array.newInstance(class_, length);

        addMouseListener(mouseListener);
    }

    public Object getArrayObject() {
        return objects;
    }

    // List model

    private final ListModel arrayListModel = new AbstractListModel() {
        @Override
        public int getSize() {
            return length;
        }

        @Override
        public Object getElementAt(int i) {
            return String.valueOf(Array.get(objects, i));
        }
    };

    // Listener

    private final MouseListener mouseListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {

            lastSelectedIndex = getSelectedIndex();

            if (Utility.isPrimitive(class_)) {
                new PrimitiveClassEditor(owner, class_.getCanonicalName(), valueDialogListener, class_);
            } else {
                new ClassViewer(owner, valueDialogListener, class_);
            }
        }
    };

    private final ValueDialogListener valueDialogListener = new ValueDialogListener() {
        @Override
        public void onDialogClose(Object return_) {
            if (Utility.isPrimitive(class_) && return_ == null) {
                return;
            } else {
                Array.set(objects, lastSelectedIndex, return_);
                setModel(arrayListModel);
            }
        }
    };
}
