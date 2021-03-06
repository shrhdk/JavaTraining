package interpret;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Set;

public class JConstructorList extends JList {

    // Data

    private Constructor[] constructors = new Constructor[0];
    private final Set<ConstructorChangedListener> listeners = new HashSet<ConstructorChangedListener>();

    // API

    public JConstructorList() {
        setModel(new ConstructorListModel());
        setCellRenderer(new ConstructorListCellRenderer());

        addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                for (ConstructorChangedListener listener : listeners) {
                    int i = getSelectedIndex();
                    listener.onChange(i == -1 ? null : constructors[i]);
                }
            }
        });
    }

    public void setClass(Class<?> class_) {
        if (class_ == null) {
            constructors = new Constructor[0];
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

    // ListModel
    private class ConstructorListModel extends AbstractListModel {

        @Override
        public int getSize() {
            if (constructors.length == 0) {
                clearSelection();
            }

            return constructors.length;
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
                label.setForeground(Color.white);
                label.setBackground(Color.blue);
                label.setOpaque(true);
            }

            return label;
        }
    }
}