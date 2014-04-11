package Interpret;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;

import static Interpret.Utility.*;

public class ObjectViewer extends ValueDialog {

    // Data
    private final Object object;
    private final Method[] methods;

    // GUI Component
    private final Label fieldLabel = new Label("Field");
    private final Label methodLabel = new Label("Method");
    private final FieldList fieldList;
    private final MethodList methodList;
    private final ArgumentList argumentList;
    private final Button cancelButton = new Button("Cancel");
    private final Button okButton = new Button("OK");
    private final Button invokeMethodButton = new Button("Execute Method");

    public ObjectViewer(Dialog owner, ValueDialogListener listener, final Object object) {
        super(owner, object.getClass().getName() + " " + object.toString(), listener);

        // Check arguments
        if(object == null)
            throw new IllegalArgumentException();

        // Stash arguments
        this.object = object;
        methods = object.getClass().getMethods();

        // Setup GUI Component
        fieldList = new FieldList(this, object);
        methodList = new MethodList(this, methodListListener, methods);
        argumentList = new ArgumentList(this, null);

        // Add listener to button
        cancelButton.addActionListener(cancelButtonListener);
        okButton.addActionListener(okButtonListener);
        invokeMethodButton.addActionListener(invokeMethodButtonListener);

        setSize(1000, 640);
        setLayout(new GridLayout(4, 2));

        // Add Components
        add(methodLabel);
        add(fieldLabel);
        add(methodList);
        add(fieldList);
        add(argumentList);
        add(cancelButton);
        add(invokeMethodButton);
        add(okButton);

        setVisible(true);
    }

    // Listener

    private MethodList.Listener methodListListener = new MethodList.Listener() {
        @Override
        public void onChange(Method method) {
            if (method != null)
                argumentList.setTypes(method.getParameterTypes());
        }
    };

    private ActionListener cancelButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            return_(null);
        }
    };

    private ActionListener okButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            return_(object);
        }
    };

    private ActionListener invokeMethodButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Method method = methodList.getSelectedMethod();
            if (method != null) {
                try {
                    Object returnValue = invoke(object, method, argumentList.getValues());
                    showMessage(ObjectViewer.this, returnValue.toString());
                } catch (Throwable e) {
                    showMessage(ObjectViewer.this, e.toString());
                }
            }
        }
    };
}
