package interpret;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Method;

import static interpret.Utility.invoke;
import static interpret.Utility.showMessage;

public class ObjectViewer extends SubFrame {

    // Data

    private final Object object;

    // API

    public ObjectViewer(Object object, DialogListener dialogListener) {
        super(dialogListener);
        this.object = object;
        methodList.setClass(object.getClass());
        fieldsTable.setObject(object);
        setupLayout();
        setupListener();

        // X button is clicked
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                return_();
            }
        });
    }

    // GUI Component

    private final JSplitPane mainSplitPane = new JSplitPane();

    // Method Panel
    private final JPanel methodPanel = new JPanel();
    private final JTextField filterTextField = new JTextField();
    private final JSplitPane methodSplitPane = new JSplitPane();
    private final JScrollPane methodListScrollPane = new JScrollPane();
    private final JMethodList methodList = new JMethodList();
    private final JScrollPane argumentsTableScrollPane = new JScrollPane();
    private final JArgumentsTable argumentsTable = new JArgumentsTable();
    private final JButton invokeButton = new JButton("Invoke Method");

    // Field Panel
    private final JPanel fieldsPanel = new JPanel();
    private final JScrollPane fieldsTableScrollPane = new JScrollPane();
    private final JFieldsTable fieldsTable = new JFieldsTable();

    // Button Panel
    private final JPanel buttonPanel = new JPanel();
    private final JButton cancelButton = new JButton("Cancel");
    private final JButton returnNullButton = new JButton("Return null");
    private final JButton returnInstanceButton = new JButton("Return instance");

    // Setup component event listener

    private final DocumentListener filterTextChangedListener = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent documentEvent) {
            methodList.setFilterText(filterTextField.getText());
        }

        @Override
        public void removeUpdate(DocumentEvent documentEvent) {
            methodList.setFilterText(filterTextField.getText());
        }

        @Override
        public void changedUpdate(DocumentEvent documentEvent) {

        }
    };

    private final JMethodList.MethodChangedListener methodChangedListener = new JMethodList.MethodChangedListener() {
        @Override
        public void onChange(Method method) {
            if (method == null) {
                argumentsTable.setClass(null);
            } else {
                argumentsTable.setClass(method.getParameterTypes());
            }
        }
    };

    private final ActionListener invokeButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Method method = methodList.getSelectedMethod();
            if (method != null) {
                try {
                    Object returnValue = invoke(object, method, argumentsTable.getValues());
                    if(returnValue == null) {
                        showMessage(ObjectViewer.this, "void");
                    } else {
                        showMessage(ObjectViewer.this, String.valueOf(returnValue));
                    }
                } catch (Throwable e) {
                    showMessage(ObjectViewer.this, e.toString());
                }
            }
        }
    };

    private final ActionListener cancelButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            return_();
        }
    };

    private final ActionListener returnNullButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            return_(null);
        }
    };

    private final ActionListener returnInstanceButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            return_(object);
        }
    };

    private void setupListener() {
        filterTextField.getDocument().addDocumentListener(filterTextChangedListener);
        methodList.addMethodChangedListener(methodChangedListener);
        invokeButton.addActionListener(invokeButtonListener);
        cancelButton.addActionListener(cancelButtonListener);
        returnNullButton.addActionListener(returnNullButtonListener);
        returnInstanceButton.addActionListener(returnInstanceButtonListener);
    }

    // Setup layout

    private void setupLayout() {
        // Setup Frame
        setTitle("Instance of " + object.getClass().getCanonicalName());
        setSize(800, 600);

        // Setup whole layout
        setLayout(new BorderLayout());
        getContentPane().add(BorderLayout.CENTER, mainSplitPane);
        getContentPane().add(BorderLayout.SOUTH, buttonPanel);

        // Setup Center area layout
        mainSplitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        mainSplitPane.setDividerLocation(400);
        mainSplitPane.setTopComponent(methodPanel);
        mainSplitPane.setBottomComponent(fieldsPanel);

        // Setup Method Panel layout
        methodPanel.setBorder(BorderFactory.createTitledBorder("Method"));
        methodPanel.setLayout(new BorderLayout());
        methodPanel.add(BorderLayout.NORTH, filterTextField);
        methodPanel.add(BorderLayout.CENTER, methodSplitPane);
        methodPanel.add(BorderLayout.SOUTH, invokeButton);
        methodSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        methodSplitPane.setDividerLocation(200);
        methodSplitPane.setTopComponent(methodListScrollPane);
        methodListScrollPane.setViewportView(methodList);
        methodSplitPane.setBottomComponent(argumentsTableScrollPane);
        argumentsTableScrollPane.setViewportView(argumentsTable);

        // Setup Field Panel
        fieldsPanel.setBorder(BorderFactory.createTitledBorder("Field"));
        fieldsPanel.setLayout(new BorderLayout());
        fieldsPanel.add(BorderLayout.CENTER, fieldsTableScrollPane);
        fieldsTableScrollPane.setViewportView(fieldsTable);

        // Setup Button Panel
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(cancelButton);
        buttonPanel.add(returnNullButton);
        buttonPanel.add(returnInstanceButton);

        // Locate JFrame to center of screen
        setLocationRelativeTo(null);    // Should be called after setup layout
    }
}
