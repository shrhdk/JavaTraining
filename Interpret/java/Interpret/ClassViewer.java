package Interpret;

import java.awt.event.*;
import java.lang.reflect.*;

import static Interpret.Utility.*;

import java.awt.*;

public class ClassViewer extends ValueDialog {

    // Data

    private final Class<?> class_;

    // API

    public ClassViewer(Dialog owner, ValueDialogListener listener) {
        this(owner, listener, null);
    }

    public ClassViewer(Dialog owner, ValueDialogListener listener, Class<?> class_) {
        super(owner, "Class Viewer", listener);

        this.class_ = class_;

        setUpComponent();

        setVisible(true);
    }

    // GUI

    private ClassNameField classNameField;
    private ConstructorList constructorList;
    private ArgumentList argumentList;
    private Button constructButton;
    private Button cancelButton;

    private void setUpComponent() {
        setSize(640, 480);

        setLayout(new GridLayout(5, 1));

        classNameField = new ClassNameField();
        classNameField.setClassNameFieldListener(classNameFieldListener);

        constructorList = new ConstructorList(this, constructListListener, null);

        argumentList = new ArgumentList(this, null);

        constructButton = new Button("Construct Object");
        constructButton.addActionListener(constructButtonListener);

        cancelButton = new Button("Cancel");
        cancelButton.addActionListener(cancelButtonListener);

        add(classNameField);
        add(constructorList);
        add(argumentList);
        add(constructButton);
        add(cancelButton);

        if (class_ != null) {
            classNameField.setEnabled(false);
            classNameField.setText(class_.getCanonicalName());
            classNameFieldListener.onChange(class_);
        }
    }

    // Listener

    private ClassNameField.Listener classNameFieldListener = new ClassNameField.Listener() {
        @Override
        public void onChange(Class<?> class_) {
            constructorList.setConstructors(class_.getConstructors());
        }
    };

    private ConstructorList.Listener constructListListener = new ConstructorList.Listener() {
        @Override
        public void onChange(Constructor constructor) {
            if (constructor != null)
                argumentList.setTypes(constructor.getParameterTypes());
        }
    };

    private ActionListener constructButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Constructor constructor = constructorList.getSelectedConstructor();
            if (constructor != null) {
                try {
                    Object object = construct(constructor, argumentList.getValues());
                    new ObjectViewer(ClassViewer.this, valueDialogListener, object);
                } catch (Throwable e) {
                    showMessage(ClassViewer.this, e.getMessage());
                }
            }
        }
    };

    private ActionListener cancelButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            return_(null);
        }
    };

    private ValueDialogListener valueDialogListener = new ValueDialogListener() {
        @Override
        public void onDialogClose(Object returnValue) {
            return_(returnValue);
        }
    };
}
