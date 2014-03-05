package Interpret;

import com.sun.jdi.Value;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.*;

public class ObjectViewer extends ValueDialog implements ValueDialogListener {

    private final Object object;

    // For Field
    private Field field;
    private final Field[] fields;
    private final List fieldList = new List();
    private final TextField fieldField = new TextField();
    private final Button updateFieldButton = new Button("Update Field");

    // For Method
    private Method method;
    private final Method[] methods;
    private final List methodList = new List();
    private final Panel argumentsFieldsPanel = new Panel();
    private TextField[] argumentsFields;
    private final Button executeMethodButton = new Button("Execute Method");

    public ObjectViewer(Dialog owner, ValueDialogListener listener, final Object object) {
        super(owner, object.getClass().getName(), listener);

        setSize(640, 640);
        setLayout(new GridLayout(4, 2));

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e)  {
                ObjectViewer.this.setVisible(false);
            }
        });

        this.object = object;
        fields = object.getClass().getDeclaredFields();
        methods = object.getClass().getMethods();

        for (Method method : methods) {
            methodList.add(method.toString());
        }
        for (Field field : fields) {
            fieldList.add(field.toString());
        }

        // Add Event Listener for Field
        fieldList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                field = fields[fieldList.getSelectedIndex()];
                updateFieldField();
            }
        });
        updateFieldButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                field = fields[fieldList.getSelectedIndex()];
                try {
                    field.setAccessible(true);
                    field.set(object, Utility.toObject(field.getType(), fieldField.getText()));
                } catch (Throwable e) {
                    showMessage("Fail: " + e.getMessage());
                    updateFieldField();
                }
            }
        });

        // Add Event Listener for Method
        methodList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                method = methods[methodList.getSelectedIndex()];
                updateMethodArgumentFields();
            }
        });
        executeMethodButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                executeMethod();
            }
        });

        // Add Components
        add(new Label("Field"));
        add(new Label("Method"));
        add(fieldList);
        add(methodList);
        add(fieldField);
        add(argumentsFieldsPanel);
        add(updateFieldButton);
        add(executeMethodButton);

        setVisible(true);
    }

    private void updateFieldField() {
        assert field != null;
        try {
            field.setAccessible(true);
            fieldField.setText(field.get(object).toString());
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private void updateMethodArgumentFields() {
        assert method != null;

        Type[] types = method.getParameterTypes();

        argumentsFields = new TextField[types.length];

        argumentsFieldsPanel.removeAll();
        argumentsFieldsPanel.setLayout(new GridLayout(types.length, 2));

        for (int i = 0; i < types.length; i++) {
            argumentsFields[i] = new TextField();
            argumentsFieldsPanel.add(new Label(String.valueOf(i + 1)));
            argumentsFieldsPanel.add(argumentsFields[i]);
        }
    }

    private void executeMethod() {
        assert method != null;
        Object return_ = null;

        Class<?>[] types = method.getParameterTypes();

        if (types.length == 0) {
            try {
                return_ = method.invoke(object);
            } catch (Throwable e) {
                showMessage("Fail: " + e.getMessage());
            }
        } else {
            Object[] arguments = new Object[argumentsFields.length];
            try {
                for (int i = 0; i < arguments.length; i++) {
                    arguments[i] = Utility.toObject(types[i], argumentsFields[i].getText());
                }
                return_ = method.invoke(object, arguments);
                new ObjectViewer(this, this, object);
            } catch (Throwable e) {
                showMessage("Fail: " + e.getMessage());
            }
        }
        showMessage("Success: " + return_.toString());
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void onDialogClose(Object return_) {

    }
}
