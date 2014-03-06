package Interpret;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Interpret.Utility.isPrimitive;
import static Interpret.Utility.toObject;

public class ObjectForm extends Panel {

    private Dialog owner;
    private final Class<?> class_;
    private Object generatedObject;

    // API

    public ObjectForm(Dialog owner, Class<?> class_) {
        if(owner == null)
            throw new IllegalArgumentException();
        else if(class_ == null)
            throw new IllegalArgumentException();

        this.class_ = class_;

        setUpComponent();
    }

    public Object getValue() {
        if(isPrimitive(class_)) {
            return toObject(class_, textField.getText());
        } else {
            return generatedObject;
        }
    }

    // GUI

    private final TextField textField = new TextField();
    private final Button generateButton = new Button();

    private void setUpComponent() {
        setLayout(new GridLayout(1, 1));

        generateButton.addActionListener(new generateButtonListener());
        generateButton.setLabel(class_.getSimpleName());

        if(isPrimitive(class_)) {
            add(textField);
        } else {
            add(generateButton);
        }

        doLayout();
    }

    private class generateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            new ClassViewer(owner, new dialogListener(), class_);
        }
    }

    private class dialogListener implements ValueDialogListener {
        @Override
        public void onDialogClose(Object returnValue) {
            generatedObject = returnValue;
            generateButton.setLabel(generatedObject.toString());
        }
    }
}
