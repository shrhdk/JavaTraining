package interpret;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ArrayViewer extends SubFrame{

    // Data

    private final Object[] array;

    // API

    public ArrayViewer(Object[] array, DialogListener dialogListener) {
        super(dialogListener);
        this.array = array;
        arraysTable.setArray(array);
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
    private final JScrollPane arraysTableScrollPane = new JScrollPane();
    private final JArraysTable arraysTable = new JArraysTable();

    private final JPanel buttonPanel = new JPanel();
    private final JButton cancelButton = new JButton("Cancel");
    private final JButton returnNullButton = new JButton("Return null");
    private final JButton returnArrayButton = new JButton("Return array");

    // Setup component event listener

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

    private final ActionListener returnArrayButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            return_(array);
        }
    };

    private void setupListener() {
        cancelButton.addActionListener(cancelButtonListener);
        returnNullButton.addActionListener(returnNullButtonListener);
        returnArrayButton.addActionListener(returnArrayButtonListener);
    }

    // Setup layout

    private void setupLayout() {

        // Setup Frame
        setTitle(array.getClass().getCanonicalName());
        setSize(800, 600);

        // Setup whole layout
        setLayout(new BorderLayout());
        getContentPane().add(BorderLayout.CENTER, arraysTableScrollPane);
        getContentPane().add(BorderLayout.SOUTH, buttonPanel);

        // Setup Center area layout
        arraysTableScrollPane.setViewportView(arraysTable);

        // Setup Button Panel
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(cancelButton);
        buttonPanel.add(returnNullButton);
        buttonPanel.add(returnArrayButton);

        // Locate JFrame to center of screen
        setLocationRelativeTo(null);    // Should be called after setup layout
    }
}
