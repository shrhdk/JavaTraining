package Interpret;

import java.awt.*;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

public class ClassNameField extends TextField {

    // Data

    private Class<?> lastClass;
    private Listener listener;

    // API

    public ClassNameField() {
        addTextListener(textListener);
    }

    public Class<?> getClassObject() {
        try {
            return Class.forName(getText());
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public void setClassNameFieldListener(Listener listener) {
        this.listener = listener;
    }

    // Interface

    public interface Listener {
        void onChange(Class<?> class_);
    }

    // Listener

    private TextListener textListener = new TextListener() {
        @Override
        public void textValueChanged(TextEvent textEvent) {
            Class<?> currentClass = getClassObject();
            if(currentClass == null || currentClass == lastClass) {
                return;
            } else {
                lastClass = currentClass;
                if(listener != null)
                    listener.onChange(currentClass);
            }
        }
    };
}
