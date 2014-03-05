package Interpret;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Constructor;

public class ConstructorList extends List {

    // Data

    private final Dialog owner;
    private final Listener listener;
    private Constructor[] constructors;

    private int lastIndex = -1;

    // API

    public ConstructorList(Dialog owner, Listener listener, Constructor... constructors) {
        if (owner == null)
            throw new IllegalArgumentException();

        setMultipleMode(false);
        addItemListener(itemListener);

        this.owner = owner;
        this.listener = listener;

        setConstructors(constructors);
    }

    public void setConstructors(Constructor... constructors) {
        this.constructors = constructors;

        if (constructors == null || constructors.length == 0) {
            removeAll();
            add("No Constructor");
        } else {
            removeAll();
            for (Constructor constructor : constructors) {
                add(constructor.toString());
            }
        }
    }

    public Constructor getSelectedConstructor() {
        int i = getSelectedIndex();
        if (i == -1) {
            return null;
        } else {
            return constructors[i];
        }
    }

    // Interface

    public interface Listener {
        void onChange(Constructor constructor);
    }

    // Listener

    private ItemListener itemListener = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            int currentIndex = getSelectedIndex();
            if (currentIndex != lastIndex) {
                lastIndex = currentIndex;
                if (listener != null)
                    listener.onChange(constructors[currentIndex]);
            }
        }
    };
}