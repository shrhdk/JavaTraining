package Interpret;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Method;

public class MethodList extends List {

    // Data

    private final Dialog owner;
    private final Listener listener;
    private final Method[] methods;

    private int lastIndex = -1;

    // API

    public MethodList(Dialog owner, Listener listener, Method... methods) {
        if(owner == null)
            throw new IllegalArgumentException();

        setMultipleMode(false);
        addItemListener(itemListener);

        this.owner = owner;
        this.listener = listener;
        this.methods = methods;

        setUpComponent();
    }

    public Method getSelectedMethod() {
        int i = getSelectedIndex();
        if(i == -1) {
            return null;
        } else {
            return methods[i];
        }
    }

    // Interface

    public interface Listener {
        void onChange(Method selectedMethod);
    }

    // GUI

    private void setUpComponent() {
        if(methods == null || methods.length == 0) {
            removeAll();
            add("No method");
        } else {
            removeAll();
            for(Method method : methods) {
                add(method.toString());
            }
        }
    }

    private ItemListener itemListener = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            int currentIndex = getSelectedIndex();
            if (currentIndex != lastIndex) {
                lastIndex = currentIndex;
                if (listener != null)
                    listener.onChange(methods[currentIndex]);
            }
        }
    };
}
