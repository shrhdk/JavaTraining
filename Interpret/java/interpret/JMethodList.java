package interpret;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JMethodList extends JList {
    // Data

    private Class<?> class_;
    private Method[] methods = new Method[0];
    private Method[] filteredMethods = new Method[0];
    private final Set<MethodChangedListener> listeners = new HashSet<MethodChangedListener>();

    // API

    public JMethodList() {
        setModel(new MethodListModel());
        setCellRenderer(new MethodListCellRenderer());

        addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                for (MethodChangedListener listener : listeners) {
                    int i = getSelectedIndex();
                    listener.onChange(i == -1 ? null : filteredMethods[i]);
                }
            }
        });
    }

    public Class<?> getClass_() {
        return class_;
    }

    public void setClass(Class<?> class_) {
        if (class_ == null) {
            methods = new Method[0];
        } else {
            methods = class_.getMethods();
        }

        filteredMethods = methods;

        updateUI();
    }

    public Method getSelectedMethod() {
        int i = getSelectedIndex();
        if (i == -1) {
            return null;
        } else {
            return filteredMethods[i];
        }
    }

    public void setFilterText(String filterText) {
        // Filter
        if (filterText.equals("")) {
            filteredMethods = methods;
        } else {
            List<Method> filteredMethodList = new ArrayList<Method>();
            for (Method method : methods) {
                if (method.getName().indexOf(filterText) != -1) {
                    filteredMethodList.add(method);
                }
            }
            filteredMethods = filteredMethodList.toArray(new Method[0]);
        }

        clearSelection();
        updateUI();
    }

    public boolean addMethodChangedListener(MethodChangedListener listener) {
        return listeners.add(listener);
    }

    public boolean removeMethodChangedListener(MethodChangedListener listener) {
        return listeners.remove(listener);
    }

    // Interface

    public interface MethodChangedListener {
        void onChange(Method method);
    }

    // Listener

    // ListModel
    private class MethodListModel extends AbstractListModel {

        @Override
        public int getSize() {
            if (filteredMethods.length == 0) {
                clearSelection();
            }

            return filteredMethods.length;
        }

        @Override
        public Object getElementAt(int i) {
            return filteredMethods[i];
        }
    }

    // ListCellRenderer
    private class MethodListCellRenderer implements ListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean hasFocus) {
            JLabel label = new JLabel(filteredMethods[index].toString());

            if (isSelected) {
                label.setForeground(Color.white);
                label.setBackground(Color.blue);
                label.setOpaque(true);
            }

            return label;
        }
    }
}
