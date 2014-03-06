package Interpret;

import java.awt.*;
import java.lang.reflect.*;

public class FieldList extends Panel {

    // Data

    private final Dialog owner;
    private final Object object;
    private final Field[] fields;

    // API

    public FieldList(Dialog owner, Object object, Field... fields) {
        if(owner == null)
            throw new IllegalArgumentException();
        else if(object == null)
            throw new IllegalArgumentException();

        this.owner = owner;
        this.object = object;
        this.fields = fields;
    }

    // GUI

    private void setUpComponent() {
        if(fields == null || fields.length == 0) {
            setLayout(new GridLayout(1, 1));
            add(new Label("No field."));
        } else {
            setLayout(new GridLayout(fields.length, 2));
            for(int i = 0; i < fields.length; i++) {
                add(new Label(fields[i].getName()));
            }
        }
    }
}
