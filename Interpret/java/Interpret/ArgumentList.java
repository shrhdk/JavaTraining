package Interpret;

import java.awt.*;

public class ArgumentList extends Panel {

    // Data

    private Dialog owner;
    private Class<?>[] types;
    private ObjectForm[] objectForms;

    // API

    public ArgumentList(Dialog owner, Class<?>... types) {
        this.owner = owner;
        setTypes(types);
    }

    public void setTypes(Class<?>... types) {
        this.types = types;
        removeAll();
        if (types == null || types.length == 0) {
            setLayout(new GridLayout(1, 1));
            add(new Label("No argument"));
        } else {
            objectForms = new ObjectForm[types.length];
            setLayout(new GridLayout(types.length + 1, 1));
            add(new Label("Input arguments"));
            for (int i = 0; i < types.length; i++) {
                objectForms[i] = new ObjectForm(owner, types[i]);
                add(objectForms[i]);
            }
        }
        doLayout();
    }

    public Object[] getValues() {
        if (types == null || types.length == 0) {
            return null;
        } else {
            Object[] returnValues = new Object[types.length];
            for (int i = 0; i < types.length; i++) {
                returnValues[i] = objectForms[i].getValue();
            }
            return returnValues;
        }
    }
}
