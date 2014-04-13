package interpret;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.util.HashSet;
import java.util.Set;

public class ClassNameField extends JTextField {

    // Data

    private Class<?> lastClass;
    private final Set<ClassChangedListener> listeners = new HashSet<ClassChangedListener>();

    // API

    public ClassNameField() {
        getDocument().addDocumentListener(documentListener);
    }

    public Class<?> getClassObject() {
        try {
            return Class.forName(getText());
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public boolean addClassChangedListener(ClassChangedListener listener) {
        return listeners.add(listener);
    }

    public boolean removeClassChangedListener(ClassChangedListener listener) {
        return listeners.remove(listener);
    }

    // Interface

    public interface ClassChangedListener {
        void onChange(Class<?> class_);
    }

    // Listener

    private synchronized void onUpdate() {
        Class<?> currentClass = getClassObject();
        if (currentClass == lastClass) {
            return;
        }

        lastClass = currentClass;

        for (ClassChangedListener listener : listeners) {
            listener.onChange(currentClass);
        }
    }

    private DocumentListener documentListener = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent documentEvent) {
            onUpdate();
        }

        @Override
        public void removeUpdate(DocumentEvent documentEvent) {
            onUpdate();
        }

        @Override
        public void changedUpdate(DocumentEvent documentEvent) {
        }
    };
}
