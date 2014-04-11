package interpret;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Constructor;

public class ClassViewer extends JFrame {

    // GUI Component

    // Class name and array length
    private final JPanel topGroup = new JPanel();
    private final JPanel classNameGroup = new JPanel();
    private final ClassNameField classNameField = new ClassNameField();
    private final JPanel arrayGroup = new JPanel();
    private final JTextField arrayLengthField = new JTextField();

    // Constructor List and arguments
    private final JSplitPane mainSplit = new JSplitPane();
    private final JPanel constructorListPanel = new JPanel();
    private final ConstructorList constructorList = new ConstructorList();
    private final JList argumentsTablePanel = new JList();
    private final JScrollPane argumentsTableScrollPane = new JScrollPane();
    private final ArgumentsTable argumentsTable = new ArgumentsTable();

    // Buttons
    private final JPanel buttonGroup = new JPanel();
    private final JButton cancelButton = new JButton("Cancel");
    private final JButton constructArrayButton = new JButton("Construct Array");
    private final JButton constructButton = new JButton("Construct Object");

    public ClassViewer() {
        setUpLayout();
        setUpListener();
    }

    private void setUpListener() {
        classNameField.addClassChangedListener(new ClassNameField.ClassChangedListener() {
            @Override
            public void onChange(Class<?> class_) {
                constructorList.setClass(class_);
            }
        });

        constructorList.addConstructorChangedListener(new ConstructorList.ConstructorChangedListener() {
            @Override
            public void onChange(Constructor constructor) {
                argumentsTable.setClass(constructor.getParameterTypes());
            }
        });
    }

    private void setUpLayout() {
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
        arrayGroup.add(arrayLengthField);
        arrayLengthField.setColumns(10);

        // Setup Center area layout
        mainSplit.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        mainSplit.setDividerLocation(400);
        mainSplit.setTopComponent(constructorListPanel);
        mainSplit.setBottomComponent(argumentsTablePanel);

        // Setup Constructor List layout
        constructorListPanel.setBorder(BorderFactory.createTitledBorder("Constructors"));
        constructorListPanel.setLayout(new BorderLayout());
        constructorListPanel.add(BorderLayout.CENTER, constructorList);

        // Setup Argument Table Layout
        argumentsTablePanel.setBorder(BorderFactory.createTitledBorder("Arguments"));
        argumentsTablePanel.setLayout(new BorderLayout());
        argumentsTablePanel.add(BorderLayout.CENTER, argumentsTableScrollPane);
        argumentsTableScrollPane.setViewportView(argumentsTable);

        // Setup Button area layout
        buttonGroup.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonGroup.add(cancelButton);
        buttonGroup.add(constructArrayButton);
        buttonGroup.add(constructButton);
    }

}
