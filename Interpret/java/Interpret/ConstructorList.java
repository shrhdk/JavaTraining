package interpret;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Set;

public class ConstructorList extends JList {

    // Data

    private Class<?> class_;
    private Constructor[] constructors;
    private final Set<ConstructorChangedListener> listeners = new HashSet<ConstructorChangedListener>();

    // API

    public ConstructorList() {
        setModel(new ConstructorListModel());
        setCellRenderer(new ConstructorListCellRenderer());
        addListSelectionListener(listSelectionListener);
    }

    public Class<?> getClass_() {
        return class_;
    }

    public void setClass(Class<?> class_) {
        this.class_ = class_;
        if (class_ == null) {
            constructors = null;
        } else {
            constructors = class_.getConstructors();
        }
        updateUI();
    }

    public Constructor getSelectedConstructor() {
        int i = getSelectedIndex();
        if (i == -1) {
            return null;
        } else {
            return constructors[i];
        }
    }

    public boolean addConstructorChangedListener(ConstructorChangedListener listener) {
        return listeners.add(listener);
    }

    public boolean removeConstructorChangedListener(ConstructorChangedListener listener) {
        return listeners.remove(listener);
    }

    // Interface

    public interface ConstructorChangedListener {
        void onChange(Constructor constructor);
    }

    // Listener

    private ListSelectionListener listSelectionListener = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent listSelectionEvent) {
            for (ConstructorChangedListener listener : listeners) {
                listener.onChange(constructors[getSelectedIndex()]);
            }
        }
    };

    // ListModel
    private class ConstructorListModel extends AbstractListModel {

        @Override
        public int getSize() {
            if (constructors == null) {
                return 0;
            } else {
                return constructors.length;
            }
        }

        @Override
        public Object getElementAt(int i) {
            return constructors[i];
        }
    }

    // ListCellRenderer
    private class ConstructorListCellRenderer implements ListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean hasFocus) {
            JLabel label = new JLabel(constructors[index].toString());

            if (isSelected) {
                label.setForeground(Color.blue);
            }

            return label;
        }
    }
}