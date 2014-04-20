package interpret;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;

import static interpret.Utility.construct;
import static interpret.Utility.showMessage;

public class ClassViewer extends SubFrame implements DialogListener {

    private final boolean isRootFrame;

    public ClassViewer() {
        this(null, null);
    }

    public ClassViewer(Class<?> type, DialogListener dialogListener) {
        super(dialogListener);

        if (type != null && type.isEnum()) {
            throw new IllegalArgumentException("type must not be enum type");
        }

        isRootFrame = (dialogListener == null);
        cancelButton.setVisible(!isRootFrame);
        returnNullButton.setVisible(!isRootFrame);

        setupLayout();
        setupListener();

        if (type != null) {
            if (type.isArray()) {
                type = type.getComponentType();
                constructButton.setVisible(false);
            } else {
                arrayPanel.setVisible(false);
                constructArrayButton.setVisible(false);
            }

            classNameField.setText(type.getCanonicalName());
            classChangedListener.onChange(type);
        }

        // X button is clicked
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                if (isRootFrame) {
                    System.exit(0);
                } else {
                    return_();
                }
            }
        });
    }

    @Override
    public void onSubFrameReturn(Object value) {
        if (isRootFrame) {
            setVisible(true);
        } else {
            return_(value);
        }
    }

    @Override
    public void onSubFrameCancel() {
        if (isRootFrame) {
            setVisible(true);
        } else {
            return_();
        }
    }

    // GUI Component

    // Class name and array length
    private final JPanel topGroup = new JPanel();
    private final JPanel classNamePanel = new JPanel();
    private final JClassNameField classNameField = new JClassNameField();
    private final JPanel arrayPanel = new JPanel();
    private final JSpinner arrayLengthSpinner = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));

    // Constructor List and arguments
    private final JSplitPane mainSplit = new JSplitPane();
    private final JScrollPane constructorListScrollPane = new JScrollPane();
    private final JPanel constructorListPanel = new JPanel();
    private final JConstructorList constructorList = new JConstructorList();
    private final JPanel argumentsTablePanel = new JPanel();
    private final JScrollPane argumentsTableScrollPane = new JScrollPane();
    private final JArgumentsTable argumentsTable = new JArgumentsTable();

    // Buttons
    private final JPanel buttonPanel = new JPanel();
    private final JButton cancelButton = new JButton("Cancel");
    private final JButton returnNullButton = new JButton("Return null");
    private final JButton constructArrayButton = new JButton("Construct Array");
    private final JButton constructButton = new JButton("Construct Object");

    // Setup component event listener

    private final JClassNameField.ClassChangedListener classChangedListener = new JClassNameField.ClassChangedListener() {
        @Override
        public void onChange(Class<?> class_) {
            constructorList.setClass(class_);
            constructArrayButton.setEnabled(class_ != null);
            constructButton.setEnabled(false);
        }
    };

    private final JConstructorList.ConstructorChangedListener constructorChangedListener = new JConstructorList.ConstructorChangedListener() {
        @Override
        public void onChange(Constructor constructor) {
            argumentsTable.setClass(constructor == null ? null : constructor.getParameterTypes());
            constructButton.setEnabled(constructor != null);
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

    private final ActionListener constructArrayButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                Class<?> class_ = classNameField.getClassObject();
                int length = Integer.parseInt(arrayLengthSpinner.getValue().toString());
                Object[] array = (Object[]) Array.newInstance(class_, length);
                ArrayViewer arrayViewer = new ArrayViewer(array, ClassViewer.this);
                setVisible(false);
                arrayViewer.setVisible(true);
            } catch (NumberFormatException e) {
                Utility.showMessage(ClassViewer.this, "Invalid array length.");
            }
        }
    };

    private final ActionListener constructButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                Constructor constructor = constructorList.getSelectedConstructor();
                Object[] arguments = argumentsTable.getValues();
                Object object = construct(constructor, arguments);
                ObjectViewer objectViewer = new ObjectViewer(object, ClassViewer.this);
                setVisible(false);
                objectViewer.setVisible(true);
            } catch (Throwable e) {
                showMessage(ClassViewer.this, e.toString());
            }
        }
    };

    private void setupListener() {
        classNameField.addClassChangedListener(classChangedListener);
        constructorList.addConstructorChangedListener(constructorChangedListener);
        cancelButton.addActionListener(cancelButtonListener);
        returnNullButton.addActionListener(returnNullButtonListener);
        constructArrayButton.addActionListener(constructArrayButtonListener);
        constructButton.addActionListener(constructButtonListener);
    }

    // Setup layout

    private void setupLayout() {
        // Setup Frame
        setTitle("ClassViewer");
        setSize(800, 600);

        // Setup whole layout
        setLayout(new BorderLayout());
        getContentPane().add(BorderLayout.NORTH, topGroup);
        getContentPane().add(BorderLayout.CENTER, mainSplit);
        getContentPane().add(BorderLayout.SOUTH, buttonPanel);

        // Setup North area layout
        topGroup.setLayout(new BorderLayout());
        topGroup.add(BorderLayout.CENTER, classNamePanel);
        topGroup.add(BorderLayout.EAST, arrayPanel);

        // Setup Class Name area layout
        classNamePanel.setBorder(BorderFactory.createTitledBorder("Class Name"));
        classNamePanel.setLayout(new GridLayout(1, 1));
        classNamePanel.add(classNameField);

        // Setup Array Length area layout
        arrayPanel.setBorder(BorderFactory.createTitledBorder("Array Length"));
        arrayPanel.setLayout(new GridLayout(1, 1));
        arrayPanel.add(arrayLengthSpinner);

        // Setup Center area layout
        mainSplit.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        mainSplit.setDividerLocation(400);
        mainSplit.setTopComponent(constructorListPanel);
        mainSplit.setBottomComponent(argumentsTablePanel);

        // Setup Constructor List layout
        constructorListPanel.setBorder(BorderFactory.createTitledBorder("Constructors"));
        constructorListPanel.setLayout(new BorderLayout());
        constructorListPanel.add(BorderLayout.CENTER, constructorListScrollPane);
        constructorListScrollPane.setViewportView(constructorList);

        // Setup Argument Table Layout
        argumentsTablePanel.setBorder(BorderFactory.createTitledBorder("Arguments"));
        argumentsTablePanel.setLayout(new BorderLayout());
        argumentsTablePanel.add(BorderLayout.CENTER, argumentsTableScrollPane);
        argumentsTableScrollPane.setViewportView(argumentsTable);

        // Setup Button area layout
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(cancelButton);
        buttonPanel.add(returnNullButton);
        buttonPanel.add(constructArrayButton);
        buttonPanel.add(constructButton);

        // Locate JFrame to center of screen
        setLocationRelativeTo(null);    // Should be called after setup layout
    }
}
