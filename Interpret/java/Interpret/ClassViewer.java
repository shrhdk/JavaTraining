package interpret;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;

import static interpret.Utility.*;

public class ClassViewer extends SubFrame implements Parent {

    private final boolean isRootFrame;

    public ClassViewer() {
        this(null, null);
    }

    public ClassViewer(Class<?> class_, Parent parent) {
        super(parent);

        isRootFrame = (parent == null);
        returnNullButton.setVisible(!isRootFrame);

        setupLayout();
        setupListener();

        if (class_ != null) {
            classNameField.setText(class_.getCanonicalName());
            classChangedListener.onChange(class_);
        }

        // X button is clicked
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                if (isRootFrame) {
                    System.exit(0);
                } else {
                    return_(null);
                }
            }
        });
    }

    @Override
    public void onSubFrameClose(Object value) {
        if (isRootFrame) {
            setVisible(true);
        } else {
            return_(value);
        }
    }

    // GUI Component

    // Class name and array length
    private final JPanel topGroup = new JPanel();
    private final JPanel classNameGroup = new JPanel();
    private final ClassNameField classNameField = new ClassNameField();
    private final JPanel arrayGroup = new JPanel();
    private final JSpinner arrayLengthSpinner = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));

    // Constructor List and arguments
    private final JSplitPane mainSplit = new JSplitPane();
    private final JScrollPane constructorListScrollPane = new JScrollPane();
    private final JPanel constructorListPanel = new JPanel();
    private final ConstructorList constructorList = new ConstructorList();
    private final JPanel argumentsTablePanel = new JPanel();
    private final JScrollPane argumentsTableScrollPane = new JScrollPane();
    private final ArgumentsTable argumentsTable = new ArgumentsTable();

    // Buttons
    private final JPanel buttonGroup = new JPanel();
    private final JButton returnNullButton = new JButton("Return null");
    private final JButton constructArrayButton = new JButton("Construct Array");
    private final JButton constructButton = new JButton("Construct Object");

    // Setup component event listener

    private final ClassNameField.ClassChangedListener classChangedListener = new ClassNameField.ClassChangedListener() {
        @Override
        public void onChange(Class<?> class_) {
            constructorList.setClass(class_);
            constructArrayButton.setEnabled(class_ != null);
            constructButton.setEnabled(false);
        }
    };

    private final ConstructorList.ConstructorChangedListener constructorChangedListener = new ConstructorList.ConstructorChangedListener() {
        @Override
        public void onChange(Constructor constructor) {
            argumentsTable.setClass(constructor == null ? null : constructor.getParameterTypes());
            constructButton.setEnabled(constructor != null);
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
                Object[] array = (Object[])Array.newInstance(class_, length);
                ArrayViewer arrayViewer = new ArrayViewer(ClassViewer.this, array);
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
                Object object = construct(constructorList.getSelectedConstructor(), argumentsTable.getValues());
                ObjectViewer objectViewer = new ObjectViewer(ClassViewer.this, object);
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
        getContentPane().add(BorderLayout.SOUTH, buttonGroup);

        // Setup North area layout
        topGroup.setLayout(new BorderLayout());
        topGroup.add(BorderLayout.CENTER, classNameGroup);
        topGroup.add(BorderLayout.EAST, arrayGroup);

        // Setup Class Name area layout
        classNameGroup.setBorder(BorderFactory.createTitledBorder("Class Name"));
        classNameGroup.setLayout(new GridLayout(1, 1));
        classNameGroup.add(classNameField);

        // Setup Array Length area layout
        arrayGroup.setBorder(BorderFactory.createTitledBorder("Array Length"));
        arrayGroup.setLayout(new GridLayout(1, 1));
        arrayGroup.add(arrayLengthSpinner);

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
        buttonGroup.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonGroup.add(returnNullButton);
        buttonGroup.add(constructArrayButton);
        buttonGroup.add(constructButton);

        // Locate JFrame to center of screen
        setLocationRelativeTo(null);    // Should be called after setup layout
    }
}
