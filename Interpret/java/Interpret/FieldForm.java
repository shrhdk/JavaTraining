package Interpret;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;

import static Interpret.Utility.*;

public class FieldForm extends Panel {

    // Data

    private final Dialog owner;
    private final Object object;
    private final Field field;


    // API

    public FieldForm(Dialog owner, Object object, Field field) {
        if(owner == null)
            throw new IllegalArgumentException();
        else if(object == null)
            throw new IllegalArgumentException();
        else if(field == null)
            throw new IllegalArgumentException();

        this.owner = owner;
        this.object = object;
        this.field = field;

        setUpComponent();
    }

    private final TextField textField = new TextField();
    private final Button generateButton = new Button();

    private void setUpComponent() {
        setLayout(new GridLayout(1, 1));

        if(isPrimitive(field.getType())) {
            textField.addKeyListener(keyListener);
            try {
                textField.setText(String.valueOf(getField(object, field)));
            } catch (Throwable throwable) {
                throw new AssertionError();
            }
            add(textField);
        } else {
            generateButton.addActionListener(generateButtonListener);
            try {
                generateButton.setLabel(String.valueOf(getField(object, field)));
            } catch (Throwable throwable) {
                throw new AssertionError();
            }
            add(generateButton);
        }

        if(!isSettableField(field)) {
            textField.setEnabled(false);
            generateButton.setEnabled(false);
        }

        doLayout();
    }

    // Listener

    private ValueDialogListener dialogListener = new ValueDialogListener() {
        @Override
        public void onDialogClose(Object returnValue) {
            if(isSettableField(field)) {
                try {
                    setField(object, field, returnValue);
                    generateButton.setLabel(String.valueOf(returnValue));
                } catch (Throwable throwable) {
                    throw new AssertionError();
                }
            }
        }
    };

    private KeyListener keyListener = new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent keyEvent) {
            if(keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                Object generatedObject = toObject(field.getType(), textField.getText());
                if(isSettableField(field)) {
                    try {
                        setField(object, field, generatedObject);
                    } catch (Throwable e) {
                        throw new AssertionError();
                    }
                }
            }
        }
    };

    private ActionListener generateButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            new ClassViewer(owner, dialogListener, field.getType());
        }
    };
}
