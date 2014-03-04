package Interpret;

import javax.swing.*;
import java.awt.event.*;
import java.lang.reflect.*;

import java.awt.*;

public class ClassViewer extends Frame {

    private Object object;
    private Constructor constructor;
    private Constructor[] constructors;
    private final TextField classNameField = new TextField("Type class name here.");
    private final List constructorList = new List();
    private final Panel argumentsFieldsPanel = new Panel();
    private Button constructButton = new Button("Construct");
    private TextField[] argumentsFields;


    public ClassViewer() {
        super();

        setSize(640, 480);
        setLayout(new GridLayout(4, 1));

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e)  {
                ClassViewer.this.setVisible(false);
            }
        });

        classNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                updateConstructorList();
            }
        });

        constructorList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                constructor = constructors[constructorList.getSelectedIndex()];
                updateConstructorArgumentFields();
            }
        });

        constructButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                construct();
                System.out.println(object);
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                return;
            }
        });

        add(classNameField);
        add(constructorList);
        add(argumentsFieldsPanel);
        add(constructButton);

        setVisible(true);
    }

    private void updateConstructorList() {
        constructorList.removeAll();
        String className = classNameField.getText();

        try {
            Class<?> class_ = Class.forName(className);
            constructors = class_.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                constructorList.add(constructor.toString());
            }
        } catch (ClassNotFoundException e) {
            return;
        }
    }

    private void updateConstructorArgumentFields() {
        assert constructor != null;

        Type[] types = constructor.getParameterTypes();

        argumentsFields = new TextField[types.length];

        argumentsFieldsPanel.removeAll();
        argumentsFieldsPanel.setLayout(new GridLayout(types.length, 2));

        for (int i = 0; i < types.length; i++) {
            argumentsFields[i] = new TextField();
            argumentsFieldsPanel.add(new Label(String.valueOf(i + 1)));
            argumentsFieldsPanel.add(argumentsFields[i]);
        }
    }

    private void construct() {
        assert constructor != null;
        object = null;
        if (constructor.getParameterTypes().length == 0) {
            try {
                object = constructor.newInstance();
                new ObjectViewer(object);
            } catch (Throwable e) {
                showMessage("Fail: " + e.getMessage());
            }
        } else {
            Class<?>[] types = constructor.getParameterTypes();
            Object[] arguments = new Object[argumentsFields.length];
            try {
                for (int i = 0; i < arguments.length; i++) {
                    arguments[i] = Primitive.toObject(types[i], argumentsFields[i].getText());
                }
                object = constructor.newInstance(arguments);
                new ObjectViewer(object);
            } catch (Throwable e) {
                showMessage("Fail: " + e.getMessage());
            }
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}

